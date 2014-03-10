package me.tatarka.sres.ast;

import java.util.Objects;

/**
 * Created by evan on 3/9/14.
 */
public class Binding {
    public final To to;
    public final Type type;
    public final String value;

    public Binding(Type type, To to, String value) {
        this.type = type;
        this.to = to;
        this.value = value;
    }

    public static Binding literal(String value) {
        return new Binding(Type.PRIMITIVE, To.LITERAL, value);
    }

    public static Binding field(String value) {
        return new Binding(Type.PRIMITIVE, To.FIELD, value);
    }

    public static Binding method(String value) {
        return new Binding(Type.PRIMITIVE, To.METHOD, value);
    }

    public static Binding literal(Type type, String value) {
        return new Binding(type, To.LITERAL, value);
    }

    public static Binding field(Type type, String value) {
        return new Binding(type, To.FIELD, value);
    }

    public static Binding method(Type type, String value) {
        return new Binding(type, To.METHOD, value);
    }

    public static enum To {
        LITERAL, FIELD, METHOD
    }

    public static enum Type {
        PRIMITIVE, OBSERVABLE, LISTENER
    }

    @Override
    public int hashCode() {
        return Objects.hash(to, value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) return false;
        Binding other = (Binding) obj;
        return Objects.equals(to, other.to) && Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return value;
    }
}
