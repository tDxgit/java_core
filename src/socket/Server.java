
package socket;

import java.io.*;
import java.net.*;

public class Server {

    public static void main(String arg[]) throws IOException {

   //singleClient();
     multiClient();
    }

public static void singleClient() throws IOException {       
            // Tạo ra socket server
            ServerSocket serverSocket = new ServerSocket(8000);
            // Lắng nghe yêu cầu kết nối trên socket server
            Socket connectToClient = serverSocket.accept();
            // Tạo ra một luồng vào để nhận dữ liệu từ Client
            DataInputStream isFromClient = new DataInputStream(connectToClient.getInputStream());
            // Tạo ra một luồng ra để gửi dữ liệu cho Client
            DataOutputStream osToClient = new DataOutputStream(connectToClient.getOutputStream());
            // Liên tục nhận, xử lý và gửi kết quả lại cho Client
            while (true) {
                // Đọc một số double từ Client
                double radius = isFromClient.readDouble();
                System.out.println("Ban kinh nhan tu Client: " + radius);
                double area = radius * radius * Math.PI;
                // Gửi kết quả: diện tích kiểu double cho Client
                osToClient.writeDouble(area);
                osToClient.flush();
                System.out.//
                println("Hinh tron co ban kinh: " + radius + " co dien tich: " + area);
            }
        }
    
    public static void multiClient() {
        try {
            // Tạo ra socket server
            ServerSocket serverSocket = new ServerSocket(8000);
            while (true) {
                //Socket connectToClient = serverSocket.accept();
                ThreadServer thread1 = new ThreadServer(serverSocket.accept());
                Thread thread = new Thread (thread1);
                thread.start();
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());

        }
    }

}

class ThreadServer implements Runnable {
    Socket sk;
 public  ThreadServer(Socket sk) throws IOException
 {
      this.sk= sk;
 }
    @Override
    public void run()
    {
        try {
         DataInputStream isFromClient = new DataInputStream(sk.getInputStream());
            // Tạo ra một luồng ra để gửi dữ liệu cho Client
         DataOutputStream osToClient = new DataOutputStream(
             sk.getOutputStream());
            // Liên tục nhận, xử lý và gửi kết quả lại cho Client
            while (true) {
                // Đọc một số double từ Client
                double radius = isFromClient.readDouble();
                
                System.out.println("Ban kinh nhan tu Client: " + radius);
                double area = radius * radius * Math.PI;
                // Gửi kết quả: diện tích kiểu double cho Client
                osToClient.writeDouble(area);
                osToClient.flush();
                System.out.//
              println("Hinh tron co ban kinh: " + radius + " co dien tich: " + area);
            }
    }
    catch(Exception e){

    }

}
}
