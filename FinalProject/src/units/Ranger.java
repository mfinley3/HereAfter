package units;

import item.Item;
import item.ItemType;

public class Ranger extends Unit {

	public Ranger() {
		super();
		Item givenWeapon = new Item("Ranger Rifle", ItemType.ATK);
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
