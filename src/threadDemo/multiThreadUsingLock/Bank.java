/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadDemo.multiThreadUsingLock;
import java.util.concurrent.locks.*; 
public class Bank {
    public static final int MAX_ACCOUNT = 10;
    public static final int MAX_AMOUNT = 10;  
    public static final int INITIAL_BALANCE = 100; 
    private Account[] accounts = new Account[MAX_ACCOUNT]; 
    private Lock bankLock; 
    public Bank() {
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new Account(INITIAL_BALANCE);
        } 
        bankLock = new ReentrantLock();
    } 
    public void transfer(int from, int to, int amount) {
       bankLock.lock(); 
        try {
            if (amount <= accounts[from].getBalance()) {
                accounts[from].withdraw(amount);
                accounts[to].deposit(amount); 
                String message = "%s transfered %d from %s to %s. Total balance: %d\n";
                String threadName = Thread.currentThread().getName();
                System.out.printf(message, threadName, amount, from, to, getTotalBalance());
            }
        } finally {
            bankLock.unlock();
        }
    } 
    public int getTotalBalance() {
        bankLock.lock(); 
        try {
            int total = 0; 
            for (int i = 0; i < accounts.length; i++) {
                total += accounts[i].getBalance();
            }
             return total;
        } finally {
            bankLock.unlock();
        }
    }
}
