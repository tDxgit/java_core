package socketJavaNIO_Sosanh;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioNonBlockingEchoClient1 {
    public static void main(String[] args) {
          Runnable client = new Runnable() {
            @Override
            public void run() {
                try {
                    new NioNonBlockingEchoClient1().startClient();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
          
         new Thread(client, "client-B").start();
      
    }
    public void startClient()  throws IOException, InterruptedException { 
        InetSocketAddress hostAddress = new InetSocketAddress("localhost", 8090);
        SocketChannel client = SocketChannel.open(hostAddress); 
        System.out.println("Client... started");
        String threadName = Thread.currentThread().getName(); 
        // Send messages to server
        String[] messages = new String[]{threadName + "#: test1", threadName
            + "#: test2", threadName + "#: test3"};

        for (int i = 0; i < messages.length; i++) {
            byte[] message = new String(messages[i]).getBytes();
            ByteBuffer buffer = ByteBuffer.wrap(message);
            client.write(buffer);
            System.out.println(messages[i]);
            buffer.clear();
          //  Thread.sleep(1000); 
             // Nhận về từ Server
            ByteBuffer buffer1 = ByteBuffer.allocate(1024);
            int read = client.read(buffer1); // blocking  
            buffer1.flip();
            String st = new String(buffer1.array(), "UTF-8");
            System.out.println("Nhận lại: " + st);
        }
        client.close();
    }
}
