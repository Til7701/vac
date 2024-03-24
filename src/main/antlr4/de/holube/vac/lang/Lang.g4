grammar Lang;

print : 'say(' STRING ')';

STRING : '"' ~'"'* '"';
