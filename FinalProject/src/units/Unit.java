package units;

import item.Item;

import java.util.ArrayList;

import model.Difficulty;

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
	private String unitType = "";
	private Difficulty d;

	public Unit() {
		canMove = false;
	}

	public String getUnitType() {
		return unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

	public int getHealth() {
		// Health level * health modifier
		int hpMod = 1;

		if (unitType.equals("Runner") || unitType.equals("Spitter")
				|| unitType.equals("AlphaProtector")) {
			hpMod *= (int) d.getValue();
			totalHealth = 100 * hpMod;
		}

		// Only non-AI characters can benefit from items
		else {
			for (Item item : itemList) {
				if (item.isHealthItem()) {
					hpMod++;
					totalHealth = 100 * hpMod;
					// Grabbing a health item will replenish your life
				}
			}
		}
		return totalHealth;
	}

	public void reduceHealth(int damage) {
		if (damage - getDefense() <= 0) {
			return;
		} else {
			totalHealth -= (damage - getDefense());
		}
	}

	public int getAttack() {
		// attack level * attack modifier
		int atkMod = 1;
		int atkPower = 40;

		if (unitType.equals("Runner") || unitType.equals("Spitter")
				|| unitType.equals("AlphaProtector")) {
			atkMod *= (int) d.getValue();
			atkPower *= atkMod;
		}

		// Only non-AI characters can benefit from items
		else {
			for (Item item : itemList) {
				if (item.isAtkItem()) {
					atkMod++;
					atkPower *= atkMod;
				}
			}
		}
		return atkPower;
	}

	public int getDefense() {
		// defense level * defense modifier
		int defMod = 1;
		int defPower = 10;

		if (unitType.equals("Runner") || unitType.equals("Spitter")
				|| unitType.equals("AlphaProtector")) {
			defMod *= (int) d.getValue();
			defPower *= defMod;
		}

		// Only non-AI characters can benefit from items
		else {
			for (Item item : itemList) {
				if (item.isAtkItem()) {
					defMod++;
					defPower *= defMod;
				}
			}
		}
		return defPower;
	}

	public abstract int movesAvailable(int hinderance);

	public boolean canMove() {
		return canMove;
	}

	public void setCanMove(boolean canMove) {
		this.canMove = canMove;
	}

	public boolean isAlive() {
		if (getHealth() <= 0)
			return false;
		return true;
	}

	public String getStats() {
		String inventory = "";
		for (Item s : itemList) {
			inventory += s.toString();
		}

		String result = "Unit Type: " + getUnitType();
		result += "<br>Current Health: " + getHealth();
		result += "<br>Current Attack Power: " + getAttack();
		result += "<br>Current Defense Power: " + getDefense();
		result += "<br>Inventory: " + inventory;

		return result;
	}

} // end of class Unit

