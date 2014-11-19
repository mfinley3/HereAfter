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
		super("Soldier", new Item("Combat Rifle", ItemType.ATK), 40, 30, 100, 6 ,difficulty);
		
	}

}