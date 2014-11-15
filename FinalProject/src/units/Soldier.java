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
		Item givenWeapon = new Item("Combat Rifle", ItemType.ATK);
		itemList.add(givenWeapon);
	}

	@Override
	public void visibility() {
		// Has minimal visibility.
		
	}

	@Override
	public int movesAvailable(int hinderance) {
		//  Has 'improved' amount of moves available.
		int moves = 6;
		if (moves - hinderance > 0)
			return moves;
		return 0;
	}

}