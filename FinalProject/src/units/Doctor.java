package units;

import item.Item;
import item.ItemType;

public class Doctor extends Unit {

	public Doctor() {
		super();
		Item givenWeapon = new Item("Emergency Kit", ItemType.HP);
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
