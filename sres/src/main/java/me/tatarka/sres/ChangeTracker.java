package me.tatarka.sres;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Tracks changes for some set of trackables. Any trackables that want to be tracked must call
 *
 * @{link ChangeTracker#track}. @{link me.tatarka.sres.Property} and it's relatives all call this
 * when you get their value.
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

    /**
     * Notifies that the given trackable wants to be tracked. This should be called when a listener
     * is registered on #{addListener}. For example, a @{link Property} calls it on @{link
     * Property#get}.
     *
     * @param trackable the trackable to track
     */
    public static void track(Trackable trackable) {
        if (isTracking.get()) trackedObservables.get().add(trackable);
    }

    /**
     * Clears all registered listeners so they will no longer be called when the trackable changes.
     */
    public void clear() {
        ensureMainThread("clear");

        for (Map.Entry<Trackable.Listener, List<Trackable>> entry : observableMap.entrySet()) {
            for (Trackable trackable : entry.getValue()) trackable.removeListener(entry.getKey());
        }
        observableMap.clear();
    }

    /**
     * Adds the listener. The listener will immediately be called to discover which trackables it
     * tracks. Then it will be called every time any of it's trackables change.
     *
     * @param listener the listener
     */
    public void addListener(Trackable.Listener listener) {
        ensureMainThread("addListener");

        startTracking();
        listener.onChange();
        List<Trackable> newEntry = stopTracking();
        List<Trackable> oldEntry = observableMap.put(listener, newEntry);
        if (oldEntry != null) {
            for (Trackable trackable : oldEntry) trackable.removeListener(listener);
        }
        for (Trackable trackable : newEntry) trackable.addListener(listener);
    }

    /**
     * Removes the listener. It will no longer be called when it's trackables change.
     *
     * @param listener the listener
     */
    public void removeListener(Trackable.Listener listener) {
        ensureMainThread("removeListener");

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
