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
public class TransactionTest {
    public static void main(String[] args) {
        Bank bank = new Bank();
 
        for (int i = 0; i < Bank.MAX_ACCOUNT; i++) {
            Thread t = new Thread(new Transaction(bank, i));
            t.start();
        }
    }
}
