/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadDemo.multiThreadUsingLock; 

/**
 *
 * @author Hung
 */
public class Transaction implements Runnable {
    private Bank bank;
    private int fromAccount;
 
    public Transaction(Bank bank, int fromAccount) {
        this.bank = bank;
 
 
        this.fromAccount = fromAccount;
    }
 
    public void run() {
 
        while (true) {
            int toAccount = (int) (Math.random() * Bank.MAX_ACCOUNT); 
            if (toAccount == fromAccount) continue; 
            int amount = (int) (Math.random() * Bank.MAX_AMOUNT); 
            if (amount == 0) continue; 
            bank.transfer(fromAccount, toAccount, amount); 
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
