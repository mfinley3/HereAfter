package units;

import item.Item;
import item.ItemType;

/**
 * 
 * @author Chioke
 *
 */
public class Sniper extends Unit {

	public Sniper() {
		super();
		setUnitType("Sniper");
		Item givenWeapon = new Item("MLG's Rifle of Quickscoping 9001", ItemType.ATK);
		itemList.add(givenWeapon);
	}

	@Override
	public int movesAvailable() {
		//  Has minimal amount of moves available.
		return 2;
	}
}
