package units;

import item.Item;
import item.ItemType;

public class SpitterAI extends Unit {
	
	public SpitterAI(double difficulty) {
		// unitType, their given item, attack, defense, health, movement, difficulty multiplier
		super("Spitter", new Item("Ebola-Virus Spit Launcher", ItemType.ATK), 40, 40, 100, 6, difficulty);
	
	}

	@Override
	public int getRange() {
		// TODO Auto-generated method stub
		return 3;
	}

}
