package me.tatarka.sres;

import android.widget.BaseAdapter;

import java.util.Collection;
import java.util.List;

/**
 * Created by evan on 3/9/14.
 */
public abstract class ObservableAdapter<T> extends BaseAdapter {
    private ObservableListViewHelper<T> helper;

    public ObservableAdapter(List<T> list) {
        helper = new ObservableListViewHelper<>(list);
        helper.setListener(new ObservableList.Listener<T>() {
            @Override
            public void onChange(ObservableList.ChangeType changeType, Collection<ObservableList.Change<T>> changes) {
                notifyDataSetChanged();
            }
        });
    }

    public void replace(List<T> list) {
        helper.replace(list);
    }

    public ObservableList<T> getList() {
        return helper.getList();
    }

    @Override
    public int getCount() {
        return helper.getList().size();
    }

    @Override
    public T getItem(int position) {
        return helper.getList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
