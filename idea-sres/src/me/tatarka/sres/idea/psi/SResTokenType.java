package me.tatarka.sres.idea.psi;

import com.intellij.psi.tree.IElementType;
import me.tatarka.sres.idea.SResLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * Created by evan on 3/2/14.
 */
public class SResTokenType extends IElementType {
    public SResTokenType(@NotNull @NonNls String debugName) {
        super(debugName, SResLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "SResTokenType." + super.toString();
    }
}
