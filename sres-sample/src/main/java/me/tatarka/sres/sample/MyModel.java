package me.tatarka.sres.sample;

import me.tatarka.sres.ObservableArrayList;
import me.tatarka.sres.property.BooleanProperty;

import java.util.Arrays;
import java.util.List;

/**
 * Created by evan on 3/9/14.
 */
public class MyModel {
    private BooleanProperty enabled = new BooleanProperty(false);

    private List<MyListItem> items = new ObservableArrayList<>(Arrays.asList(
            new MyListItem(this, "One"),
            new MyListItem(this, "Two"),
            new MyListItem(this, "Three")
    ));

    public void toggle() {
        enabled.set(!enabled.get());
    }

    public String getText() {
        return enabled.get() ? "Enabled" : "Not Enabled";
    }

    public List<MyListItem> items() {
        return items;
    }

    public void addItem(String text) {
        items().add(new MyListItem(this, text));
    }
}
