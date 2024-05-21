/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadDemo.atomicVariables;

/**
 *
 * @author Hung
 */
 
public class CounterThreadTest {
    static final int NUMBER_THREADS = 100;
 
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        System.out.println("Initial Counter = " + counter.get());
 
        UpdateCounter[] threads = new UpdateCounter[NUMBER_THREADS];
 
        for (int i = 0; i < NUMBER_THREADS; i++) {
            threads[i] = new UpdateCounter(counter);
            threads[i].start();
        }
 
        for (int i = 0; i < NUMBER_THREADS; i++) {
            threads[i].join();
        }
 
        System.out.println("Final Counter = " + counter.get());
    }
}