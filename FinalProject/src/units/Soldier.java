package units;

import item.Item;
import item.ItemType;

public class Soldier extends Unit {
	
	public Soldier() {
		super();
		Item givenWeapon = new Item("Combat Rifle", ItemType.ATK);
		itemList.add(givenWeapon);
	}

	@Override
	public void visibility() {
		// TODO Auto-generated method stub
		//increased view
		
	}

	@Override
	public int getMovement() {
		// TODO Auto-generated method stub
		//increased move
		return 0;
	}

}