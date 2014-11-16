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
		Item givenWeapon = new Item("Emergency Kit", ItemType.HP);
		itemList.add(givenWeapon);
	}
	
	@Override
	public void visibility() {
		// Has minimal visibility.
		
		/*
		 * The unit would need to know the map, or at least
		 * make reference to it, to understand its visibility.
		 * 
		 * Personally, I think that would make for overly complex
		 * unit classes and I could foresee some problems arising
		 * out of it in the long run (Units overlapping each other,
		 * etc.). To avoid that, I think we should add the visibility
		 * to the gameController, where if unit type is <unitType>,
		 * give it such and such functionality.
		 */
		
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
