package me.tatarka.sres.impl;

import me.tatarka.sres.LayoutParser;
import me.tatarka.sres.SResLexer;
import me.tatarka.sres.SResParser;
import me.tatarka.sres.ast.RootView;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by evan on 3/1/14.
 */
public class SResLayoutParser implements LayoutParser {
    @Override
    public RootView parse(Reader reader) throws IOException {
        CharStream input = new ANTLRInputStream(reader);
        TokenStream tokens = new CommonTokenStream(new SResLexer(input));
        SResParser parser = new SResParser(tokens);
        SResVisitor visitor = new SResVisitor();
        return visitor.visitRoot(parser.root());
    }
}
