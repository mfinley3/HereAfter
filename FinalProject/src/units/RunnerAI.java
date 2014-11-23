package units;

import item.Item;
import item.ItemType;

/**
 * @author Chioke
 * 
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
		super("Runner", new Item("Emergency Kit", ItemType.HP), 45, 10, 100, 8, 1, difficulty);
	}

} // end of RunnerAI