package item;

import java.util.Random;

public class RandomItem extends Item{

	public RandomItem(String title, ItemType item) {
		super(title, item);
		// TODO Auto-generated constructor stub
	}
	
	public RandomItem() {
		super(null, null);
	}

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
