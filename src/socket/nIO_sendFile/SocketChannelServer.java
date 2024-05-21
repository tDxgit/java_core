/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket.nIO_sendFile;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SocketChannelServer {
   public static void main(String[] args) throws IOException {
     System.out.println("Server");
      SocketChannel server = SocketChannel.open();
      SocketAddress socketAddr = new InetSocketAddress("localhost", 9000);
      server.connect(socketAddr); 
      Path path = Paths.get("D:/SocketChannelServer.txt");
      FileChannel fileChannel = FileChannel.open(path);
      ByteBuffer buffer = ByteBuffer.allocate(1024);
      while(fileChannel.read(buffer) > 0) {
         buffer.flip();
         server.write(buffer);
         buffer.clear();
      }
      fileChannel.close();
      System.out.println("File Sent");
      server.close();
   }
}