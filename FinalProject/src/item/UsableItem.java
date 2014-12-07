package item;

import java.io.Serializable;

public class UsableItem extends Item implements Serializable{
	
	private ItemType item;
	private int points;
	private String title;

	public UsableItem(String title, ItemType item) {
		super(title, item);
		// TODO Auto-generated constructor stub
		if(item == ItemType.MEDKIT)
			points = 1000;
		else if(item == ItemType.MINE){
			points = 200;
		}
		else
			points = 75;
	}
	
	@Override
	public int useItem(){
		return points;
	}
}
