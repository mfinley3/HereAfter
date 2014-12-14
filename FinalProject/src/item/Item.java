package item;


import java.io.Serializable;

/**
 * The Item class. Creates and holds all information for items.
 *
 */
public class Item implements Serializable {

	private String title;
	private ItemType item;
	private boolean atkItem;
	private boolean defItem;
	private boolean healthItem;

	/**
	 * Constructor that creates the new item, sets the title and sets the item type.
	 * 
	 * @param title
	 * @param itemType
	 */
	public Item(String title, ItemType item) {
		this.title = title;
		this.setItemType(item);
	}

	/**
	 * Gets the item's title
	 * 
	 * @return item title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Gets the item type
	 * @return the type of item the item is
	 */
	public ItemType getItemType() {
		return item;
	}

	/**
	 * Sets the type of the item
	 * @param item, the new item type
	 */
	public void setItemType(ItemType item) {
		this.item = item;
	}
	
	/**
	 * Gets the modifier of what the item is
	 * @param modType
	 */
	public void getItemModifier(ItemType modType) {
		if(modType.equals(ItemType.ATK)) {
			setAtkItem(true);
		}
		
		if(modType.equals(ItemType.DEF)) {
			setDefItem(true);
		} 
		
		if(modType.equals(ItemType.HP)) {
			setHealthItem(true);
		} 
	}

	/**
	 * Gets if the item is a defense item
	 * @return the atkItem
	 */
	public boolean isAtkItem() {
		return atkItem;
	}

	/**
	 * Sets if the item is a attack item
	 * @param atkItem the atkItem to set
	 */
	public void setAtkItem(boolean atkItem) {
		this.atkItem = atkItem;
	}

	/**
	 * Gets if the item is a defense item
	 * @return the defItem
	 */
	public boolean isDefItem() {
		return defItem;
	}

	/**
	 * Sets if the item is a defense item

	 * @param defItem the defItem to set
	 */
	public void setDefItem(boolean defItem) {
		this.defItem = defItem;
	}

	/**
	 * Returns if the item is a health item
	 * @return the healthItem
	 */
	public boolean isHealthItem() {
		return healthItem;
	}

	/**
	 * Sets if the item is a health item
	 * @param healthItem the healthItem to set
	 */
	public void setHealthItem(boolean healthItem) {
		this.healthItem = healthItem;
	}
	
	/**
	 * Returns the toString of the item. Used in the text view.
	 */
	public String toString() {
		return "\n  *Item: " + title + "\n      -Item Type: " + item;
	}

	/**
	 * Use the item.
	 * @return
	 */
	public int useItem() {
		// TODO Auto-generated method stub
		return 0;
	}

} // end of class Item