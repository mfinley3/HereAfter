/**
 * 
 */
package aitype;

import java.awt.Point;
import java.util.ArrayList;

import units.*;

// TODO: Auto-generated Javadoc
/**
 * The Class AIBehavior.
 *
 * @author Brian Seaman
 */
public class AIBehavior {
	
	/** The player local. */
	private ArrayList<Point> playerLocal;
	
	/**
	 * Instantiates a new AI behavior.
	 */
	public AIBehavior(){
		
	}
	
	/**
	 * Update.
	 *
	 * @param o the o
	 */
	public void update(ArrayList<Point> o){
		 playerLocal = o;
	}
	
	/**
	 * Move unit.
	 *
	 * @param u the u
	 * @param row the row
	 * @param col the col
	 */
	public void moveUnit(Unit u, int row, int col){
		
	}
	
	/**
	 * Check if nearby.
	 *
	 * @param row the row
	 * @param col the col
	 */
	public void checkIfNearby(int row, int col){
		
	}
}