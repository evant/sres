package me.tatarka.sres.impl;

import com.sun.codemodel.CodeWriter;
import com.sun.codemodel.JPackage;
import org.apache.commons.io.output.WriterOutputStream;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

/**
 * Created by evan on 3/6/14.
 */
class WriterCodeWriter extends CodeWriter {
    private Writer writer;

    public WriterCodeWriter(Writer writer) {
        this.writer = writer;
    }

    @Override
    public OutputStream openBinary(JPackage pkg, String fileName) throws IOException {
        return new WriterOutputStream(writer, StandardCharsets.UTF_8);
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }
}
