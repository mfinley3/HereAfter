package units;

import item.Item;
import item.ItemType;

/**
 * 
 * @author Chioke
 *
 */
public class Doctor extends Unit {

	public Doctor() {
		super();
		setUnitType("Doctor");
		Item givenWeapon = new Item("Emergency Kit", ItemType.HP);
		itemList.add(givenWeapon);
	}

	@Override
	public int movesAvailable() {
		//  Has average amount of moves available.
		return 4;
	}

}
