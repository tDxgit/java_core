/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadDemo.semaphore;

import java.util.concurrent.Semaphore;

public class SemaphoreExample {
 
    private static Semaphore semaphore 
            = new Semaphore(4);// 4 tickets
 
    public static void main(String[] args) {
        System.out.println("Total available Semaphore permits: " 
                + semaphore.availablePermits());
        for (int i = 1; i <= 6; i++) {
            WorkerThread atmWorker 
                    = new WorkerThread(semaphore, "Truy cập " + i);
            atmWorker.start();
        }
    }
}