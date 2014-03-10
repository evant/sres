package me.tatarka.sres.idea;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Created by evan on 3/2/14.
 */
public class SResFile extends PsiFileBase {
    protected SResFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, SResLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return SResFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "SRes File";
    }

    @Nullable
    @Override
    public Icon getIcon(int flags) {
        return super.getIcon(flags);
    }
}
