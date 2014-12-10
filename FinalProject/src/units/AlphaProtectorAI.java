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
		// Unit Type, Given Item, Attack, Defense, Health, Movement, Range, Difficulty multiplier
		super("AlphaProtector", new Item("None", ItemType.NONE), 35, 65, 100, 1, 2, difficulty);
		
	}

} // end of AlphaProtectorAI