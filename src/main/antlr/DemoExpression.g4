grammar DemoExpression;
@header {
package com.hermant.generated;
}

// grammar:
expression
    : additive EOF
    ;

additive
    : multiplicative                # MULTIPLICATIVE
    | additive '+' multiplicative   # Add
    | additive '-' multiplicative   # Subtract
    ;

multiplicative
    : unary                         # UNARY
    | multiplicative '*' unary      # Multiply
    | multiplicative '/' unary      # Divide
    | multiplicative '%' unary      # Modulo
    ;

unary
    : '(' additive ')'              # Pars
    | '+' unary                     # UnaryPlus
    | '-' unary                     # UnaryMinus
    | INT                           # Int
    | REAL                          # Real
    | BOOL                          # Bool
    ;

// tokens:
INT
    : [1-9][0-9]*|'0'
    ;
REAL
    :   INT ('.'[0-9]*)? | '.'[0-9]+
    ;
BOOL
    : TRUE
    | FALSE
    ;
TRUE
    : T R U E
    ;
FALSE
    : F A L S E
    ;

// whitespace:
WS          :   [ \t\r\n]+ -> skip;

// alphabetic:
fragment A:     [aA];
fragment B:     [bB];
fragment C:     [cC];
fragment D:     [dD];
fragment E:     [eE];
fragment F:     [fF];
fragment G:     [gG];
fragment H:     [hH];
fragment I:     [iI];
fragment J:     [jJ];
fragment K:     [kK];
fragment L:     [lL];
fragment M:     [mM];
fragment N:     [nN];
fragment O:     [oO];
fragment P:     [pP];
fragment Q:     [qQ];
fragment R:     [rR];
fragment S:     [sS];
fragment T:     [tT];
fragment U:     [uU];
fragment V:     [vV];
fragment W:     [wW];
fragment X:     [xX];
fragment Y:     [yY];
fragment Z:     [zZ];