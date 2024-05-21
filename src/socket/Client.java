package socket;

/**
 *
 * @author ManhHung
 */
import java.io.*;
import java.net.*;
public class Client{
    public static void main(String arg[]){
      try{
            //  Tạo ra một socket để kết nối với socket server
          Socket connectToServer = new Socket("localhost", 8000);
            //  Tạo ra một luồng vào để nhận dữ liệu từ server
          DataInputStream isFromServer = new DataInputStream(connectToServer.getInputStream());
            //  Tạo ra một luồng ra để gửi dữ liệu đến server
          DataOutputStream osToServer = new DataOutputStream(connectToServer.getOutputStream());
            //  Tạo ra một luồng vào để nhập dữ liệu từ bàn phím
           DataInputStream str = new DataInputStream(System.in);
           //  Liên tục gửi dữ liệu nhập vào từ bàn phím cho server và nhận lại diện tích
            while(true){
                 // Nhập bán kính từ bàn phím
              System.out.print("Hay cho biet ban kinh: ");
              double radius = Double.parseDouble(str.readLine());
                 // Gửi dữ liệu cho server
              osToServer.writeDouble(radius);
              osToServer.flush();
                 // Nhận lại kết quả từ server
              double area = isFromServer.readDouble();
              System.out.println("Dien tich hinh tron nhan duoc tu Server: " +	area);
                    }
            }catch(IOException ex){
                    System.err.println(ex);
            }
    }
}
