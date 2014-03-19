package me.tatarka.sres.ast;

import java.util.Objects;

/**
 * Created by evan on 3/11/14.
 */
public class Converter {
    public final Type type;
    public final String value;

    public Converter(Type type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Converter converter = (Converter) o;

        return type == converter.type && value.equals(converter.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, value);
    }

    public static enum Type {
        CAST, METHOD
    }
}
