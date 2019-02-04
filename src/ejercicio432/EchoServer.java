/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio432;

import java.net.*;
import java.io.*;

public class EchoServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35020);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String inputLine, outputLine;

        Double resp = 0.0;
        int numero;
        String g;
        String Operacion;
        while ((inputLine = in.readLine()) != null) {

            System.out.println("que recibimos +" + inputLine);
            if (inputLine.contains("fun:")) {
                Operacion = inputLine.substring(4);
                
                if (Operacion.contains("sen")) {
                    System.out.println("Se calculara Seno");
                    System.out.println("Ingrese el numero");
                    inputLine = in.readLine();
                    numero = Integer.parseInt(inputLine);
                    resp = Math.sin(numero);
                    System.out.println("EL resultado es:" + resp);
                } else if (Operacion.contains("cos")) {
                    System.out.println("Se calculara Cos");
                    System.out.println("Ingrese el numero");
                    inputLine = in.readLine();
                    numero = Integer.parseInt(inputLine);
                    resp = Math.cos(numero);
                    //System.out.println("el resutado es putos"+resp);
                    System.out.println("EL resultado es:" + resp);

                } else if (Operacion.contains("tan")) {
                    System.out.println("Se calculara Tan");
                    System.out.println("Ingrese el numero");
                    inputLine = in.readLine();
                    numero = Integer.parseInt(inputLine);
                    resp = Math.tan(numero);
                    System.out.println("EL resultado es:" + resp);

                } else {
                    System.out.println("Se calculara cos");
                    System.out.println("Ingrese el numero (por defecto se calculara coseno)");
                    inputLine = in.readLine();
                    numero = Integer.parseInt(inputLine);
                    resp = Math.cos(numero);
                    System.out.println("EL resultado es:" + resp);
                }

            }
            g = inputLine;
            outputLine = "Esto regresa" + resp;
            out.println(outputLine);
            if (outputLine.equals("Respuestas: Bye.")) {
                break;
            }
        }
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}
