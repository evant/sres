package me.tatarka.sres;

/**
* Created by evan on 3/11/14.
*/
public interface LayoutSelector<T> {
    int getLayout(int position, T model);
    int getCount();
}
