package units;

import item.Item;
import item.ItemType;

/**
 * 
 * @author Chioke
 *
 */
public class Sniper extends Unit {

	public Sniper(double difficulty) {
		super("Sniper", new Item("MLG's Rifle of Quickscoping 9001", ItemType.ATK), 60, 10, 100, 5 ,difficulty);
		
	}
}
