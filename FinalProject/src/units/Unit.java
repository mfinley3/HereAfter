package units;

import item.Item;

import java.util.ArrayList;

/**
 * 
 * @author Chioke
 *
 */
public abstract class Unit {

	// Every Unit gets a blank list of items
	public ArrayList<Item> itemList = new ArrayList<Item>();

	public int getHealth() {
		// Health level * health modifier
		int hpMod = 1;
		int totalHealth = 100;

		for (Item item : itemList) {
			if (item.isHealthItem()) {
				hpMod += 1;
				totalHealth = 100 * hpMod;
				// Grabbing a health item will replenish your life
			}
		}
		return totalHealth;
	}

	public int attack() {
		return 0;
	}

	public int defense() {
		return 0;
	}

	public abstract void visibility();

	public int getMovement() {
		return 0;
	}

	public boolean canMove() {
		return false;
	}

	public void setCanMove() {
	}

	/*
	 * Methods dealing w/ Unit Locations
	 */
	public int getUR() {
		return 0;
	} // Getting their row location

	public void setUR(int uR) {
	} // Setting their row location

	public int getUC() {
		return 0;
	} // Getting their column location

	public void setHC(int uC) {
	} // Setting their column location

	public boolean isAlive() {
		return false;
	}

	public String getStats() {
		return null;
	}

} // end of class Unit

