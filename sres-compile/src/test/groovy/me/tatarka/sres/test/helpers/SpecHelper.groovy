package me.tatarka.sres.test.helpers

import com.google.common.base.CaseFormat
import me.tatarka.sres.SResOutput
import me.tatarka.sres.SourceInfo
import me.tatarka.sres.ast.RootView
import me.tatarka.sres.impl.SResCodeLayoutGenerator
import me.tatarka.sres.impl.SResLayoutParser
import me.tatarka.sres.impl.SResXmlLayoutGenerator

import java.nio.file.Paths

/**
 * Created by evan on 5/3/14.
 */
class SpecHelper {
    private static parser = new SResLayoutParser()
    private static xmlGenerator = new SResXmlLayoutGenerator()
    private static codeGenerator = new SResCodeLayoutGenerator()

    static RootView parse(String input)  {
        parser.parse(new StringReader(input))
    }

    static Xml toXml(String name, RootView view) {
        def writer = new StringWriter()
        xmlGenerator.generate(view, new SResOutput(testSourceInfo(name), writer))
        new Xml(writer.toString())
    }

    static Code toCode(String name, RootView view) {
        StringWriter writer = new StringWriter()
        codeGenerator.generate(view, new SResOutput(testSourceInfo(name), writer))
        new Code(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, name), writer.toString())
    }

    private static SourceInfo testSourceInfo(String name) {
        new SourceInfo(Paths.get("test/$name"), 'test', 'me.tatarka.sres.test');
    }
}
