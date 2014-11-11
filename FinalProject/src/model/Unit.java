package model;

import item.ItemList;

/**
 * 
 * @author Chioke
 *
 */
public interface Unit {

	ItemList[] itemList = new ItemList[6]; // Every Unit gets a max of 6 items
	
	public int health();
	
	public void setHealth(int health);
	
	public int movesAvailable();
	
	public void move();
	
	public void attack();
	
	public void visibility();
	
	/*
	 * Methods dealing w/ Unit Locations
	 */
	public int getUR(); // Getting their row location

	public void setUR(int uR); // Setting their row location

	public int getUC(); // Getting their column location

	public void setHC(int uC); // Setting their column location
	
	public boolean isAlive();
	
	public String getStats();
	
} //end of class Unit

