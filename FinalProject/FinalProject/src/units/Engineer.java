package units;

import item.Item;
import item.ItemType;

/**
 * 
 * @author Chioke
 *
 */
public class Engineer extends Unit {

	public Engineer() {
		super();
		setUnitType("Engineer");
		Item givenWeapon = new Item("Homemade Armor Suit", ItemType.DEF);
		itemList.add(givenWeapon);
	}

	@Override
	public int movesAvailable() {
		//  Has average amount of moves available.
		return 4;
	}

}
