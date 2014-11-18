package units;

import item.Item;
import item.ItemType;

/**
 * 
 * @author Chioke
 *
 */
public class Soldier extends Unit {
	
	public Soldier() {
		super();
		setUnitType("Soldier");
		Item givenWeapon = new Item("Combat Rifle", ItemType.ATK);
		itemList.add(givenWeapon);
	}

	@Override
	public int movesAvailable() {
		//  Has 'improved' amount of moves available.
		return 6;
	}

}