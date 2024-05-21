/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package socket;

/**
 *
 * @author ManhHung
 */
public class SharedBuffer{
	public static void main( String [] args ){
		//create shared object used by threads
		//Buffer sharedBuffer = new Buffer();
                BufferSynchronized sharedBuffer = new BufferSynchronized();
		//create producer and consumer objects
		Producer producer=new Producer(sharedBuffer);
		Consumer consumer=new Consumer(sharedBuffer);
		producer.start(); 	// start producer thread
		consumer.start();   // start consumer thread
	}
}
