// This is a generated file. Not intended for manual editing.
package me.tatarka.sres.idea.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import me.tatarka.sres.idea.psi.impl.*;

public interface SResTypes {

  IElementType ARGUMENTS = new SResElementType("ARGUMENTS");
  IElementType ATOM = new SResElementType("ATOM");
  IElementType ATTRIBUTE = new SResElementType("ATTRIBUTE");
  IElementType BLOCK = new SResElementType("BLOCK");
  IElementType CHILD = new SResElementType("CHILD");
  IElementType FUNCTION = new SResElementType("FUNCTION");

  IElementType COMMENT = new SResTokenType("comment");
  IElementType ID = new SResTokenType("id");
  IElementType NUMBER = new SResTokenType("number");
  IElementType REF = new SResTokenType("ref");
  IElementType STRING = new SResTokenType("string");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
       if (type == ARGUMENTS) {
        return new SResArgumentsImpl(node);
      }
      else if (type == ATOM) {
        return new SResAtomImpl(node);
      }
      else if (type == ATTRIBUTE) {
        return new SResAttributeImpl(node);
      }
      else if (type == BLOCK) {
        return new SResBlockImpl(node);
      }
      else if (type == CHILD) {
        return new SResChildImpl(node);
      }
      else if (type == FUNCTION) {
        return new SResFunctionImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
