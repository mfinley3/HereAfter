package units;

import item.Item;
import item.ItemType;

public class Engineer extends Unit {

	public Engineer() {
		super();
		Item givenWeapon = new Item("Homemade Armor Suit", ItemType.DEF);
		itemList.add(givenWeapon);
	}
	
	@Override
	public void visibility() {
		// TODO Auto-generated method stub
		//Avg View
		
	}

	@Override
	public int getMovement() {
		// TODO Auto-generated method stub
		//avg. mvmt
		return 0;
	}

}