package item;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Chioke
 *
 */
public class ItemList {
	private List<Item> itemList;

	/**
	 * Constructor
	 */
	public ItemList() {
		// Remember Item Format (Title, Item Enum)
		setItemList(new ArrayList<Item>());
	}

	/**
	 * @return the itemList
	 */
	public List<Item> getItemList() {
		return itemList;
	}

	/**
	 * @param itemList the itemList to set
	 */
	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

} //end of class ItemList
