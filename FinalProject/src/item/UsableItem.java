package item;

import java.io.Serializable;

/**
 * The Class UsableItem.
 */
public class UsableItem extends Item implements Serializable{
	
	private ItemType item;
	private int points;
	private String title;

	/**
	 * The Usable Item extends {@link Item}. Sets the UseItem value that can be called.
	 *
	 * @param title the title
	 * @param item the item
	 */
	public UsableItem(String title, ItemType item) {
		super(title, item);
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
