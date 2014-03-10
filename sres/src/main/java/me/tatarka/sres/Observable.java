package me.tatarka.sres;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by evan on 3/9/14.
 */
public class Observable<T> {
    private T value;
    private List<Listener<T>> listeners = new ArrayList<>();

    public Observable() {
    }

    public Observable(T value) {
        this.value = value;
    }

    public void set(T value) {
        this.value = value;
        for (Listener<T> listener : listeners) listener.onChange(value);
    }

    public T get() {
        return value;
    }

    public void addListener(Listener<T> listener) {
        listeners.add(listener);
    }

    public void removeListener(Listener<T> listener) {
        listeners.remove(listener);
    }

    public interface Listener<T> {
        void onChange(T value);
    }
}
