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

public class SResFunctionImpl extends ASTWrapperPsiElement implements SResFunction {

  public SResFunctionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SResVisitor) ((SResVisitor)visitor).visitFunction(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public SResArguments getArguments() {
    return findChildByClass(SResArguments.class);
  }

  @Override
  @Nullable
  public SResBlock getBlock() {
    return findChildByClass(SResBlock.class);
  }

  @Override
  @NotNull
  public PsiElement getId() {
    return findNotNullChildByType(ID);
  }

}
