/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio2;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 *
 * @author cesar
 */
public class Ejercicio2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        Scanner pag = new Scanner(System.in);
        String pagina = pag.nextLine();
        System.out.println("POrfavor escriba la paguina a consultar");
        URL google = new URL(pagina);
        String tmp;
        System.out.println("Hola loco: " + google);
        try {
            

            BufferedReader reader = new BufferedReader(new InputStreamReader(google.openStream()));
            System.out.println("que vemos :"+reader.readLine());
            while ((tmp = reader.readLine()) != null) {
                System.out.println(tmp);
            }

        } catch (IOException x) {
            System.err.println(x);
        }

        // TODO code application logic here
    }
}
