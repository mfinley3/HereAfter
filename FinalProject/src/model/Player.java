package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import units.*;;

/**
 * The Player class. Contains all of the player's units (although
 * not their locations on the field), their stats, if they're alive
 * or dead
 * 
 * @author Brian Seaman
 *
 */
public class Player {
	public int missionsDone;
	public int livingTeam;
	private Stack<Unit> livingUnits;
	private List<Unit> deadUnits;
	private String id;
	
	public Player(String id){
		this.setID(id);
		livingUnits = new Stack<Unit>();
		
		// Add five soldiers for now
		livingUnits.push((Unit) new Soldier());
		livingUnits.push((Unit) new Soldier());
		livingUnits.push((Unit) new Soldier());
		livingUnits.push((Unit) new Soldier());
		livingUnits.push((Unit) new Soldier());
	}

	public Object getTeamStats() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Unit> getTeam(){
		return livingUnits;
	}

	public List<Unit> allAliveUnits() {
		// TODO Auto-generated method stub
		return livingUnits;
	}

	public String getID() {
		return id;
	}

	public void setID(String id) {
		this.id = id;
	}
}
