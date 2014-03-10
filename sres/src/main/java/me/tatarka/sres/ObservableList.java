package me.tatarka.sres;

import java.util.Collection;
import java.util.List;

/**
 * Created by evan on 3/9/14.
 */
public interface ObservableList<T> extends List<T> {
    void addListener(Listener<T> listener);
    void removeListener(Listener<T> listener);

    public interface Listener<T> {
        void onChange(ChangeType changeType, Collection<Change<T>> changes);
    }

    public enum ChangeType {
        ADD, REMOVE, UPDATE
    }

    public static class Change<T> {
        public final int index;
        public final T value;

        public Change(int index, T value) {
            this.index = index;
            this.value = value;
        }
    }
}
