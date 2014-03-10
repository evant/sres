package me.tatarka.sres.test;

import com.google.testing.compile.JavaFileObjects;

import javax.tools.JavaFileObject;

/**
 * Created by evan on 3/6/14.
 */
public class Code {
    public final String name;
    public final String code;

    public Code(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public JavaFileObject getJavaFileObject() {
        return JavaFileObjects.forSourceString(name, code);
    }

}
