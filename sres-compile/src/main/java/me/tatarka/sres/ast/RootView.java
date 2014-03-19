package me.tatarka.sres.ast;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.misc.Nullable;

import java.util.*;

/**
 * Created by evan on 2/27/14.
 */
public class RootView {
    public final String subclass;
    public final View view;
    public final String bindClass;

    public RootView(@NotNull String name, @Nullable String subclass,
                    @NotNull java.util.Set<Attribute> attributes, @NotNull List<Child> children) {
        view = new View(name, attributes,  children);
        this.subclass = subclass;
        this.bindClass = findBindClass();

        if (this.bindClass == null) {
            verifyNoBindings(view);
        }
    }

    private String findBindClass() {
        for (Attribute attribute : view.attributes) {
            if (attribute.name.equals(Attribute.BIND_CLASS)) return attribute.value;
        }
        return null;
    }

    private void verifyNoBindings(View view) {
        if (!view.bindings.isEmpty()) {
            throw new IllegalArgumentException("bind:class must be defined if there are any data bindings");
        }
        for (Child child : view.children) {
            if (child instanceof View) verifyNoBindings((View) child);
        }
    }

    public static Builder of(String name) {
        return new Builder(name);
    }

    public static class Builder {
        private String name;
        private java.util.Set<Attribute> attributes = new LinkedHashSet<>();
        private List<Child> children = new ArrayList<>();
        private String subclass;

        public Builder(String name) {
            this.name = name;
        }

        public Builder subclasses(String subclass) {
            this.subclass = subclass;
            return this;
        }

        public Builder bindClass(String bindClass) {
            return attribute(new Attribute(Attribute.BIND_CLASS, bindClass));
        }

        public Builder id(String id) {
            return attribute(Attribute.ID, id);
        }

        public Builder size(String width, String height) {
            attribute(Attribute.LAYOUT_WIDTH, width);
            attribute(Attribute.LAYOUT_HEIGHT, height);
            return this;
        }

        public Builder attribute(Attribute attribute) {
            attributes.add(attribute);
            return this;
        }

        public Builder attribute(String name, String value) {
            return attribute(new Attribute(name, value));
        }

        public Builder bind(String name, String value) {
            return attribute(new Attribute(name, value, Attribute.Namespace.BIND));
        }

        public Builder child(Child child) {
            children.add(child);
            return this;
        }

        public Builder child(Child.Builder child) {
            children.add(child.build());
            return this;
        }

        public RootView build() {
            return new RootView(name, subclass, attributes, children);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(subclass, bindClass, view);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) return false;
        RootView other = (RootView) obj;
        return  Objects.equals(subclass, other.subclass)
                && Objects.equals(bindClass, other.bindClass)
                && Objects.equals(view, other.view);
    }

    @Override
    public String toString() {
        return (subclass == null ? "" : subclass + "<") + view.toString();
    }
}
