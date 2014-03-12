package me.tatarka.sres.sample;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import layout.ListItem;

/**
 * Created by evan on 3/11/14.
 */
public class MyListItemView extends ListItem {
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
    public void bind(final MyListItem model) {
        super.bind(model);

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                model.remove();
            }
        });
    }
}
