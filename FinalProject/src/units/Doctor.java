package units;

import java.awt.Graphics;

import item.*;

/**
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
		super("Doctor", new Item("Emergency Kit", ItemType.HP), 20, 25, 100, 5, 2, difficulty);
		this.addItem(new UsableItem("Medkit", ItemType.MEDKIT));
		this.addItem(new UsableItem("Medkit", ItemType.MEDKIT));
		this.addItem(new UsableItem("Medkit", ItemType.MEDKIT));
	}

	@Override
	public void drawUnit(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	
} // end of Doctor