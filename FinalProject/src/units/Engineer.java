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
		super("Engineer", new Item("Homemade Armor Suit", ItemType.DEF), 20, 50, 100, 5 ,difficulty);
		
	}

}
