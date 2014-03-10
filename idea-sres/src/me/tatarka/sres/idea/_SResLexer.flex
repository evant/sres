package me.tatarka.sres.idea;
import com.intellij.lexer.*;
import com.intellij.psi.tree.IElementType;
import static me.tatarka.sres.idea.psi.SResTypes.*;

%%

%{
  public _SResLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class _SResLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

EOL="\r"|"\n"|"\r\n"
LINE_WS=[\ \t\f]
WHITE_SPACE=({LINE_WS}|{EOL})+

COMMENT="//".*
NUMBER=[0-9]+(\.[0-9]*)?(px|dp|dip|pt|sp|in|mm)?
ID=[:letter:][\w\._$]*
STRING=('([^'\\]|\\.)*'|\"([^\"\\]|\\.)*\")
REF=@\+?([:letter:][a-zA-Z_0-9]*:)?([:letter:][a-zA-Z_0-9]*)"/"([:letter:][a-zA-Z_0-9]*)

%%
<YYINITIAL> {
  {WHITE_SPACE}      { return com.intellij.psi.TokenType.WHITE_SPACE; }


  {COMMENT}          { return COMMENT; }
  {NUMBER}           { return NUMBER; }
  {ID}               { return ID; }
  {STRING}           { return STRING; }
  {REF}              { return REF; }

  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}
