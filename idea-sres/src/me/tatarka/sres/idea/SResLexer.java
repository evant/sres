package me.tatarka.sres.idea;

import com.intellij.lexer.FlexAdapter;

/**
 * Created by evan on 3/2/14.
 */
public class SResLexer extends FlexAdapter {
    public SResLexer() {
        super(new _SResLexer());
    }
}
