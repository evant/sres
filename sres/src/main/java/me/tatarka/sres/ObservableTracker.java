package me.tatarka.sres;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by evan on 3/19/14.
 */
public class ObservableTracker {
    private static ThreadLocal<Boolean> isTracking = new ThreadLocal<Boolean>() {
        @Override
        protected Boolean initialValue() {
            return false;
        }
    };
    private static ThreadLocal<List<Observable>> trackedObservables = new ThreadLocal<List<Observable>>() {
        @Override
        protected List<Observable> initialValue() {
            return new ArrayList<>();
        }
    };

    private Map<Observable.Listener, List<Observable>> observableMap = new HashMap<Observable.Listener, List<Observable>>();

    private static void startTracking() {
        isTracking.set(true);
    }

    private static List<Observable> stopTracking() {
        isTracking.set(false);
        List<Observable> observables = new ArrayList<Observable>(trackedObservables.get());
        trackedObservables.get().clear();
        return observables;
    }

    public static void track(Observable property) {
        if (isTracking.get()) trackedObservables.get().add(property);
    }

    public void clear() {
        if (!ThreadHandlerProvider.getDefault().isOnMainThread()) {
            throw new IllegalStateException("ObservableTracker.clear() must be called on the UI thread");
        }

        for (Map.Entry<Observable.Listener, List<Observable>> entry : observableMap.entrySet()) {
            for (Observable observable : entry.getValue()) observable.removeListener(entry.getKey());
        }
        observableMap.clear();
    }

    public void listen(Observable.Listener listener) {
        if (!ThreadHandlerProvider.getDefault().isOnMainThread()) {
            throw new IllegalStateException("ObservableTracker.listen() must be called on the UI thread");
        }

        startTracking();
        listener.onChange();
        List<Observable> newEntry = stopTracking();
        List<Observable> oldEntry = observableMap.put(listener, newEntry);
        if (oldEntry != null) {
            for (Observable observable : oldEntry) observable.removeListener(listener);
        }
        for (Observable observable : newEntry) observable.addListener(listener);
    }
}
