package units;

import item.Item;
import item.ItemType;

/**
 * @author Chioke
 * 
 * The Class Doctor.
 */
public class Doctor extends Unit {

	/**
	 * Instantiates a new doctor.
	 *
	 * @param difficulty the difficulty
	 */
	public Doctor(double difficulty) {
		// Unit Type, Given Item, Attack, Defense, Health, Movement, Range, Difficulty multiplier
		
		// Health is doubled at creation so Health is actually 200
		super("Doctor", new Item("Emergency Kit", ItemType.HP), 20, 10, 100, 5, 2, difficulty);
	}
	
} // end of Doctor