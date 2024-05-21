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
 
public class ThreadInterupt_TieptucThucThi1Thread extends Thread { 
    ThreadInterupt_TieptucThucThi1Thread(String st){
        super.setName(st);
    }
    public void run() {
        System.out.println("Thread running "+ this.getName());
        try { 
             Thread.sleep(1000000); //
        } catch (InterruptedException e) {
              Thread self = Thread.currentThread();
              System.out.println("Thread "+self.getName()+" resumed");
              System.out.println("Thread "+ self.getName() + " is " + self.getState());   
        }         
    }
 
    public static void main(String args[]) {
        ThreadInterupt_TieptucThucThi1Thread 
                t1 = new ThreadInterupt_TieptucThucThi1Thread("Test");
         t1.start(); 
         t1.interrupt();
    }
}