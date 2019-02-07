/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datagramas;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatagramTimeServer {

    DatagramSocket socket;

    public DatagramTimeServer() {
        try {
            socket = new DatagramSocket(4445);
        } catch (SocketException ex) {
            Logger.getLogger(DatagramTimeServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void startServer() {
        byte[] buf = new byte[256];

        try {
            while (true) {
                Date date = new Date();
                DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
//hora =calendario.get(Calendar.HOUR_OF_DAY);
                //String dString = new Date().toString();
                String dString = (hourFormat.format(date));
                System.out.println("Hola... hay alguien " + hourFormat.format(date));
                buf = dString.getBytes();
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buf, buf.length, address, port);
                socket.send(packet);
            }
        } catch (IOException ex) {
            Logger.getLogger(DatagramTimeServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        socket.close();
    }

    public static void main(String[] args) {
        DatagramTimeServer ds = new DatagramTimeServer();
        ds.startServer();
    }
}
