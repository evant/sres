package me.tatarka.sres.sample;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import layout.ListItem;
import me.tatarka.sres.Observable;

/**
 * Created by evan on 3/11/14.
 */
public class MyListItemView extends ListItem {
    private MyListItem model;
    private Observable.Listener textListener;

    public MyListItemView(Context context) {
        super(context);
    }

    public MyListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyListItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onFinishInflate() {
        super.onFinishInflate();

        textListener = new Observable.Listener() {
            @Override
            public void onChange(Object value) {
                text.setText((String) value);
            }
        };
    }

    @Override
    public void bind(final MyListItem model) {
        this.model = model;
        super.bind(model);

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                model.remove();
            }
        });

//        model.text.addListener(textListener);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

//        model.text.removeListener(textListener);
    }
}
