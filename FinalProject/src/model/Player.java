package model;

import java.util.LinkedList;
import java.util.List;

import units.*;;

// TODO: Auto-generated Javadoc
/**
 * The Player class. Contains all of the player's units (although
 * not their locations on the field), their stats, if they're alive
 * or dead
 * 
 * @author Brian Seaman
 *
 */
public class Player {
	
	/** The games finished. */
	private int gamesFinished;
	
	/** The living team. */
	private int livingTeam;
	
	/** The living units. */
	private List<Unit> livingUnits;
	
	/** The all units. */
	private List<Unit> allUnits;
	
	/** The id. */
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
	 * Gets the team stats.
	 *
	 * @return the team stats
	 */
	public String getTeamStats() {
		// TODO Auto-generated method stub
		String temp = "\n";
		for(Unit i: allUnits)
			temp = temp + i.getStats() + "\n";
		
		return temp;
	}
	
	/**
	 * Gets the team.
	 *
	 * @return the team
	 */
	public List<Unit> getTeam(){
		return livingUnits;
	}
	
	/**
	 * Everyones dead dave.
	 *
	 * @return true, if successful
	 */
	public boolean everyonesDeadDave(){
		return livingUnits.isEmpty();
	}

	/**
	 * All alive units.
	 *
	 * @return the list
	 */
	public List<Unit> allAliveUnits() {
		// TODO Auto-generated method stub
		return livingUnits;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getID() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setID(String id) {
		this.id = id;
	}
	
	/**
	 * Adds the units.
	 *
	 * @param toAdd the to add
	 */
	public void addUnits(Unit toAdd){
		livingUnits.add(toAdd);
		allUnits.add(toAdd);
		livingTeam++;
	}
	
	/**
	 * Gets the alive num.
	 *
	 * @return the alive num
	 */
	public int getAliveNum(){
		return livingTeam;
	}
	
	/**
	 * Unit killed.
	 *
	 * @param dead the dead
	 */
	public void unitKilled(Unit dead){
		livingUnits.remove(dead);
		livingTeam--;
	}

	/**
	 * Gets the games finished.
	 *
	 * @return the games finished
	 */
	public int getGamesFinished() {
		// TODO Auto-generated method stub
		return gamesFinished;
	}

	/**
	 * Game finished.
	 */
	public void gameFinished() {
		// TODO Auto-generated method stub
		gamesFinished++;
	}
}
