package model;

import java.util.List;

/**
 * The controller of the project. Sends messages to map, Saves Data, Loads Data,
 * sets up players, calculate which map is needed, sends messages to the enemy
 * team factory, etc.
 * 
 * @author Brian Seaman
 *
 */
public class GameController {
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
	
	public GameController(Player player1, Player player2, Map map){
		this.map = map;
		this.player1 = player1;
		this.player2 = player2;
	
		// Place the players on the map
		map.placePlayer1(player1);
		map.placePlayer2(player1);
			
		currPlayer = player1;
		tempUnitList = player1.getAllAliveUnits();
		
	}
	
	public void move(int i, int j){
		if(currMovesLeft()>0){
			// SOME STUFF REGARDING THE MAP
			
			currUnit.move();	// 
		}
		
	}
	
	public boolean canMove(){
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
}