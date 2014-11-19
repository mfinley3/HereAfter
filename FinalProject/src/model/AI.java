package model;

import java.util.LinkedList;
import java.util.List;

import units.*;

/**
 * Going to have a similar design as player. In iteration 2, will be able
 * to assign AI types to different units 
 * 
 * @author Brian Seaman
 *
 */
public class AI {

	private List<Unit> livingUnits;
	private List<Unit> allUnits;
	private List<Unit> deadUnits;
	private double d;
	private int totalAILeft;

	public AI(Difficulty d){
		this.d = d.getValue();
		allUnits = new LinkedList<Unit>();
		deadUnits = new LinkedList<Unit>();
		livingUnits = new LinkedList<Unit>();
		generateTeam();
		totalAILeft=0;
	}

	/**
	 * Generate the enemy team
	 */
	private void generateTeam() {
		// TODO Use the factory to make a team
	}

	public String getTeamStats() {
		String temp = "";
		for(Unit i: allUnits)
			temp = temp + i.getStats() + "\n";
		temp = temp + "\n";
		return temp;
	}
	
	public List<Unit> getTeam(){
		return allUnits;
	}
	
	public boolean everyonesDeadDave(){
		return livingUnits.isEmpty();
	}

	public List<Unit> allAliveUnits() {
		return livingUnits;
	}

	public void addUnits(Unit toAdd){
		totalAILeft++;
		livingUnits.add(toAdd);
	}
	
	public int getAliveNum(){
		return totalAILeft;
	}
	
	// Respawn Enemies
	
	public void unitKilled(Unit dead){
		livingUnits.remove(dead);
		totalAILeft--;
	}
}
