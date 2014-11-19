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
	public int movesAvailable() {
		// Almost like an enemy AI version of a ranger
		return 7;
	}

}
