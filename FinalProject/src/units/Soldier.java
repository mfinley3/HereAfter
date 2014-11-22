package units;

import item.Item;
import item.ItemType;

/**
 * 
 * @author Chioke
 *
 */
public class Soldier extends Unit {
	
	public Soldier(double difficulty) {
		// Unit Type, Given Item, Attack, Defense, Health, Movement, Range, Difficulty multiplier
		
		// Attack is doubled at creation so Attack is actually 60
		super("Soldier", new Item("Combat Rifle", ItemType.ATK), 30, 20, 100, 6000, 3, difficulty);
		
	}

}