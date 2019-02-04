/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorio3;

import java.io.*;
import java.net.*;

/**
 *
 * @author cesar
 */
public class URLReader {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MalformedURLException {
        // TODO code application logic here
        try {
            URL pagina = new URL("https://www.google.com");
            System.out.println("Miremos los datos de la pagina :"+"protocolo : "+pagina.getProtocol());
            System.out.println("Miremos los datos de la pagina :"+"AUtoridad : "+pagina.getAuthority());
            System.out.println("Miremos los datos de la pagina :"+"Host : "+ pagina.getHost());
            System.out.println("Miremos los datos de la pagina :"+"Puerto : "+pagina.getPort());
            System.out.println("Miremos los datos de la pagina :"+"Path : "+pagina.getPath());            
            System.out.println("Miremos los datos de la pagina :"+"Query : "+pagina.getQuery());            
            System.out.println("Miremos los datos de la pagina :"+"File : "+pagina.getFile());            
            System.out.println("Miremos los datos de la pagina :"+"Ref : "+pagina.getRef());            
            
            
            
        } catch (MalformedURLException e) {
            
            e.printStackTrace();
            
        }
    }

}
