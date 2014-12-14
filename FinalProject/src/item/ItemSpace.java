package item;

/**
 * The Class ItemSpace, Not yet used in the game.
 */
public class ItemSpace {

	private boolean atkItem;
	private boolean defItem;
	private boolean healthItem;

	/**
	 * Instantiates a new item space.
	 */
	public ItemSpace() {
		// Creates an empty game space
	}

	/**
	 * Sets the attack item
	 *
	 * @param atkItem the new atk item
	 */
	public void setAtkItem(boolean atkItem) {
		this.atkItem = atkItem;
	}

	/**
	 * Returns the attack item attack item.
	 *
	 * @return true, if is atk item
	 */
	public boolean isAtkItem() {
		return atkItem;
	}

	/**
	 * Sets the def item.
	 *
	 * @param defItem the new def item
	 */
	public void setDefItem(boolean defItem) {
		this.defItem = defItem;
	}

	/**
	 * Returns the def item.
	 *
	 * @return true, if is def item
	 */
	public boolean isDefItem() {
		return defItem;
	}

	/**
	 * Sets the health item
	 *
	 * @param healthItem the new health item
	 */
	public void setHealthItem(boolean healthItem) {
		this.healthItem = healthItem;
	}

	/**
	 * Returns the health item.
	 *
	 * @return true, if is health item
	 */
	public boolean isHealthItem() {
		return healthItem;
	}

}
