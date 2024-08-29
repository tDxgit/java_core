package java8;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

//A lambda expression :
//short block of code which takes in parameters and returns a value. 
//similar to methods
//but they do not need a name and they can be implemented right in the body of a method.


interface StringFunction {
	  String run(String str);
	  //String run1(String str, int val);
}
public class lambdaExpressions {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> intlist = new ArrayList<Integer>();
		intlist.add(1);
		intlist.add(1);
		intlist.add(2);
		intlist.add(9);
		List<Integer> intlist1 = new ArrayList<Integer>();
		
		//Ex1 function in method
		intlist.forEach(t -> {System.out.println(t+3); if(t==2) { intlist1.add(t); }});
		System.out.println(intlist1.get(0));
		
		//Ex2 Consumer interface to store a lambda expression in a variable
		Consumer<Integer> method = (n) -> { System.out.print(n + " "); };
		intlist.forEach(method);
		
		//Ex3 Create a method which takes a lambda expression as a parameter
		StringFunction exclaim = (s) -> s + "!";
	    StringFunction ask = (s) -> s + "?";
	    System.out.println(ask.run("Are you got it"));
	    System.out.println(exclaim.run("No"));
	}

}
