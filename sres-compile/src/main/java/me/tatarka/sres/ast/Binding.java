package me.tatarka.sres.ast;

import java.util.Objects;

/**
 * Created by evan on 3/9/14.
 */
public class Binding {
    public final String name;
    public final String value;
    public final Type type;

    public Binding(String name, String value) {
        this.name = name;

        if (value.endsWith("()")) {
            this.type = Type.METHOD;
            this.value = value.substring(0, value.length() - 2);
        } else {
            this.type = Type.FIELD;
            this.value = value;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Binding binding = (Binding) o;

        return name.equals(binding.name) && value.equals(binding.value) && type.equals(binding.type);

    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value, type);
    }

    public static enum Type {
        FIELD, METHOD
    }
}
