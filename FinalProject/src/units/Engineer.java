package units;

import item.Item;
import item.ItemType;

/**
 * 
 * @author Chioke
 *
 */
public class Engineer extends Unit {

	public Engineer(double difficulty) {
		// Unit Type, Given Item, Attack, Defense, Health, Movement, Range, Difficulty multiplier
		
		// Defense is doubled at creation so Defense is actually 50
		super("Engineer", new Item("Homemade Armor Suit", ItemType.DEF), 20, 25, 100, 5, 2, difficulty);
		
	}
}
