package me.tatarka.sres;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by evan on 3/11/14.
 */
public class ViewPager<T> extends android.support.v4.view.ViewPager implements Bindable<List<T>> {
    private LayoutSelectorHelper<T> helper = new LayoutSelectorHelper<>();

    public ViewPager(Context context) {
        this(context, null);
    }

    public ViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);

        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SRes);
            int layout = a.getResourceId(R.styleable.SRes_layout, 0);
            if (layout > 0) setLayout(layout);
            a.recycle();
        }
    }

    public void setLayoutSelector(LayoutSelector<T> layoutSelector) {
        helper.setLayoutSelector(layoutSelector);
        if (getAdapter() != null) {
            ObservableList<T> model = getAdapter().getList();
            bind(model);
        }
    }

    public void setLayout(final int layoutId) {
        setLayoutSelector(LayoutSelectorHelper.<T>fromLayout(layoutId));
    }

    @Override
    public ObservablePagerAdapter<T> getAdapter() {
        return (ObservablePagerAdapter<T>) super.getAdapter();
    }

    @Override
    public void bind(List<T> model) {
        setAdapter(new ObservablePagerAdapter<T>(model) {
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
}
