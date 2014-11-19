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
		// Unit Type, Given Item, Attack, Defense, Health, Movement, Range, Difficulty multiplier
		
		// Attack is doubled at creation so Attack is actually 70
		super("Sniper", new Item("MLG's Rifle of Quickscoping 9001", ItemType.ATK), 35, 10, 100, 5, 6, difficulty);
		
	}

}
