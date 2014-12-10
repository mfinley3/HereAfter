package item;

import java.util.Random;

public class RandomBoost extends Item {

	public RandomBoost(String title, ItemType item) {
		super(title, item);
		// TODO Auto-generated constructor stub
	}

	public RandomBoost(){
		super(null, null);
	}
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
