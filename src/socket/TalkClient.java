/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package socket;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author ManhHung
 */
public class TalkClient {
public static void main(String arg[]) throws UnknownHostException, IOException{
    FileOutputStream fo = new FileOutputStream("Test.txt");
    DataOutputStream dos=  new DataOutputStream(fo);
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(dos, "utf-8"));


    try{
            //  Tạo ra một socket để kết nối với socket server
          Socket connectToServer = new Socket("localhost", 5050);
            //  Tạo ra một luồng vào để nhận dữ liệu từ server
          DataInputStream isFromServer
                  = new DataInputStream(connectToServer.getInputStream());
            //  Tạo ra một luồng ra để gửi dữ liệu đến server
            DataOutputStream osToServer
                    = new DataOutputStream(connectToServer.getOutputStream());
            //  Tạo ra một luồng vào để nhập dữ liệu từ bàn phím

            //DataInputStream str = new DataInputStream(System.in);
           //  Liên tục gửi dữ liệu nhập vào từ bàn phím cho server và nhận lại diện tích
            while(true){
                 // Gửi dữ liệu cho server
              osToServer.writeUTF("Độc lập, Tự do, Hạnh phúc");
              osToServer.flush();
                 // Nhận lại kết quả từ server
              String strR = isFromServer.readUTF();
              System.out.println("from Server =" + strR);
              bw.write(strR);
              bw.newLine();

                    }
            }catch(IOException ex){
                    System.err.println(ex);
            }
    bw.close();
    }
}
