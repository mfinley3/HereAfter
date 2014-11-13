package units;

import item.Item;
import item.ItemType;

public class Soldier extends Unit {

	private boolean canMove = false;
	
	public Soldier() {
		super();
		Item givenWeapon = new Item("Rifle", ItemType.ATK);
		itemList.add(givenWeapon);
	}

	@Override
	public void visibility() {
		// TODO Auto-generated method stub
		
	}

}