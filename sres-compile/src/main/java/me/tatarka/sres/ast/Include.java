package me.tatarka.sres.ast;

import org.antlr.v4.runtime.misc.NotNull;

import java.util.*;

/**
 * Created by evan on 3/5/14.
 */
public class Include implements Child {
    public static final String INCLUDE = "include";

    public final String layout;
    public final String id;
    public final java.util.Set<Attribute> attributes;
    public final List<Child> yields;

    public Include(@NotNull java.util.Set<Attribute> attributes, @NotNull List<Child> yields) {
        this.attributes = Collections.unmodifiableSet(attributes);
        this.yields = Collections.unmodifiableList(yields);
        this.layout = findLayout();
        this.id = findId();
    }

    private String findLayout() {
        for (Attribute attribute : attributes) {
            if (attribute.name.equals(Attribute.LAYOUT)) return attribute.value;
        }
        throw new IllegalArgumentException("Include must have a layout");
    }

    private String findId() {
        for (Attribute attribute : attributes) {
            if (attribute.name.equals(Attribute.ID)) return attribute.value;
        }
        return null;
    }

    public static Builder of(String id) {
        return new Builder(id);
    }

    public static class Builder implements Child.Builder {
        private final java.util.Set<Attribute> attributes = new LinkedHashSet<>();
        private final List<Child> yields = new ArrayList<>();

        public Builder(String layout) {
            attribute(new Attribute(Attribute.LAYOUT, layout, Attribute.Namespace.NONE));
        }

        public Builder id(String id) {
            return attribute(Attribute.ID, id);
        }

        public Builder attribute(Attribute attribute) {
            attributes.add(attribute);
            return this;
        }

        public Builder attribute(String name, String value) {
            return attribute(new Attribute(name, value));
        }

        public Builder yield(View view) {
            if (view.id == null) throw new IllegalArgumentException("Yielded view must have an id");
            yields.add(view);
            return this;
        }

        public Builder yield(View.Builder view) {
            View v = view.build();
            if (v.id == null) throw new IllegalArgumentException("Yielded view must have an id");
            yields.add(v);
            return this;
        }

        @Override
        public Include build() {
            return new Include(attributes, yields);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, attributes);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) return false;
        Include other = (Include) obj;
        return Objects.equals(id, other.id)
                && Objects.equals(attributes, other.attributes);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(INCLUDE + "(\n");
        for (Attribute attribute : attributes) {
            builder.append(attribute.toString()).append("\n");
        }
        builder.append(")");

        return builder.toString();
    }
}
