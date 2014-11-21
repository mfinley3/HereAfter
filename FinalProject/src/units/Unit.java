package units;

import item.Item;
import item.ItemType;

import java.util.ArrayList;

/**
 * 
 * @author Chioke
 *
 */
public abstract class Unit {
	//small change to fix git not working
	// Every Unit gets a blank list of items 
	public ArrayList<Item> itemList = new ArrayList<Item>();
	private boolean canMove;

	private int attack;
	private int defense;
	private int movement;
	private int health;
	private int range;
	private String unitType;
	private Double difficulty;

	public Unit(String unitType, Item item, int attack, int defense, int health, int movement, int range, double difficulty) {
		
		this.unitType = unitType;
		itemList.add(item); // Adds each Unit's given item
		this.attack = attack;
		this.defense = defense;
		this.health = health;
		this.movement = movement;
		this.difficulty = difficulty;
		this.range = range;
		
	}

	public String getUnitType() {
		return unitType;
	}

	public int getHealth() {
		// Health level * health modifier
		int hpMod = 1;

		/*
		 * If the unitType is an AI, items do not benefit it,
		 * it only gets its stats changed depending upon the
		 * chosen game difficulty.
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
					// Grabbing a health item will replenish your life
				}
			}
		}
		return health;
	}

	public void reduceHealth(int damage) {
		if (damage <= getDefense()) {
			return;
		} else {
			health -= (damage - defense);
		}
	}

	public int getAttack() {
		// attack level * attack modifier
		int atkMod = 1;

		/*
		 * If the unitType is an AI, items do not benefit it,
		 * it only gets its stats changed depending upon the
		 * chosen game difficulty.
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
		return attack;
	}

	public int getDefense() {
		// defense level * defense modifier
		int defMod = 1;

		/*
		 * If the unitType is an AI, items do not benefit it,
		 * it only gets its stats changed depending upon the
		 * chosen game difficulty.
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
		return defense;
	}

	public int getMovement(){
		return movement;
	}

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
	
	public int getRange(){
		return range;
	}
		

} // end of class Unit

