package units;

import item.Item;
import item.ItemType;

public class Sniper extends Unit {

	public Sniper() {
		super();
		Item givenWeapon = new Item("MLG's Rifle of Quickscoping 9001", ItemType.ATK);
		itemList.add(givenWeapon);
	}

	@Override
	public void visibility() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getMovement() {
		// TODO Auto-generated method stub
		return 0;
	}
}
