/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package socket;

/**
 *
 * @author ManhHung
 */
import java.net.*;
class InetAdd {
public static void main(String[] args)
{
    try {
        InetAddress address = InetAddress.getByName("www.vnexpress.net");
        System.out.println(address);
    }
        catch (UnknownHostException ex) {
        System.out.println("Could not find www.vnexpress.net");
    }
}
}
