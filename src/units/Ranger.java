package units;

import item.Item;
import item.ItemType;

/**
 * 
 * @author Chioke
 *
 */
public class Ranger extends Unit {

	public Ranger() {
		super();
		setUnitType("Ranger");
		Item givenWeapon = new Item("Ranger Rifle", ItemType.ATK);
		itemList.add(givenWeapon);
	}

	@Override
	public int movesAvailable() {
		//  Has high amount of moves available.
		return 10;
	}

}
