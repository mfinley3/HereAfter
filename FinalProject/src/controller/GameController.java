package controller;

import gametype.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.JOptionPane;

import model.*;
import space.Space;
import units.*;

/**
 * TODO Add win conditions and change attack
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
	private int turns;
	private boolean playerTurn;
	private boolean gameOver;
	private boolean playerWon;

	private int currRow;
	private int currCol;
	private int endRow;
	private int endCol;

	private GameTypeInterface j;
	private Object winConditions;

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

		j = new CaptureTower();
		winConditions = false;

		Stack<Unit> temp = new Stack<Unit>();

		// Put the player's units into a stack and put it into the Map
		for (Unit k : player1.getTeam()) {
			k.setCanMove(true);
			temp.push(k);
		}

		// Place the players on the map
		map.addUnitsToMap(temp);
		// Place the enemy on the map / Get Enemy from map
		player2.addListOfUnits(map.getEnemyUnits());

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
				map.resetMapCanMove();

				currUnit = map.getUnitAt(row, col);
				currRow = row;
				currCol = col;
				setCanMove(row, col, true);
				System.out.println("New CurrUnit " + currUnit.getUnitType()
						+ " at: (" + currRow + ", " + currCol + ")");
				return true;
			}

			else {
				return false;
			}
		}

		else {
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
			if (currUnit.canMove() && (!map.isOccupied(endRow, endCol) || map.getUnitAt(endRow, endCol)==currUnit)
					&& map.getSpace(endRow, endCol).getCanMoveTo()) {
				map.resetMapCanMove();
				map.moveUnit(currRow, currCol, endRow, endCol);

				// Set the new CurrRow and CurrCol, and check
				currRow = endRow;
				currCol = endCol;
				gameOver();

				// Take the unit that can no longer move out of the tempUnitList
				currUnit.setCanMove(false);
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

		if (currUnit != null && map.getUnitAt(endRow, endCol) != null) {
			// if both exist, check if one can move
			if (currUnit.canMove()) {
				boolean canAttack = inAttackRange(endRow, endCol);
				if (canAttack) {
					actAttack();
					map.attacked(currRow,currCol, endRow, endCol);
					return canAttack;
				}
				
				else{
					canAttack = attackHelper(currUnit.getMovement()-1, currRow, currCol);
					if(canAttack){
						actAttack();
						map.attacked(currRow,currCol, endRow, endCol);
					}
					return canAttack;
				}
			}

		}
		/*
		 * if (currUnit != null && map.getUnitAt(endRow, endCol) != null) { //
		 * if both exist, check if one can move if (currUnit.canMove()) {
		 * boolean canAttack = false; if (map.getSpace(currRow,
		 * currCol).getCanMoveTo()) { canAttack = true; if
		 * (inAttackRange(currRow, currCol)) { // If it's in range, just attack
		 * actAttack(); return canAttack; } else { // Move closer
		 * attackHelper(currUnit.getMovement(), currRow, currCol); actAttack();
		 * return canAttack; } } else { canAttack =
		 * attackHelper(currUnit.getMovement(), currRow, currCol);
		 * 
		 * }
		 * 
		 * map.getUnitAt(endRow, endCol) .reduceHealth(currUnit.getAttack()); }
		 * 
		 * else JOptionPane.showMessageDialog(null,
		 * "The currently selected unit cannot move."); } else
		 * JOptionPane.showMessageDialog(null,
		 * "Current unit or target are not available.");
		 */

		return false;
	}

	/**
	 * TODO
	 * 
	 * Move, and then check if the enemy is in range.
	 * 
	 * @return If the current unit is in range of the target after a move
	 */
	private boolean attackHelper(int movesLeft, int row, int col) {
		if (movesLeft < 0)
			return false;
		else {

			boolean toReturn = this.inAttackRange(endRow, endCol);

			if (toReturn) {
				map.moveUnit(currRow, currCol, col, row);
				currCol = col;
				currRow = row;
				return true;
			}

			else {
				if (row > 0 && endRow < row && !toReturn)
					toReturn = attackHelper(
							(movesLeft - 1 - map.getSpace(row - 1, col)
									.getMoveHinderance()), row - 1, col);
				if (row < 49 && endRow > row && !toReturn)
					toReturn = attackHelper(
							(movesLeft - 1 - map.getSpace(row + 1, col)
									.getMoveHinderance()), row + 1, col);
				if (col > 0 && endCol < col && !toReturn)
					toReturn = attackHelper(
							(movesLeft - 1 - map.getSpace(row, col - 1)
									.getMoveHinderance()), row, col - 1);
				if (col < 49 && endCol > col && !toReturn)
					toReturn = attackHelper(
							(movesLeft - 1 - map.getSpace(row, col + 1)
									.getMoveHinderance()), row, col + 1);
			}

			return toReturn;
		}
	}

	/**
	 * Simply check if the enemy is in range. Range is based on the four
	 * cardinal directions.
	 * 
	 * @return If the current unit is in range of the target.
	 */
	private boolean inAttackRange(int row, int col) {
		int temp = currUnit.getRange();

		while (temp >= 0) {
			// Try to see if the weapon will reach.
			if (row < endRow) {
				row++;
				temp--;
			} else if (row > endRow) {
				row--;
				temp--;
			} else if (col < endCol) {
				col++;
				temp--;
			} else if (col > endCol) {
				col--;
				temp--;
			}
			// Else, both are equal; return true.
			else
				return true;
		}

		// If the last move set them both equal, check if they are equal
		if (col == endCol && row == endRow)
			return true;
		else
			return false;
	}

	/**
	 * Actually attack the target. Only called if the currUnit can attack the
	 * targeted one.
	 */
	private void actAttack() {
		map.getUnitAt(endRow, endCol).reduceHealth(currUnit.getAttack());
		targetDead(endRow, endCol);

		gameOver();
		// If no other unit can move, end the turn
		currUnit.setCanMove(false);
		tempUnitList.remove(currUnit);
		if (tempUnitList.isEmpty())
			endTurn();
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
		if (!gameOver) {
			if (checkWinConditions()) {
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
				System.out.println(player2.getTeamStats());
				return true;
			} else
				return false;
		}

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
		if (currUnit != null) {
			endCol = currCol;
			endRow = currRow;
			move();
		}

		else
			JOptionPane.showMessageDialog(null, "Please select a unit.");
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
		map.resetMapCanMove();
		if (!gameOver) {
			if (playerTurn) {
				// Remove all of the player's units from tempList
				playerTurn = false;
				for (Unit i : tempUnitList)
					i.setCanMove(false);
				tempUnitList.clear();
				currUnit = null;

				System.out.println("Player 1 ends turn.");

				// Switch to AI
				tempUnitList = new ArrayList<Unit>(player2.allAliveUnits());
				for (Unit i : tempUnitList)
					i.setCanMove(true);

				if (tempUnitList.isEmpty())
					endTurn();
			} else {

				// Remove all of the AI's units from the tempList
				playerTurn = true;

				//
				for (Unit i : tempUnitList)
					i.setCanMove(false);
				tempUnitList.clear();
				currUnit = null;

				// Switch to player, add one to turns
				tempUnitList = new ArrayList<Unit>(player1.allAliveUnits());
				for (Unit i : tempUnitList)
					i.setCanMove(true);
				turns++;

				System.out.println("Second Player (AI) ends its turn.");

				gameOver();
				if (tempUnitList.isEmpty())
					endTurn();
			}
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
		System.out.println("New endRow: " + endRow);
		this.endRow = endRow;
	}

	public void setEndColumn(int endCol) {
		System.out.println("New endCol: " + endCol);
		this.endCol = endCol;
	}

	/**
	 * Decides if the current unit can move onto a surrounding space. Called
	 * twice, before and after a move/attack.
	 * 
	 * @param currRow
	 * @param currCol
	 */
	private void setCanMove(int row, int col, boolean set) {
		map.getSpace(currRow, currCol).setCanMoveTo(true);
		if (row < 49)
			canMoveHelper(currUnit.getMovement() - 1, row + 1, col,
					MoveDirection.DOWN);
		if (row > 0)
			canMoveHelper(currUnit.getMovement() - 1, row - 1, col,
					MoveDirection.UP);
		if (col < 49)
			canMoveHelper(currUnit.getMovement() - 1, row, col + 1,
					MoveDirection.RIGHT);
		if (col > 0)
			canMoveHelper(currUnit.getMovement() - 1, row, col - 1,
					MoveDirection.LEFT);
	}

	/**
	 * Helper method for setCanMove.
	 * 
	 * @param movesAvail
	 * @param row
	 * @param col
	 */
	private void canMoveHelper(int movesAvail, int row, int col, MoveDirection m) {

		movesAvail = movesAvail - map.getSpace(row, col).getMoveHinderance();
		if (movesAvail < 0 || map.getSpace(row, col).getCanMoveTo()) {
			return;
		} else {
			map.getSpace(row, col).setCanMoveTo(true);
			if (row < 49 && m != MoveDirection.UP)
				canMoveHelper(movesAvail - 1, row + 1, col, m);
			if (row > 0 && m != MoveDirection.DOWN)
				canMoveHelper(movesAvail - 1, row - 1, col, m);
			if (col < 49 && m != MoveDirection.LEFT)
				canMoveHelper(movesAvail - 1, row, col + 1, m);
			if (col > 0 && m != MoveDirection.RIGHT)
				canMoveHelper(movesAvail - 1, row, col - 1, m);
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
		if(temp!=null){
		if (!temp.isAlive()) {
			// TODO Remove them from the map

			// Remove them from the associated list
			if (player1.allAliveUnits().contains(temp))
				player1.unitKilled(temp);
			else
				player2.unitKilled(temp);

			if (tempUnitList.contains(temp))
				tempUnitList.remove(temp);
			// Check to see if the game is over
			System.out.println("Unit " + temp.getUnitType() + " at (" + row
					+ ", " + col + ") is dead!");
			System.out.println("Numbers on both sides: " + player1.getAliveNum()+", " + player2.getAliveNum());
			checkWinConditions();
			gameOver();

			return true;
		}
		
		else{
			map.attacked(currRow,currCol, row, col);
			return false;
		}}
		else return false;
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

	/**
	 * TODO FINISH
	 * 
	 * @return Depending on the game type, if the game has been won
	 */
	public boolean checkWinConditions() {
		if (j instanceof gametype.CaptureTower) {
			// If it's a CaptureTower game, check to see if the current player
			// unit
			// unit is on a tower space. If it is, the game has been won;
			// return true. Else, return false.
			if ((map.getSpace(currRow, currCol) instanceof space.TowerSpace
					&& player1.allAliveUnits().contains(
							map.getUnitAt(currRow, currCol))) || player2.everyonesDeadDave())
				winConditions = true;
			return j.CheckWinCondition(winConditions);
		} else
			return false;
	}

	private enum MoveDirection {
		UP, DOWN, LEFT, RIGHT;
	}
}