package units;

import item.Item;
import item.ItemType;

/**
 * 
 * @author Chioke
 *
 */
public class Ranger extends Unit {

	public Ranger(double difficulty) {
		super("Ranger", new Item("Ranger Rifle", ItemType.ATK), 30, 45, 100, 7 ,difficulty);
		
	}
}
