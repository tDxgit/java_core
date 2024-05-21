/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author ManhHung
 */
public class TalkServer {
    public static void main (String [] args) throws IOException{
                try {
            // Tạo ra socket server
            ServerSocket serverSocket = new ServerSocket(5050);
            // Lắng nghe yêu cầu kết nối trên socket server
            Socket connectToClient = serverSocket.accept();
            // Tạo ra một luồng vào để nhận dữ liệu từ Client
            DataInputStream isFromClient
                    = new DataInputStream(connectToClient.getInputStream());
            // Tạo ra một luồng ra để gửi dữ liệu cho Client
            DataOutputStream osToClient
                    = new DataOutputStream(
                    connectToClient.getOutputStream());
            // Liên tục nhận, xử lý và gửi kết quả lại cho Client
            while (true) {
                // Đọc  từ Client
                String str = isFromClient.readUTF();
                System.out.println("from client = " + str);
                
                // Gửi  cho Client
                osToClient.writeUTF("Cộng Hòa Xã Hội Chủ Nghĩa Việt Nam");
                osToClient.flush();
               
            }
        } catch (IOException ex) {
            System.err.println(ex);

    }

}
}
