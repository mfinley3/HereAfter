package item;

import java.util.Random;

public class RandomItem extends Item{

	/**
	 * Places a random item on the map depending on the type.
	 * @param title
	 * @param item
	 */
	public RandomItem(String title, ItemType item) {
		super(title, item);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Creates a new, empty randomItem
	 */
	public RandomItem() {
		super(null, null);
	}

	/**
	 * A static method called upon to create a new, random, usable item.
	 * @return the newly generated item.
	 */
	public static Item generateItem() {
		Random randomNumberGen = new Random();
		int itemToGenerate = randomNumberGen.nextInt(3);
		
		if(itemToGenerate == 0){
			return new UsableItem("Basic MedKit", ItemType.MEDKIT);
		}
		if(itemToGenerate == 1){
			return new UsableItem("Basic Mine", ItemType.MINE);
		}
		if(itemToGenerate == 2){
			return new UsableItem("Basic Grenade", ItemType.GRENADE);
		}
		
		return null;
	}


}
