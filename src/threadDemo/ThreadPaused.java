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
 
public class ThreadPaused implements Runnable {
 
    public void run() {
 
        for (int i = 1; i <= 5; i++) {
 
            System.out.println(i);
 
            try {
 
                Thread.sleep(2000);
               
                                
 
            } catch (InterruptedException ex) {
                System.out.println("I'm interrupted");
            }
        }
    }
 
    public static void main(String[] args) {
        Runnable task = new ThreadPaused();
        Thread thread = new Thread(task);
        
        thread.start();
    }
 
}
