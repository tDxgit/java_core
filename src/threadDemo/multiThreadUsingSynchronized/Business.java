/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadDemo.multiThreadUsingSynchronized;

/**
 *
 * @author Hung
 */
public class Business {
 
    private Object lock1 = new Object();
    private Object lock2 = new Object();
 
    public void foo() {
        synchronized (lock1) {
            synchronized (lock2) {
                System.out.println("foo");
            }
        }
    }
 
    public void bar() {
        synchronized (lock2) {
            synchronized (lock1) {
                System.out.println("bar");
            }
        }
    }
}