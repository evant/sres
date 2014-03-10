package me.tatarka.sres;

/**
 * Created by evan on 3/9/14.
 */
public interface Bindable<T> {
    public static final String NAMESPACE = "http://schemas.android.com/apk/lib/me.tatarka.sres";

    void bind(T model);
}
