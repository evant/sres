package me.tatarka.sres;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by evan on 3/11/14.
 */
public abstract class ObservablePagerAdapter<T> extends PagerAdapter {
    private ObservableListViewHelper<T> helper;
    private List<View>[] recycleViews;

    public ObservablePagerAdapter(List<T> list) {
        helper = new ObservableListViewHelper<>(list);
        helper.setListener(new ObservableList.Listener<T>() {
            @Override
            public void onChange(ObservableList.ChangeType changeType, Collection<ObservableList.Change<T>> changes) {
                notifyDataSetChanged();
            }
        });
        recycleViews = new List[getViewTypeCount()];
        for (int i = 0; i < recycleViews.length; i++) {
            recycleViews[i] = new ArrayList<>();
        }
    }

    public void replace(List<T> list) {
        helper.replace(list);
    }

    public ObservableList<T> getList() {
        return helper.getList();
    }

    public T getItem(int position) {
        return helper.getList().get(position);
    }

    public abstract View getView(int position, View convertView, ViewGroup parent);

    public abstract int getViewTypeCount();
    public abstract int getItemViewType(int position);

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (position >= getCount()) return null;

        List<View> recycleViewList = recycleViews[getItemViewType(position)];
        View view;
        if (recycleViewList.isEmpty()) {
            view = getView(position, null, container);
        } else {
            view = getView(position, recycleViewList.remove(recycleViewList.size() - 1), container);
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
        if (position >= getCount()) return;

        List<View> recycleViewList = recycleViews[getItemViewType(position)];
        recycleViewList.add(view);
    }

    @Override
    public int getCount() {
        return helper.getList().size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }
}
