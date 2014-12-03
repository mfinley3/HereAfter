/**
 * 
 */
package aitype;

import java.awt.Point;
import java.util.ArrayList;

import units.*;

/**
 * @author Brian Seaman
 *
 */
public class AIBehavior {
	private ArrayList<Point> playerLocal;
	
	public AIBehavior(){
		
	}
	
	public void update(ArrayList<Point> o){
		 playerLocal = o;
	}
	
	public void moveUnit(Unit u, int row, int col){
		
	}
	
	public void checkIfNearby(int row, int col){
		
	}
}