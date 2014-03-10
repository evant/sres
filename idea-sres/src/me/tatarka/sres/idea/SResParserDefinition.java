package me.tatarka.sres.idea;

import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import me.tatarka.sres.idea.parser.SResParser;
import me.tatarka.sres.idea.psi.SResTypes;
import org.jetbrains.annotations.NotNull;

/**
 * Created by evan on 3/2/14.
 */
public class SResParserDefinition implements ParserDefinition {
    public static final TokenSet WHITE_SPACE = TokenSet.create(TokenType.WHITE_SPACE);
    public static final TokenSet COMMENTS = TokenSet.create(SResTypes.COMMENT);
    public static final TokenSet STRINGS = TokenSet.create(SResTypes.STRING);

    public static final IFileElementType FILE =
            new IFileElementType(Language.findInstance (SResLanguage.class));

    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new SResLexer();
    }

    @Override
    public PsiParser createParser(Project project) {
        return new SResParser();
    }

    @Override
    public IFileElementType getFileNodeType() {
        return FILE;
    }

    @NotNull
    @Override
    public TokenSet getWhitespaceTokens() {
        return WHITE_SPACE;
    }

    @NotNull
    @Override
    public TokenSet getCommentTokens() {
        return COMMENTS;
    }

    @NotNull
    @Override
    public TokenSet getStringLiteralElements() {
        return STRINGS;
    }

    @NotNull
    @Override
    public PsiElement createElement(ASTNode node) {
        return SResTypes.Factory.createElement(node);
    }

    @Override
    public PsiFile createFile(FileViewProvider fileViewProvider) {
        return new SResFile(fileViewProvider);
    }

    @Override
    public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode node, ASTNode node2) {
        return SpaceRequirements.MAY;
    }
}
