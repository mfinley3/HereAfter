package units;

import item.Item;
import item.ItemType;

// TODO: Auto-generated Javadoc
/**
 * The Class Soldier.
 *
 * @author Chioke
 */
public class Soldier extends Unit {
	
	/**
	 * Instantiates a new soldier.
	 *
	 * @param difficulty the difficulty
	 */
	public Soldier(double difficulty) {
		// Unit Type, Given Item, Attack, Defense, Health, Movement, Range, Difficulty multiplier
		
		// Attack is doubled at creation so Attack is actually 60
		super("Soldier", new Item("Combat Rifle", ItemType.ATK), 30, 20, 100, 6, 3, difficulty);
		
	}

}