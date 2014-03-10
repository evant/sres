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

    public static final int RAW = 0;
    public static final int SMART = 1;

    public final String name;
    public final Binding binding;

    public Attribute(String name, Binding binding) {
        this(name, binding, SMART);
    }

    public Attribute(String name, String value) {
        this(name, Binding.literal(value));
    }

    public Attribute(String name, String value, int conversionMode) {
        this(name, Binding.literal(value), conversionMode);
    }

    public Attribute(String name, Binding binding, int conversionMode) {
        if (conversionMode == SMART && binding.to == Binding.To.LITERAL) {
            this.name = normalizeName(name);
            this.binding = normalizeValue(this.name, binding);
        } else {
            this.name = name;
            this.binding = binding;
        }
    }

    private static String normalizeName(String name) {
        if (name.contains(":")) return name;
        return "android:" + name;
    }

    private static Binding normalizeValue(String name, Binding value) {
        if (value.to != Binding.To.LITERAL) return value;

        if (name.equals(LAYOUT_WIDTH) || name.equals(LAYOUT_HEIGHT)) {
            if (value.value.equals("match")) return Binding.literal("match_parent");
            if (value.value.equals("wrap")) return Binding.literal("wrap_content");
            return value;
        } else {
            return value;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, binding);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) return false;
        Attribute other = (Attribute) obj;
        return Objects.equals(name, other.name) && Objects.equals(binding, other.binding);
    }

    @Override
    public String toString() {
        return name + "=" + binding;
    }
}
