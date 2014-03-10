package me.tatarka.sres.test;

import me.tatarka.sres.ast.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static me.tatarka.sres.test.TestHelper.parse;
import static org.fest.assertions.Assertions.assertThat;

@RunWith(JUnit4.class)
public class TestParser {
    @Test
    public void testSingleView() {
        RootView expected = RootView.of("TextView").build();
        RootView actual = parse("TextView");

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testSingleViewArgs() {
        RootView expected = RootView.of("TextView")
                .attribute(Attribute.LAYOUT_WIDTH, "match")
                .attribute(Attribute.LAYOUT_HEIGHT, "wrap")
                .build();
        RootView actual = parse("TextView(match, wrap)");

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testNumberedArgs() {
        RootView expected = RootView.of("TextView")
                .attribute(Attribute.LAYOUT_WIDTH, "100dp")
                .attribute(Attribute.LAYOUT_HEIGHT, "20px")
                .build();
        RootView actual = parse("TextView(100dp, 20px)");

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testSingleSubclassView() {
        RootView expected = RootView.of("TextView").subclasses("MyView").build();
        RootView actual = parse("MyView<TextView");

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testSingleId() {
        RootView expected = RootView.of("TextView").id("@+id/my_textview").build();
        RootView actual = parse("TextView { @+id/my_textview }");

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void tesSingletAttribute() {
        RootView expected = RootView.of("TextView").attribute("text", "Some Text").build();
        RootView actual = parse("TextView { text = \"Some Text\" }");

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testSingleChild() {
        RootView expected = RootView.of("FrameLayout").child(View.of("TextView")).build();
        RootView actual = parse("FrameLayout { TextView }");

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testArgsIdAndAttribute() {
        RootView expected = RootView.of("TextView")
                .id("@+id/my_textview")
                .attribute(Attribute.LAYOUT_WIDTH, "match")
                .attribute(Attribute.LAYOUT_HEIGHT, "wrap")
                .attribute("text", "Some Text")
                .build();
        RootView actual = parse("TextView(match, wrap) { @+id/my_textview\ntext = \"Some Text\" }");

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testMultipleNested() {
        RootView expected = RootView.of("LinearLayout")
                .child(View.of("TextView"))
                .child(View.of("ImageView"))
                .build();
        RootView actual = parse("LinearLayout { TextView\nImageView }");

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testNestedAttributes() {
        RootView expected = RootView.of("LinearLayout")
                .child(View.of("TextView").attribute("text", "Some Text"))
                .build();
        RootView actual = parse("LinearLayout { TextView { text = \"Some Text\" } }");

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testSimpleInclude() {
        RootView expected = RootView.of("LinearLayout")
                .child(Include.of("@layout/test"))
                .build();

        RootView actual = parse("LinearLayout { include(@layout/test) }");

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testBindField() {
        RootView expected = RootView.of("TextView")
                .attribute("text", Binding.field("text"))
                .build();

        RootView actual = parse("TextView { text = bind(text) }");

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testBindMethod() {
        RootView expected = RootView.of("TextView")
                .attribute("text", Binding.method("text"))
                .build();

        RootView actual = parse("TextView { text = bind(text()) }");

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testBindClass() {
        RootView expected = RootView.of("TextView")
                .bindClass("Model")
                .build();

        RootView actual = parse("TextView { bind:class = Model }");

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testBindObservable() {
        RootView expected = RootView.of("TextView")
                .bindClass("String")
                .attribute("text", Binding.field(Binding.Type.OBSERVABLE, "text"))
                .build();

        RootView actual = parse("TextView { bind:class = String\ntext = bind(observe, text) }");

        assertThat(actual).isEqualTo(expected);
    }
}
