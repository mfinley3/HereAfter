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
	public int movesAvailable(int hinderance) {
		//  Has average amount of moves available.
		int moves = 4;
		if (moves - hinderance > 0)
			return moves;
		return 0;
	}

}
