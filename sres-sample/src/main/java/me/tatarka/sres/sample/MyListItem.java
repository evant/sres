package me.tatarka.sres.sample;

/**
 * Created by evan on 3/9/14.
 */
public class MyListItem {
    private MyModel model;
    private String text;

    public MyListItem(MyModel model, String text) {
        this.model = model;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void remove() {
        model.items().remove(this);
    }
}
