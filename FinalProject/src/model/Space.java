package model;

public class Space {

	private boolean atkItem;
	private boolean defItem;
	private boolean healthItem;

	public Space() {
		// Creates an empty game space
	}

	/**
	 * Methods dealing w/ visited game spaces
	 * 
	 * @param hunter
	 */
	public void setAtkItem(boolean atkItem) {
		this.atkItem = atkItem;
	}

	/*
	 * Returns true if the Hunter is here
	 * 
	 */
	public boolean isAtkItem() {
		return atkItem;
	}

	/*
	 * Method dealing w/ visited game spaces
	 */
	public void setDefItem(boolean defItem) {
		this.defItem = defItem;
	}

	/*
	 * Returns true if the player has come through
	 */
	public boolean isDefItem() {
		return defItem;
	}

	/**
	 * Methods dealing w/ visited game spaces
	 * 
	 * @param hunter
	 */
	public void setHealthItem(boolean healthItem) {
		this.healthItem = healthItem;
	}

	/*
	 * Returns true if the Hunter is here
	 * 
	 */
	public boolean isHealthItem() {
		return healthItem;
	}

}
