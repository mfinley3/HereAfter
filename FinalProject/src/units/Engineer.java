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
	public void visibility() {
		// Has minimal visibility.
		
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
