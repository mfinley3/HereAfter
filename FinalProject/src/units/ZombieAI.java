package units;

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
		// unitType, their given item, attack, defense, health, movement, difficulty multiplier
		super("Zombie", new Item("Emergency Kit", ItemType.HP), 45, 20, 100, 5, 1, difficulty);
	}

} // end of RunnerAI