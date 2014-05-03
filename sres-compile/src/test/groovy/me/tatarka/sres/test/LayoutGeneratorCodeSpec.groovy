package me.tatarka.sres.test
import me.tatarka.sres.ast.RootView
import me.tatarka.sres.ast.View
import me.tatarka.sres.test.helpers.Code
import spock.lang.Specification

import static me.tatarka.sres.test.helpers.SpecHelper.toCode

/**
 * Created by evan on 5/2/14.
 */
class LayoutGeneratorCodeSpec extends Specification {
    def "generates a view"() {
        expect:
        toCode('test_view_base', RootView.of('TextView').build()) ==
        new Code('TestViewBase',
        """
        package test;

        import android.content.Context;
        import android.util.AttributeSet;
        import android.widget.TextView;

        public class TestViewBase
            extends TextView
        {


            public TestViewBase(Context context) {
                this(context, null);
            }

            public TestViewBase(Context context, AttributeSet attrs) {
                this(context, attrs, 0);
            }

            public TestViewBase(Context context, AttributeSet attrs, int defStyle) {
                super(context, attrs, defStyle);
            }

        }
        """.stripIndent())
    }

    def "generates a view with a child id"() {
        expect:
        toCode('test_view_base',
                RootView.of('LinearLayout').child(View.of('TextView').id('@+id/my_text')).build()) ==
        new Code('TestViewBase',
        """
        package test;

        import android.content.Context;
        import android.util.AttributeSet;
        import android.widget.LinearLayout;
        import android.widget.TextView;
        import me.tatarka.sres.test.R;

        public class TestViewBase
            extends LinearLayout
        {

            public TextView myText;

            public TestViewBase(Context context) {
                this(context, null);
            }

            public TestViewBase(Context context, AttributeSet attrs) {
                this(context, attrs, 0);
            }

            public TestViewBase(Context context, AttributeSet attrs, int defStyle) {
                super(context, attrs, defStyle);
            }

            public void onFinishInflate() {
                super.onFinishInflate();
                myText = ((TextView) findViewById(R.id.my_text));
            }

        }
        """.stripIndent())
    }

    def "generates a view with a binding"() {
        expect:
        toCode('test_view_base', RootView.of('TextView')
                .bindClass('me.tatarka.sres.test.Model')
                .bind('text', 'text')
                .bind('background_color', 'getColor()')
                .build()) ==
        new Code('TestViewBase',
        """
        package test;

        import android.content.Context;
        import android.util.AttributeSet;
        import android.widget.TextView;
        import me.tatarka.sres.Bindable;
        import me.tatarka.sres.ChangeTracker;
        import me.tatarka.sres.Trackable.Listener;
        import me.tatarka.sres.test.Model;

        public class TestViewBase
            extends TextView
            implements Bindable<Model>
        {

            protected final ChangeTracker tracker = new ChangeTracker();

            public TestViewBase(Context context) {
                this(context, null);
            }

            public TestViewBase(Context context, AttributeSet attrs) {
                this(context, attrs, 0);
            }

            public TestViewBase(Context context, AttributeSet attrs, int defStyle) {
                super(context, attrs, defStyle);
            }

            public void bind(final Model model) {
                setText(model.text);
                tracker.addListener(new Listener() {


                    @Override
                    public void onChange() {
                        setBackgroundColor(model.getColor());
                    }

                }
                );
            }

            @Override
            protected void onDetachedFromWindow() {
                super.onDetachedFromWindow();
                tracker.clear();
            }

        }
        """.stripIndent())
    }
}
