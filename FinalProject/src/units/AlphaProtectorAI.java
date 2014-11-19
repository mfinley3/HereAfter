package units;

import item.Item;
import item.ItemType;

public class AlphaProtectorAI extends Unit {

	public AlphaProtectorAI(double difficulty) {
		// unitType, their given item, attack, defense, health, movement, difficulty multiplier
		super("AlphaProtector", new Item("Big Daddy", ItemType.HP), 35, 65, 100, 1, 2, difficulty);
		
	}
	//7
}
