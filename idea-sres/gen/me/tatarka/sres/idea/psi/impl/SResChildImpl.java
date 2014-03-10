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

public class SResChildImpl extends ASTWrapperPsiElement implements SResChild {

  public SResChildImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SResVisitor) ((SResVisitor)visitor).visitChild(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public SResAttribute getAttribute() {
    return findChildByClass(SResAttribute.class);
  }

  @Override
  @Nullable
  public SResFunction getFunction() {
    return findChildByClass(SResFunction.class);
  }

  @Override
  @Nullable
  public PsiElement getRef() {
    return findChildByType(REF);
  }

}
