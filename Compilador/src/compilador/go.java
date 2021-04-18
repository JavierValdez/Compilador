/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JFileChooser;

/**
 *
 * @author Usuario
 */
public class go {

    public static void main(String argv[]) throws java.io.IOException {
        JFileChooser file = new JFileChooser();
        file.showOpenDialog(file);
        File archivo = file.getSelectedFile();
        int Error = 0;
        String texto = "";
        if (archivo != null) {
            FileReader archivos = new FileReader(archivo);
            /*BufferedReader lee = new BufferedReader(archivos);
            String aux;
                while((aux=lee.readLine())!=null) {
                    texto += aux + "\n";
                }
                lee.close();
             */
            Yylex yy = new Yylex(archivos);
            Yytoken token;
            while ((token = yy.yylex()) != null) {
                if (token.m_text == "Error: Illegal character.") {
                    System.out.println(token + "    " + token.m_text);
                    Error++;
                }

            }
        }
        if (Error > 0) {
            System.out.println("Compilacion Fallida \nErrores Encontrados: "+Error);
        }else{
            System.out.println("Compilacion Exitosa");
        }
        System.out.println(texto);
    }
}
