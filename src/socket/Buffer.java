/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package socket;

/**
 *
 * @author ManhHung
 */
class Buffer{
	private int buffer = -1;
	public void set( int value )
        {
		buffer = value;
	}
	public int get(){
		return buffer;
	}
}

