package me.tatarka.sres.test;

import com.google.common.base.Joiner;
import me.tatarka.sres.ast.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static me.tatarka.sres.test.CodeAssert.assertThat;
import static me.tatarka.sres.test.TestHelper.toCode;

/**
 * Created by evan on 3/6/14.
 */
@RunWith(JUnit4.class)
public class TestLayoutGeneratorCode {
    @Test
    public void testSingleView() {
        Code expected = new Code("TestViewBase", Joiner.on("\n").join(
                "",
                "package test;",
                "",
                "import android.content.Context;",
                "import android.util.AttributeSet;",
                "import android.widget.TextView;",
                "",
                "public class TestViewBase",
                "    extends TextView", "{",
                "", "",
                "    public TestViewBase(Context context) {",
                "        this(context, null);",
                "    }",
                "",
                "    public TestViewBase(Context context, AttributeSet attrs) {",
                "        this(context, attrs, 0);",
                "    }",
                "",
                "    public TestViewBase(Context context, AttributeSet attrs, int defStyle) {",
                "        super(context, attrs, defStyle);",
                "    }",
                "",
                "}", ""
        ));
        Code actual = toCode("test_view_base", RootView.of("TextView").build());

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testSingleId() {
        Code expected = new Code("TestViewBase", Joiner.on("\n").join(
                "",
                "package test;",
                "",
                "import android.content.Context;",
                "import android.util.AttributeSet;",
                "import android.widget.LinearLayout;",
                "import android.widget.TextView;",
                "import me.tatarka.sres.test.R;",
                "",
                "public class TestViewBase",
                "    extends LinearLayout", "{",
                "",
                "    public TextView myText;",
                "",
                "    public TestViewBase(Context context) {",
                "        this(context, null);",
                "    }",
                "",
                "    public TestViewBase(Context context, AttributeSet attrs) {",
                "        this(context, attrs, 0);",
                "    }",
                "",
                "    public TestViewBase(Context context, AttributeSet attrs, int defStyle) {",
                "        super(context, attrs, defStyle);",
                "    }",
                "",
                "    public void onFinishInflate() {",
                "        super.onFinishInflate();",
                "        myText = ((TextView) findViewById(R.id.my_text));",
                "    }",
                "",
                "}", ""
        ));
        Code actual = toCode("test_view_base", RootView.of("LinearLayout")
                .child(View.of("TextView").id("@+id/my_text"))
                .build());

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testBind() {
        Code expected = new Code("TestViewBase", Joiner.on("\n").join(
                "",
                "package test;",
                "",
                "import android.content.Context;",
                "import android.util.AttributeSet;",
                "import android.widget.TextView;",
                "import me.tatarka.sres.Bindable;",
                "import me.tatarka.sres.test.Model;",
                "",
                "public class TestViewBase",
                "    extends TextView",
                "    implements Bindable<Model>", "{",
                "", "",
                "    public TestViewBase(Context context) {",
                "        this(context, null);",
                "    }",
                "",
                "    public TestViewBase(Context context, AttributeSet attrs) {",
                "        this(context, attrs, 0);",
                "    }",
                "",
                "    public TestViewBase(Context context, AttributeSet attrs, int defStyle) {",
                "        super(context, attrs, defStyle);",
                "    }",
                "",
                "    public void bind(Model model) {",
                "        setText(model.text);",
                "        setBackgroundColor(model.getColor());",
                "    }",
                "",
                "}", ""
        ));
        Code actual = toCode("test_view_base", RootView.of("TextView")
                .bindClass("me.tatarka.sres.test.Model")
                .bind("text", "text")
                .bind("background_color", "getColor()")
                .build());

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testBindObservable() {
        Code expected = new Code("TestViewBase", Joiner.on("\n").join(
                "",
                "package test;",
                "",
                "import android.content.Context;",
                "import android.util.AttributeSet;",
                "import android.widget.TextView;",
                "import me.tatarka.sres.Bindable;",
                "import me.tatarka.sres.test.Model;",
                "",
                "public class TestViewBase",
                "    extends TextView",
                "    implements Bindable<Model>", "{",
                "", "",
                "    public TestViewBase(Context context) {",
                "        this(context, null);",
                "    }",
                "",
                "    public TestViewBase(Context context, AttributeSet attrs) {",
                "        this(context, attrs, 0);",
                "    }",
                "",
                "    public TestViewBase(Context context, AttributeSet attrs, int defStyle) {",
                "        super(context, attrs, defStyle);",
                "    }",
                "",
                "    public void bind(Model model) {",
                "        setText(model.text);",
                "        setBackgroundColor(model.getColor());",
                "    }",
                "",
                "}", ""
        ));
        Code actual = toCode("test_view_base", RootView.of("TextView")
                .bindClass("me.tatarka.sres.test.Model")
                .bind("text", "text")
                .bind("background_color", "getColor()")
                .build());

        assertThat(actual).isEqualTo(expected);
    }
}
