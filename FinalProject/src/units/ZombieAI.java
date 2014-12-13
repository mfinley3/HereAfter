package units;

import java.awt.Graphics;

import item.Item;
import item.ItemType;

/**
 * The Class RunnerAI.
 */
public class ZombieAI extends Unit {
	
	/**
	 * Instantiates a new runner ai.
	 *
	 * @param difficulty the difficulty
	 */
	public ZombieAI(double difficulty) {
		// Unit Type, Given Item, Attack, Defense, Health, Movement, Range, Difficulty multiplier
		super("Zombie", new Item("None", ItemType.NONE), 45, 20, 100, 5, 1, difficulty);
	}

	@Override
	public void drawUnit(Graphics g) {
		// TODO Auto-generated method stub
		
	}

} // end of RunnerAI