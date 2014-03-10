package me.tatarka.sres;

import me.tatarka.sres.util.PathTransformer;

import java.nio.file.Path;

/**
 * Created by evan on 3/8/14.
 */
public class SourceInfo {
    private Path path;
    private String codePackageName;
    private String appPackageName;

    public SourceInfo(Path path, String codePackageName, String appPackageName) {
        this.path = path;
        this.codePackageName = codePackageName;
        this.appPackageName = appPackageName;
    }

    public String getPackageName() {
        return codePackageName;
    }

    public String getAppPackageName() {
        return appPackageName;
    }

    public String getName() {
        return PathTransformer.of(path.getFileName()).extension("").toString();
    }
}
