package me.tatarka.sres;

import java.io.Writer;

/**
 * Created by evan on 3/8/14.
 */
public class SResOutput {
    public final SourceInfo sourceInfo;
    public final Writer writer;

    public SResOutput(SourceInfo sourceInfo, Writer writer) {
        this.sourceInfo = sourceInfo;
        this.writer = writer;
    }
}
