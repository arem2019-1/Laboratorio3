
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatagramTimeClient {

    static Timer timer;
    static int reloj = 0;
    static String hora ="";

    public static void time() {
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                reloj += 1000;
                System.out.println("que dice " + reloj);

            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

    public static void main(String[] args) throws InterruptedException {
        byte[] sendBuf = new byte[256];
 String received;
        try {
            DatagramSocket socket = new DatagramSocket();
            byte[] buf = new byte[256];
            InetAddress address = InetAddress.getByName("127.0.0.1");
            DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
            socket.send(packet);

            packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            //String
            //time();
            while (true) {
                System.out.println("buuu");
                reloj = (int) System.currentTimeMillis();
                Thread.sleep(5000);
                
                received = new String(packet.getData(), 0, packet.getLength());
                 System.out.println("mire 2 :" + received );
                long t = System.currentTimeMillis();;
                System.out.println("miremos " + (t - reloj >= 5000));
                if (t - reloj >= 5000) {
                    //reloj=0;

                    System.out.println("mire " + received );
                    if (received != null) {
                        System.out.println("La hora es nueva: " + received);
                        
                        hora = received;
                    } else {
                        System.out.println("La hora es: "+hora);

                    }
                }

            socket.send(packet);

            packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            //received = new String(packet.getData(), 0, packet.getLength());                
                
                
            }

        } catch (SocketException ex) {
            Logger.getLogger(DatagramTimeClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(DatagramTimeClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DatagramTimeClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
