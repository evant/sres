package me.tatarka.sres;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by evan on 3/9/14.
 */
public class Observable<T> {
    private T value;
    private List<Listener> listeners = new ArrayList<>();

    public Observable(T value) {
        this.value = value;
    }

    public void set(T value) {
        this.value = value;
        ThreadHandlerProvider.getDefault().postToMainThread(new Runnable() {
            @Override
            public void run() {
                for (Listener listener : listeners) listener.onChange();
            }
        });
    }

    public T get() {
        ObservableTracker.track(this);
        return value;
    }

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    public interface Listener {
        void onChange();
    }
}
