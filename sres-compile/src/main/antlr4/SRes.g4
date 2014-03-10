grammar SRes;

@header {
package me.tatarka.sres;
}

root
: (Identifier '<')? Identifier arguments? block?
;

arguments
: '(' atom? (',' atom)* ')'
;

block
: '{' child* '}'
;

child
: Reference | attribute | function
;

attribute
: Identifier '=' atom
;

function
: Identifier arguments? block?
;

atom
: function | Reference | String | Dimension | Number
;

Identifier
: JavaLetter JavaLetterOrDigit*
;

String
: '"' StringCharacters? '"'
;

Reference
: '@' '+'? (Identifier ':')? Identifier '/' Identifier
;

Dimension
: Number Unit
;

Number
: Digits '.' Digits?
| '.' Digits
| Digits
;

fragment
Unit
: 'px' | 'dp' | 'dip' | 'pt' | 'sp' | 'in' | 'mm'
;

fragment
Digits
: Digit+
;

fragment
Digit 
:[0-9]
;

fragment
StringCharacters
: StringCharacter+
;

fragment
StringCharacter
: ~["\\]
| EscapeSequence
;

fragment
EscapeSequence
: '\\' [btnfr"'\\]
| OctalEscape
| UnicodeEscape
;

fragment
OctalEscape
: '\\' OctalDigit
| '\\' OctalDigit OctalDigit
| '\\' ZeroToThree OctalDigit OctalDigit
;

fragment
OctalDigit
: [0-7]
;

fragment
UnicodeEscape
: '\\' 'u' HexDigit HexDigit HexDigit HexDigit
;

fragment
HexDigit
: [0-9a-fA-F]
;

fragment
ZeroToThree
: [0-3]
;

fragment
JavaLetter
: [a-zA-Z$_] // these are the "java letters" below 0xFF
| // covers all characters above 0xFF which are not a surrogate
~[\u0000-\u00FF\uD800-\uDBFF]
{Character.isJavaIdentifierStart(_input.LA(-1))}?
| // covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
[\uD800-\uDBFF] [\uDC00-\uDFFF]
{Character.isJavaIdentifierStart(Character.toCodePoint((char)_input.LA(-2), (char)_input.LA(-1)))}?
;

fragment
JavaLetterOrDigit
: [a-zA-Z0-9$_\.:] // these are the "java letters or digits" below 0xFF
| // covers all characters above 0xFF which are not a surrogate
~[\u0000-\u00FF\uD800-\uDBFF]
{Character.isJavaIdentifierPart(_input.LA(-1))}?
| // covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
[\uD800-\uDBFF] [\uDC00-\uDFFF]
{Character.isJavaIdentifierPart(Character.toCodePoint((char)_input.LA(-2), (char)_input.LA(-1)))}?
;

WS
: [ \t\r\n\u000C]+ -> skip
;

COMMENT
: '/*' .*? '*/' -> skip
;

LINE_COMMENT
: '//' ~[\r\n]* -> skip
;