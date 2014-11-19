package units;

import item.Item;
import item.ItemType;

public class AlphaProtectorAI extends Unit {

	public AlphaProtectorAI(double difficulty) {
		super("AlphaProtector", new Item("Big Daddy", ItemType.HP), 35, 65, 100, 1 ,difficulty);
	
	}

}
