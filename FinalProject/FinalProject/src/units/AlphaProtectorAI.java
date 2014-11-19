package units;

import item.Item;
import item.ItemType;

public class AlphaProtectorAI extends Unit {

	public AlphaProtectorAI() {
		super();
		setUnitType("AlphaProtector");
		Item givenWeapon = new Item("Big Daddy", ItemType.HP);
		itemList.add(givenWeapon);
	}

	@Override
	public int movesAvailable() {
		// Suppose to have really little-to-no movement
		return 1;
	}

}
