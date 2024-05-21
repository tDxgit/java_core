package socket.asynchronousSocket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.Future;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.net.InetSocketAddress; 
public class ClientExample {

public static void main(String[] args) throws Exception {
    ClientExample ex = new ClientExample();
    ex.startClient();
}

private void startClient()
        throws IOException, InterruptedException, ExecutionException {
    AsynchronousSocketChannel client = AsynchronousSocketChannel.open();
    InetSocketAddress hostAddress = new InetSocketAddress("localhost", 3883);
    Future<Void> future = client.connect(hostAddress);
    future.get();  

    System.out.println("Client is started: " + client.isOpen());
    System.out.println("Sending messages to server: ");

    String[] messages = new String[]{"Time goes fast.", "What now?", "Bye."};

    for (int i = 0; i < messages.length; i++) {
        byte[] message = new String(messages[i]).getBytes();
        ByteBuffer buffer = ByteBuffer.wrap(message);
        Future<Integer> result = client.write(buffer); 
        while (!result.isDone()) {
            System.out.println("... ");
        }
        System.out.println(messages[i]);
        buffer.clear();
        Thread.sleep(3000);
    } // for 
    client.close();
}
}
