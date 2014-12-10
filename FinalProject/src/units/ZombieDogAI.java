package units;

import item.Item;
import item.ItemType;

public class ZombieDogAI extends Unit{

	public ZombieDogAI(double difficulty) {
		// Unit Type, Given Item, Attack, Defense, Health, Movement, Range, Difficulty multiplier
		super("ZDog", new Item("None", ItemType.NONE), 35, 0, 100, 10, 1, difficulty);
		
	}

}
