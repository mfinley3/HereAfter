package units;

import item.*;

/**
 * The Class Sniper.
 */
public class Sniper extends Unit {

	/**
	 * Instantiates a new sniper.
	 *
	 * @param difficulty the difficulty
	 */
	public Sniper(double difficulty) {
		// Unit Type, Given Item, Attack, Defense, Health, Movement, Range, Difficulty multiplier
		
		// Attack is doubled at creation so Attack is actually 70
		super("Sniper", new Item("MLG's Rifle of Quickscoping 9001", ItemType.ATK), 35, 10, 100, 5, 7, difficulty);
	}

} // end of Sniper