package me.tatarka.sres.sample;

/**
 * Created by evan on 3/9/14.
 */
public class MyListItem {
    private MyModel model;
    public String text;

    public MyListItem(MyModel model, String text) {
        this.model = model;
        this.text = text;
    }

    public void remove() {
        model.items().remove(this);
    }
}
