package me.tatarka.sres.impl;

import com.google.common.base.CaseFormat;
import com.jamesmurty.utils.XMLBuilder;
import me.tatarka.sres.Bindable;
import me.tatarka.sres.LayoutGenerator;
import me.tatarka.sres.SResOutput;
import me.tatarka.sres.ast.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerException;
import java.util.Properties;

/**
 * Created by evan on 3/1/14.
 */
public class SResXmlLayoutGenerator implements LayoutGenerator {
    public static final String NS_ANDROID = "http://schemas.android.com/apk/res/android";
    public static final String NS_APP = "http://schemas.android.com/apk/res-auto";

    @Override
    public void generate(RootView rootView, SResOutput output) {
        try {
            String rootClassName =  rootView.subclass != null
                            ? rootView.subclass
                            : output.sourceInfo.getPackageName() + "."
                            + CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, output.sourceInfo.getName());

            XMLBuilder b = XMLBuilder.create(rootClassName);

            b.namespace("android", NS_ANDROID);
            b.namespace("app", NS_APP);

            if (rootView.bindClass != null) {
                b.namespace("bind", Bindable.NAMESPACE);
            }

            for (Attribute attribute : rootView.view.attributes) {
                buildAttribute(b, attribute);
            }

            for (Child child : rootView.view.children) {
                buildXml(b, child);
            }

            Properties props = new Properties();
            props.put(OutputKeys.OMIT_XML_DECLARATION, "yes");
            props.put(OutputKeys.INDENT, "yes");
            b.toWriter(output.writer, props);

        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }

    private static void buildXml(XMLBuilder b, Child child) {
        if (child instanceof View) {
            buildViewXml(b, (View) child);
        } else if (child instanceof Include) {
            buildIncludeXml(b, (Include) child);
        }
    }

    private static void buildViewXml(XMLBuilder b, View view) {
        XMLBuilder e = b.e(view.name);

        for (Attribute attribute : view.attributes) {
            buildAttribute(e, attribute);
        }

        for (Child child : view.children) {
            buildXml(e, child);
        }
    }

    private static void buildIncludeXml(XMLBuilder b, Include include) {
        XMLBuilder e = b.e(Include.INCLUDE);

        for (Attribute attribute : include.attributes) {
            buildAttribute(e, attribute);
        }
    }

    private static void buildAttribute(XMLBuilder e, Attribute attribute) {
        e.a(attribute.name, attribute.value);
    }
}
