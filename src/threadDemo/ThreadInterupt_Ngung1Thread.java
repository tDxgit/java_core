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
public class ThreadInterupt_Ngung1Thread extends Thread {  
    ThreadInterupt_Ngung1Thread(String st) {
        super.setName(st);
    } 
    public void run() {
        System.out.println("Thread running:" + this.getName());
        for (int i = 1; i <= 50; i++) {
            System.out.println("This is message #" + i);
            
            if (Thread.interrupted()) { 
                  System.out.println("I'm about to stop");
                return;
            }
        }
    }    
    public static void main(String[] args) {
        Thread t1 = new ThreadInterupt_Ngung1Thread("Tong");
        t1.start();        
        try {
            Thread mainThread = Thread.currentThread();
            System.out.println("Thread running:" + mainThread.getName());
           // Pause thread chính
            Thread.sleep(1);// Có thể xảy ra ngoại lệ
            t1.interrupt();
            
        } catch (Exception ex) { // Bắt ngoại lệ của Thread.sleep(1); 
            System.out.println(" Exception in mainthread");
        }
        
    }
}
 