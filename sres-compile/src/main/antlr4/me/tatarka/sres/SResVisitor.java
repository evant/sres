// Generated from /home/evan/android/sresProject/sres-compile/src/main/antlr4/SRes.g4 by ANTLR 4.2

package me.tatarka.sres;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SResParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SResVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SResParser#root}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoot(@NotNull SResParser.RootContext ctx);

	/**
	 * Visit a parse tree produced by {@link SResParser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(@NotNull SResParser.FunctionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SResParser#arguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArguments(@NotNull SResParser.ArgumentsContext ctx);

	/**
	 * Visit a parse tree produced by {@link SResParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(@NotNull SResParser.BlockContext ctx);

	/**
	 * Visit a parse tree produced by {@link SResParser#attribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttribute(@NotNull SResParser.AttributeContext ctx);

	/**
	 * Visit a parse tree produced by {@link SResParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom(@NotNull SResParser.AtomContext ctx);

	/**
	 * Visit a parse tree produced by {@link SResParser#child}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChild(@NotNull SResParser.ChildContext ctx);
}