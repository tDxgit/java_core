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
public class Account {
    private int balance = 0;
 
    public Account(int balance) {
        this.balance = balance;
    }
 // Rút tiền synchronized
    public  void withdraw(int amount) {
        this.balance -= amount;
    }
    //Gửi tiền synchronized
    public  void deposit(int amount) {
        this.balance += amount;
    }
    
    public int getBalance() {
        return this.balance;
    }
}
