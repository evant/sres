package me.tatarka.sres.idea.psi;

import com.intellij.psi.tree.IElementType;
import me.tatarka.sres.idea.SResLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * Created by evan on 3/2/14.
 */
public class SResElementType extends IElementType {
    public SResElementType(@NotNull @NonNls String debugName) {
        super(debugName, SResLanguage.INSTANCE);
    }
}
