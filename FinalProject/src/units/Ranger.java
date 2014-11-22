package units;

import item.Item;
import item.ItemType;

// TODO: Auto-generated Javadoc
/**
 * The Class Ranger.
 *
 * @author Chioke
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
}
