package units;

import item.*;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The abstract Unit class. Used in creating new units and keeping track of each
 * of the units stats.
 */
public abstract class Unit implements Serializable {
	
	/** The item list. */
	public ArrayList<Item> itemList = new ArrayList<Item>();

	/** The can move. */
	private boolean canMove;
	
	/** The attack. */
	private int attack;
	
	/** The defense. */
	private int defense;
	
	/** The movement. */
	private int movement;
	
	/** The health. */
	private int health;
	
	/** The range. */
	private int range;

	/** The base attack. */
	private int baseAttack;
	
	/** The base defense. */
	private int baseDefense;
	
	/** The base health. */
	private int baseHealth;
	
	/** The unit type. */
	private String unitType;
	
	/** The difficulty. */
	private Double difficulty;

	/** The attack set. */
	private boolean attackSet;
	
	/** The defense set. */
	private boolean defenseSet;
	
	/** The health set. */
	private boolean healthSet;
	
	/** The current y. */
	protected int currentX, currentY;
	
	/** The is selected. */
	protected boolean isSelected;
	
	/** The no damage. */
	private boolean noDamage;

	/**
	 * Instantiates a new unit.
	 *
	 * @param unitType
	 *            the unit type
	 * @param item
	 *            the given item
	 * @param attack
	 *            the attack power
	 * @param defense
	 *            the defense power
	 * @param health
	 *            the health amount
	 * @param movement
	 *            the amount of movement
	 * @param range
	 *            the range
	 * @param difficulty
	 *            the difficulty
	 */
	public Unit(String unitType, Item item, int attack, int defense, int health, int movement, int range, double difficulty){

		this.baseAttack = attack;
		this.baseDefense = defense;
		this.baseHealth = health;
		
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
	 * Will be responsible for drawing the unit in the Graphical View.
	 *
	 * @param g the g
	 */
	abstract public void drawUnit(Graphics g);

	/**
	 * Sets the CurrentPostition of the unit. Changed when the unit moves/
	 *
	 * @param x the x
	 * @param y the y
	 */
	public void setCurrentPosition(int x, int y) {
		currentX = x;
		currentY = y;
	}
	
	/**
	 * Sets whether or not the unit selected.
	 *
	 * @param isSelected the new checks if is selected
	 */
	public void setIsSelected(boolean isSelected) {
		this.isSelected = isSelected;
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
			if (itemList.get(0).equals(ItemType.NONE)) {

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
	 * @param damage
	 *            the damage
	 */
	public void reduceHealth(int damage) {
		if (damage <= getDefense()) {
			noDamage = true;
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
			if (unitType.equals("Zombie") || unitType.equals("Spitter")
					|| unitType.equals("AlphaProtector")
					|| unitType.equals("ZDog") || unitType.equals("Carrier")) {
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
			if (unitType.equals("Zombie") || unitType.equals("Spitter")
					|| unitType.equals("AlphaProtector")
					|| unitType.equals("ZDog") || unitType.equals("Carrier")) {
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
	 * Gets if the unit can move. If true, it can
	 *
	 * @return if it can or not
	 */
	public boolean canMove() {
		return canMove;
	}

	/**
	 * Sets if the unit can move.
	 *
	 * @param canMove
	 *            the new can move
	 */
	public void setCanMove(boolean canMove) {
		this.canMove = canMove;
	}

	/**
	 * Checks if the unit is alive. If false, the unit is dead.
	 *
	 * @return true, if is alive
	 */
	public boolean isAlive() {
		if (getHealth() <= 0)
			return false;
		return true;
	}

	/**
	 * Gets the unit's stats.
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
		result += "\nCurrent Movement: " + getMovement();
		result += "\nCurrent Range: " + getRange();
		result += "\nUnit's Inventory: " + inventory + "\n";

		return result;
	}

	/**
	 * Gets the unit's range.
	 *
	 * @return the range
	 */
	public int getRange() {
		return range;
	}

	/**
	 * Goes through itemList to see if the unit has the specific item.
	 *
	 * @param item the item
	 * @return true, if successful
	 */
	public boolean hasItem(ItemType item) {
		for (Item i : itemList)
			if (i.getItemType() == item)
				return true;
		return false;
	}

	/**
	 * Adds the item to the unit's inventory.
	 *
	 * @param item the item
	 */
	public void addItem(Item item) {
		itemList.add(item);
	}

	/**
	 * Gets an item from the inventory depending on desired type and
	 * uses it. If that type is not in the person's inventory, return null.
	 *
	 * @param item the item
	 * @return the item
	 */
	public Item removeItem(ItemType item) {
		Item used = null;

		for (Item j : itemList) {
			if (used == null && item == j.getItemType()) {
				used = j;
				break;
			}
		}

		itemList.remove(used);

		return used;
	}

	/**
	 * Called when a unit is healed by another. Raises the units current health
	 * back up. Won't exceed the max health.
	 */
	public void restoreHealth() {
		health = 100;
		UpdateBoosts();
	}

	/**
	 * Get the Item's list.
	 *
	 * @return the item list
	 */
	public Object getItemList() {
		return itemList;
	}

	/**
	 * Updates the boosts of the unit.
	 */
	public void UpdateBoosts() {
		int defMod = 1;
		int atkMod = 1;
		int HPMod = 1;
		
		for (Item item : itemList) {
			if ((item.getItemType() == ItemType.DEF)) {
				defMod++;
			}
			if ((item.getItemType() == ItemType.ATK)) {
				atkMod++;
			}

			if ((item.getItemType() == ItemType.HP)) {
				HPMod++;
			}
			defense = (defMod * baseDefense);
			attack = (atkMod * baseAttack);
			health = (HPMod * baseHealth);
		}

	}
	
	/**
	 * Sets the current posisiton of the unit on the map.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public void setCurrentPostion(int x, int y){
		currentX = x;
		currentY = y;
	}
	
	/**
	 * Gets the x-location of the unit on the map (or the column, if you will).
	 *
	 * @return the x
	 */
	public int getX(){
		return currentX;
	}
	
	/**
	 * Gets the y-location of the unit on the map (or the row, if you will).
	 *
	 * @return the y
	 */
	public int getY(){
		return currentY;
	}

	/**
	 * Gets if damage is not enough.
	 *
	 * @return the no damage
	 */
	public boolean getNoDamage() {
		// TODO Auto-generated method stub
		return noDamage;
	}
	
	/**
	 * Sets if the damage is too high to allow the unit to be hurt.
	 *
	 * @param noDamage the new no damage
	 */
	public void setNoDamage(boolean noDamage) {
		this.noDamage = noDamage;
	}
	
} // end of class Unit

