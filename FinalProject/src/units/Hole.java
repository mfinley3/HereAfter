package units;

import item.Item;
import item.ItemType;

public class Hole extends Unit{

	public Hole(double difficulty) {
		// unitType, their given item, attack, defense, health, movement, range, difficulty multiplier
		super("Hole", new Item("None", ItemType.NONE), 0, 0, 1, 0, 0, difficulty);
		// TODO Auto-generated constructor stub
	}

	

}
