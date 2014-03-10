package me.tatarka.sres.idea;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Created by evan on 3/2/14.
 */
public class SResFileType extends LanguageFileType {
    public static final SResFileType INSTANCE = new SResFileType();

    protected SResFileType() {
        super(SResLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "SRes file";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "SRes language file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "sres";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return SResIcons.FILE;
    }
}
