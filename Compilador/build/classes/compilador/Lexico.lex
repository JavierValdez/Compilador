%% 
%class Yylex
%public 
%line 
%char 
%state COMMENT


%{
		private int comment_count = 0;
%}


L=[a-zA-Záéíóú]
D=[0-9]
ID=({L})({L}|{D}|"_")*

BLANCOS=[\n\ \t\b\012]
STRING_TEXT=(\\\"|[^\n\"]|\\{BLANCOS}+\\)*
COMMENT_TEXT=([^/*\n]|[^*\n]"/"[^*\n]|[^/\n]"*"[^/\n]|"*"[^/\n]|"/"[^*\n])*
SPACE = [ ,\t]+
LINE_BREAK = [\n]+
 
%%
<YYINITIAL> "puts"     { return (new Yytoken(1,yytext(),yyline,yychar,yychar+4)); }
<YYINITIAL> "set"	{ return (new Yytoken(2,yytext(),yyline,yychar,yychar+3)); }
<YYINITIAL> "$" { return (new Yytoken(3,yytext(),yyline,yychar,yychar+1)); }
<YYINITIAL> "{" { return (new Yytoken(4,yytext(),yyline,yychar,yychar+1)); }
<YYINITIAL> "}" { return (new Yytoken(5,yytext(),yyline,yychar,yychar+1)); }
<YYINITIAL> "[" { return (new Yytoken(6,yytext(),yyline,yychar,yychar+1)); }
<YYINITIAL> "]" { return (new Yytoken(7,yytext(),yyline,yychar,yychar+1)); }
<YYINITIAL> "expr" { return (new Yytoken(9,yytext(),yyline,yychar,yychar+4)); }
<YYINITIAL> "-"  { return (new Yytoken(10,yytext(),yyline,yychar,yychar+1)); }
<YYINITIAL> "+"  { return (new Yytoken(11,yytext(),yyline,yychar,yychar+1)); }
<YYINITIAL> "**" { return (new Yytoken(12,yytext(),yyline,yychar,yychar+2)); }
<YYINITIAL> "*"  { return (new Yytoken(13,yytext(),yyline,yychar,yychar+1)); }
<YYINITIAL> "/"  { return (new Yytoken(14,yytext(),yyline,yychar,yychar+1)); }
<YYINITIAL> "%"  { return (new Yytoken(15,yytext(),yyline,yychar,yychar+1)); }
<YYINITIAL> "<"  { return (new Yytoken(16,yytext(),yyline,yychar,yychar+1)); }
<YYINITIAL> ">"  { return (new Yytoken(17,yytext(),yyline,yychar,yychar+1)); }
<YYINITIAL> "<=" { return (new Yytoken(18,yytext(),yyline,yychar,yychar+2)); }
<YYINITIAL> ">=" { return (new Yytoken(19,yytext(),yyline,yychar,yychar+2)); }
<YYINITIAL> "==" { return (new Yytoken(20,yytext(),yyline,yychar,yychar+2)); }
<YYINITIAL> "eq" { return (new Yytoken(21,yytext(),yyline,yychar,yychar+2)); }
<YYINITIAL> "ne" { return (new Yytoken(22,yytext(),yyline,yychar,yychar+2)); }
<YYINITIAL> "in" { return (new Yytoken(23,yytext(),yyline,yychar,yychar+2)); }
<YYINITIAL> "ni" { return (new Yytoken(24,yytext(),yyline,yychar,yychar+2)); }
<YYINITIAL> "&&" { return (new Yytoken(25,yytext(),yyline,yychar,yychar+2)); }
<YYINITIAL> "||" { return (new Yytoken(26,yytext(),yyline,yychar,yychar+2)); }
<YYINITIAL> "!"  { return (new Yytoken(27,yytext(),yyline,yychar,yychar+1)); }
<YYINITIAL> "#" { return (new Yytoken(28,yytext(),yyline,yychar,yychar+1)); }
<YYINITIAL> "if"  { return (new Yytoken(29,yytext(),yyline,yychar,yychar+2)); }
<YYINITIAL> "then" { return (new Yytoken(30,yytext(),yyline,yychar,yychar+4)); }
<YYINITIAL> "elseif" { return (new Yytoken(31,yytext(),yyline,yychar,yychar+6)); }
<YYINITIAL> "else" { return (new Yytoken(32,yytext(),yyline,yychar,yychar+4)); }
<YYINITIAL> "switch" { return (new Yytoken(33,yytext(),yyline,yychar,yychar+6)); }
<YYINITIAL> "default" { return (new Yytoken(34,yytext(),yyline,yychar,yychar+7)); }
<YYINITIAL> "while" { return (new Yytoken(35,yytext(),yyline,yychar,yychar+5)); }
<YYINITIAL> "continue" { return (new Yytoken(36,yytext(),yyline,yychar,yychar+8)); }
<YYINITIAL> "break" { return (new Yytoken(37,yytext(),yyline,yychar,yychar+5)); }
<YYINITIAL> "for" { return (new Yytoken(38,yytext(),yyline,yychar,yychar+3)); }
<YYINITIAL> "proc" { return (new Yytoken(39,yytext(),yyline,yychar,yychar+4)); }
<YYINITIAL> "incr" { return (new Yytoken(40,yytext(),yyline,yychar,yychar+4)); }
<YYINITIAL> "return" { return (new Yytoken(41,yytext(),yyline,yychar,yychar+6)); }

<YYINITIAL> {LINE_BREAK} { }
<YYINITIAL> {SPACE} { }


<YYINITIAL> \"{STRING_TEXT}\" {
	String str =  yytext().substring(1,yytext().length() - 1);
	Utility.confirmar(str.length() == yytext().length() - 2);
	return (new Yytoken(42,str,yyline,yychar,yychar + str.length()));
}

<YYINITIAL> {D}+ { 
	return (new Yytoken(44,yytext(),yyline,yychar,yychar + yytext().length()));
}	
<YYINITIAL> {ID} {
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}	
<YYINITIAL,COMMENT> . {
        System.out.println("ERROR LEXICO: " + yytext() + "\n Linea No."+yyline+", columna No."+yychar);
	Utility.error(Utility.E_UNMATCHED);
} 