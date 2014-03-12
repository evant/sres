package me.tatarka.sres;

import android.os.Handler;
import android.os.Looper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by evan on 3/11/14.
 */
public class ObservableListViewHelper<T> {
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
    private ObservableList.Listener<T> deleteListener;

    public ObservableListViewHelper(List<T> list) {
        handler = new Handler(Looper.getMainLooper());
        replace(list);
    }

    public void setListener(ObservableList.Listener<T> listener) {
        deleteListener = listener;
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

        viewList.clear();
        viewList.addAll(list);

        this.list.addListener(listener);
    }

    private void updateBackingList(ObservableList.ChangeType changeType, final Collection<ObservableList.Change<T>> changes) {
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

        if (deleteListener != null) deleteListener.onChange(changeType, changes);
    }

    public ObservableList<T> getList() {
        return list;
    }

    public List<T> getViewList() {
        return viewList;
    }
}
