package me.tatarka.sres.property;

import me.tatarka.sres.AbstractTrackable;
import me.tatarka.sres.ChangeTracker;

/**
 * A Property is a value in which to can track changes.
 */
public class BooleanProperty extends AbstractTrackable {
    private boolean value;

    /**
     * Constructs a new property with the given initial value.
     *
     * @param value the initial value
     */
    public BooleanProperty(boolean value) {
        this.value = value;
    }

    /**
     * Sets the property's value, notifying all listeners that it has changed.
     *
     * @param value the new value
     */
    public void set(boolean value) {
        this.value = value;
        notifyChange();
    }

    /**
     * Gets the property's value.
     *
     * @return the current value
     */
    public boolean get() {
        ChangeTracker.track(this);
        return value;
    }
}

