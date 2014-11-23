package item;

/**
 * The Class ItemSpace, Not yet used in the game.
 */
public class ItemSpace {

	/** The atk item. */
	private boolean atkItem;
	
	/** The def item. */
	private boolean defItem;
	
	/** The health item. */
	private boolean healthItem;

	/**
	 * Instantiates a new item space.
	 */
	public ItemSpace() {
		// Creates an empty game space
	}

	/**
	 * Methods dealing w/ visited game spaces.
	 *
	 * @param atkItem the new atk item
	 */
	public void setAtkItem(boolean atkItem) {
		this.atkItem = atkItem;
	}

	/*
	 * Returns true if the Hunter is here
	 * 
	 */
	/**
	 * Checks if is atk item.
	 *
	 * @return true, if is atk item
	 */
	public boolean isAtkItem() {
		return atkItem;
	}

	/*
	 * Method dealing w/ visited game spaces
	 */
	/**
	 * Sets the def item.
	 *
	 * @param defItem the new def item
	 */
	public void setDefItem(boolean defItem) {
		this.defItem = defItem;
	}

	/*
	 * Returns true if the player has come through
	 */
	/**
	 * Checks if is def item.
	 *
	 * @return true, if is def item
	 */
	public boolean isDefItem() {
		return defItem;
	}

	/**
	 * Methods dealing w/ visited game spaces.
	 *
	 * @param healthItem the new health item
	 */
	public void setHealthItem(boolean healthItem) {
		this.healthItem = healthItem;
	}

	/*
	 * Returns true if the Hunter is here
	 * 
	 */
	/**
	 * Checks if is health item.
	 *
	 * @return true, if is health item
	 */
	public boolean isHealthItem() {
		return healthItem;
	}

}
