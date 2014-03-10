// This is a generated file. Not intended for manual editing.
package me.tatarka.sres.idea.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import com.intellij.openapi.diagnostic.Logger;
import static me.tatarka.sres.idea.psi.SResTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class SResParser implements PsiParser {

  public static final Logger LOG_ = Logger.getInstance("me.tatarka.sres.idea.parser.SResParser");

  public ASTNode parse(IElementType root_, PsiBuilder builder_) {
    boolean result_;
    builder_ = adapt_builder_(root_, builder_, this, null);
    Marker marker_ = enter_section_(builder_, 0, _COLLAPSE_, null);
    if (root_ == ARGUMENTS) {
      result_ = arguments(builder_, 0);
    }
    else if (root_ == ATOM) {
      result_ = atom(builder_, 0);
    }
    else if (root_ == ATTRIBUTE) {
      result_ = attribute(builder_, 0);
    }
    else if (root_ == BLOCK) {
      result_ = block(builder_, 0);
    }
    else if (root_ == CHILD) {
      result_ = child(builder_, 0);
    }
    else if (root_ == FUNCTION) {
      result_ = function(builder_, 0);
    }
    else {
      result_ = parse_root_(root_, builder_, 0);
    }
    exit_section_(builder_, 0, marker_, root_, result_, true, TRUE_CONDITION);
    return builder_.getTreeBuilt();
  }

  protected boolean parse_root_(final IElementType root_, final PsiBuilder builder_, final int level_) {
    return root(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // '(' atom? (',' atom)* ')'
  public static boolean arguments(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arguments")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<arguments>");
    result_ = consumeToken(builder_, "(");
    result_ = result_ && arguments_1(builder_, level_ + 1);
    result_ = result_ && arguments_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, ")");
    exit_section_(builder_, level_, marker_, ARGUMENTS, result_, false, null);
    return result_;
  }

  // atom?
  private static boolean arguments_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arguments_1")) return false;
    atom(builder_, level_ + 1);
    return true;
  }

  // (',' atom)*
  private static boolean arguments_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arguments_2")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!arguments_2_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "arguments_2", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // ',' atom
  private static boolean arguments_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arguments_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ",");
    result_ = result_ && atom(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // id | ref | string | number
  public static boolean atom(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "atom")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<atom>");
    result_ = consumeToken(builder_, ID);
    if (!result_) result_ = consumeToken(builder_, REF);
    if (!result_) result_ = consumeToken(builder_, STRING);
    if (!result_) result_ = consumeToken(builder_, NUMBER);
    exit_section_(builder_, level_, marker_, ATOM, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // id '=' atom
  public static boolean attribute(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute")) return false;
    if (!nextTokenIs(builder_, ID)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ID);
    result_ = result_ && consumeToken(builder_, "=");
    result_ = result_ && atom(builder_, level_ + 1);
    exit_section_(builder_, marker_, ATTRIBUTE, result_);
    return result_;
  }

  /* ********************************************************** */
  // '{' child* '}'
  public static boolean block(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "block")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<block>");
    result_ = consumeToken(builder_, "{");
    result_ = result_ && block_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, "}");
    exit_section_(builder_, level_, marker_, BLOCK, result_, false, null);
    return result_;
  }

  // child*
  private static boolean block_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "block_1")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!child(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "block_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  /* ********************************************************** */
  // ref | attribute | function
  public static boolean child(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "child")) return false;
    if (!nextTokenIs(builder_, "<child>", ID, REF)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<child>");
    result_ = consumeToken(builder_, REF);
    if (!result_) result_ = attribute(builder_, level_ + 1);
    if (!result_) result_ = function(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, CHILD, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // id arguments? block?
  public static boolean function(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function")) return false;
    if (!nextTokenIs(builder_, ID)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ID);
    result_ = result_ && function_1(builder_, level_ + 1);
    result_ = result_ && function_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, FUNCTION, result_);
    return result_;
  }

  // arguments?
  private static boolean function_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_1")) return false;
    arguments(builder_, level_ + 1);
    return true;
  }

  // block?
  private static boolean function_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_2")) return false;
    block(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // (id ':')? id arguments? block?
  static boolean root(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "root")) return false;
    if (!nextTokenIs(builder_, ID)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = root_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, ID);
    result_ = result_ && root_2(builder_, level_ + 1);
    result_ = result_ && root_3(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (id ':')?
  private static boolean root_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "root_0")) return false;
    root_0_0(builder_, level_ + 1);
    return true;
  }

  // id ':'
  private static boolean root_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "root_0_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ID);
    result_ = result_ && consumeToken(builder_, ":");
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // arguments?
  private static boolean root_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "root_2")) return false;
    arguments(builder_, level_ + 1);
    return true;
  }

  // block?
  private static boolean root_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "root_3")) return false;
    block(builder_, level_ + 1);
    return true;
  }

}
