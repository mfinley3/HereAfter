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
		super("Doctor", new Item("Emergency Kit", ItemType.HP), 10, 35, 100, 6 ,difficulty);

	}

}
