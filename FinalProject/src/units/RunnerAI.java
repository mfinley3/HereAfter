package units;

import item.Item;
import item.ItemType;

public class RunnerAI extends Unit {
	
	public RunnerAI(double difficulty) {
		// unitType, their given item, attack, defense, health, movement, difficulty multiplier
		super("Runner", new Item("Emergency Kit", ItemType.HP), 35, 25, 100, 8, difficulty);
		
	}

	@Override
	public int getRange() {
		// TODO Auto-generated method stub
		return 1;
	}


}
