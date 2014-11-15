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
		Item givenWeapon = new Item("MLG's Rifle of Quickscoping 9001", ItemType.ATK);
		itemList.add(givenWeapon);
	}

	@Override
	public void visibility() {
		// Has high visibility.
		
	}

	@Override
	public int movesAvailable(int hinderance) {
		//  Has minimal amount of moves available.
		int moves = 2;
		if (moves - hinderance > 0)
			return moves;
		return 0;
	}
}
