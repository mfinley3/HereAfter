package model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import units.*;;

/**
 * The Player class. Contains all of the player's units (although
 * not their locations on the field), their stats, if they're alive
 * or dead.
 *
 */
public class Player implements Serializable{
	
	private int gamesFinished;
	private int livingTeam;
	private List<Unit> livingUnits;
	private List<Unit> allUnits;
	private String id;
	
	/**
	 * Instantiates a new player.
	 *
	 * @param id the id
	 */
	public Player(String id){
		this.setID(id);
		livingUnits = new LinkedList<Unit>();
		allUnits = new LinkedList<Unit>();
		gamesFinished= livingTeam = 0;
	}

	/**
	 * Gets the team's stats.
	 *
	 * @return the team stats
	 */
	public String getTeamStats() {
		String temp = "\n";
		for(Unit i: allUnits)
			temp = temp + i.getStats() + "\n";
		
		return temp;
	}
	
	/**
	 * Gets the units of the team that are currently alive.
	 *
	 * @return the list of the currently living team members
	 */
	public List<Unit> getTeam(){
		return livingUnits;
	}
	
	/**
	 * Checks to see if all of the player's team is dead by
	 * checking to see if the LivingUnits list is empty. If it
	 * is, return true.
	 *
	 * @return whether or not the player's team is dead.
	 */
	public boolean everyonesDeadDave(){
		return livingUnits.isEmpty();
	}

	/**
	 * Gets all of the currently living units.
	 *
	 * @return the list
	 */
	public List<Unit> allAliveUnits() {
		return livingUnits;
	}

	/**
	 * Gets the player's ID (name).
	 *
	 * @return The player's ID
	 */
	public String getID() {
		return id;
	}

	/**
	 * Sets the player's ID (name).
	 *
	 * @param id the new id
	 */
	public void setID(String id) {
		this.id = id;
	}
	
	/**
	 * Adds units to the Player's team.
	 *
	 * @param toAdd the to add
	 */
	public void addUnits(Unit toAdd){
		livingUnits.add(toAdd);
		allUnits.add(toAdd);
		livingTeam++;
	}
	
	/**
	 * Get the total number of alive units left.
	 *
	 * @return The number of alive members of the team
	 */
	public int getAliveNum(){
		return livingTeam;
	}
	
	/**
	 * Called when a Unit is killed in game. Removes it from the living units.
	 *
	 * @param dead the dead
	 */
	public void unitKilled(Unit dead){
		livingUnits.remove(dead);
		livingTeam--;
	}

	/**
	 * Returns the number of games finished.
	 * 
	 * @return The total number of games completed by the player
	 */
	public int getGamesFinished() {
		return gamesFinished;
	}

	/**
	 * Adds one to the games finished. Will be used when serializable
	 * in iteration 2.
	 */
	public void gameFinished() {
		gamesFinished++;
	}
}
