package me.tatarka.sres;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by evan on 3/19/14.
 */
public class ChangeTracker {
    private static ThreadLocal<Boolean> isTracking = new ThreadLocal<Boolean>() {
        @Override
        protected Boolean initialValue() {
            return false;
        }
    };
    private static ThreadLocal<List<Trackable>> trackedObservables = new ThreadLocal<List<Trackable>>() {
        @Override
        protected List<Trackable> initialValue() {
            return new ArrayList<>();
        }
    };

    private Map<Trackable.Listener, List<Trackable>> observableMap = new HashMap<Trackable.Listener, List<Trackable>>();

    private static void startTracking() {
        isTracking.set(true);
    }

    private static List<Trackable> stopTracking() {
        isTracking.set(false);
        List<Trackable> properties = new ArrayList<Trackable>(trackedObservables.get());
        trackedObservables.get().clear();
        return properties;
    }

    public static void track(Trackable trackable) {
        if (isTracking.get()) trackedObservables.get().add(trackable);
    }

    public void clear() {
        ensureMainThread("clear");

        for (Map.Entry<Trackable.Listener, List<Trackable>> entry : observableMap.entrySet()) {
            for (Trackable trackable : entry.getValue()) trackable.removeListener(entry.getKey());
        }
        observableMap.clear();
    }

    public void listen(Trackable.Listener listener) {
        ensureMainThread("listen");

        startTracking();
        listener.onChange();
        List<Trackable> newEntry = stopTracking();
        List<Trackable> oldEntry = observableMap.put(listener, newEntry);
        if (oldEntry != null) {
            for (Trackable trackable : oldEntry) trackable.removeListener(listener);
        }
        for (Trackable trackable : newEntry) trackable.addListener(listener);
    }

    public void unlisten(Trackable.Listener listener) {
        ensureMainThread("unlisten");

        List<Trackable> entry = observableMap.remove(listener);
        if (entry != null) {
            for (Trackable trackable : entry) trackable.removeListener(listener);
        }
    }

    private void ensureMainThread(String name) {
        if (!ThreadHandlerProvider.getDefault().isOnMainThread()) {
            throw new IllegalStateException("ChangeTracker" + name + "() must be called on the UI thread");
        }
    }
}
