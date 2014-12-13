package units;

import java.awt.Graphics;

import item.*;

/**
 * The Class Ranger.
 */
public class Ranger extends Unit {

	/**
	 * Instantiates a new ranger.
	 *
	 * @param difficulty the difficulty
	 */
	public Ranger(double difficulty) {
		// Unit Type, Given Item, Attack, Defense, Health, Movement, Range, Difficulty multiplier
		
		// Attack is doubled at creation so Attack is actually 50
		super("Ranger", new Item("Ranger Rifle", ItemType.ATK), 15, 15, 100, 8, 3, difficulty);
	}

	@Override
	public void drawUnit(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	
} // end of Ranger
