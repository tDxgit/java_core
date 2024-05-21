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
class ThreadInGroup extends Thread {
    public ThreadInGroup(ThreadGroup threadGroup, String name) {
        super(threadGroup, name);
    }
 
    public void run() {
        boolean running = true;
 
        while (running) {
            try {
                System.out.println(getName() + " is running");
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                running = false;
                System.out.println(getName() + " is interrupted and then terminates");
            }
        }
    }
}