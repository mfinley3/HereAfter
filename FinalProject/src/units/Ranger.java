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
	public void visibility() {
		// Has minimal visibility.
		
	}

	@Override
	public int movesAvailable(int hinderance) {
		//  Has high amount of moves available.
		int moves = 10;
		if (moves - hinderance > 0)
			return moves;
		return 0;
	}

}
