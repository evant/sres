package me.tatarka.sres;

import me.tatarka.sres.ast.RootView;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by evan on 3/1/14.
 */
public interface LayoutParser {
    RootView parse(Reader reader) throws IOException;
}
