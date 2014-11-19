package units;

import item.Item;
import item.ItemType;

/**
 * 
 * @author Chioke
 *
 */
public class Doctor extends Unit {

	public Doctor(double difficulty) {
		// unitType, their given item, attack, defense, health, movement, difficulty multiplier
		super("Doctor", new Item("Emergency Kit", ItemType.HP), 10, 35, 100, 6, 2, difficulty);

	}
//4
}
