package model;

import java.io.Serializable;

public class Item implements Serializable {

	private String title;
	private String itemType;
	private boolean atkItem;
	private boolean defItem;
	private boolean healthItem;

	/**
	 * Constructor
	 * 
	 * @param title
	 * @param itemType
	 */
	public Item(String title, String itemType) {
		this.title = title;
		this.itemType = itemType;
	}

	/**
	 * Gets the song title
	 * 
	 * @return song title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Gets the song length
	 * 
	 * @return song length
	 */
	public String getItemType() {
		return itemType;
	}

	public void setItemType() {
		if (itemType.equals("attack")) {
			atkItem = true;
		} else if (itemType.equals("defense")) {
			defItem = true;
		} else {
			healthItem = true;
		}
	}

} // end of class Item