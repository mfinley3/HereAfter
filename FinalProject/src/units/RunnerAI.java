package units;

import item.Item;
import item.ItemType;

public class RunnerAI extends Unit {
	
	public RunnerAI(double difficulty) {
		super("Runner", new Item("Emergency Kit", ItemType.HP), 35, 25, 100, 8 ,difficulty);
		
	}


}
