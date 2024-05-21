/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadDemo.multiThreadUsingLockAndCondiction; 


/**
 *
 * @author Hung
 */
public class Account {
    private int balance = 0;
 
    public Account(int balance) {
        this.balance = balance;
    }
 
    // Rút tiền
    public synchronized void withdraw(int amount) {
        this.balance -= amount;
    }
    //Gửi tiền
    public synchronized void deposit(int amount) {
        this.balance += amount;
    }
    public int getBalance() {
        return this.balance;
    }
}
