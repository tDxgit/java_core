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
public class ThreadGroupEx  {
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup group = new ThreadGroup("GroupA");
 
        new ThreadInGroup(group, "A").start();
        new ThreadInGroup(group, "B").start();
        new ThreadInGroup(group, "C").start();
        new ThreadInGroup(group, "D").start();
 
        Thread.sleep(10);
 
        group.interrupt(); 
    }
}
