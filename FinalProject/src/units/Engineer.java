package units;

import item.*;

/**
 * The Class Engineer.
 */
public class Engineer extends Unit {

	/**
	 * Instantiates a new engineer.
	 *
	 * @param difficulty the difficulty
	 */
	public Engineer(double difficulty) {
		// Unit Type, Given Item, Attack, Defense, Health, Movement, Range, Difficulty multiplier
		
		// Defense is doubled at creation so Defense is actually 50
		super("Engineer", new Item("Homemade Armor Suit", ItemType.DEF), 20, 25, 100, 4, 2, difficulty);	
		this.addItem(new UsableItem("Salvaged Mine", ItemType.MINE));
		this.addItem(new UsableItem("Homemade Mine", ItemType.MINE));
	}
	
} // end of Engineer