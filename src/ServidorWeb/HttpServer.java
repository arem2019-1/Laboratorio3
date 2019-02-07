/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServidorWeb;

import ejercicoi451.*;
import ejercicio44.*;
import java.awt.image.BufferedImage;
import java.net.*;
import java.io.*;
import static java.lang.System.out;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
//import sun.misc.IOUtils;

public class HttpServer {

    static private Socket clientSocket;

    public static void main(String[] args) throws IOException {
        String tmpp = "";
        String pagina;

        List<ServerSocket> listServers = new ArrayList<ServerSocket>();
        int tmp = 0;
        ServerSocket serverSocket = null;
        while (true) {

            try {
                //serverSocket = new ServerSocket(35000);
                serverSocket = new ServerSocket(35000);

            } catch (IOException e) {
                System.err.println("Could not listen on port: 35000.");
                System.exit(1);
            }
            clientSocket = null;
            try {

                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()
                    ));
            String inputLine, outputLine;
            BufferedImage imagen = null;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine);
                
                try {
                    System.out.println("Miremos in : " + inputLine);
                    FileReader f = new FileReader("src/archivos/pagSW.html");
                    pngResource("bug.png");
                    //File ima=new File("src/archivos/bug.png");
                    ///imagen=ImageIO.read(ima);
                    //ByteArrayOutputStream output = new ByteArrayOutputStream();
                    //byte[] buffer                = new byte[ 1024 ];
                    //FileInputStream myStream = new FileInputStream("src/archivos/bug.png");
                    //BufferedImage image = ImageIO.read(new File("src/archivos/bug.png")); 
                    //ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); 
                    //ImageIO.write(image, "png", byteArrayOutputStream); 
                    //byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array(); 
                    BufferedReader b = new BufferedReader(f);
                    while ((pagina = b.readLine()) != null) {
                        System.out.println(pagina);
                        tmpp += pagina;
                    }

                } catch (Exception e) {

                    System.err.print("No se encontro pagina");
                    break;
                }
                pngResource("bug.png");
                //src/archivos/pagSW.html
                if (!in.ready()) {
                    break;
                }
            }
            
            outputLine = "HTTP/1.1 200 OK\r\n"
                    + "Content-Type: text/html\r\n"
                    + "\r\n"
                    + tmpp;
            
            out.println(outputLine);
            
            out.close();
            in.close();
            
            clientSocket.close();
            serverSocket.close();
            
            tmp++;
        }
    }

    private static DataOutputStream pngResource(String resource) {

//        PrintWriter out = new PrintWriter(
//                this.clientSocket.getOutputStream(), true);
//
//        String outputLine = "HTTP/1.1 404 Not Found\r\n"
//                + "Content-Type: image/png\r\n"
//                + "\r\n"
//                + "<!DOCTYPE html>"
//                + "<html>"
//                + "<head>"
//                + "<meta charset=\"UTF-8\">"
//                + "<title>"+ resource.replaceFirst("/","") +"</title>\n"
//                + "</head>"
//                + "<body>"
//                + "<img src=resources"+resource+"/>"
//                + "</body>"
//                + "</html>";
//
//        out.println(outputLine);
//        out.close();
        try {
            File graphicResource = new File("src/archivos/" + resource);
            FileInputStream inputImage = new FileInputStream(graphicResource);
            byte[] bytes = new byte[(int) graphicResource.length()];
            inputImage.read(bytes);

            DataOutputStream binaryOut;
            binaryOut = new DataOutputStream(clientSocket.getOutputStream());
            binaryOut.writeBytes("HTTP/1.1 200 OK \r\n");
            binaryOut.writeBytes("Content-Type: image/png\r\n");
            binaryOut.writeBytes("Content-Length: " + bytes.length);
            binaryOut.writeBytes("\r\n\r\n");
            binaryOut.write(bytes);
            binaryOut.close();
            return binaryOut; 
        } catch (IOException ex) {
            //raise404();
            
            System.err.println("Error en la lectura de el archivo");
        }
        return null;
    }

    private  void raise404() {
        
        try {
             PrintWriter out;
            out = new PrintWriter(this.clientSocket.getOutputStream(), true);
            String outputLine = "HTTP/1.1 404 Not Found\r\n"
                    + "Content-Type: text/html\r\n"
                    + "\r\n"
                    + "<!DOCTYPE html>"
                    + "<html>"
                    + "<head>"
                    + "<meta charset=\"UTF-8\">"
                    + "<title>404 Not Found</title>\n"
                    + "</head>"
                    + "<body>"
                    + "<h1>Error 404: Not Found</h1>"
                    + "</body>"
                    + "</html>";

            out.println(outputLine);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
