/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pattern.bihaviralPattern.statePattern;
public class SubmittedState implements State {
 
    @Override
    public void handleRequest() {
        System.out.println("Submitted");
    }
}