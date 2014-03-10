package me.tatarka.sres.sample;

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

    public void set(T newValue) {
        value = newValue;
        for (Listener<T> listener : listeners) {
            listener.onChange(value);
        }
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

    public <B> Observable<B> map(final Map<T, B> map) {
        final Observable<B> newObservable = value == null ? new Observable<B>() : new Observable<B>(map.map(value));
        addListener(new Listener<T>() {
            @Override
            public void onChange(T value) {
                newObservable.set(map.map(value));
            }
        });
        return newObservable;
    }

    public interface Listener<T> {
        void onChange(T value);
    }

    public interface Map<A, B> {
        B map(A value);
    }
}
