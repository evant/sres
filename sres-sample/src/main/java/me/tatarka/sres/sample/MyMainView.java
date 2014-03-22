package me.tatarka.sres.sample;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.util.AttributeSet;

import java.util.List;

/**
 * Created by evan on 3/19/14.
 */
public class MyMainView extends layout.MainActivity {
    public MyMainView(Context context) {
        super(context);
    }

    public MyMainView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyMainView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    protected void bindText_text(final String value) {
        bindText.animate().alpha(0)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        bindText.setText(value);
                        bindText.animate().alpha(1);
                    }
                });
    }

    protected void bindViewPager_bind(List<MyListItem> value) {
        bindViewPager.bind(value);
    }
}
