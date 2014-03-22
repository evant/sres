package me.tatarka.sres;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * A base implementation of trackable. This implementation calls the listeners on the main thread in
 * the order that they were registered.
 *
 * You can subclass this and call @{link AbstractTrackable#notifyChange} when the trqckable has
 * changed.
 */
public abstract class AbstractTrackable implements Trackable {
    private Set<Listener> listeners = new LinkedHashSet<Listener>();

    @Override
    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    protected void notifyChange() {
        ThreadHandlerProvider.getDefault().postToMainThread(new Runnable() {
            @Override
            public void run() {
                for (Listener listener : listeners) listener.onChange();
            }
        });
    }
}
