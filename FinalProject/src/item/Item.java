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
	 * Constructor
	 * 
	 * @param title
	 * @param itemType
	 */
	public Item(String title, ItemType item) {
		this.title = title;
		this.setItemType(item);
	}

	/**
	 * Gets the item title
	 * 
	 * @return item title
	 */
	public String getTitle() {
		return title;
	}

	public ItemType getItemType() {
		return item;
	}

	public void setItemType(ItemType item) {
		this.item = item;
	}
	
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
	 * @return the atkItem
	 */
	public boolean isAtkItem() {
		return atkItem;
	}

	/**
	 * @param atkItem the atkItem to set
	 */
	public void setAtkItem(boolean atkItem) {
		this.atkItem = atkItem;
	}

	/**
	 * @return the defItem
	 */
	public boolean isDefItem() {
		return defItem;
	}

	/**
	 * @param defItem the defItem to set
	 */
	public void setDefItem(boolean defItem) {
		this.defItem = defItem;
	}

	/**
	 * @return the healthItem
	 */
	public boolean isHealthItem() {
		return healthItem;
	}

	/**
	 * @param healthItem the healthItem to set
	 */
	public void setHealthItem(boolean healthItem) {
		this.healthItem = healthItem;
	}
	
	public String toString() {
		return "\n  *Item: " + title + "\n      -Item Type: " + item;
	}

	public int useItem() {
		// TODO Auto-generated method stub
		return 0;
	}

} // end of class Item