package units;

import item.Item;
import item.ItemType;

/** 
 * The Class AlphaProtectorAI.
 */
public class AlphaProtectorAI extends Unit {

	/**
	 * Instantiates a new alpha protector ai.
	 *
	 * @param difficulty the difficulty
	 */
	public AlphaProtectorAI(double difficulty) {
		// unitType, their given item, attack, defense, health, movement, difficulty multiplier
		super("AlphaProtector", new Item("Big Daddy", ItemType.HP), 35, 65, 100, 1, 2, difficulty);
		
	}

} // end of AlphaProtectorAI