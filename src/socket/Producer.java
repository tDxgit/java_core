/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package socket;

/**
 *
 * @author ManhHung
 */
class Producer extends Thread
{
 //private Buffer sharedBuffer;
   private BufferSynchronized sharedBuffer;
//public Producer(Buffer shared)
public Producer( BufferSynchronized shared )
{
	super( "Producer" );
	sharedBuffer = shared;
}
public void run(){
	   for ( int count = 1; count <= 5; count++ ){ // So cai banh laf 5
		try{
		    Thread.sleep((int)(Math.random() * 3000));//
		    sharedBuffer.set( count );//
		    System.out.println( "Producer writes " + count);
		} catch (InterruptedException e){
		    e.printStackTrace();
		}
	   }
	   System.out.println( getName() + " finished.");
    }
}