// Generated from /home/evan/android/sresProject/sres-compile/src/main/antlr4/SRes.g4 by ANTLR 4.2

package me.tatarka.sres;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SResParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__6=1, T__5=2, T__4=3, T__3=4, T__2=5, T__1=6, T__0=7, Identifier=8, 
		String=9, Reference=10, Dimension=11, Number=12, WS=13, COMMENT=14, LINE_COMMENT=15;
	public static final String[] tokenNames = {
		"<INVALID>", "'('", "')'", "'{'", "','", "'<'", "'='", "'}'", "Identifier", 
		"String", "Reference", "Dimension", "Number", "WS", "COMMENT", "LINE_COMMENT"
	};
	public static final int
		RULE_root = 0, RULE_arguments = 1, RULE_block = 2, RULE_child = 3, RULE_attribute = 4, 
		RULE_function = 5, RULE_atom = 6;
	public static final String[] ruleNames = {
		"root", "arguments", "block", "child", "attribute", "function", "atom"
	};

	@Override
	public String getGrammarFileName() { return "SRes.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SResParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class RootContext extends ParserRuleContext {
		public List<TerminalNode> Identifier() { return getTokens(SResParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(SResParser.Identifier, i);
		}
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public RootContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_root; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SResVisitor ) return ((SResVisitor<? extends T>)visitor).visitRoot(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RootContext root() throws RecognitionException {
		RootContext _localctx = new RootContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_root);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(16);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				setState(14); match(Identifier);
				setState(15); match(5);
				}
				break;
			}
			setState(18); match(Identifier);
			setState(20);
			_la = _input.LA(1);
			if (_la==1) {
				{
				setState(19); arguments();
				}
			}

			setState(23);
			_la = _input.LA(1);
			if (_la==3) {
				{
				setState(22); block();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgumentsContext extends ParserRuleContext {
		public AtomContext atom(int i) {
			return getRuleContext(AtomContext.class,i);
		}
		public List<AtomContext> atom() {
			return getRuleContexts(AtomContext.class);
		}
		public ArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arguments; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SResVisitor ) return ((SResVisitor<? extends T>)visitor).visitArguments(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentsContext arguments() throws RecognitionException {
		ArgumentsContext _localctx = new ArgumentsContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_arguments);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(25); match(1);
			setState(27);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Identifier) | (1L << String) | (1L << Reference) | (1L << Dimension) | (1L << Number))) != 0)) {
				{
				setState(26); atom();
				}
			}

			setState(33);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==4) {
				{
				{
				setState(29); match(4);
				setState(30); atom();
				}
				}
				setState(35);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(36); match(2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public List<ChildContext> child() {
			return getRuleContexts(ChildContext.class);
		}
		public ChildContext child(int i) {
			return getRuleContext(ChildContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SResVisitor ) return ((SResVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38); match(3);
			setState(42);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Identifier || _la==Reference) {
				{
				{
				setState(39); child();
				}
				}
				setState(44);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(45); match(7);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ChildContext extends ParserRuleContext {
		public AttributeContext attribute() {
			return getRuleContext(AttributeContext.class,0);
		}
		public TerminalNode Reference() { return getToken(SResParser.Reference, 0); }
		public FunctionContext function() {
			return getRuleContext(FunctionContext.class,0);
		}
		public ChildContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_child; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SResVisitor ) return ((SResVisitor<? extends T>)visitor).visitChild(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ChildContext child() throws RecognitionException {
		ChildContext _localctx = new ChildContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_child);
		try {
			setState(50);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(47); match(Reference);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(48); attribute();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(49); function();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttributeContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(SResParser.Identifier, 0); }
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public AttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribute; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SResVisitor ) return ((SResVisitor<? extends T>)visitor).visitAttribute(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AttributeContext attribute() throws RecognitionException {
		AttributeContext _localctx = new AttributeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_attribute);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52); match(Identifier);
			setState(53); match(6);
			setState(54); atom();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(SResParser.Identifier, 0); }
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public FunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SResVisitor ) return ((SResVisitor<? extends T>)visitor).visitFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionContext function() throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56); match(Identifier);
			setState(58);
			_la = _input.LA(1);
			if (_la==1) {
				{
				setState(57); arguments();
				}
			}

			setState(61);
			_la = _input.LA(1);
			if (_la==3) {
				{
				setState(60); block();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AtomContext extends ParserRuleContext {
		public TerminalNode Reference() { return getToken(SResParser.Reference, 0); }
		public TerminalNode String() { return getToken(SResParser.String, 0); }
		public TerminalNode Dimension() { return getToken(SResParser.Dimension, 0); }
		public TerminalNode Number() { return getToken(SResParser.Number, 0); }
		public FunctionContext function() {
			return getRuleContext(FunctionContext.class,0);
		}
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SResVisitor ) return ((SResVisitor<? extends T>)visitor).visitAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_atom);
		try {
			setState(68);
			switch (_input.LA(1)) {
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(63); function();
				}
				break;
			case Reference:
				enterOuterAlt(_localctx, 2);
				{
				setState(64); match(Reference);
				}
				break;
			case String:
				enterOuterAlt(_localctx, 3);
				{
				setState(65); match(String);
				}
				break;
			case Dimension:
				enterOuterAlt(_localctx, 4);
				{
				setState(66); match(Dimension);
				}
				break;
			case Number:
				enterOuterAlt(_localctx, 5);
				{
				setState(67); match(Number);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\21I\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\3\2\3\2\5\2\23\n\2\3\2\3\2"+
		"\5\2\27\n\2\3\2\5\2\32\n\2\3\3\3\3\5\3\36\n\3\3\3\3\3\7\3\"\n\3\f\3\16"+
		"\3%\13\3\3\3\3\3\3\4\3\4\7\4+\n\4\f\4\16\4.\13\4\3\4\3\4\3\5\3\5\3\5\5"+
		"\5\65\n\5\3\6\3\6\3\6\3\6\3\7\3\7\5\7=\n\7\3\7\5\7@\n\7\3\b\3\b\3\b\3"+
		"\b\3\b\5\bG\n\b\3\b\2\2\t\2\4\6\b\n\f\16\2\2O\2\22\3\2\2\2\4\33\3\2\2"+
		"\2\6(\3\2\2\2\b\64\3\2\2\2\n\66\3\2\2\2\f:\3\2\2\2\16F\3\2\2\2\20\21\7"+
		"\n\2\2\21\23\7\7\2\2\22\20\3\2\2\2\22\23\3\2\2\2\23\24\3\2\2\2\24\26\7"+
		"\n\2\2\25\27\5\4\3\2\26\25\3\2\2\2\26\27\3\2\2\2\27\31\3\2\2\2\30\32\5"+
		"\6\4\2\31\30\3\2\2\2\31\32\3\2\2\2\32\3\3\2\2\2\33\35\7\3\2\2\34\36\5"+
		"\16\b\2\35\34\3\2\2\2\35\36\3\2\2\2\36#\3\2\2\2\37 \7\6\2\2 \"\5\16\b"+
		"\2!\37\3\2\2\2\"%\3\2\2\2#!\3\2\2\2#$\3\2\2\2$&\3\2\2\2%#\3\2\2\2&\'\7"+
		"\4\2\2\'\5\3\2\2\2(,\7\5\2\2)+\5\b\5\2*)\3\2\2\2+.\3\2\2\2,*\3\2\2\2,"+
		"-\3\2\2\2-/\3\2\2\2.,\3\2\2\2/\60\7\t\2\2\60\7\3\2\2\2\61\65\7\f\2\2\62"+
		"\65\5\n\6\2\63\65\5\f\7\2\64\61\3\2\2\2\64\62\3\2\2\2\64\63\3\2\2\2\65"+
		"\t\3\2\2\2\66\67\7\n\2\2\678\7\b\2\289\5\16\b\29\13\3\2\2\2:<\7\n\2\2"+
		";=\5\4\3\2<;\3\2\2\2<=\3\2\2\2=?\3\2\2\2>@\5\6\4\2?>\3\2\2\2?@\3\2\2\2"+
		"@\r\3\2\2\2AG\5\f\7\2BG\7\f\2\2CG\7\13\2\2DG\7\r\2\2EG\7\16\2\2FA\3\2"+
		"\2\2FB\3\2\2\2FC\3\2\2\2FD\3\2\2\2FE\3\2\2\2G\17\3\2\2\2\f\22\26\31\35"+
		"#,\64<?F";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}