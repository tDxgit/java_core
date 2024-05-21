/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package socket;

/**
 *
 * @author ManhHung
 */
public class BufferSynchronized {// Thiết kế lại lớp Buffer
	private int buffer = -1;// mang hang doi quay vong
	private boolean writable = true;
        public  BufferSynchronized (){}  

        public synchronized void set( int value ){
                while (!writable){
                        try{
                             wait();
                        }catch (InterruptedException e){
                                e.printStackTrace();
                        }
                }
                buffer = value;
                writable = false;
                notify();
        }
      public synchronized int get(){
            while(writable ){
                    try{
                            wait();
                    } catch (InterruptedException e){
                            e.printStackTrace();
                    }
            }
            writable = true;
            notify();
            return buffer;
            }
}
