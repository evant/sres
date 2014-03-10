// Generated from /home/evan/android/sresProject/sres-compile/src/main/antlr4/SRes.g4 by ANTLR 4.2

package me.tatarka.sres;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SResLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__6=1, T__5=2, T__4=3, T__3=4, T__2=5, T__1=6, T__0=7, Identifier=8, 
		String=9, Reference=10, Dimension=11, Number=12, WS=13, COMMENT=14, LINE_COMMENT=15;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'('", "')'", "'{'", "','", "'<'", "'='", "'}'", "Identifier", "String", 
		"Reference", "Dimension", "Number", "WS", "COMMENT", "LINE_COMMENT"
	};
	public static final String[] ruleNames = {
		"T__6", "T__5", "T__4", "T__3", "T__2", "T__1", "T__0", "Identifier", 
		"String", "Reference", "Dimension", "Number", "Unit", "Digits", "Digit", 
		"StringCharacters", "StringCharacter", "EscapeSequence", "OctalEscape", 
		"OctalDigit", "UnicodeEscape", "HexDigit", "ZeroToThree", "JavaLetter", 
		"JavaLetterOrDigit", "WS", "COMMENT", "LINE_COMMENT"
	};


	public SResLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "SRes.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 23: return JavaLetter_sempred((RuleContext)_localctx, predIndex);

		case 24: return JavaLetterOrDigit_sempred((RuleContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean JavaLetterOrDigit_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2: return Character.isJavaIdentifierPart(_input.LA(-1));

		case 3: return Character.isJavaIdentifierPart(Character.toCodePoint((char)_input.LA(-2), (char)_input.LA(-1)));
		}
		return true;
	}
	private boolean JavaLetter_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return Character.isJavaIdentifierStart(_input.LA(-1));

		case 1: return Character.isJavaIdentifierStart(Character.toCodePoint((char)_input.LA(-2), (char)_input.LA(-1)));
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\21\u00e1\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\3\2\3\2\3\3\3\3\3\4\3\4"+
		"\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\7\tL\n\t\f\t\16\tO\13\t\3\n\3"+
		"\n\5\nS\n\n\3\n\3\n\3\13\3\13\5\13Y\n\13\3\13\3\13\3\13\5\13^\n\13\3\13"+
		"\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\5\rj\n\r\3\r\3\r\3\r\5\ro\n\r"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\5\16\u0080\n\16\3\17\6\17\u0083\n\17\r\17\16\17\u0084\3\20\3\20"+
		"\3\21\6\21\u008a\n\21\r\21\16\21\u008b\3\22\3\22\5\22\u0090\n\22\3\23"+
		"\3\23\3\23\3\23\5\23\u0096\n\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\5\24\u00a3\n\24\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\5\31\u00b8\n\31"+
		"\3\32\3\32\3\32\3\32\3\32\3\32\5\32\u00c0\n\32\3\33\6\33\u00c3\n\33\r"+
		"\33\16\33\u00c4\3\33\3\33\3\34\3\34\3\34\3\34\7\34\u00cd\n\34\f\34\16"+
		"\34\u00d0\13\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\7\35\u00db"+
		"\n\35\f\35\16\35\u00de\13\35\3\35\3\35\3\u00ce\2\36\3\3\5\4\7\5\t\6\13"+
		"\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\2\35\2\37\2!\2#\2%\2\'\2)\2"+
		"+\2-\2/\2\61\2\63\2\65\17\67\209\21\3\2\17\3\2\62;\4\2$$^^\n\2$$))^^d"+
		"dhhppttvv\3\2\629\5\2\62;CHch\3\2\62\65\6\2&&C\\aac|\4\2\2\u0101\ud802"+
		"\udc01\3\2\ud802\udc01\3\2\udc02\ue001\t\2&&\60\60\62<C\\^^aac|\5\2\13"+
		"\f\16\17\"\"\4\2\f\f\17\17\u00ee\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2"+
		"\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2"+
		"\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2"+
		"\29\3\2\2\2\3;\3\2\2\2\5=\3\2\2\2\7?\3\2\2\2\tA\3\2\2\2\13C\3\2\2\2\r"+
		"E\3\2\2\2\17G\3\2\2\2\21I\3\2\2\2\23P\3\2\2\2\25V\3\2\2\2\27c\3\2\2\2"+
		"\31n\3\2\2\2\33\177\3\2\2\2\35\u0082\3\2\2\2\37\u0086\3\2\2\2!\u0089\3"+
		"\2\2\2#\u008f\3\2\2\2%\u0095\3\2\2\2\'\u00a2\3\2\2\2)\u00a4\3\2\2\2+\u00a6"+
		"\3\2\2\2-\u00ad\3\2\2\2/\u00af\3\2\2\2\61\u00b7\3\2\2\2\63\u00bf\3\2\2"+
		"\2\65\u00c2\3\2\2\2\67\u00c8\3\2\2\29\u00d6\3\2\2\2;<\7*\2\2<\4\3\2\2"+
		"\2=>\7+\2\2>\6\3\2\2\2?@\7}\2\2@\b\3\2\2\2AB\7.\2\2B\n\3\2\2\2CD\7>\2"+
		"\2D\f\3\2\2\2EF\7?\2\2F\16\3\2\2\2GH\7\177\2\2H\20\3\2\2\2IM\5\61\31\2"+
		"JL\5\63\32\2KJ\3\2\2\2LO\3\2\2\2MK\3\2\2\2MN\3\2\2\2N\22\3\2\2\2OM\3\2"+
		"\2\2PR\7$\2\2QS\5!\21\2RQ\3\2\2\2RS\3\2\2\2ST\3\2\2\2TU\7$\2\2U\24\3\2"+
		"\2\2VX\7B\2\2WY\7-\2\2XW\3\2\2\2XY\3\2\2\2Y]\3\2\2\2Z[\5\21\t\2[\\\7<"+
		"\2\2\\^\3\2\2\2]Z\3\2\2\2]^\3\2\2\2^_\3\2\2\2_`\5\21\t\2`a\7\61\2\2ab"+
		"\5\21\t\2b\26\3\2\2\2cd\5\31\r\2de\5\33\16\2e\30\3\2\2\2fg\5\35\17\2g"+
		"i\7\60\2\2hj\5\35\17\2ih\3\2\2\2ij\3\2\2\2jo\3\2\2\2kl\7\60\2\2lo\5\35"+
		"\17\2mo\5\35\17\2nf\3\2\2\2nk\3\2\2\2nm\3\2\2\2o\32\3\2\2\2pq\7r\2\2q"+
		"\u0080\7z\2\2rs\7f\2\2s\u0080\7r\2\2tu\7f\2\2uv\7k\2\2v\u0080\7r\2\2w"+
		"x\7r\2\2x\u0080\7v\2\2yz\7u\2\2z\u0080\7r\2\2{|\7k\2\2|\u0080\7p\2\2}"+
		"~\7o\2\2~\u0080\7o\2\2\177p\3\2\2\2\177r\3\2\2\2\177t\3\2\2\2\177w\3\2"+
		"\2\2\177y\3\2\2\2\177{\3\2\2\2\177}\3\2\2\2\u0080\34\3\2\2\2\u0081\u0083"+
		"\5\37\20\2\u0082\u0081\3\2\2\2\u0083\u0084\3\2\2\2\u0084\u0082\3\2\2\2"+
		"\u0084\u0085\3\2\2\2\u0085\36\3\2\2\2\u0086\u0087\t\2\2\2\u0087 \3\2\2"+
		"\2\u0088\u008a\5#\22\2\u0089\u0088\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u0089"+
		"\3\2\2\2\u008b\u008c\3\2\2\2\u008c\"\3\2\2\2\u008d\u0090\n\3\2\2\u008e"+
		"\u0090\5%\23\2\u008f\u008d\3\2\2\2\u008f\u008e\3\2\2\2\u0090$\3\2\2\2"+
		"\u0091\u0092\7^\2\2\u0092\u0096\t\4\2\2\u0093\u0096\5\'\24\2\u0094\u0096"+
		"\5+\26\2\u0095\u0091\3\2\2\2\u0095\u0093\3\2\2\2\u0095\u0094\3\2\2\2\u0096"+
		"&\3\2\2\2\u0097\u0098\7^\2\2\u0098\u00a3\5)\25\2\u0099\u009a\7^\2\2\u009a"+
		"\u009b\5)\25\2\u009b\u009c\5)\25\2\u009c\u00a3\3\2\2\2\u009d\u009e\7^"+
		"\2\2\u009e\u009f\5/\30\2\u009f\u00a0\5)\25\2\u00a0\u00a1\5)\25\2\u00a1"+
		"\u00a3\3\2\2\2\u00a2\u0097\3\2\2\2\u00a2\u0099\3\2\2\2\u00a2\u009d\3\2"+
		"\2\2\u00a3(\3\2\2\2\u00a4\u00a5\t\5\2\2\u00a5*\3\2\2\2\u00a6\u00a7\7^"+
		"\2\2\u00a7\u00a8\7w\2\2\u00a8\u00a9\5-\27\2\u00a9\u00aa\5-\27\2\u00aa"+
		"\u00ab\5-\27\2\u00ab\u00ac\5-\27\2\u00ac,\3\2\2\2\u00ad\u00ae\t\6\2\2"+
		"\u00ae.\3\2\2\2\u00af\u00b0\t\7\2\2\u00b0\60\3\2\2\2\u00b1\u00b8\t\b\2"+
		"\2\u00b2\u00b3\n\t\2\2\u00b3\u00b8\6\31\2\2\u00b4\u00b5\t\n\2\2\u00b5"+
		"\u00b6\t\13\2\2\u00b6\u00b8\6\31\3\2\u00b7\u00b1\3\2\2\2\u00b7\u00b2\3"+
		"\2\2\2\u00b7\u00b4\3\2\2\2\u00b8\62\3\2\2\2\u00b9\u00c0\t\f\2\2\u00ba"+
		"\u00bb\n\t\2\2\u00bb\u00c0\6\32\4\2\u00bc\u00bd\t\n\2\2\u00bd\u00be\t"+
		"\13\2\2\u00be\u00c0\6\32\5\2\u00bf\u00b9\3\2\2\2\u00bf\u00ba\3\2\2\2\u00bf"+
		"\u00bc\3\2\2\2\u00c0\64\3\2\2\2\u00c1\u00c3\t\r\2\2\u00c2\u00c1\3\2\2"+
		"\2\u00c3\u00c4\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00c6"+
		"\3\2\2\2\u00c6\u00c7\b\33\2\2\u00c7\66\3\2\2\2\u00c8\u00c9\7\61\2\2\u00c9"+
		"\u00ca\7,\2\2\u00ca\u00ce\3\2\2\2\u00cb\u00cd\13\2\2\2\u00cc\u00cb\3\2"+
		"\2\2\u00cd\u00d0\3\2\2\2\u00ce\u00cf\3\2\2\2\u00ce\u00cc\3\2\2\2\u00cf"+
		"\u00d1\3\2\2\2\u00d0\u00ce\3\2\2\2\u00d1\u00d2\7,\2\2\u00d2\u00d3\7\61"+
		"\2\2\u00d3\u00d4\3\2\2\2\u00d4\u00d5\b\34\2\2\u00d58\3\2\2\2\u00d6\u00d7"+
		"\7\61\2\2\u00d7\u00d8\7\61\2\2\u00d8\u00dc\3\2\2\2\u00d9\u00db\n\16\2"+
		"\2\u00da\u00d9\3\2\2\2\u00db\u00de\3\2\2\2\u00dc\u00da\3\2\2\2\u00dc\u00dd"+
		"\3\2\2\2\u00dd\u00df\3\2\2\2\u00de\u00dc\3\2\2\2\u00df\u00e0\b\35\2\2"+
		"\u00e0:\3\2\2\2\24\2MRX]in\177\u0084\u008b\u008f\u0095\u00a2\u00b7\u00bf"+
		"\u00c4\u00ce\u00dc\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}