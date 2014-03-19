package me.tatarka.sres.ast;

import com.google.common.base.CaseFormat;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.*;


/**
 * Created by evan on 2/27/14.
 */
public class View implements Child {
    public final String name;
    public final java.util.Set<Attribute> attributes;
    public final java.util.Set<Binding> bindings;
    public final List<Child> children;
    public final String id;

    public View(@NotNull String name, @NotNull java.util.Set<Attribute> attributes,
                @NotNull List<Child> children) {
        this.name = name;
        this.attributes = Collections.unmodifiableSet(attributes);
        this.children = Collections.unmodifiableList(children);
        this.id = findId();
        this.bindings = findBindings();
    }

    private String findId() {
        for (Attribute attribute : attributes) {
            if (attribute.name.equals(Attribute.ID)) return attribute.value;
        }
        return null;
    }

    private Set<Binding> findBindings() {
        Set<Binding> bindings = new LinkedHashSet<>();
        for (Attribute attribute : attributes) {
            if (attribute.isBinding()) {
                bindings.add(new Binding(attribute.simpleName(), attribute.value));
            }
        }
        return bindings;
    }

    public String qualifiedName() {
        if (name.contains(".")) return name;
        return "android.widget." + name;
    }

    public String idReference() {
        return id.replaceFirst("@\\+?(\\w+:)?id/", "");
    }

    public String variableName() {
        if (id == null) return null;
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, idReference());
    }

    public static Builder of(String name) {
        return new Builder(name);
    }

    public static class Builder implements Child.Builder {
        private String name;
        private java.util.Set<Attribute> attributes = new LinkedHashSet<>();
        private List<Child> children = new ArrayList<>();

        public Builder(String name) {
            this.name = name;
        }

        public Builder id(String id) {
            return attribute(Attribute.ID, id);
        }

        public Builder size(String width, String height) {
            attribute(Attribute.LAYOUT_WIDTH, width);
            attribute(Attribute.LAYOUT_HEIGHT, height);
            return this;
        }

        public Builder bindClass(String bindClass) {
            return attribute(Attribute.BIND_CLASS, bindClass);
        }

        public Builder bind(String name, String value) {
            return attribute(new Attribute(name, value, Attribute.Namespace.BIND));
        }

        public Builder attribute(Attribute attribute) {
            attributes.add(attribute);
            return this;
        }

        public Builder attribute(String name, String value) {
            return attribute(new Attribute(name, value));
        }

        public Builder child(Child child) {
            children.add(child);
            return this;
        }

        public Builder child(Child.Builder child) {
            return child(child.build());
        }

        @Override
        public View build() {
            return new View(name, attributes, children);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, attributes, children);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) return false;
        View other = (View) obj;
        return Objects.equals(name, other.name)
                && Objects.equals(attributes, other.attributes)
                && Objects.equals(children, other.children);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(name + "(\n");
        for (Attribute attribute : attributes) {
            builder.append(attribute.toString()).append("\n");
        }
        for (Child child : children) {
            builder.append(child.toString()).append("\n");
        }
        builder.append(")");

        return builder.toString();
    }
}
