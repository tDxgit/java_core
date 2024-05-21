package socketJavaNIO_Sosanh; 

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;

public class NioNonBlockingEchoServer { 
    private Selector selector;
    private Map<SocketChannel, List<byte[]>> dataMapper;
    private InetSocketAddress listenAddress;

    public static void main(String[] args) throws Exception {
        Runnable server1 = new Runnable() {
            @Override
            public void run() {
                try {
                    new NioNonBlockingEchoServer("localhost", 8090).startServer();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };   
        new Thread(server1).start();  
    } 
    public NioNonBlockingEchoServer(String address, int port) throws IOException {
        listenAddress = new InetSocketAddress(address, port);
        dataMapper = new HashMap<SocketChannel, List<byte[]>>();
    } 
    // create server channel	
    private void startServer() throws IOException {
        this.selector = Selector.open();
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.socket().bind(listenAddress);   // retrieve server socket and bind to port
        serverChannel.register(this.selector, SelectionKey.OP_ACCEPT);
        System.out.println("Server started...");
        while (true) { 
            this.selector.select(); // wait for events blocking
            //work on selected keys
            Iterator<SelectionKey> keys = this.selector.selectedKeys().iterator();
            while (keys.hasNext()) {
                SelectionKey key = (SelectionKey) keys.next();
                // this is necessary to prevent the same key from coming up 
                // again the next time around.
                keys.remove();
                if (!key.isValid()) {
                    continue;
                }
                if (key.isAcceptable()) {
                    System.out.println("Connected"); 
                    this.accept(key);
                } else if (key.isReadable()) { 
                    System.out.println("Read"); 
                     this.read(key); 
                } 
            }
        }
    } 
    //accept a connection made to this channel's socket
    private void accept(SelectionKey key) throws IOException {
        ServerSocketChannel serverChannel 
                = (ServerSocketChannel) key.channel();
        SocketChannel channel = serverChannel.accept();
        channel.configureBlocking(false);
        Socket socket = channel.socket();
        SocketAddress remoteAddr = socket.getRemoteSocketAddress();
        System.out.println("Connected to: " + remoteAddr);
        // register channel with selector for further IO
        dataMapper.put(channel, new ArrayList<byte[]>());
         channel.register(this.selector, SelectionKey.OP_READ); 
    }

    //read from the socket channel
    private void read(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int numRead = -1;
        numRead = channel.read(buffer);// nonblocking

        if (numRead == -1) {
            this.dataMapper.remove(channel);
            Socket socket = channel.socket();
            SocketAddress remoteAddr = socket.getRemoteSocketAddress();
            System.out.println("Connection closed by client: " + remoteAddr);
            channel.close();
            key.cancel();
            return;
        }
        buffer.flip();
        byte[] data = new byte[numRead];
        System.arraycopy(buffer.array(), 0, data, 0, numRead);
        String stNhandc=new String(data);
        System.out.println("Got: " + stNhandc); 
        // Gửi ngược lại 
        byte[] bt= (";===;"+ stNhandc  ).getBytes(); 
        buffer= ByteBuffer.wrap(bt );  
        channel.write(buffer);// nonblocking
        
         
    }
}
