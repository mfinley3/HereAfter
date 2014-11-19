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
		// unitType, their given item, attack, defense, health, movement, difficulty multiplier
		super("Ranger", new Item("Ranger Rifle", ItemType.ATK), 30, 45, 100, 7, difficulty);
		
	}

	@Override
	public int getRange() {
		// TODO Auto-generated method stub
		return 3;
	}
}
