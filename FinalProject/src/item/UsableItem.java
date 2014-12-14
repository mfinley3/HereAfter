package item;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class UsableItem.
 */
public class UsableItem extends Item implements Serializable{
	
	/** The item. */
	private ItemType item;
	
	/** The points. */
	private int points;
	
	/** The title. */
	private String title;

	/**
	 * The Usable Item extends {@link Item}. Sets the UseItem value that can be called.
	 *
	 * @param title the title
	 * @param item the item
	 */
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
	
	/* (non-Javadoc)
	 * @see item.Item#useItem()
	 */
	@Override
	public int useItem(){
		return points;
	}
}
