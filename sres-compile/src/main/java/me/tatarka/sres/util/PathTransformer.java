package me.tatarka.sres.util;

import com.google.common.base.CaseFormat;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by evan on 3/1/14.
 */
public class PathTransformer {
    private Path path;

    public PathTransformer(Path path) {
        this.path = path;
    }

    public static PathTransformer of(Path path) {
        return new PathTransformer(path);
    }

    public static PathTransformer of(File file) {
        return new PathTransformer(file.toPath());
    }

    public static PathTransformer of(String fileName) {
        return new PathTransformer(new File(fileName).toPath());
    }

    public PathTransformer mirror(Path inputDir, Path outputDir) {
        return PathTransformer.of(outputDir.resolve(inputDir.relativize(path)));
    }

    public PathTransformer mirror(File inputDir, File outputDir) {
        return mirror(inputDir.toPath(), outputDir.toPath());
    }

    public PathTransformer mirror(String inputDir, String outputDir) {
        return mirror(new File(inputDir), new File(outputDir));
    }

    public PathTransformer extension(String extension) {
        String name = path.getFileName().toString();
        int extensionIndex = FilenameUtils.indexOfExtension(name);
        String newName = name.substring(0, extensionIndex < 0 ? name.length() : extensionIndex) +
                (extension.length() > 0 ? "." : "") + extension;
        return PathTransformer.of(path.getParent() == null ? Paths.get(newName) : path.getParent().resolve(newName));
    }

    public PathTransformer changeNameCase(CaseFormat fromFormat, CaseFormat toFormat) {
        String name = path.getFileName().toString();
        String extension = FilenameUtils.getExtension(name);
        int extensionIndex = FilenameUtils.indexOfExtension(name);
        String bareName = extensionIndex < 0 ? name : name.substring(0, extensionIndex);
        String newName = fromFormat.to(toFormat, bareName) + "." + extension;
        return PathTransformer.of(path.getParent() == null ? Paths.get(newName) : path.getParent().resolve(newName));
    }

    public Path toPath() {
        return path;
    }

    @Override
    public String toString() {
        return path.toString();
    }
}
