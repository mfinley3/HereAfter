package model;

import java.util.List;

import units.Unit;

/**
 * The controller of the project. Sends messages to map, Saves Data, Loads Data,
 * sets up players, calculate which map is needed, sends messages to the enemy
 * team factory, etc.
 * 
 * @author Brian Seaman
 *
 */
public class GameController1 {
	public Player player1;
	public Player player2;
	private Map map;
	private List<Unit> tempUnitList;
	private Unit currUnit;
	private Player currPlayer;
	private int turns;
	
	/*
	 * Will work on being able to control each unit on the map. Things 
	 * included in this are making sure units can move, attack, use items 
	 * and show results of the battle along with the user being able to 
	 * create a new game.
	 */
	
	public GameController1(Player player1, Difficulty i){
		this.map = new Map(i);
		this.player1 = player1;
	
		// Place the players on the map
		map.addUnitstoMap(player1.getTeam());
			
		currPlayer = player1;
		tempUnitList = player1.getAllAliveUnits();
		
	}
	/**
	 * Move a selected Unit to a another space. Can 
	 * 
	 * @param sr, the starting row
	 * @param sc, the starting column
	 * @param er, the ending row
	 * @param ec, the ending column
	 */
	public void move(int sr, int sc, int er, int ec){
		// SOME STUFF REGARDING THE MAP
		if(map.getUnitAt(sr,sc).canMove())
			map.moveUnit(sr, sc, er, ec);
		
	}
	
	public boolean playerCanMove(){
		return !tempUnitList.empty();
	}
	
	public boolean attack(){
		if(canMove)
			// Send attack message to map
			return true;
		else
			return false;
	}
	
	public boolean hasItem(){
		return currUnit.hasItem();
	}
	
	public boolean useItem(){
		if(!hasItem())
			return false;
		else{
			// If attack item, use on target space
			
			// If health item, use on target space/self
			
			// If defense item, use on target space/self
			
			return true;
		}
	}
	
	public boolean gameOver(){
		if(player1.allAliveUnits().isEmpty())
			//Display some kind of message telling player 2 won
			return true;
		else if (player2.allAliveUnits().isEmpty())
			// Display some kind of message telling player 1 won
			return true;
		else
			return false;
	}
	
	public Object getTeamStats(Player p){
		return p.getTeamStats();
	}
	
	public Object getCurrUnitStats(Player p, Unit u){
		return p.getUnitStats(u);
	}
	
	public int getTurns(){
		return turns;
	}
	
	public void doNothing(){
		currUnit.setMovesLeft(0);
		nextPlayerOnList();
	}
	
	public Map getMap(){
		return map;
	}
	
	// Selected unit
	
	// Move options, wait
	
	// End Turn message
	
	// Heal Command 
}