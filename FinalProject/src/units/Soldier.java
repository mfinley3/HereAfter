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
		// unitType, their given item, attack, defense, health, movement, difficulty multiplier
		super("Soldier", new Item("Combat Rifle", ItemType.ATK), 40, 30, 100, 6, difficulty);
		
	}

}