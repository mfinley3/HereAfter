package units;

import item.Item;
import item.ItemType;

/**
 * @author Chioke
 * 
 * The Class Ranger.
 */
public class Ranger extends Unit {

	/**
	 * Instantiates a new ranger.
	 *
	 * @param difficulty the difficulty
	 */
	public Ranger(double difficulty) {
		// Unit Type, Given Item, Attack, Defense, Health, Movement, Range, Difficulty multiplier
		
		// Attack is doubled at creation so Attack is actually 50
		super("Ranger", new Item("Ranger Rifle", ItemType.ATK), 25, 15, 100, 8, 3, difficulty);
	}
	
} // end of Ranger
