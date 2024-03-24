grammar Lang;

file : (print CRLF+)* EOF;
print : 'say("' STRING '")';


fragment DIGIT : [0-9];
fragment LETTER : [A-Za-z];

STRING : (TEXT ' '?)+;
TEXT : LETTER+;
INTEGER : DIGIT+;

CRLF : '\r'? '\n' | '\r';

ANY : .;
