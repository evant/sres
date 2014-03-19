package me.tatarka.sres.ast;

import java.util.Objects;

/**
 * Created by evan on 2/27/14.
 */
public class Attribute {
    public static final String ID = "android:id";
    public static final String LAYOUT_WIDTH = "android:layout_width";
    public static final String LAYOUT_HEIGHT = "android:layout_height";
    public static final String LAYOUT = "layout";
    public static final String BIND_CLASS = "bind:class";

    public final String name;
    public final String value;

    public Attribute(String name, String value) {
        this(name, value, Namespace.ANDROID);
    }

    public Attribute(String name, String value, Namespace namespace) {
        this.name = normalizeName(name, namespace);
        this.value = normalizeValue(this.name, value, namespace);
    }

    private static String normalizeName(String name, Namespace namespace) {
        if (namespace == Namespace.NONE || name.contains(":")) return name;
        return namespace + ":" + name;
    }

    private static String normalizeValue(String name, String value, Namespace namespace) {
        if (namespace != Namespace.ANDROID) return value;

        if (name.equals(LAYOUT_WIDTH) || name.equals(LAYOUT_HEIGHT)) {
            if (value.equals("match")) return "match_parent";
            if (value.equals("wrap")) return "wrap_content";
            return value;
        } else {
            return value;
        }
    }

    public String simpleName() {
        if (name.contains(":")) return name.split(":")[1];
        return name;
    }

    public boolean isBinding() {
        return name.startsWith(Namespace.BIND.toString()) && !name.equals(BIND_CLASS);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || obj.getClass() != getClass()) return false;
        Attribute other = (Attribute) obj;
        return Objects.equals(name, other.name) && Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return name + "=" + value;
    }

    public static enum Namespace {
        ANDROID, BIND, APP, NONE;

        @Override
        public String toString() {
            switch (this) {
                case ANDROID:return "android";
                case BIND: return "bind";
                case APP: return "app";
            }
            return "";
        }
    }
}
