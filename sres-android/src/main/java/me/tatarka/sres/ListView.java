package me.tatarka.sres;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by evan on 3/9/14.
 */
public class ListView extends android.widget.ListView {
    private LayoutSelectorHelper helper = new LayoutSelectorHelper();

    public ListView(Context context) {
        this(context, null);
    }

    public ListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SRes);
            int layout = a.getResourceId(R.styleable.SRes_layout, 0);
            if (layout > 0) setLayout(layout);
            a.recycle();
        }
    }

    public <T> void setLayoutSelector(LayoutSelector<T> layoutSelector) {
        helper.setLayoutSelector(layoutSelector);
        if (getAdapter() != null) {
            ObservableList<T> items = (ObservableList<T>) getAdapter().getList();
            setItems(items);
        }
    }

    public void setLayout(final int layout) {
        setLayoutSelector(LayoutSelectorHelper.fromLayout(layout));
    }

    public <T> void setItems(List<T> items) {
        setAdapter(new ObservableAdapter<T>(items) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = convertView;
                T item = getItem(position);
                if (convertView == null) {
                    view = LayoutInflater.from(getContext()).inflate(helper.getLayout(position, item), parent, false);
                }
                ((Bindable<T>) view).bind(item);
                return view;
            }

            @Override
            public int getItemViewType(int position) {
                return helper.getItemViewType(position, getItem(position));
            }

            @Override
            public int getViewTypeCount() {
                return helper.getItemTypeCount();
            }
        });
    }

    @Override
    public ObservableAdapter<?> getAdapter() {
        return (ObservableAdapter<?>) super.getAdapter();
    }
}
