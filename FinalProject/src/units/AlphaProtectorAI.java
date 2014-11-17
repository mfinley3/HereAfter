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
	public void visibility() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int movesAvailable(int hinderance) {
		// TODO Auto-generated method stub
		return 0;
	}

}
