package com.github.sip.parser;

import java_cup.sym;
import java_cup.runtime.Symbol;


/**
* A simple Lexer class to parse sip headers
* References
*
* 1. https://www.ietf.org/rfc/rfc3261.txt
* 2. https://tools.ietf.org/html/rfc5234
*
*/
%%

%class SipLexer
%unicode
%cup
%line
%column

%{

      private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
      }
      private Symbol symbol(int type, Object value) {
      	return new Symbol(type, yyline, yycolumn, value);
      }
%}

%init{
  super(in);
%init}

Identifier = [:jletter:] [:jletterdigit:]*

SIP_URI                  =  "sip:" {userinfo}? // {hostport} {uri_parameters} {headers}?
 
SIPS-URI               =  "sips:" {userinfo}? {hostport} {uri_parameters} {headers}?

// skipping telephone_subscriber part
//userinfo               = ( {user} | {telephone_subscriber} ) ( ":" {password} )? "@"
userinfo                 = {user} ( ":" {password} )? "@"
user                     = ( {unreserved} | {escaped} | {user_unreserved} )+
password                 = ( {unreserved} | {escaped} | "&" | "=" | "+" | "$" | "," )*
unreserved               = {alphanum} | {mark}
escaped                  = "%" {HEXDIG} {HEXDIG}
user_unreserved          = "&" | "=" | "+" | "$" | "," | ";" | "?" | "/"
alphanum                 = {ALPHA} | {DIGIT}
mark                     = "-" | "_" | "." | "!" | "~" | "*" | "'" | "(" | ")"
HEXDIG                   = {DIGIT} | "A" | "B" | "C" | "D" | "E" | "F"
ALPHA                    = [a-zA-Z]
DIGIT                    = [0-9]



hostport = {Identifier}
uri_parameters = {Identifier}
headers = {Identifier}


//
////hostport         =  host [ ":" port ]
//host             =  hostname / IPv4address / IPv6reference
//hostname         =  *( domainlabel "." ) toplabel [ "." ]
//domainlabel      =  alphanum
//                    / alphanum *( alphanum / "-" ) alphanum
//toplabel         =  ALPHA / ALPHA *( alphanum / "-" ) alphanum

%%

<YYINITIAL> {SIP_URI}              { return symbol(sym.ID, yytext()); }

[^]                              { throw new Error("Illegal character <"+ yytext()+ ">"); }