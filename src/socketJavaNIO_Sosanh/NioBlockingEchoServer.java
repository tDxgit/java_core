/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketJavaNIO_Sosanh;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;

public class NioBlockingEchoServer {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost", 7000));
        boolean active=true;
        Stop:
        while (active) {
            SocketChannel socketChannel = serverSocketChannel.accept(); // blocking
          System.out.println(" Đã kết nối");
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            ByteBuffer buffer1 = ByteBuffer.allocate(1024);
            while (true) {
                buffer.clear();
                  System.out.println(" Chờ nhận  ");
                int read = socketChannel.read(buffer); // blocking 
                if (read < 0) { 
                    System.out.println("Client đóng kết nối");
                    socketChannel.close();
                    break Stop;
                }
                buffer.flip();
                String st = new String(buffer.array(), "UTF-8");
                System.out.println("Nhận được: " + st); 
                // Gửi trả lại Client 
                byte[] bt= (";===;"+ st  ).getBytes(); 
                 buffer1= ByteBuffer.wrap(bt ); 
                  socketChannel.write(buffer1); // có thể non-blocking phụ thuộc vào bộ đệm 
            }
            
        }
        serverSocketChannel.close();
    }
}
