package me.tatarka.sres;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by evan on 3/9/14.
 */
public class ListView<T> extends android.widget.ListView implements Bindable<List<T>> {
    private LayoutSelector<T> layoutSelector;
    private SparseIntArray viewTypeIndexes = new SparseIntArray();

    public ListView(Context context) {
        this(context, null);
    }

    public ListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        if (attrs != null) {
            String layoutName = attrs.getAttributeValue(Bindable.NAMESPACE, "layout");
            if (layoutName != null) {
                int layout = getResources().getIdentifier(layoutName, "layout", getContext().getPackageName());
                setLayout(layout);
            }
        }
    }

    public void setLayoutSelector(LayoutSelector<T> layoutSelector) {
        this.layoutSelector = layoutSelector;
        if (getAdapter() != null) {
            ObservableList<T> model = getAdapter().getList();
            bind(model);
        }
    }

    public void setLayout(final int layout) {
        setLayoutSelector(new LayoutSelector<T>() {
            @Override
            public int getLayout(int position, T model) {
                return layout;
            }

            @Override
            public int getCount() {
                return 1;
            }
        });
    }

    @Override
    public void bind(List<T> model) {
        setAdapter(new ObservableAdapter<T>(model) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = convertView;
                T item = getItem(position);
                if (convertView == null) {
                    view = LayoutInflater.from(getContext()).inflate(layoutSelector.getLayout(position, item), parent, false);
                }
                ((Bindable<T>) view).bind(item);
                return view;
            }

            @Override
            public int getItemViewType(int position) {
                int layout = layoutSelector.getLayout(position, getItem(position));
                int itemType = viewTypeIndexes.get(layout, -1);
                if (itemType < 0) {
                    itemType = viewTypeIndexes.size();
                    viewTypeIndexes.append(layout, itemType);
                }
                return itemType;
            }

            @Override
            public int getViewTypeCount() {
                return layoutSelector.getCount();
            }
        });
    }

    @Override
    public ObservableAdapter<T> getAdapter() {
        return (ObservableAdapter<T>) super.getAdapter();
    }

    public interface LayoutSelector<T> {
        int getLayout(int position, T model);
        int getCount();
    }
}
