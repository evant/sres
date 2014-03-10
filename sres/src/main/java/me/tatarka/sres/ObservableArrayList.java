package me.tatarka.sres;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by evan on 3/9/14.
 */
public class ObservableArrayList<T> extends ArrayList<T> implements ObservableList<T> {
    private List<Listener<T>> listeners = new ArrayList<>();

    public ObservableArrayList(int initialCapacity) {
        super(initialCapacity);
    }

    public ObservableArrayList() {
        super();
    }

    public ObservableArrayList(Collection<? extends T> c) {
        super(c);
    }

    @Override
    public boolean add(T element) {
        boolean result = super.add(element);
        for (Listener<T> listener : listeners) {
            listener.onChange(ChangeType.ADD, Collections.singletonList(new Change<>(size()-1, element)));
        }
        return result;
    }

    @Override
    public void add(int index, T element) {
        super.add(index, element);
        for (Listener<T> listener : listeners) {
            listener.onChange(ChangeType.ADD, Collections.singletonList(new Change<>(index, element)));
        }
    }

    @Override
    public T set(int index, T element) {
        T result =  super.set(index, element);
        for (Listener<T> listener : listeners) {
            listener.onChange(ChangeType.UPDATE, Collections.singletonList(new Change<>(index, element)));
        }
        return result;
    }

    @Override
    public T remove(int index) {
        T result = super.remove(index);
        for (Listener<T> listener : listeners) {
           listener.onChange(ChangeType.REMOVE, Collections.singletonList(new Change<>(index, result)));
        }
        return result;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        boolean result = super.remove(o);
        if (result) {
            for (Listener<T> listener : listeners) {
                listener.onChange(ChangeType.REMOVE, Collections.singletonList(new Change<>(index, (T) o)));
            }
        }
        return result;
    }

    @Override
    public void clear() {
        List<Change<T>> changes = new ArrayList<>();
        for (int i = 0; i < size(); i++) {
            changes.add(new Change<>(i, get(i)));
        }
        if (!changes.isEmpty()) {
            for (Listener<T> listener : listeners) {
                listener.onChange(ChangeType.REMOVE, Collections.unmodifiableList(changes));
            }
        }
        super.clear();
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        int startIndex = size();
        boolean result = super.addAll(c);
        List<Change<T>> changes = new ArrayList<>();
        for (int i = startIndex; i < size(); i++) {
            changes.add(new Change<>(i, get(i)));
        }
        if (!changes.isEmpty()) {
            for (Listener<T> listener : listeners) {
                listener.onChange(ChangeType.ADD, Collections.unmodifiableList(changes));
            }
        }
        return result;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        boolean result = super.addAll(index, c);
        List<Change<T>> changes = new ArrayList<>();
        for (int i = index; i < size(); i++) {
            changes.add(new Change<>(i, get(i)));
        }
        if (!changes.isEmpty()) {
            for (Listener<T> listener : listeners) {
                listener.onChange(ChangeType.ADD, Collections.unmodifiableList(changes));
            }
        }
        return result;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        List<Change<T>> changes = new ArrayList<>();
        for (int i = size() - 1; i >= 0; i--) {
            if (contains(get(i))) changes.add(new Change<>(i, get(i)));
        }
        boolean result = super.removeAll(c);
        if (result) {
            for (Listener<T> listener : listeners) {
                listener.onChange(ChangeType.REMOVE, Collections.unmodifiableList(changes));
            }
        }
        return result;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        List<Change<T>> changes = new ArrayList<>();
        for (int i = size() - 1; i >= 0; i--) {
            if (!contains(get(i))) changes.add(new Change<>(i, get(i)));
        }
        boolean result = super.retainAll(c);
        if (result) {
            for (Listener<T> listener : listeners) {
                listener.onChange(ChangeType.REMOVE, Collections.unmodifiableList(changes));
            }
        }
        return result;
    }

    @Override
    public void addListener(Listener<T> listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(Listener<T> listener) {
        listeners.remove(listener);
    }
}
