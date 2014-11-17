package units;

import item.Item;
import item.ItemType;

public class RunnerAI extends Unit {
	
	public RunnerAI() {
		super();
		setUnitType("Runner");
		// Maybe no given item?
		Item givenWeapon = new Item("Emergency Kit", ItemType.HP);
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
