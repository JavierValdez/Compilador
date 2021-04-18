package compilador;



public class Yylex {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 128;
	private final int YY_EOF = 129;

		private int comment_count = 0;
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	public Yylex (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	public Yylex (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Yylex () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int COMMENT = 1;
	private final int yy_state_dtrans[] = {
		0,
		61
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NOT_ACCEPT,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NOT_ACCEPT,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NOT_ACCEPT,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NOT_ACCEPT,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NOT_ACCEPT,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NO_ANCHOR,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NO_ANCHOR,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NO_ANCHOR,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NO_ANCHOR,
		/* 86 */ YY_NO_ANCHOR,
		/* 87 */ YY_NO_ANCHOR,
		/* 88 */ YY_NO_ANCHOR,
		/* 89 */ YY_NO_ANCHOR,
		/* 90 */ YY_NO_ANCHOR,
		/* 91 */ YY_NO_ANCHOR,
		/* 92 */ YY_NO_ANCHOR,
		/* 93 */ YY_NO_ANCHOR,
		/* 94 */ YY_NO_ANCHOR,
		/* 95 */ YY_NO_ANCHOR,
		/* 96 */ YY_NO_ANCHOR,
		/* 97 */ YY_NO_ANCHOR,
		/* 98 */ YY_NO_ANCHOR,
		/* 99 */ YY_NO_ANCHOR,
		/* 100 */ YY_NO_ANCHOR,
		/* 101 */ YY_NO_ANCHOR,
		/* 102 */ YY_NO_ANCHOR,
		/* 103 */ YY_NO_ANCHOR,
		/* 104 */ YY_NO_ANCHOR,
		/* 105 */ YY_NO_ANCHOR,
		/* 106 */ YY_NO_ANCHOR,
		/* 107 */ YY_NO_ANCHOR,
		/* 108 */ YY_NO_ANCHOR,
		/* 109 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"48:8,43,44,38,48:2,42,48:18,44,26,40,27,6,17,24,48:3,15,14,39,13,48,16,45:1" +
"0,48:2,18,20,19,48:2,46:26,9,41,10,48,47,48,34,36,32,33,5,28,46,29,23,46,37" +
",30,46,22,35,1,21,12,4,3,2,46,31,11,46:2,7,25,8,48:2,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,110,
"0,1,2,1:7,3,1:2,4,5,6,1:2,7,8,9,10,1:4,10:2,11,10,1:3,10:6,12,10:8,13,14,15" +
",13,16,17,18,19,20,13,21,22,1,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37," +
"38,39,40,41,10,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61," +
"10,62,63,64,65,66,67,68,69")[0];

	private int yy_nxt[][] = unpackFromString(70,49,
"1,2,101,104,76,49,3,4,5,6,7,101,105,8,9,10,11,12,13,14,15,101,53,56,50,54,1" +
"6,17,77,101:2,106,107,108,101:2,109,101,18,19,57,60,-1,60,19,20,101,60:2,-1" +
":50,101,78,101:3,-1:5,101,79,-1:8,101:3,-1:4,101:10,-1:7,80,101,80,-1:16,22" +
",-1:53,23,-1:48,24,-1:48,25,-1:66,18,-1:49,19,-1:4,19,-1:49,20,-1:4,101:5,-" +
"1:5,101:2,-1:8,101:3,-1:4,101:10,-1:7,80,101,80,-1:2,101:5,-1:5,101:2,-1:8," +
"101:3,-1:4,101:4,68,101:5,-1:7,80,101,80,-1:2,101:5,-1:5,101:2,-1:8,101:2,7" +
"2,-1:4,101:10,-1:7,80,101,80,-1:2,48:37,-1,48,32,52,48:7,-1,101:5,-1:5,83,1" +
"01,-1:8,21,101:2,-1:4,101:2,84,101:7,-1:7,80,101,80,-1:25,30,-1:25,48:37,55" +
",48,51,52,48,58:2,48:4,-1,101:4,26,-1:5,101:2,-1:8,101:2,27,-1:4,101:10,-1:" +
"7,80,101,80,-1:26,31,-1:61,55,-1:2,48,-1,55:2,-1:5,101:5,-1:5,101:2,-1:8,10" +
"1,28,101,-1:4,29,101:9,-1:7,80,101,80,-1:2,48:37,55,48,32,52,48,58:2,48:4,-" +
"1,101:2,33,101:2,-1:5,101:2,-1:8,101:3,-1:4,101:10,-1:7,80,101,80,-1,1,60:3" +
"7,-1,60:3,-1,60:6,-1,101:5,-1:5,101,34,-1:8,101:3,-1:4,101:10,-1:7,80,101,8" +
"0,-1:2,101:3,35,101,-1:5,101:2,-1:8,101:3,-1:4,101:10,-1:7,80,101,80,-1:2,1" +
"01:5,-1:5,101:2,-1:8,101:3,-1:4,101:4,36,101:5,-1:7,80,101,80,-1:2,101:5,-1" +
":5,101:2,-1:8,101,37,101,-1:4,101:10,-1:7,80,101,80,-1:2,101:5,-1:5,101,38," +
"-1:8,101:3,-1:4,101:10,-1:7,80,101,80,-1:2,101:4,39,-1:5,101:2,-1:8,101:3,-" +
"1:4,101:10,-1:7,80,101,80,-1:2,101:5,-1:5,101,40,-1:8,101:3,-1:4,101:10,-1:" +
"7,80,101,80,-1:2,101:4,41,-1:5,101:2,-1:8,101:3,-1:4,101:10,-1:7,80,101,80," +
"-1:2,101:5,-1:5,101:2,-1:8,101:3,-1:4,101:9,42,-1:7,80,101,80,-1:2,101:5,-1" +
":5,101:2,-1:8,101:3,-1:4,101,43,101:8,-1:7,80,101,80,-1:2,101:5,-1:5,101:2," +
"-1:8,101:3,-1:4,44,101:9,-1:7,80,101,80,-1:2,101:5,-1:5,101:2,-1:8,101,45,1" +
"01,-1:4,101:10,-1:7,80,101,80,-1:2,101:2,46,101:2,-1:5,101:2,-1:8,101:3,-1:" +
"4,101:10,-1:7,80,101,80,-1:2,101:4,47,-1:5,101:2,-1:8,101:3,-1:4,101:10,-1:" +
"7,80,101,80,-1:2,101:4,59,-1:5,101:2,-1:8,101:3,-1:4,101:3,82,101:6,-1:7,80" +
",101,80,-1:2,101:5,-1:5,101:2,-1:8,101:3,-1:4,101:7,62,101:2,-1:7,80,101,80" +
",-1:2,101:2,63,101:2,-1:5,101:2,-1:8,101:3,-1:4,101:10,-1:7,80,101,80,-1:2," +
"101:5,-1:5,101:2,-1:8,101:3,-1:4,101:7,64,101:2,-1:7,80,101,80,-1:2,101:4,6" +
"5,-1:5,101:2,-1:8,101:3,-1:4,101:10,-1:7,80,101,80,-1:2,101:5,-1:5,101:2,-1" +
":8,101:2,103,-1:4,101:10,-1:7,80,101,80,-1:2,66,101:4,-1:5,101:2,-1:8,101:3" +
",-1:4,101:10,-1:7,80,101,80,-1:2,101:3,67,101,-1:5,101:2,-1:8,101:3,-1:4,10" +
"1:10,-1:7,80,101,80,-1:2,101:2,89,101:2,-1:5,101:2,-1:8,101:3,-1:4,101:10,-" +
"1:7,80,101,80,-1:2,101:5,-1:5,101:2,-1:8,101,91,101,-1:4,101:10,-1:7,80,101" +
",80,-1:2,101:5,-1:5,101:2,-1:8,101:3,-1:4,92,101:9,-1:7,80,101,80,-1:2,101:" +
"4,93,-1:5,101:2,-1:8,101:3,-1:4,101:10,-1:7,80,101,80,-1:2,101,95,101:3,-1:" +
"5,101:2,-1:8,101:3,-1:4,101:10,-1:7,80,101,80,-1:2,101:5,-1:5,101:2,-1:8,10" +
"1:3,-1:4,101:2,69,101:7,-1:7,80,101,80,-1:2,101:2,96,101:2,-1:5,101:2,-1:8," +
"101:3,-1:4,101:10,-1:7,80,101,80,-1:2,101:5,-1:5,101:2,-1:8,101:3,-1:4,101:" +
"6,97,101:3,-1:7,80,101,80,-1:2,101:5,-1:5,101:2,-1:8,101:3,-1:4,101:6,70,10" +
"1:3,-1:7,80,101,80,-1:2,101:5,-1:5,101:2,-1:8,101:3,-1:4,101:4,71,101:5,-1:" +
"7,80,101,80,-1:2,101:5,-1:5,101,73,-1:8,101:3,-1:4,101:10,-1:7,80,101,80,-1" +
":2,101:5,-1:5,101:2,-1:8,101:2,98,-1:4,101:10,-1:7,80,101,80,-1:2,101,99,10" +
"1:3,-1:5,101:2,-1:8,101:3,-1:4,101:10,-1:7,80,101,80,-1:2,101:5,-1:5,101:2," +
"-1:8,101,100,101,-1:4,101:10,-1:7,80,101,80,-1:2,101:5,-1:5,101:2,-1:8,101:" +
"3,-1:4,101:2,74,101:7,-1:7,80,101,80,-1:2,101,75,101:3,-1:5,101:2,-1:8,101:" +
"3,-1:4,101:10,-1:7,80,101,80,-1:2,101:5,-1:5,101:2,-1:8,101:2,90,-1:4,101:1" +
"0,-1:7,80,101,80,-1:2,101:2,94,101:2,-1:5,101:2,-1:8,101:3,-1:4,101:10,-1:7" +
",80,101,80,-1:2,101:5,-1:5,101:2,-1:8,101:3,-1:4,101,81,101:8,-1:7,80,101,8" +
"0,-1:2,101:4,85,-1:5,101:2,-1:8,101:3,-1:4,101:10,-1:7,80,101,80,-1:2,101:5" +
",-1:5,101:2,-1:8,101:3,-1:4,101,102,101:8,-1:7,80,101,80,-1:2,101:5,-1:5,10" +
"1:2,-1:8,101:3,-1:4,101:7,86,101:2,-1:7,80,101,80,-1:2,101:4,87,-1:5,101:2," +
"-1:8,101:3,-1:4,101:10,-1:7,80,101,80,-1:2,101:5,-1:5,101,88,-1:8,101:3,-1:" +
"4,101:10,-1:7,80,101,80,-1");

	public Yytoken yylex ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {
				return null;
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -3:
						break;
					case 3:
						{ return (new Yytoken(3,yytext(),yyline,yychar,yychar+1)); }
					case -4:
						break;
					case 4:
						{ return (new Yytoken(4,yytext(),yyline,yychar,yychar+1)); }
					case -5:
						break;
					case 5:
						{ return (new Yytoken(5,yytext(),yyline,yychar,yychar+1)); }
					case -6:
						break;
					case 6:
						{ return (new Yytoken(6,yytext(),yyline,yychar,yychar+1)); }
					case -7:
						break;
					case 7:
						{ return (new Yytoken(7,yytext(),yyline,yychar,yychar+1)); }
					case -8:
						break;
					case 8:
						{ return (new Yytoken(10,yytext(),yyline,yychar,yychar+1)); }
					case -9:
						break;
					case 9:
						{ return (new Yytoken(11,yytext(),yyline,yychar,yychar+1)); }
					case -10:
						break;
					case 10:
						{ return (new Yytoken(13,yytext(),yyline,yychar,yychar+1)); }
					case -11:
						break;
					case 11:
						{ return (new Yytoken(14,yytext(),yyline,yychar,yychar+1)); }
					case -12:
						break;
					case 12:
						{ return (new Yytoken(15,yytext(),yyline,yychar,yychar+1)); }
					case -13:
						break;
					case 13:
						{ return (new Yytoken(16,yytext(),yyline,yychar,yychar+1)); }
					case -14:
						break;
					case 14:
						{ return (new Yytoken(17,yytext(),yyline,yychar,yychar+1)); }
					case -15:
						break;
					case 15:
						{
        System.out.println("ERROR LEXICO: " + yytext() + " Linea No."+yyline+", columna No."+yychar);
	Utility.error(Utility.E_UNMATCHED);
}
					case -16:
						break;
					case 16:
						{ return (new Yytoken(27,yytext(),yyline,yychar,yychar+1)); }
					case -17:
						break;
					case 17:
						{ return (new Yytoken(28,yytext(),yyline,yychar,yychar+1)); }
					case -18:
						break;
					case 18:
						{ }
					case -19:
						break;
					case 19:
						{ }
					case -20:
						break;
					case 20:
						{ 
	return (new Yytoken(44,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -21:
						break;
					case 21:
						{ return (new Yytoken(21,yytext(),yyline,yychar,yychar+2)); }
					case -22:
						break;
					case 22:
						{ return (new Yytoken(12,yytext(),yyline,yychar,yychar+2)); }
					case -23:
						break;
					case 23:
						{ return (new Yytoken(18,yytext(),yyline,yychar,yychar+2)); }
					case -24:
						break;
					case 24:
						{ return (new Yytoken(19,yytext(),yyline,yychar,yychar+2)); }
					case -25:
						break;
					case 25:
						{ return (new Yytoken(20,yytext(),yyline,yychar,yychar+2)); }
					case -26:
						break;
					case 26:
						{ return (new Yytoken(22,yytext(),yyline,yychar,yychar+2)); }
					case -27:
						break;
					case 27:
						{ return (new Yytoken(24,yytext(),yyline,yychar,yychar+2)); }
					case -28:
						break;
					case 28:
						{ return (new Yytoken(23,yytext(),yyline,yychar,yychar+2)); }
					case -29:
						break;
					case 29:
						{ return (new Yytoken(29,yytext(),yyline,yychar,yychar+2)); }
					case -30:
						break;
					case 30:
						{ return (new Yytoken(25,yytext(),yyline,yychar,yychar+2)); }
					case -31:
						break;
					case 31:
						{ return (new Yytoken(26,yytext(),yyline,yychar,yychar+2)); }
					case -32:
						break;
					case 32:
						{
	String str =  yytext().substring(1,yytext().length() - 1);
	Utility.confirmar(str.length() == yytext().length() - 2);
	return (new Yytoken(42,str,yyline,yychar,yychar + str.length()));
}
					case -33:
						break;
					case 33:
						{ return (new Yytoken(2,yytext(),yyline,yychar,yychar+3)); }
					case -34:
						break;
					case 34:
						{ return (new Yytoken(38,yytext(),yyline,yychar,yychar+3)); }
					case -35:
						break;
					case 35:
						{ return (new Yytoken(1,yytext(),yyline,yychar,yychar+4)); }
					case -36:
						break;
					case 36:
						{ return (new Yytoken(39,yytext(),yyline,yychar,yychar+4)); }
					case -37:
						break;
					case 37:
						{ return (new Yytoken(30,yytext(),yyline,yychar,yychar+4)); }
					case -38:
						break;
					case 38:
						{ return (new Yytoken(9,yytext(),yyline,yychar,yychar+4)); }
					case -39:
						break;
					case 39:
						{ return (new Yytoken(32,yytext(),yyline,yychar,yychar+4)); }
					case -40:
						break;
					case 40:
						{ return (new Yytoken(40,yytext(),yyline,yychar,yychar+4)); }
					case -41:
						break;
					case 41:
						{ return (new Yytoken(35,yytext(),yyline,yychar,yychar+5)); }
					case -42:
						break;
					case 42:
						{ return (new Yytoken(37,yytext(),yyline,yychar,yychar+5)); }
					case -43:
						break;
					case 43:
						{ return (new Yytoken(33,yytext(),yyline,yychar,yychar+6)); }
					case -44:
						break;
					case 44:
						{ return (new Yytoken(31,yytext(),yyline,yychar,yychar+6)); }
					case -45:
						break;
					case 45:
						{ return (new Yytoken(41,yytext(),yyline,yychar,yychar+6)); }
					case -46:
						break;
					case 46:
						{ return (new Yytoken(34,yytext(),yyline,yychar,yychar+7)); }
					case -47:
						break;
					case 47:
						{ return (new Yytoken(36,yytext(),yyline,yychar,yychar+8)); }
					case -48:
						break;
					case 49:
						{
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -49:
						break;
					case 50:
						{
        System.out.println("ERROR LEXICO: " + yytext() + " Linea No."+yyline+", columna No."+yychar);
	Utility.error(Utility.E_UNMATCHED);
}
					case -50:
						break;
					case 51:
						{
	String str =  yytext().substring(1,yytext().length() - 1);
	Utility.confirmar(str.length() == yytext().length() - 2);
	return (new Yytoken(42,str,yyline,yychar,yychar + str.length()));
}
					case -51:
						break;
					case 53:
						{
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -52:
						break;
					case 54:
						{
        System.out.println("ERROR LEXICO: " + yytext() + " Linea No."+yyline+", columna No."+yychar);
	Utility.error(Utility.E_UNMATCHED);
}
					case -53:
						break;
					case 56:
						{
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -54:
						break;
					case 57:
						{
        System.out.println("ERROR LEXICO: " + yytext() + "\nLinea No."+yyline+", columna No."+yychar);
	Utility.error(Utility.E_UNMATCHED);
}
					case -55:
						break;
					case 59:
						{
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -56:
						break;
					case 60:
						{
        System.out.println("ERROR LEXICO: " + yytext() + "\nLinea No."+yyline+", columna No."+yychar);
	Utility.error(Utility.E_UNMATCHED);
}
					case -57:
						break;
					case 62:
						{
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -58:
						break;
					case 63:
						{
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -59:
						break;
					case 64:
						{
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -60:
						break;
					case 65:
						{
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -61:
						break;
					case 66:
						{
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -62:
						break;
					case 67:
						{
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -63:
						break;
					case 68:
						{
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -64:
						break;
					case 69:
						{
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -65:
						break;
					case 70:
						{
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -66:
						break;
					case 71:
						{
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -67:
						break;
					case 72:
						{
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -68:
						break;
					case 73:
						{
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -69:
						break;
					case 74:
						{
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -70:
						break;
					case 75:
						{
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -71:
						break;
					case 76:
						{
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -72:
						break;
					case 77:
						{
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -73:
						break;
					case 78:
						{
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -74:
						break;
					case 79:
						{
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -75:
						break;
					case 80:
						{
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -76:
						break;
					case 81:
						{
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -77:
						break;
					case 82:
						{
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -78:
						break;
					case 83:
						{
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -79:
						break;
					case 84:
						{
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -80:
						break;
					case 85:
						{
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -81:
						break;
					case 86:
						{
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -82:
						break;
					case 87:
						{
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -83:
						break;
					case 88:
						{
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -84:
						break;
					case 89:
						{
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -85:
						break;
					case 90:
						{
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -86:
						break;
					case 91:
						{
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -87:
						break;
					case 92:
						{
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -88:
						break;
					case 93:
						{
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -89:
						break;
					case 94:
						{
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -90:
						break;
					case 95:
						{
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -91:
						break;
					case 96:
						{
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -92:
						break;
					case 97:
						{
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -93:
						break;
					case 98:
						{
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -94:
						break;
					case 99:
						{
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -95:
						break;
					case 100:
						{
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -96:
						break;
					case 101:
						{
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -97:
						break;
					case 102:
						{
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -98:
						break;
					case 103:
						{
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -99:
						break;
					case 104:
						{
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -100:
						break;
					case 105:
						{
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -101:
						break;
					case 106:
						{
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -102:
						break;
					case 107:
						{
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -103:
						break;
					case 108:
						{
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -104:
						break;
					case 109:
						{
	return (new Yytoken(45,yytext(),yyline,yychar,yychar + yytext().length()));
}
					case -105:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
