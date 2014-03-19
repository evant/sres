package me.tatarka.sres.test;

import com.google.common.base.Joiner;
import me.tatarka.sres.ast.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static me.tatarka.sres.test.TestHelper.toXml;
import static me.tatarka.sres.test.XmlAssert.assertThat;

/**
 * Created by evan on 2/27/14.
 */
@RunWith(JUnit4.class)
public class TestLayoutGeneratorXml {
    @Test
    public void testSingleView() {
        Xml expected = new Xml("<test.Test xmlns:android=\"http://schemas.android.com/apk/res/android\"/>");
        Xml actual = toXml("test", RootView.of("TextView").build());

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testSingleViewArgs() {
        Xml expected = new Xml(Joiner.on("\n").join(
                "<test.Test xmlns:android=\"http://schemas.android.com/apk/res/android\"",
                "   android:layout_width=\"match_parent\"",
                "   android:layout_height=\"wrap_content\"",
                "/>"
        ));

        Xml actual = toXml("test", RootView.of("TextView")
                .attribute(Attribute.LAYOUT_WIDTH, "match")
                .attribute(Attribute.LAYOUT_HEIGHT, "wrap")
                .build());

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testSingleSubclassView() {
        Xml expected = new Xml("<test.MyView xmlns:android=\"http://schemas.android.com/apk/res/android\"/>");
        Xml actual = toXml("test", RootView.of("TextView").subclasses("test.MyView").build());

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testSingleId() {
        Xml expected = new Xml(Joiner.on("\n").join(
                "<test.Test xmlns:android=\"http://schemas.android.com/apk/res/android\"",
                "   android:id=\"@+id/my_textview\"",
                "/>"
        ));
        Xml actual = toXml("test",RootView.of("TextView").id("@+id/my_textview").build());

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void tesSingletAttribute() {
        Xml expected = new Xml(Joiner.on("\n").join(
                "<test.Test xmlns:android=\"http://schemas.android.com/apk/res/android\"",
                "   android:text=\"Some Text\"",
                "/>"
        ));
        Xml actual = toXml("test",RootView.of("TextView").attribute("text", "Some Text").build());

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testSingleChild() {
        Xml expected = new Xml(Joiner.on("\n").join(
                "<test.Test xmlns:android=\"http://schemas.android.com/apk/res/android\">",
                "<TextView/>",
                "</test.Test>"
        ));
        Xml actual = toXml("test",RootView.of("FrameLayout").child(View.of("TextView")).build());

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testArgsIdAndAttribute() {
        Xml expected = new Xml(Joiner.on("\n").join(
                "<test.Test xmlns:android=\"http://schemas.android.com/apk/res/android\"",
                "   android:id=\"@+id/my_textview\"",
                "   android:layout_width=\"match_parent\"",
                "   android:layout_height=\"wrap_content\"",
                "   android:text=\"Some Text\"",
                "/>"
        ));
        Xml actual = toXml("test",RootView.of("TextView")
                .id("@+id/my_textview")
                .attribute(Attribute.LAYOUT_WIDTH, "match")
                .attribute(Attribute.LAYOUT_HEIGHT, "wrap")
                .attribute("text", "Some Text")
                .build());

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testMultipleNested() {
        Xml expected = new Xml(Joiner.on("\n").join(
                "<test.Test xmlns:android=\"http://schemas.android.com/apk/res/android\">",
                "<TextView/>",
                "<ImageView/>",
                "</test.Test>"
        ));
        Xml actual = toXml("test",RootView.of("LinearLayout")
                .child(View.of("TextView"))
                .child(View.of("ImageView"))
                .build());

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testNestedAttributes() {
        Xml expected = new Xml(Joiner.on("\n").join(
                "<test.Test xmlns:android=\"http://schemas.android.com/apk/res/android\">",
                "<TextView android:text=\"Some Text\"/>",
                "</test.Test>"
        ));
        Xml actual = toXml("test",RootView.of("LinearLayout")
                .child(View.of("TextView").attribute("text", "Some Text"))
                .build());

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testSimpleInclude() {
        Xml expected = new Xml(Joiner.on("\n").join(
                "<test.Test xmlns:android=\"http://schemas.android.com/apk/res/android\">",
                "<include layout=\"@layout/test\"/>",
                "</test.Test>"
        ));
        Xml actual = toXml("test",RootView.of("LinearLayout")
                .child(Include.of("@layout/test"))
                .build());

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testBindField() {
        Xml expected = new Xml(Joiner.on("\n").join(
                "<test.Test xmlns:android=\"http://schemas.android.com/apk/res/android\"",
                "xmlns:bind=\"http://schemas.android.com/apk/lib/me.tatarka.sres\"",
                "bind:class=\"MyClass\"",
                "bind:text=\"text\"/>"
        ));
        Xml actual = toXml("test", RootView.of("TextView")
                .bindClass("MyClass")
                .bind("text", "text")
                .build());

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testBindMethod() {
        Xml expected = new Xml(Joiner.on("\n").join(
                "<test.Test xmlns:android=\"http://schemas.android.com/apk/res/android\"",
                "xmlns:bind=\"http://schemas.android.com/apk/lib/me.tatarka.sres\"",
                "bind:class=\"MyClass\"",
                "bind:text=\"text()\"/>"
        ));
        Xml actual = toXml("test", RootView.of("TextView")
                .bindClass("MyClass")
                .bind("text", "text()")
                .build());

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testBindClass() {
        Xml expected = new Xml(Joiner.on("\n").join(
                "<test.Test xmlns:android=\"http://schemas.android.com/apk/res/android\"",
                "xmlns:bind=\"http://schemas.android.com/apk/lib/me.tatarka.sres\"",
                "bind:class=\"Model\"/>"
        ));
        Xml actual = toXml("test", RootView.of("TextView")
                .bindClass("Model")
                .build());

        assertThat(actual).isEqualTo(expected);
    }
}
