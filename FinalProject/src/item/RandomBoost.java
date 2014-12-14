package item;

import java.util.Random;

/**
 * The Class RandomBoost.
 */
public class RandomBoost extends Item {

	/**
	 * Creates a new random boost item. The item is placed on the map, but it isn't
	 * a usable item, but it does augment the stats of the unit with the item.
	 *
	 * @param title the title
	 * @param item the item
	 */
	public RandomBoost(String title, ItemType item) {
		super(title, item);
	}

	/**
	 * Create a new RandomBoost item, but have it all be null.
	 */
	public RandomBoost(){
		super(null, null);
	}
	
	/**
	 * Generates a new random BoostItem through a static method.
	 * @return the newly created item
	 */
	public static Item generateBoost() {
		Random randomNumberGen = new Random();
		int itemToGenerate = randomNumberGen.nextInt(3);
		
		if(itemToGenerate == 0){
			return new UsableItem("Boost Attack", ItemType.ATK);
		}
		if(itemToGenerate == 1){
			return new UsableItem("Boost Defence", ItemType.DEF);
		}
		if(itemToGenerate == 2){
			return new UsableItem("Boost HP", ItemType.HP);
		}
		
		return null;
	}

}
