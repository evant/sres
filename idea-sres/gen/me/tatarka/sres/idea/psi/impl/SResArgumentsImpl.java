// This is a generated file. Not intended for manual editing.
package me.tatarka.sres.idea.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static me.tatarka.sres.idea.psi.SResTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import me.tatarka.sres.idea.psi.*;

public class SResArgumentsImpl extends ASTWrapperPsiElement implements SResArguments {

  public SResArgumentsImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SResVisitor) ((SResVisitor)visitor).visitArguments(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<SResAtom> getAtomList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, SResAtom.class);
  }

}
