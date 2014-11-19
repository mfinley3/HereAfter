package units;

import item.Item;
import item.ItemType;

public class SpitterAI extends Unit {
	
	public SpitterAI(double difficulty) {
		super("Spitter", new Item("Ebola-Virus Spit Launcher", ItemType.ATK), 40, 40, 100, 6 ,difficulty);
	
	}

}
