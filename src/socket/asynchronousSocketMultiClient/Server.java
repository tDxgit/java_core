/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket.asynchronousSocketMultiClient;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Server { 
public static void main(String[] args) {
ExecutorService taskExecutor 
        = Executors.newCachedThreadPool(Executors.defaultThreadFactory()); 
try (AsynchronousServerSocketChannel asyncServerChannel
        = AsynchronousServerSocketChannel.open()) {
if (asyncServerChannel.isOpen()) {
    asyncServerChannel.setOption(StandardSocketOptions.SO_RCVBUF, 4 * 1024);
     asyncServerChannel.setOption(StandardSocketOptions.SO_REUSEADDR, true);
    asyncServerChannel.bind(new InetSocketAddress("localhost", 5555));
    System.out.println("Waiting for connections ...");

    while (true) {
        Future<AsynchronousSocketChannel> asynchFuture
                = asyncServerChannel.accept(); 
        try {
            final AsynchronousSocketChannel asyncChannel = asynchFuture.get();
                 Callable<String> worker = new Callable<String>() { 
                @Override
                public String call() throws Exception { 
                    String host = asyncChannel.getRemoteAddress().toString();
                    System.out.println("Incoming connection from: " + host);

                    final ByteBuffer buffer = ByteBuffer.allocateDirect(1024);  
                    while (asyncChannel.read(buffer).get() != -1) { 
                        buffer.flip();  // 
                        System.out.println(buffer.array().toString());
                      System.out.println("Nhận từ: " );
                         String st = new String(buffer.array(), "UTF-8");

                         System.out.println("Nhận từ: " + st);

                        asyncChannel.write(buffer).get(); 
                        if (buffer.hasRemaining()) {
                            buffer.compact();
                        } else {
                            buffer.clear();
                        }
                    } 
                    asyncChannel.close();
                    System.out.println(host + " was successfully served!");
                    return host;
                }
            };
            taskExecutor.submit(worker);
        } catch (InterruptedException | ExecutionException ex) {
            System.err.println(ex);
            System.err.println("\n Server is shutting down ...");
            taskExecutor.shutdown();
            while (!taskExecutor.isTerminated()) {
            }
            break;
        }
    }
    } else {
    System.out.println("The asynchronous server-socket channel cannot be opened!");
    }
} catch (IOException ex) {
    System.err.println(ex);
}
}
}
