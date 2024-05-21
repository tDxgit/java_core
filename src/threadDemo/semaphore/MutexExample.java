/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadDemo.semaphore;

import java.util.concurrent.Semaphore;

public class MutexExample {
 
    private static Semaphore semaphore = new Semaphore(1);
 
    public static void main(String[] args) {
        System.out.println("Total available Semaphore permits: " 
                + semaphore.availablePermits());
        for (int i = 1; i <= 6; i++) {
            WorkerThread atmWorker = new WorkerThread(semaphore, "AMT " + i);
            atmWorker.start();
        }
    }
}