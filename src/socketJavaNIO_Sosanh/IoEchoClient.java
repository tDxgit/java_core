/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketJavaNIO_Sosanh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Hung
 */
public class IoEchoClient {

    public static void main(String[] args) throws IOException {
        String serverHostname = new String("127.0.0.1");
       System.out.println("Attemping to connect to host "
                + serverHostname + " on port 10007.");
        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try { 
            echoSocket = new Socket(serverHostname, 10007);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(
                    echoSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + serverHostname);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                    + "the connection to: " + serverHostname);
            System.exit(1);
        } 
        BufferedReader stdIn = new BufferedReader(
                new InputStreamReader(System.in));
        String userInput;

        System.out.print("input: ");
        while ((userInput = stdIn.readLine()) != null) {
            out.println(userInput);
            System.out.println("echo: " + in.readLine());
            System.out.print("input: ");
        }

        out.close();
        in.close();
        stdIn.close();
        echoSocket.close();
    }
}
