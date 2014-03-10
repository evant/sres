package me.tatarka.sres;

import com.google.common.base.CaseFormat;
import me.tatarka.sres.ast.RootView;
import me.tatarka.sres.impl.SResCodeLayoutGenerator;
import me.tatarka.sres.impl.SResLayoutParser;
import me.tatarka.sres.impl.SResXmlLayoutGenerator;
import me.tatarka.sres.util.PathTransformer;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by evan on 2/27/14.
 */
public class SRes {
    private LayoutParser parser;
    private LayoutGenerator xmlGenerator;
    private LayoutGenerator codeGenerator;

    public SRes() {
        this(new SResLayoutParser(), new SResXmlLayoutGenerator(), new SResCodeLayoutGenerator());
    }

    public SRes(LayoutParser parser, LayoutGenerator xmlGenerator, LayoutGenerator codeGenerator) {
        this.parser = parser;
        this.xmlGenerator = xmlGenerator;
        this.codeGenerator = codeGenerator;
    }

    public void generate(Reader reader, SResOutput xmlOutput, SResOutput codeOutput) throws IOException {
        RootView view = parser.parse(reader);
        xmlGenerator.generate(view, xmlOutput);
        codeGenerator.generate(view, codeOutput);
    }

    public void generateAll(final String appPackageName, final Path inputDir, final Path xmlOutputDir, final Path codeOutputDir, final DirectoryStream.Filter<Path> filter) throws IOException {
        Files.walkFileTree(inputDir, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path inputPath, BasicFileAttributes attrs) throws IOException {
                if (filter.accept(inputPath)) {
                    Path xmlOutputPath = newXmlOutputPath(inputPath, inputDir, xmlOutputDir);
                    Path codeOutputPath = newCodeOutputPath(inputPath, inputDir, codeOutputDir);

                    Files.createDirectories(xmlOutputPath.getParent());
                    Files.createDirectories(codeOutputPath.getParent());

                    try (Reader reader = Files.newBufferedReader(inputPath);
                         Writer xmlFileWriter = Files.newBufferedWriter(xmlOutputPath);
                         Writer codeFileWriter = Files.newBufferedWriter(codeOutputPath)) {

                        String packageName = toPackageName(inputPath, inputDir);
                        SourceInfo xmlSourceInfo = new SourceInfo(xmlOutputPath, packageName, appPackageName);
                        SourceInfo codeSourceInfo = new SourceInfo(xmlOutputPath, packageName, appPackageName);

                        SResOutput xmlOutput = new SResOutput(xmlSourceInfo, xmlFileWriter);
                        SResOutput codeOutput = new SResOutput(codeSourceInfo, codeFileWriter);

                        generate(reader, xmlOutput, codeOutput);
                    }
                }
                return FileVisitResult.CONTINUE;
            }
        });
    }

    public void generateAll(String appPackageName, Path inputDir, Path xmlOutputDir, Path codeOutputDir) throws IOException {
        generateAll(appPackageName, inputDir, xmlOutputDir, codeOutputDir, new DirectoryStream.Filter<Path>() {
            @Override
            public boolean accept(Path entry) throws IOException {
                return entry.toString().endsWith(".sres");
            }
        });
    }

    private String toPackageName(Path path, Path inputDir) {
        return PathTransformer.of(inputDir.relativize(path).getParent())
                .extension("")
                .toString().replace("/", ".");
    }

    private Path newXmlOutputPath(Path path, Path inputDir, Path outputDir) {
        return PathTransformer.of(path)
                .extension("xml")
                .mirror(inputDir, outputDir)
                .toPath();
    }

    private Path newCodeOutputPath(Path path, Path inputDir, Path outputDir) {
        return PathTransformer.of(path)
                .extension("java")
                .changeNameCase(CaseFormat.LOWER_UNDERSCORE, CaseFormat.UPPER_CAMEL)
                .mirror(inputDir, outputDir)
                .toPath();
    }
}
