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
public class BusinessTest2 {
    public static void main(String[] args) {
        Business business = new Business();
 
        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                public void run() {
                    business.foo();
                }
            }).start();
        }
 
        for (int i = 0; i < 10000; i++) {
            new Thread(new Runnable() {
                public void run() {
                    business.bar();
                }
            }).start();
        }
    }
}
