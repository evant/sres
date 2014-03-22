package me.tatarka.sres;

/**
 * An interface for something that can be tracked. When a trackable changes, it will notify all
 * listeners.
 */
public interface Trackable {
    /**
     * Add a listener to the property. The listener is notified when the property's value has
     * changed.
     *
     * @param listener the listener to add
     */
    void addListener(Listener listener);

    /**
     * Remove a listener from the property.
     *
     * @param listener the listener to remove
     */
    void removeListener(Listener listener);

    /**
     * The listener that will be called when the trackable changes.
     */
    public interface Listener {
        /**
         * Called when the trackable changes
         */
        void onChange();
    }
}
