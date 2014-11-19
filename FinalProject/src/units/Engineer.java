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
		// unitType, their given item, attack, defense, health, movement, difficulty multiplier
		super("Engineer", new Item("Homemade Armor Suit", ItemType.DEF), 20, 50, 100, 5, 2, difficulty);
		
	}
	//2
}
