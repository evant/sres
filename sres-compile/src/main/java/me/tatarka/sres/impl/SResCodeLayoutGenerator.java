package me.tatarka.sres.impl;

import com.google.common.base.CaseFormat;
import com.sun.codemodel.*;
import me.tatarka.sres.Bindable;
import me.tatarka.sres.LayoutGenerator;
import me.tatarka.sres.SResOutput;
import me.tatarka.sres.ast.Binding;
import me.tatarka.sres.ast.Child;
import me.tatarka.sres.ast.RootView;
import me.tatarka.sres.ast.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.sun.codemodel.JExpr.*;
import static com.sun.codemodel.JMod.PUBLIC;

/**
 * Created by evan on 3/6/14.
 */
public class SResCodeLayoutGenerator implements LayoutGenerator {
    @Override
    public void generate(RootView rootView, SResOutput output) {
        JCodeModel m = new JCodeModel();
        JPackage pkg = m._package(output.sourceInfo.getPackageName());

        try {
            String className = toClassName(output.sourceInfo.getName());
            JDefinedClass clazz = pkg._class(PUBLIC, className)._extends(m.ref(rootView.view.qualifiedName()));
            if (rootView.bindClass != null) {
                clazz._implements(m.ref(Bindable.class).narrow(m.ref(rootView.bindClass)));
            }

            JClass contextClass = m.ref("android.content.Context");
            JClass attrsClass = m.ref("android.util.AttributeSet");
            JPrimitiveType intClass = m.INT;
            JPrimitiveType voidClass = m.VOID;

            List<View> viewsWithIds = findViewsWithIds(rootView);

            List<JFieldVar> viewFields = emitFields(m, clazz, viewsWithIds);

            emitConstructor1(clazz, contextClass);
            emitConstructor2(clazz, contextClass, attrsClass);
            emitConstructor3(clazz, contextClass, attrsClass, intClass);

            emitOnFinishInflate(m, output.sourceInfo.getAppPackageName(), clazz, voidClass, viewsWithIds, viewFields);
            emitBindings(m, clazz, voidClass, rootView, viewsWithIds, viewFields);

            m.build(new WriterCodeWriter(output.writer));
        } catch (JClassAlreadyExistsException | IOException e) {
            e.printStackTrace();
        }
    }

    private List<View> findViewsWithIds(RootView rootView) {
        List<View> views = new ArrayList<>();
        for (Child child : rootView.view.children) {
            if (child instanceof View) findViewsWithIds((View) child, views);
        }
        return views;
    }

    private void findViewsWithIds(View view, List<View> views) {
        if (view.id != null) views.add(view);
        for (Child child : view.children) {
            if (child instanceof View) findViewsWithIds((View) child, views);
        }
    }

    private List<JFieldVar> emitFields(JCodeModel m, JDefinedClass clazz, List<View> views) {
        List<JFieldVar> fields = new ArrayList<>(views.size());
        for (View view : views) {
            fields.add(clazz.field(PUBLIC, m.ref(view.qualifiedName()), view.variableName()));
        }
        return fields;
    }

    private void emitConstructor1(JDefinedClass clazz, JClass contextClass) {
        JMethod c = clazz.constructor(PUBLIC);
        JVar context = c.param(contextClass, "context");
        JBlock body = c.body();
        body.invoke("this").arg(context).arg(_null());
    }

    private void emitConstructor2(JDefinedClass clazz, JClass contextClass, JClass attrsClass) {
        JMethod c = clazz.constructor(PUBLIC);
        JVar context = c.param(contextClass, "context");
        JVar attrs = c.param(attrsClass, "attrs");
        JBlock body = c.body();
        body.invoke("this").arg(context).arg(attrs).arg(JExpr.lit(0));
    }

    private void emitConstructor3(JDefinedClass clazz, JClass contextClass, JClass attrsClass, JPrimitiveType intClass) {
        JMethod c = clazz.constructor(PUBLIC);
        JVar context = c.param(contextClass, "context");
        JVar attrs = c.param(attrsClass, "attrs");
        JVar defStyle = c.param(intClass, "defStyle");
        JBlock body = c.body();
        body.invoke("super").arg(context).arg(attrs).arg(defStyle);
    }

    private void emitOnFinishInflate(JCodeModel m, String androidPackageName, JDefinedClass clazz, JPrimitiveType voidClass, List<View> views, List<JFieldVar> fields) {
        if (views.isEmpty()) return;

        JBlock body = clazz.method(PUBLIC, voidClass, "onFinishInflate").body();
        body.invoke(_super(), "onFinishInflate");

        for (int i = 0; i < views.size(); i++) {
            JFieldVar field = fields.get(i); View view = views.get(i);
            JFieldRef ref = m.ref(androidPackageName + ".R").staticRef("id").ref(view.idReference());

            body.assign(field, cast(m.ref(view.qualifiedName()), invoke("findViewById").arg(ref)));
        }
    }

    private void emitBindings(JCodeModel m, JDefinedClass clazz, JPrimitiveType voidClass, RootView rootView, List<View> views, List<JFieldVar> fields) {
        if (rootView.bindClass == null) return;

        JClass bindingClass = m.ref(rootView.bindClass);
        JMethod b = clazz.method(PUBLIC, voidClass, "bind");
        JVar model = b.param(bindingClass, "model");
        JBlock body = b.body();

        for (Binding binding : rootView.view.bindings) {
            emitBinding(body, null, model, binding);
        }

        for (int i = 0; i < views.size(); i++) {
            JFieldVar field = fields.get(i); View view = views.get(i);

            for (Binding binding : view.bindings) {
                emitBinding(body, field, model, binding);
            }
        }
    }

    private void emitBinding(JBlock body, JExpression target, JVar model, Binding binding) {
        JExpression arg = binding.type == Binding.Type.FIELD
                ? model.ref(binding.value)
                : model.invoke(binding.value);

        String setter = toSetter(binding.name);

        (target == null ? body.invoke(setter) : body.invoke(target, setter)).arg(arg);
    }

    private String toClassName(String name) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, name);
    }

    private String toSetter(String name) {
        // Special case bind, since it doesn't start with 'set'
        if (name.equals("bind")) return name;
        return "set" + CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, name);
    }
}
