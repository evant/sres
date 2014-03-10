package me.tatarka.sres.impl;

import me.tatarka.sres.SResBaseVisitor;
import me.tatarka.sres.SResParser;
import me.tatarka.sres.ast.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.misc.Nullable;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by evan on 2/27/14.
 */
class SResVisitor extends SResBaseVisitor {
    @Override
    public RootView visitRoot(@NotNull SResParser.RootContext ctx) {
        List<TerminalNode> identifiers = ctx.Identifier();

        String name; String subclass = null;
        if (identifiers.size() == 1) {
            name = identifiers.get(0).getText();
        } else {
            subclass = identifiers.get(0).getText();
            name = identifiers.get(1).getText();
        }

        Block block = joinBlock(View.class, ctx.block(), ctx.arguments());
        return new RootView(name, subclass, block.attributes, block.children);
    }

    @Override
    public Child visitFunction(@NotNull SResParser.FunctionContext ctx) {
        String name = ctx.Identifier().getText();
        Class<? extends Child> nodeType = getNodeType(name);
        Block block = joinBlock(nodeType, ctx.block(), ctx.arguments());

        if (nodeType.equals(Include.class)) {
            return new Include(block.attributes, block.children);
        } else {
            return new View(name, block.attributes, block.children);
        }
    }

    private Block joinBlock(@NotNull Class<? extends Child> nodeType,
                            @Nullable SResParser.BlockContext  blockNode,
                            @Nullable SResParser.ArgumentsContext argumentsNode) {

        Block block;
        if (blockNode != null) {
            block = visitBlock(blockNode);
        } else {
            block = new Block();
        }

        if (argumentsNode != null) {
            List<String> arguments = visitArguments(argumentsNode);

            if (nodeType.equals(Include.class)) {
                addIncludeArguments(block.attributes, arguments);
            } else {
                addViewArguments(block.attributes, arguments);
            }
        }

        return block;
    }

    private void addIncludeArguments(Set<Attribute> attributes, List<String> arguments) {
        if (arguments.size() > 0) {
            attributes.add(new Attribute(Attribute.LAYOUT, arguments.get(0), Attribute.RAW));
        }

        String arg1 = arguments.size() > 1 ? arguments.get(1) : null;
        String arg2 = arguments.size() > 2 ? arguments.get(2) : null;
        addDimensionArguments(attributes, arg1, arg2);
    }

    private void addViewArguments(Set<Attribute> attributes, List<String> arguments) {
        String arg1 = arguments.size() > 0 ? arguments.get(0) : null;
        String arg2 = arguments.size() > 1 ? arguments.get(1) : null;
        addDimensionArguments(attributes, arg1, arg2);
    }

    private void addDimensionArguments(Set<Attribute> attributes, String arg1, String arg2) {
        if (arg1 == null && arg2 == null) return;
        if (arg2 == null) arg2 = arg1;

        attributes.add(new Attribute(Attribute.LAYOUT_WIDTH,  arg1));
        attributes.add(new Attribute(Attribute.LAYOUT_HEIGHT, arg2));
    }

    private Class<? extends Child> getNodeType(@NotNull String name) {
        if (name.equals(Include.INCLUDE)) {
            return Include.class;
        } else {
            return View.class;
        }
    }

    @Override
    public List<String> visitArguments(@NotNull SResParser.ArgumentsContext ctx) {
        List<String> arguments = new ArrayList<>();
        for (SResParser.AtomContext atomNode : ctx.atom()) {
            arguments.add(visitAtom(atomNode).toString());
        }
        return arguments;
    }

    @Override
    public Block visitBlock(@NotNull SResParser.BlockContext ctx) {
        Block block = new Block();

        for (SResParser.ChildContext child : ctx.child()) {
            TerminalNode idNode = child.Reference();
            if (idNode != null) {
                block.attributes.add(new Attribute(Attribute.ID, idNode.getText()));
                continue;
            }

            SResParser.AttributeContext attributeNode = child.attribute();
            if (attributeNode != null) {
                block.attributes.add(visitAttribute(attributeNode));
                continue;
            }

            SResParser.FunctionContext childNode = child.function();
            if (childNode != null) {
                block.children.add(visitFunction(childNode));
            }
        }

        return block;
    }

    @Override
    public Attribute visitAttribute(@NotNull SResParser.AttributeContext ctx) {
        String name = ctx.Identifier().getText();
        Object value = visitAtom(ctx.atom());
        if (value instanceof Binding) {
            return new Attribute(name, (Binding) value);
        } else {
            return new Attribute(name, value.toString());
        }
    }

    @Override
    public Object visitAtom(@NotNull SResParser.AtomContext ctx) {
        // Check for binding function
        SResParser.FunctionContext bindingFunction = ctx.function();
        if (bindingFunction != null) {
            return visitBindingFunction(bindingFunction);
        }

        // Strip quotes from string
        if (ctx.String() != null) {
            String string = ctx.getText();
            return string.substring(1, string.length() - 1);
        }

        return ctx.getText();
    }

    private Binding visitBindingFunction(@NotNull SResParser.FunctionContext ctx) {
        if (ctx.Identifier().getText().equals("bind")) {
            List<String> arguments = visitArguments(ctx.arguments());
            if (arguments.size() == 1) {
                String value = arguments.get(0);
                return newBinding(Binding.Type.PRIMITIVE, value);
            } else if (arguments.size() == 2) {
                String type = arguments.get(0);
                String value = arguments.get(1);
                return newBinding(type, value);
            }
        }

        // Unknown function, just treat as literal
        return Binding.literal(ctx.getText());
    }

    private Binding newBinding(String type, String value) {
        if (type.equals("observe")) return newBinding(Binding.Type.OBSERVABLE, value);
        if (type.equals("listen")) return newBinding(Binding.Type.LISTENER, value);
        // Unknown binding type, ignore
        return newBinding(Binding.Type.PRIMITIVE, value);
    }

    private Binding newBinding(Binding.Type type, String value) {
        if (value.endsWith("()")) {
            return Binding.method(type, value.replace("()", ""));
        } else {
            return Binding.field(type, value);
        }
    }

    private static class Block {
        public final Set<Attribute> attributes = new LinkedHashSet<>();
        public final List<Child> children = new ArrayList<>();
    }
}
