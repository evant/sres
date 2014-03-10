package me.tatarka.sres;

import me.tatarka.sres.ast.RootView;

/**
 * Created by evan on 3/1/14.
 */
public interface LayoutGenerator {
    void generate(RootView rootView, SResOutput output);
}
