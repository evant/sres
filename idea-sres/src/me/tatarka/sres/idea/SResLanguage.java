package me.tatarka.sres.idea;

import com.intellij.lang.Language;

/**
 * Created by evan on 3/2/14.
 */
public class SResLanguage extends Language {
    public static final SResLanguage INSTANCE = new SResLanguage();

    protected SResLanguage() {
        super("SRes");
    }
}
