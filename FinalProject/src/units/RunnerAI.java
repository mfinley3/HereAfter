package units;

import item.Item;
import item.ItemType;

// TODO: Auto-generated Javadoc
/**
 * The Class RunnerAI.
 */
public class RunnerAI extends Unit {
	
	/**
	 * Instantiates a new runner ai.
	 *
	 * @param difficulty the difficulty
	 */
	public RunnerAI(double difficulty) {
		// unitType, their given item, attack, defense, health, movement, difficulty multiplier
		super("Runner", new Item("Emergency Kit", ItemType.HP), 35, 10, 100, 8000, 1, difficulty);
		
	}

}
