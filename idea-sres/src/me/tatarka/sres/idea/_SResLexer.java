/* The following code was generated by JFlex 1.4.3 on 3/2/14 4:16 PM */

package me.tatarka.sres.idea;
import com.intellij.lexer.*;
import com.intellij.psi.tree.IElementType;
import static me.tatarka.sres.idea.psi.SResTypes.*;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 3/2/14 4:16 PM from the specification file
 * <tt>/home/evan/android/sresProject/idea-sres/src/me/tatarka/sres/idea/_SResLexer.flex</tt>
 */
public class _SResLexer implements FlexLexer {
  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\1\1\2\1\0\1\1\1\1\22\0\1\1\1\0\1\22"+
    "\1\0\1\16\2\0\1\20\3\0\1\24\2\0\1\5\1\3\12\4"+
    "\1\27\5\0\1\23\32\25\1\0\1\21\2\0\1\26\1\0\3\25"+
    "\1\10\4\25\1\11\3\25\1\14\1\13\1\25\1\6\2\25\1\12"+
    "\1\7\2\25\1\17\1\7\2\25\57\0\1\15\12\0\1\15\4\0"+
    "\1\15\5\0\27\15\1\0\37\15\1\0\u01ca\15\4\0\14\15\16\0"+
    "\5\15\7\0\1\15\1\0\1\15\201\0\5\15\1\0\2\15\2\0"+
    "\4\15\10\0\1\15\1\0\3\15\1\0\1\15\1\0\24\15\1\0"+
    "\123\15\1\0\213\15\10\0\236\15\11\0\46\15\2\0\1\15\7\0"+
    "\47\15\110\0\33\15\5\0\3\15\55\0\53\15\43\0\2\15\1\0"+
    "\143\15\1\0\1\15\17\0\2\15\7\0\2\15\12\0\3\15\2\0"+
    "\1\15\20\0\1\15\1\0\36\15\35\0\131\15\13\0\1\15\30\0"+
    "\41\15\11\0\2\15\4\0\1\15\5\0\26\15\4\0\1\15\11\0"+
    "\1\15\3\0\1\15\27\0\31\15\107\0\1\15\1\0\13\15\127\0"+
    "\66\15\3\0\1\15\22\0\1\15\7\0\12\15\17\0\7\15\1\0"+
    "\7\15\5\0\10\15\2\0\2\15\2\0\26\15\1\0\7\15\1\0"+
    "\1\15\3\0\4\15\3\0\1\15\20\0\1\15\15\0\2\15\1\0"+
    "\3\15\16\0\2\15\23\0\6\15\4\0\2\15\2\0\26\15\1\0"+
    "\7\15\1\0\2\15\1\0\2\15\1\0\2\15\37\0\4\15\1\0"+
    "\1\15\23\0\3\15\20\0\11\15\1\0\3\15\1\0\26\15\1\0"+
    "\7\15\1\0\2\15\1\0\5\15\3\0\1\15\22\0\1\15\17\0"+
    "\2\15\43\0\10\15\2\0\2\15\2\0\26\15\1\0\7\15\1\0"+
    "\2\15\1\0\5\15\3\0\1\15\36\0\2\15\1\0\3\15\17\0"+
    "\1\15\21\0\1\15\1\0\6\15\3\0\3\15\1\0\4\15\3\0"+
    "\2\15\1\0\1\15\1\0\2\15\3\0\2\15\3\0\3\15\3\0"+
    "\14\15\26\0\1\15\64\0\10\15\1\0\3\15\1\0\27\15\1\0"+
    "\12\15\1\0\5\15\3\0\1\15\32\0\2\15\6\0\2\15\43\0"+
    "\10\15\1\0\3\15\1\0\27\15\1\0\12\15\1\0\5\15\3\0"+
    "\1\15\40\0\1\15\1\0\2\15\17\0\2\15\22\0\10\15\1\0"+
    "\3\15\1\0\51\15\2\0\1\15\20\0\1\15\21\0\2\15\30\0"+
    "\6\15\5\0\22\15\3\0\30\15\1\0\11\15\1\0\1\15\2\0"+
    "\7\15\72\0\60\15\1\0\2\15\14\0\7\15\72\0\2\15\1\0"+
    "\1\15\2\0\2\15\1\0\1\15\2\0\1\15\6\0\4\15\1\0"+
    "\7\15\1\0\3\15\1\0\1\15\1\0\1\15\2\0\2\15\1\0"+
    "\4\15\1\0\2\15\11\0\1\15\2\0\5\15\1\0\1\15\25\0"+
    "\4\15\40\0\1\15\77\0\10\15\1\0\44\15\33\0\5\15\163\0"+
    "\53\15\24\0\1\15\20\0\6\15\4\0\4\15\3\0\1\15\3\0"+
    "\2\15\7\0\3\15\4\0\15\15\14\0\1\15\21\0\46\15\1\0"+
    "\1\15\5\0\1\15\2\0\53\15\1\0\u014d\15\1\0\4\15\2\0"+
    "\7\15\1\0\1\15\1\0\4\15\2\0\51\15\1\0\4\15\2\0"+
    "\41\15\1\0\4\15\2\0\7\15\1\0\1\15\1\0\4\15\2\0"+
    "\17\15\1\0\71\15\1\0\4\15\2\0\103\15\45\0\20\15\20\0"+
    "\125\15\14\0\u026c\15\2\0\21\15\1\0\32\15\5\0\113\15\25\0"+
    "\15\15\1\0\4\15\16\0\22\15\16\0\22\15\16\0\15\15\1\0"+
    "\3\15\17\0\64\15\43\0\1\15\4\0\1\15\103\0\130\15\10\0"+
    "\51\15\1\0\1\15\5\0\106\15\12\0\35\15\63\0\36\15\2\0"+
    "\5\15\13\0\54\15\25\0\7\15\70\0\27\15\11\0\65\15\122\0"+
    "\1\15\135\0\57\15\21\0\7\15\67\0\36\15\15\0\2\15\12\0"+
    "\54\15\32\0\44\15\51\0\3\15\12\0\44\15\153\0\4\15\1\0"+
    "\4\15\3\0\2\15\11\0\300\15\100\0\u0116\15\2\0\6\15\2\0"+
    "\46\15\2\0\6\15\2\0\10\15\1\0\1\15\1\0\1\15\1\0"+
    "\1\15\1\0\37\15\2\0\65\15\1\0\7\15\1\0\1\15\3\0"+
    "\3\15\1\0\7\15\3\0\4\15\2\0\6\15\4\0\15\15\5\0"+
    "\3\15\1\0\7\15\164\0\1\15\15\0\1\15\20\0\15\15\145\0"+
    "\1\15\4\0\1\15\2\0\12\15\1\0\1\15\3\0\5\15\6\0"+
    "\1\15\1\0\1\15\1\0\1\15\1\0\4\15\1\0\13\15\2\0"+
    "\4\15\5\0\5\15\4\0\1\15\64\0\2\15\u0a7b\0\57\15\1\0"+
    "\57\15\1\0\205\15\6\0\4\15\3\0\2\15\14\0\46\15\1\0"+
    "\1\15\5\0\1\15\2\0\70\15\7\0\1\15\20\0\27\15\11\0"+
    "\7\15\1\0\7\15\1\0\7\15\1\0\7\15\1\0\7\15\1\0"+
    "\7\15\1\0\7\15\1\0\7\15\120\0\1\15\u01d5\0\2\15\52\0"+
    "\5\15\5\0\2\15\4\0\126\15\6\0\3\15\1\0\132\15\1\0"+
    "\4\15\5\0\51\15\3\0\136\15\21\0\33\15\65\0\20\15\u0200\0"+
    "\u19b6\15\112\0\u51cd\15\63\0\u048d\15\103\0\56\15\2\0\u010d\15\3\0"+
    "\20\15\12\0\2\15\24\0\57\15\20\0\31\15\10\0\106\15\61\0"+
    "\11\15\2\0\147\15\2\0\4\15\1\0\4\15\14\0\13\15\115\0"+
    "\12\15\1\0\3\15\1\0\4\15\1\0\27\15\35\0\64\15\16\0"+
    "\62\15\76\0\6\15\3\0\1\15\16\0\34\15\12\0\27\15\31\0"+
    "\35\15\7\0\57\15\34\0\1\15\60\0\51\15\27\0\3\15\1\0"+
    "\10\15\24\0\27\15\3\0\1\15\5\0\60\15\1\0\1\15\3\0"+
    "\2\15\2\0\5\15\2\0\1\15\1\0\1\15\30\0\3\15\2\0"+
    "\13\15\7\0\3\15\14\0\6\15\2\0\6\15\2\0\6\15\11\0"+
    "\7\15\1\0\7\15\221\0\43\15\35\0\u2ba4\15\14\0\27\15\4\0"+
    "\61\15\u2104\0\u016e\15\2\0\152\15\46\0\7\15\14\0\5\15\5\0"+
    "\1\15\1\0\12\15\1\0\15\15\1\0\5\15\1\0\1\15\1\0"+
    "\2\15\1\0\2\15\1\0\154\15\41\0\u016b\15\22\0\100\15\2\0"+
    "\66\15\50\0\14\15\164\0\5\15\1\0\207\15\44\0\32\15\6\0"+
    "\32\15\13\0\131\15\3\0\6\15\2\0\6\15\2\0\6\15\2\0"+
    "\3\15\43\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\1\1\1\3\1\4\3\1\1\5"+
    "\1\3\6\0\1\6\5\0\1\3\2\0\1\7\1\0";

  private static int [] zzUnpackAction() {
    int [] result = new int[28];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\30\0\60\0\110\0\140\0\170\0\220\0\250"+
    "\0\300\0\330\0\360\0\u0108\0\u0120\0\u0138\0\u0150\0\u0168"+
    "\0\220\0\30\0\u0180\0\250\0\u0198\0\u01b0\0\u01c8\0\30"+
    "\0\u01e0\0\u01f8\0\u0210\0\u0228";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[28];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\2\3\1\4\1\5\1\2\10\6\1\2\1\6"+
    "\1\7\1\2\1\10\1\11\1\2\1\6\2\2\31\0"+
    "\2\3\30\0\1\12\30\0\1\5\1\13\1\14\1\0"+
    "\1\15\1\16\1\17\1\0\1\20\20\0\1\6\10\0"+
    "\2\6\6\0\1\6\1\0\20\21\1\22\1\23\6\21"+
    "\21\24\1\25\1\22\5\24\6\0\10\26\1\0\1\26"+
    "\4\0\1\27\1\26\2\0\2\12\1\0\25\12\4\0"+
    "\1\13\1\0\1\14\1\0\1\15\1\16\1\17\1\0"+
    "\1\20\22\0\1\30\26\0\1\30\2\0\1\17\31\0"+
    "\1\30\22\0\1\30\35\0\1\30\13\0\2\21\1\0"+
    "\25\21\2\24\1\0\25\24\3\0\1\31\1\26\1\0"+
    "\7\26\2\0\1\26\5\0\2\26\1\32\6\0\10\26"+
    "\1\0\1\26\5\0\1\26\10\0\10\33\1\0\1\33"+
    "\5\0\1\33\10\0\10\34\1\0\1\34\5\0\1\34"+
    "\6\0\1\33\1\0\7\33\2\0\1\33\5\0\2\33"+
    "\4\0\1\31\1\34\1\0\7\34\2\0\1\34\5\0"+
    "\2\34\1\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[576];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;
  private static final char[] EMPTY_BUFFER = new char[0];
  private static final int YYEOF = -1;
  private static java.io.Reader zzReader = null; // Fake

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\11\1\6\0\1\11\5\0\1\11\2\0"+
    "\1\1\1\0";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[28];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private CharSequence zzBuffer = "";

  /** this buffer may contains the current text array to be matched when it is cheap to acquire it */
  private char[] zzBufferArray;

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the textposition at the last state to be included in yytext */
  private int zzPushbackPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /**
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /* user code: */
  public _SResLexer() {
    this((java.io.Reader)null);
  }


  public _SResLexer(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  public _SResLexer(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 1564) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }

  public final int getTokenStart(){
    return zzStartRead;
  }

  public final int getTokenEnd(){
    return getTokenStart() + yylength();
  }

  public void reset(CharSequence buffer, int start, int end,int initialState){
    zzBuffer = buffer;
    zzBufferArray = com.intellij.util.text.CharArrayUtil.fromSequenceWithoutCopying(buffer);
    zzCurrentPos = zzMarkedPos = zzStartRead = start;
    zzPushbackPos = 0;
    zzAtEOF  = false;
    zzAtBOL = true;
    zzEndRead = end;
    yybegin(initialState);
  }

  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   *
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {
    return true;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final CharSequence yytext() {
    return zzBuffer.subSequence(zzStartRead, zzMarkedPos);
  }


  /**
   * Returns the character at position <tt>pos</tt> from the
   * matched text.
   *
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch.
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBufferArray != null ? zzBufferArray[zzStartRead+pos]:zzBuffer.charAt(zzStartRead+pos);
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of
   * yypushback(int) and a match-all fallback rule) this method
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public IElementType advance() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    CharSequence zzBufferL = zzBuffer;
    char[] zzBufferArrayL = zzBufferArray;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      zzState = ZZ_LEXSTATE[zzLexicalState];


      zzForAction: {
        while (true) {

          if (zzCurrentPosL < zzEndReadL)
            zzInput = (zzBufferArrayL != null ? zzBufferArrayL[zzCurrentPosL++] : zzBufferL.charAt(zzCurrentPosL++));
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = (zzBufferArrayL != null ? zzBufferArrayL[zzCurrentPosL++] : zzBufferL.charAt(zzCurrentPosL++));
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 3: 
          { return NUMBER;
          }
        case 8: break;
        case 1: 
          { return com.intellij.psi.TokenType.BAD_CHARACTER;
          }
        case 9: break;
        case 7: 
          { return REF;
          }
        case 10: break;
        case 5: 
          { return COMMENT;
          }
        case 11: break;
        case 4: 
          { return ID;
          }
        case 12: break;
        case 2: 
          { return com.intellij.psi.TokenType.WHITE_SPACE;
          }
        case 13: break;
        case 6: 
          { return STRING;
          }
        case 14: break;
        default:
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            return null;
          }
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
