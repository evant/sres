package me.tatarka.sres.sample;

import me.tatarka.sres.ObservableArrayList;

import java.util.Arrays;
import java.util.List;

/**
 * Created by evan on 3/9/14.
 */
public class MyModel {
    private boolean enabled;
    private List<MyListItem> items = new ObservableArrayList<>(Arrays.asList(
            new MyListItem(this, "One"),
            new MyListItem(this, "Two"),
            new MyListItem(this, "Three")
    ));

    public void toggle() {
        enabled = !enabled;
    }

    public String getText() {
        return enabled ? "Enabled" : "Not Enabled";
    }

    public List<MyListItem> items() {
        return items;
    }

    public void addItem(String text) {
        items().add(new MyListItem(this, text));
    }
}
