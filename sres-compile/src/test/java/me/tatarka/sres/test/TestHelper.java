package me.tatarka.sres.test;

import com.google.common.base.CaseFormat;
import me.tatarka.sres.SResOutput;
import me.tatarka.sres.SourceInfo;
import me.tatarka.sres.ast.RootView;
import me.tatarka.sres.impl.SResCodeLayoutGenerator;
import me.tatarka.sres.impl.SResLayoutParser;
import me.tatarka.sres.impl.SResXmlLayoutGenerator;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.file.Paths;

/**
 * Created by evan on 2/27/14.
 */
public class TestHelper {
    private static SResLayoutParser parser = new SResLayoutParser();
    private static SResXmlLayoutGenerator xmlGenerator = new SResXmlLayoutGenerator();
    private static SResCodeLayoutGenerator codeGenerator = new SResCodeLayoutGenerator();

    public static RootView parse(String input) {
        StringReader reader = new StringReader(input);
        try {
            return parser.parse(reader);
        } catch (IOException e) {
            // Strings can't throw IOExceptions so this shouldn't happen
            throw new RuntimeException(e);
        }
    }

    public static Xml toXml(String name, RootView view) {
        StringWriter writer = new StringWriter();
        xmlGenerator.generate(view, new SResOutput(testSourceInfo(name), writer));
        return new Xml(writer.toString());
    }

    public static Code toCode(String name, RootView view) {
        StringWriter writer = new StringWriter();
        codeGenerator.generate(view, new SResOutput(testSourceInfo(name), writer));
        return new Code(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, name), writer.toString());
    }

    private static SourceInfo testSourceInfo(String name) {
        return new SourceInfo(Paths.get("test/" + name), "test", "me.tatarka.sres.test");
    }
}
