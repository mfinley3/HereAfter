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
	private boolean canMove;

	private int totalHealth = 100;
	
	public Unit(){
		canMove = false;
	}
	
	public int getHealth() {
		// Health level * health modifier
		int hpMod = 1;

		for (Item item : itemList) {
			if (item.isHealthItem()) {
				hpMod += 1;
				totalHealth = 100 * hpMod;
				// Grabbing a health item will replenish your life
			}
		}
		return totalHealth;
	}

	public void reduceHealth(int damage) {
		if(damage - getDefense() <= 0) {
			return;
		} else {
			totalHealth -= (damage - getDefense());
		}
	}

	public int getAttack() {
		// attack level * attack modifier
		int atkMod = 1;
		int atkPower = 40;

		for (Item item : itemList) {
			if (item.isAtkItem()) {
				atkMod += 1;
				atkPower *= atkMod;
			}
		}
		return atkPower;
	}

	public int getDefense() {
		// defense level * defense modifier
		int defMod = 1;
		int defPower = 10;

		for (Item item : itemList) {
			if (item.isAtkItem()) {
				defMod += 1;
				defPower *= defMod;
			}
		}
		return defPower;
	}

	public abstract void visibility();

	public abstract int movesAvailable(int hinderance);

	public boolean canMove() {
		return canMove;
	}

	public void setCanMove() {
		
		canMove = !canMove;
		
	}

	public boolean isAlive() {
		if (getHealth() <= 0)
			return false;
		return true;
	}

	public String getStats() {
		String inventory = "";
		for (Item s : itemList) {
		    inventory += "\n" + String.format("%s ", s);
		}
		
		String result = "Unit Type: " + "<Unit Type>";
		result += "\nCurrent Health: " + getHealth();
		result += "\nCurrent Attack Power: " + getAttack();
		result += "\nCurrent Defense Power: " + getDefense();
		result += "\nInventory: " + inventory;
		
		System.out.println("getStats of a unit");
		
		return result;
	}

} // end of class Unit

