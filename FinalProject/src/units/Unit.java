package units;

import item.Item;
import item.ItemType;

import java.util.ArrayList;

/**
 * @author Chioke
 * 
 *         The Class Unit.
 */
public abstract class Unit {
	public ArrayList<Item> itemList = new ArrayList<Item>();

	private boolean canMove;
	private int attack;
	private int defense;
	private int movement;
	private int health;
	private int range;

	private String unitType;
	private Double difficulty;

	private boolean attackSet;
	private boolean defenseSet;
	private boolean healthSet;

	/**
	 * Instantiates a new unit.
	 *
	 * @param unitType		the unit type
	 * @param item			the given item
	 * @param attack		the attack power
	 * @param defense		the defense power
	 * @param health		the health amount
	 * @param movement		the amount of movement
	 * @param range			the range
	 * @param difficulty	the difficulty
	 */
	public Unit(String unitType, Item item, int attack, int defense,
			int health, int movement, int range, double difficulty) {

		this.unitType = unitType;
		itemList.add(item); // Adds each Unit's given item to their list
		this.attack = attack;
		this.defense = defense;
		this.health = health;
		this.movement = movement;
		this.difficulty = difficulty;
		this.range = range;
	}

	/**
	 * Gets the unit type.
	 *
	 * @return the unit type
	 */
	public String getUnitType() {
		return unitType;
	}

	/**
	 * Gets the health.
	 *
	 * @return the health
	 */
	public int getHealth() {

		if (!healthSet) {
			// Health level * health modifier
			int hpMod = 1;

			/*
			 * If the unitType is an AI, items do not benefit it, it only gets
			 * its stats changed depending upon the chosen game difficulty.
			 */
			if (unitType.equals("Runner") || unitType.equals("Spitter")
					|| unitType.equals("AlphaProtector")) {
				hpMod *= difficulty;
				health = health * hpMod;
			}

			// Only non-AI characters can benefit from items
			else {
				for (Item item : itemList) {
					if ((item.getItemType() == ItemType.HP)) {
						hpMod++;
						health = health * hpMod;
					}
				}
			}
			healthSet = true;
		}
		return health;
	}

	/**
	 * Reduce health.
	 *
	 * @param damage	the damage
	 */
	public void reduceHealth(int damage) {
		if (damage <= getDefense()) {
			return;
		} else {
			health -= (damage - defense);
		}
	}

	/**
	 * Gets the attack.
	 *
	 * @return the attack
	 */
	public int getAttack() {

		if (!attackSet) {
			// attack level * attack modifier
			int atkMod = 1;

			/*
			 * If the unitType is an AI, items do not benefit it, it only gets
			 * its stats changed depending upon the chosen game difficulty.
			 */
			if (unitType.equals("Runner") || unitType.equals("Spitter")
					|| unitType.equals("AlphaProtector")) {
				atkMod *= difficulty;
				attack *= atkMod;
			}

			// Only non-AI characters can benefit from items
			else {
				for (Item item : itemList) {
					if (item.getItemType() == ItemType.ATK) {
						atkMod++;
						attack *= atkMod;
					}
				}
			}
			attackSet = true;
		}
		return attack;
	}

	/**
	 * Gets the defense.
	 *
	 * @return the defense
	 */
	public int getDefense() {

		if (!defenseSet) {
			// defense level * defense modifier
			int defMod = 1;

			/*
			 * If the unitType is an AI, items do not benefit it, it only gets
			 * its stats changed depending upon the chosen game difficulty.
			 */
			if (unitType.equals("Runner") || unitType.equals("Spitter")
					|| unitType.equals("AlphaProtector")) {
				defMod *= difficulty;
				defense *= defMod;
			}

			// Only non-AI characters can benefit from items
			else {
				for (Item item : itemList) {
					if ((item.getItemType() == ItemType.DEF)) {
						defMod++;
						defense *= defMod;
					}
				}
			}
			defenseSet = true;
		}
		return defense;
	}

	/**
	 * Gets the movement.
	 *
	 * @return the movement
	 */
	public int getMovement() {
		return movement;
	}

	/**
	 * Can move.
	 *
	 * @return true, if successful
	 */
	public boolean canMove() {
		return canMove;
	}

	/**
	 * Sets the can move.
	 *
	 * @param canMove	the new can move
	 */
	public void setCanMove(boolean canMove) {
		this.canMove = canMove;
	}

	/**
	 * Checks if is alive.
	 *
	 * @return true, if is alive
	 */
	public boolean isAlive() {
		if (getHealth() <= 0)
			return false;
		return true;
	}

	/**
	 * Gets the stats.
	 *
	 * @return the stats
	 */
	public String getStats() {
		String inventory = "";
		for (Item s : itemList) {
			inventory += s.toString();
		}

		// This way stats doesn't display negative health.
		int displayHealth = getHealth();
		if (displayHealth <= 0)
			displayHealth = 0;

		String result = "Unit Type: " + getUnitType();
		result += "\nCurrent Health: " + displayHealth;
		result += "\nCurrent Attack Power: " + getAttack();
		result += "\nCurrent Defense Power: " + getDefense();
		result += "\nUnit's Inventory: " + inventory + "\n";

		return result;
	}

	/**
	 * Gets the range.
	 *
	 * @return the range
	 */
	public int getRange() {
		return range;
	}

} // end of class Unit

