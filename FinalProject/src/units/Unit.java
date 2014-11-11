package units;

import item.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Chioke
 *
 */
public interface Unit {

	public ArrayList<Item> itemList = new ArrayList<Item>(); // Every Unit gets a blank list of items
	
	public int health();
	
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

