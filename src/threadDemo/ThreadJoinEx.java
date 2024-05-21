/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadDemo;

/**
 *
 * @author Hung
 */
public class ThreadJoinEx  implements Runnable {
 
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("This is message #" + i);
 
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                System.out.println("I'm about to stop");
                return;
            }
        }
    }
 
    public static void main(String[] args) {
        Thread t1 = new Thread(new ThreadJoinEx());
        t1.start();
 
        try {
 
            t1.join();
 
        } catch (InterruptedException ex) {
            // do nothing
        }
 
        System.out.println("I'm " + Thread.currentThread().getName());
    }
}
