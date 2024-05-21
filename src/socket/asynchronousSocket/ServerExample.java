package socket.asynchronousSocket;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.Future;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.net.InetSocketAddress;

public class ServerExample { 
    public static void main (String [] args)  throws Exception {
	ServerExample ex= new ServerExample();
        ex.startServer();
    } 
    private void startServer() throws IOException, InterruptedException
            , ExecutionException { 
        AsynchronousServerSocketChannel serverChannel 
                = AsynchronousServerSocketChannel.open();
        InetSocketAddress hostAddress = new InetSocketAddress("localhost", 3883); 
        serverChannel.bind(hostAddress); 
        System.out.println("Server channel bound to port: " + hostAddress.getPort());
        System.out.println("Waiting for client to connect... "); 
        Future<AsynchronousSocketChannel> acceptResult = serverChannel.accept();
        AsynchronousSocketChannel clientChannel = acceptResult.get(); 
        System.out.println("Messages from client: "); 
        if ((clientChannel != null) && (clientChannel.isOpen())) { 
            while (true) { 
                ByteBuffer buffer = ByteBuffer.allocate(32);
                Future<Integer> result = clientChannel.read(buffer); 
                while (!result.isDone()) {   // do nothing
                } 
                buffer.flip();
                String message = new String(buffer.array()).trim();
                System.out.println(message); 
                if (message.equals("Bye.")) { 
                    break; // while loop
                } 
                buffer.clear(); 
            } // while() 
            clientChannel.close(); 
        } // end-if 
        serverChannel.close();
    }
}
