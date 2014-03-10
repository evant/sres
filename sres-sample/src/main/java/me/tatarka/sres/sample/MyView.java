package me.tatarka.sres.sample;

import android.content.Context;
import android.util.AttributeSet;
import layout.Other;

/**
 * Created by evan on 3/6/14.
 */
public class MyView extends Other {
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }
}
