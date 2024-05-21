/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package socket;

/**
 *
 * @author ManhHung
 */
class Consumer extends Thread
{
  //  private Buffer sharedBuffer;
   private BufferSynchronized sharedBuffer;
   // public Consumer( Buffer shared )	
   public Consumer( BufferSynchronized shared )
	{
		super( "Consumer" );
		sharedBuffer = shared;
	}
        public void run(){
		for ( int count = 1; count <= 5; count++ ){ //
		    try{
		    	Thread.sleep((int)(Math.random() *3000));
		    	System.out.println( "Consumer reads " +
						sharedBuffer.get());
		    }catch (InterruptedException e){
		    	e.printStackTrace();
		    }
		}
		System.out.println( getName() + " finished.");
	}
}
