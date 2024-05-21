/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketJavaNIO_Sosanh;

import java.io.*;
import java.net.*;

public class IoEchoServer {

    public static void main(String[] args) throws IOException { 
        ServerSocket serverSocket = null;  
            serverSocket = new ServerSocket(10007); 
        Socket clientSocket = null;
        System.out.println("Waiting for connection.....");  
            clientSocket = serverSocket.accept();// blocking
        
        System.out.println("Connection successful");  
        PrintWriter out 
                =  new PrintWriter(clientSocket.getOutputStream(),  true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream())); 
        String inputLine; 
        while ((inputLine = in.readLine()) != null) {
            System.out.println("Server: " + inputLine);
            out.println(inputLine); 
            if (inputLine.equals("Bye.")) {
                break;
            }
        }

        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();

//        ServerSocket serverSocket = new ServerSocket(7000);
//        boolean active=true;
//        while (active) {
//            Socket socket = serverSocket.accept(); // blocking
//            InputStream is = socket.getInputStream();
//            OutputStream os = socket.getOutputStream();
//            int read;
//            byte[] bytes = new byte[1024];
//            while ((read = is.read(bytes)) != -1) { // blocking
//                os.write(bytes, 0, read); // blocking
//            }
//            socket.close();
//        }
//        serverSocket.close();
    }
}
