package units;

import item.*;

/**
 * The Class Soldier.
 */
public class Soldier extends Unit {
	
	/**
	 * Instantiates a new soldier.
	 *
	 * @param difficulty the difficulty
	 */
	public Soldier(double difficulty) {
		// Unit Type, Given Item, Attack, Defense, Health, Movement, Range, Difficulty multiplier
		
		// Attack is doubled at creation so Attack is actually 60
		super("Soldier", new Item("Combat Rifle", ItemType.ATK), 30, 20, 100, 6, 3, difficulty);
		this.addItem(new UsableItem("Salvaged Grenade", ItemType.GRENADE));
		this.addItem(new UsableItem("Military Grenade", ItemType.GRENADE));
	}


} // end of Soldier