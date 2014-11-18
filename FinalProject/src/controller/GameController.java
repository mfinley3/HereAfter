package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.JOptionPane;

import model.*;
import units.*;

/**
 * TODO Finish this
 * 
 * The controller for a game. Sends messages to map, Saves Data, Loads Data,
 * sets up players, calculate which map is needed, sends messages to the enemy
 * team factory, etc.
 * 
 * @author Brian Seaman
 *
 */
public class GameController {
	private Player player1;
	private AI player2;
	private Map map;
	private List<Unit> tempUnitList;
	private Unit currUnit;
	private Player currPlayer;
	private int turns;
	private boolean playerTurn;
	private boolean gameOver;
	private boolean playerWon;

	private int currRow;
	private int currCol;
	private int endRow;
	private int endCol;

	/*
	 * Will work on being able to control each unit on the map. Things included
	 * in this are making sure units can move, attack, use items and show
	 * results of the battle along with the user being able to create a new
	 * game.
	 */

	/**
	 * TODO Work on this. Will add a createAI method soon
	 * 
	 * Constructor for one player.
	 * 
	 * @param player1
	 * @param i
	 */
	public GameController(Player player1, Difficulty i) {
		this.map = new Map(i.getValue());
		this.player1 = player1;
		this.player2 = new AI(i);
		gameOver = playerWon = false;

		Stack<Unit> temp = new Stack<Unit>();

		// Put the player's units into a stack and put it into the Map
		for (Unit k : player1.getTeam()) {
			k.setCanMove(true);
			temp.push(k);
		}

		// Place the players on the map
		map.addUnitsToMap(temp);
		// Place the enemy on the map

		currPlayer = player1;
		tempUnitList = new ArrayList<Unit>(player1.allAliveUnits());
		for (Unit j : tempUnitList)
			j.setCanMove(true);
		turns = 0;
		playerTurn = true;
	}

	/**
	 * Set the the current unit to the unit located at this space. Will return
	 * true if it
	 * 
	 * @param row
	 * @param col
	 */
	public boolean setCurrentUnit(int row, int col) {
		if (map.getUnitAt(row, col) != null) {
			if (map.getUnitAt(row, col).canMove()) {
				if (currUnit != null) {
					setCanMove(currRow, currCol, false);
				} 

				currUnit = map.getUnitAt(row, col);
				currRow = row;
				currCol = col;
				setCanMove(row, col, true);
				System.out.println("New CurrUnit " + currUnit.getUnitType()
						+ " at: (" + currRow + ", " + currCol + ")");
				return true;
			}
			
			else{
				JOptionPane.showMessageDialog(null, "Selected player cannot move");
				return false;
			}
		} 
		
		else {
			JOptionPane.showMessageDialog(null, "Space is empty. Please select a new space.");
			return false;
		}
	}

	/**
	 * Get method for CurrUnit
	 * 
	 * @return the currently selected unit
	 */
	public Unit getCurrentUnit() {
		return currUnit;
	}

	/**
	 * Used when selecting a unit from the GUI. If the Unit is there and can
	 * move, set it to be the current. Return the unit, or null.
	 * 
	 * @param r
	 *            , the row
	 * @param c
	 *            , the column
	 * @return the current Unit
	 */
	public Unit getUnitOnMap(int r, int c) {
		Unit temp = map.getUnitAt(r, c);
		return temp;
	}

	/**
	 * TODO Test and Finish
	 * 
	 * Move a selected Unit to a another space.
	 * 
	 * @param sr
	 *            , the starting row
	 * @param sc
	 *            , the starting column
	 * @param er
	 *            , the ending row
	 * @param ec
	 *            , the ending column
	 */
	public boolean move() {
		System.out.println("(" + currRow + ", " + currCol + ") Move to ("
				+ endRow + ", " + endCol + ")");

		if (currUnit != null) {
			if (currUnit.canMove() && !map.isOccupied(endRow, endCol)
					&& map.getSpace(endRow, endCol).getCanMoveTo()) {
				setCanMove(currRow, currCol, false);
				map.moveUnit(currRow, currCol, endRow, endCol);
				tempUnitList.remove(currUnit);
				if (tempUnitList.isEmpty())
					endTurn();
				return true;
			}
		}

		if (currUnit == null)
			JOptionPane.showMessageDialog(null, "Please select a Unit");
		else if (!currUnit.canMove())
			JOptionPane.showMessageDialog(null,
					"Unit can't move anymore. Select a new unit.");
		else if (map.isOccupied(endRow, endCol))
			JOptionPane.showMessageDialog(null,
					"Space is occupied, you can't move there");
		else if (!map.getSpace(endRow, endCol).getCanMoveTo())
			JOptionPane.showMessageDialog(null, "Space is out of range.");
		return false;
	}

	/**
	 * TODO 1) Check if friendly 2) Check within range
	 * 
	 * Get it to attack
	 * 
	 * 
	 * @param sr
	 * @param sc
	 * @param er
	 * @param ec
	 * @return
	 */
	public boolean attack() {
		if (currUnit.canMove() && map.isOccupied(endRow, endCol)) {
			map.getUnitAt(endRow, endCol).reduceHealth(currUnit.getAttack());
			targetDead(endRow, endCol);
			// If no other unit can move, end the turn
			tempUnitList.remove(currUnit);
			if (tempUnitList.isEmpty())
				endTurn();
			return true;
		} else
			return false;
	}

	/**
	 * TODO Finish this method.
	 */
	// public boolean hasItem(){
	// return currUnit.;
	// }

	/**
	 * TODO Finish this method
	 * 
	 * Use the item of a selected unit.
	 * 
	 * @return if the item was used.
	 */
	public boolean useItem() {
		if (false)
			return false;
		else {
			// If attack item, use on target space

			// If health item, use on target space/self

			// If defense item, use on target space/self

			return true;
		}
	}

	/**
	 * TODO Test this method
	 * 
	 * Checks both of the player's aliveUnits to see if all of their units are
	 * dead. If either of them are out of units they can move, return true and
	 * end the game. Checked after every move and attack.
	 * 
	 * @return if the game is over or not
	 */

	public boolean gameOver() {
		if (player2.everyonesDeadDave()) {
			gameOver = true;
			playerWon = true;
			// Display some kind of message telling player 2 won
			System.out.println("Player " + player1.getID() + " won!");
			System.out.println("Number of turns taken: " + turns);
			player1.gameFinished();
			System.out.println("Games you finished: "
					+ player1.getGamesFinished());
			System.out.println("Your team stats: ");
			System.out.println(player1.getTeamStats());
			return true;
		} else if (player1.everyonesDeadDave()) {
			gameOver = true;
			playerWon = false;
			// Display some kind of message telling player 1 won
			System.out.println("AI won! Better luck next time...");
			System.out.println("Number of turns taken: " + turns);
			player1.gameFinished();
			System.out.println("Games you've finished: "
					+ player1.getGamesFinished());
			System.out.println("AI team stats: ");
			System.out.println(player1.getTeamStats());
			return true;
		} else
			return false;
	}

	/**
	 * Get all of the stats for the selected player.
	 * 
	 * @return
	 */
	public String getTeamStats() {
		if (this.playerTurn) {
			return player1.getTeamStats();
		} else { // Finish once AI is working
			return player2.getTeamStats();
		}
	}

	/**
	 * Get the selected unit's stats.
	 * 
	 * @param p
	 *            , the player that is asking
	 * @param u
	 *            , the player's unit
	 * @return
	 */
	public String getCurrUnitStats(Unit u) {
		return currUnit.getStats();
	}

	/**
	 * Get the number of turns gone through in the game
	 * 
	 * @return the number of turns taken in game
	 */
	public int getTurns() {
		return turns;
	}

	/**
	 * This method is used when a player wants to do nothing and end that
	 * current unit's turn. Doesn't end the entire turn, just the turn of the
	 * currently selected unit.
	 */
	public void unitDoNothing() {
		currUnit.setCanMove(false);
		tempUnitList.remove(currUnit);
		if (tempUnitList.isEmpty())
			endTurn();
	}

	/**
	 * Return the map. Used in setting up the GUI with the current game.
	 * 
	 * @return the map of the current game
	 */
	public Map getMap() {
		return map;
	}

	/**
	 * When called, ends a turn.
	 * 
	 */
	public void endTurn() {
		if (playerTurn) {
			// Remove all of the player's units from tempList
			playerTurn = false;
			for (Unit i : tempUnitList)
				i.setCanMove(false);
			tempUnitList.clear();

			// Switch to AI
			tempUnitList = player2.allAliveUnits();
		} else {
			// Remove all of the AI's units from the tempList
			playerTurn = true;
			for (Unit i : tempUnitList)
				i.setCanMove(false);
			tempUnitList.clear();

			// Switch to player, add one to turns
			tempUnitList = player1.allAliveUnits();
			turns++;
		}
	}

	/**
	 * TODO Check item type, see if it is on same side
	 * 
	 * Heal a friendly unit. Checks to see if on the same side, and if the unit
	 * can heal.
	 * 
	 * @param er
	 *            , target row
	 * @param ec
	 *            , target col
	 * @return can heal, or can't heal
	 */
	public boolean heal(int targetRow, int targetCol) {
		return false;
	}

	public void setEndRow(int endRow) {
		System.out.println("New endCol: " + endRow);
		this.endRow = endRow;
	}

	public void setEndColumn(int endCol) {
		System.out.println("New endCol: " + endCol);
		this.endCol = endCol;
	}

	/**
	 * TODO fix this
	 * 
	 * Decides if the current unit can move onto a surrounding space. Called
	 * twice, before and after a move/attack.
	 * 
	 * @param currRow
	 * @param currCol
	 */
	private void setCanMove(int currRow, int currCol, boolean set) {
		if (currRow > 0)
			canMoveHelper(currUnit.movesAvailable(), currRow - 1, currCol, set);
		if (currRow < map.getSpaces().length)
			canMoveHelper(currUnit.movesAvailable(), currRow + 1, currCol, set);
		if (currCol > 0)
			canMoveHelper(currUnit.movesAvailable(), currRow, currCol - 1, set);
		if (currCol < map.getSpaces()[currRow].length)
			canMoveHelper(currUnit.movesAvailable(), currRow, currCol + 1, set);
	}

	/**
	 * Helper method for setCanMove.
	 * 
	 * @param movesAvail
	 * @param currRow
	 * @param currCol
	 */
	private void canMoveHelper(int movesAvail, int currRow, int currCol,
			boolean set) {
		movesAvail = movesAvail
				- map.getSpace(currRow, currCol).getMoveHinderance();
		movesAvail = movesAvail - 1;
		if (movesAvail >= 0) {
			map.getSpace(currRow, currCol).setCanMoveTo(set);
			if (currRow < map.getSpaces().length)
				canMoveHelper(movesAvail, currRow + 1, currCol, set);
			if (currRow > 0)
				canMoveHelper(movesAvail, currRow - 1, currCol, set);
			if (currCol < map.getSpaces()[currRow].length)
				canMoveHelper(movesAvail, currRow, currCol + 1, set);
			if (currCol > 0)
				canMoveHelper(movesAvail, currRow, currCol - 1, set);

		}
	}

	/**
	 * Checks to see if a specific unit is dead. If it is, remove it from the
	 * map and from the alive unit lists in the associated team.
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	private boolean targetDead(int row, int col) {
		Unit temp = map.getUnitAt(row, col);
		if (!temp.isAlive()) {
			// Remove them from the map

			// Remove them from the associated list
			if (player1.allAliveUnits().contains(temp))
				player1.unitKilled(temp);
			else
				player2.unitKilled(temp);

			if(tempUnitList.contains(temp))
				tempUnitList.remove(temp);
			// Check to see if the game is over
			gameOver();

			return true;
		} else
			return false;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public boolean playerWon() {
		return playerWon;
	}

	public boolean playerTurn() {
		return playerTurn;
	}
}