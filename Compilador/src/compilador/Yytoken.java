/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

/**
 *
 * @author Usuario
 */
class Yytoken {

    Yytoken(
            int index,
            String text,
            int line,
            int charBegin,
            int charEnd
    ) {
        m_index = index;
        m_text = new String(text);
        m_line = line;
        m_charBegin = charBegin;
        m_charEnd = charEnd;
    }

    public int m_index;
    public String m_text;
    public int m_line;
    public int m_charBegin;
    public int m_charEnd;

    public String toString() {
        return "Token #" + m_index + ": " + m_text + " (line " + m_line + ")";
    }
}
