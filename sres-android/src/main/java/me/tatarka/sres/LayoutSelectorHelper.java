package me.tatarka.sres;

import android.util.SparseIntArray;

/**
 * Created by evan on 3/11/14.
 */
public class LayoutSelectorHelper<T> {
    private LayoutSelector<T> layoutSelector;
    private SparseIntArray viewTypeIndexes = new SparseIntArray();

    public void setLayoutSelector(LayoutSelector<T> layoutSelector) {
        this.layoutSelector = layoutSelector;
    }

    public static <T> LayoutSelector<T> fromLayout(final int layoutId) {
        return new LayoutSelector<T>() {
            @Override
            public int getLayout(int position, T model) {
                return layoutId;
            }

            @Override
            public int getCount() {
                return 1;
            }
        };
    }

    public int getItemTypeCount() {
        return layoutSelector.getCount();
    }

    public int getItemViewType(int position, T item) {
        int layout = layoutSelector.getLayout(position, item);
        int itemType = viewTypeIndexes.get(layout, -1);
        if (itemType < 0) {
            itemType = viewTypeIndexes.size();
            viewTypeIndexes.append(layout, itemType);
        }
        return itemType;
    }

    public int getLayout(int position, T item) {
        return layoutSelector.getLayout(position, item);
    }
}
