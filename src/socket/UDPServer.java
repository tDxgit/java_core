package socket;

/**
 *
 * @author ManhHung
 */
import java.io.*;
import java.net.*;

class UDPServer {

    public static void main(String args[]) throws Exception {
        try {
            DatagramSocket serverSocket = new DatagramSocket(9879);
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];
            while (true) {
                receiveData = new byte[1024];
                DatagramPacket receivePacket
                        = new DatagramPacket(receiveData, receiveData.length);
                System.out.println("Waiting for datagram packet");
                serverSocket.receive(receivePacket);
                String sentence = new String(receivePacket.getData(), 0, receivePacket.getLength());
                InetAddress IPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();
                System.out.println("From: " + IPAddress + ":" + port);
                System.out.println("Message: " + sentence);
                // Chuyen thanh chu hoa gui lai Client
                String capitalizedSentence = sentence.toUpperCase();
                sendData = capitalizedSentence.getBytes();
                DatagramPacket sendPacket
                        = new DatagramPacket(sendData, sentence.length(), IPAddress,
                                port);
                serverSocket.send(sendPacket);
            }
        } catch (SocketException ex) {
            System.out.println("UDP Port 9876 is occupied.");
            System.exit(1);
        }

    }
}
