package units;

import java.awt.Graphics;

import item.Item;
import item.ItemType;

/**
 * The Class SpitterAI.
 */
public class SpitterAI extends Unit {
	
	/**
	 * Instantiates a new spitter ai.
	 *
	 * @param difficulty the difficulty
	 */
	public SpitterAI(double difficulty) {
		// Unit Type, Given Item, Attack, Defense, Health, Movement, Range, Difficulty multiplier
		super("Spitter", new Item("None", ItemType.NONE), 40, 40, 100, 6, 3, difficulty);
	}

	@Override
	public void drawUnit(Graphics g) {
		// TODO Auto-generated method stub
		
	}

} // end of SpitterAI
