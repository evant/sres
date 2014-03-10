package me.tatarka.sres;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.lang.System.err;
import static java.lang.System.out;

/**
 * Created by evan on 2/26/14.
 */
public class Main {
    public static void main(String[] args) {
        String packageName = System.getProperty("sres.packageName");
        String inputDirName = System.getProperty("sres.inputDir");
        String xmlOutputDirName = System.getProperty("sres.xmlOutputDir");
        String codeOutputDirName = System.getProperty("sres.codeOutputDir");

        if (inputDirName == null || xmlOutputDirName == null || codeOutputDirName == null) {
            out.println(
                 "Usage: java -Dsres.packageName=? -Dsres.intputDir=? -Dsres.xmlOutputDir=? -Dsres.codeOutputDir=? -jar sres.jar"
            );
            return;
        }

        Path inputDir = Paths.get(inputDirName);
        Path xmlOutputDir = Paths.get(xmlOutputDirName);
        Path codeOutputDir = Paths.get(codeOutputDirName);

        if (!Files.isDirectory(inputDir)) {
            err.println(inputDir + " is not a directory");
            return;
        }

        SRes generator = new SRes();
        try {
            generator.generateAll(packageName, inputDir, xmlOutputDir, codeOutputDir);
        } catch (IOException e) {
            err.println(e.getMessage());
        }
    }
}
