/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketJavaNIO_Sosanh;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 *
 * @author Hung
 */
public class NioBlockingEchoClient {

    public static void main(String[] args) 
            throws IOException, InterruptedException {
        InetSocketAddress hostAddress = new InetSocketAddress("localhost", 7000); 
        SocketChannel client = SocketChannel.open(hostAddress);

        System.out.println("Client... started");
        String threadName = Thread.currentThread().getName();   
        // Send messages to server
        String[] messages = new String[]{threadName + ": test1", threadName
            + ": test2", threadName + ": test3"};  
        for (int i = 0; i < messages.length; i++) {
            byte[] message = new String(messages[i]).getBytes();
            ByteBuffer buffer = ByteBuffer.wrap(message); 
            client.write(buffer);
            System.out.println("Thông tin gửi đi: " + messages[i]);
            buffer.clear();
             Thread.sleep(100); 
            // Nhận về từ Server
            ByteBuffer buffer1 = ByteBuffer.allocate(1024);
            int read = client.read(buffer1); // blocking  
            buffer1.flip();
            String st = new String(buffer1.array(), "UTF-8"); 
            System.out.println("Nhận lại: "+ st); 
        } 
         
        client.close();
    }
}
