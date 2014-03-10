package me.tatarka.sres;

import android.os.Handler;
import android.os.Looper;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by evan on 3/9/14.
 */
public abstract class ObservableAdapter<T> extends BaseAdapter {
    private Handler handler;
    private ObservableList<T> list;
    private List<T> viewList = new ArrayList<>();
    private ObservableList.Listener<T> listener = new ObservableList.Listener<T>() {
        @Override
        public void onChange(final ObservableList.ChangeType changeType, final Collection<ObservableList.Change<T>> changes) {
            if (Looper.myLooper() != Looper.getMainLooper()) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        updateBackingList(changeType, changes);
                    }
                });
            } else {
                updateBackingList(changeType, changes);
            }
        }
    };

    public ObservableAdapter(List<T> list) {
        handler = new Handler(Looper.getMainLooper());
        replace(list);
    }

    public void replace(List<T> list) {
        if (this.list != null) {
            this.list.removeListener(listener);
        }

        if (list instanceof ObservableList) {
            this.list = (ObservableList<T>) list;
        } else {
            this.list = new ObservableArrayList<>(list);
        }

        setBackingList();

        this.list.addListener(listener);
    }

    private void setBackingList() {
        getViewList().clear();
        getViewList().addAll(list);
    }

    protected void updateBackingList(ObservableList.ChangeType changeType, final Collection<ObservableList.Change<T>> changes) {
        if (changeType == ObservableList.ChangeType.UPDATE) {
            for (ObservableList.Change<T> change : changes) {
                getViewList().set(change.index, change.value);
            }
        } else if (changeType == ObservableList.ChangeType.ADD) {
            for (ObservableList.Change<T> change : changes) {
                getViewList().add(change.index, change.value);
            }
        } else if (changeType == ObservableList.ChangeType.REMOVE) {
            for (ObservableList.Change<T> change : changes) {
                getViewList().remove(change.index);
            }
        }
        notifyDataSetChanged();
    }

    public ObservableList<T> getList() {
        return list;
    }

    protected List<T> getViewList() {
        return viewList;
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public T getItem(int position) {
        return viewList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
