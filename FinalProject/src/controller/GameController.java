package controller;

import gametype.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.JOptionPane;

import model.*;
import space.*;
import units.*;
import item.*;

/**
 * 
 * The controller for a game. Sends messages to map, Saves Data, Loads Data,
 * sets up players, calculate which map is needed, sends messages to the enemy
 * team factory, etc.
 * 
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
	private AIPathFinder aiMove;

	private int currRow;
	private int currCol;
	private int endRow;
	private int endCol;
	private Player currPlayer;

	private GameTypeInterface gameType;
	private Object winConditions;
	private List<Point> playerLocals;
	private List<Point> enemyLocals;

	private boolean moveOn;

	/**
	 * TODO Work on this. Will add a createAI method soon
	 * 
	 * Constructor for one player.
	 * 
	 * @param player1
	 * @param i
	 */
	public GameController(Player player1, Difficulty i, String gameT) {
		this.map = new Map(i.getValue(), gameT);
		this.player1 = player1;
		this.player2 = new AI(i);

		if (gameT.equalsIgnoreCase("corner"))
			gameType = new FourCorners();
		else if (gameT.equalsIgnoreCase("survive"))
			gameType = new Survive(10);
		else
			gameType = new CaptureTower();
		// winConditions = false;

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
		currPlayer = player1;

		// TODO: Give the enemy units behaviors.
		aiMove = new AIPathFinder(map);

		enemyLocals = map.getEnemyUnitPositions();
		playerLocals = map.getGoodUnitPositions();
	}

	public String getCurrPlayerName() {
		if (playerTurn)
			return player1.getID();
		else
			return "AI Enemy ";
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
				setCanMove(row, col);
				System.out.println("New CurrUnit " + currUnit.getUnitType()
						+ " at: (" + currRow + ", " + currCol + ")");
				map.updateObservers();
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
	 * Move a selected Unit to a another space. Checks to see if the player can
	 * move to the targeted space, and if can, move them.
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
	public void move() {
		// Check to see if the end Row and End Col point to something
		if (endRow != 51 || endCol != 51) {
			if (!(map.getSpace(endCol, endRow).getSpaceType().equals("Wall"))) {
				System.out.println("(" + currRow + ", " + currCol
						+ ") Move to (" + endRow + ", " + endCol + ")");
				if (currUnit != null) {
					if (currUnit.canMove()
							&& (!map.isOccupied(endRow, endCol) || map
									.getUnitAt(endRow, endCol) == currUnit)
							&& map.getSpace(endRow, endCol).getCanMoveTo()) {

						moveOn = false;

						map.resetMapCanMove();
						map.moveUnit(currRow, currCol, endRow, endCol);

						// Set the new CurrRow and CurrCol, and check
						currRow = endRow;
						currCol = endCol;
						System.out.println(currRow);
						System.out.println(currCol);
						gameOver();

						if (playerTurn)
							attackAfterMove();

						endRow = 51;
						endCol = 51;

						// Take the unit that can no longer move out of the
						// tempUnitList
						if (!moveOn) {
							currUnit.setCanMove(false);
							tempUnitList.remove(currUnit);
							currUnit = null;
						}
						map.updateObservers();

						if (tempUnitList.isEmpty())
							endTurn();
						return;
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
					JOptionPane.showMessageDialog(null,
							"Space is out of range.");
				return;
			} else
				JOptionPane.showMessageDialog(null,
						"You can't move on top of walls!");
		} else
			JOptionPane.showMessageDialog(null,
					"Pick a space to move to before you try moving...");

	}

	private void attackAfterMove() {

		for (Point p : enemyLocals) {

			if (inAttackRange((int) p.getY(), (int) p.getX())) {
				int answer = JOptionPane
						.showConfirmDialog(
								null,
								"There are possible Units to attack in range. Would you like to attack one of them?",
								"Attack?", JOptionPane.YES_NO_OPTION);
				if (answer == JOptionPane.YES_OPTION) {

					moveOn = true;

					endCol = (int) p.getX();
					endRow = (int) p.getY();
					attack();
					break;

				}
				break;
			}
		}

	}

	/**
	 * Get it to attack
	 * 
	 * 
	 * @param sr
	 * @param sc
	 * @param er
	 * @param ec
	 * @return
	 */
	public void attack() {
		// Checks to see if the target is in range.
		if (endRow != 51 || endCol != 51) {

			// Makes sure that the target is not the current unit
			if (currUnit != map.getUnitAt(endRow, endCol)) {

				// Checks to see if on the same side.
				if (!SameTeam()) {

					// Checks to see if either of the units are false.
					if (currUnit != null
							&& map.getUnitAt(endRow, endCol) != null) {
						// if both exist, check if one can move
						if (inAttackRange(currRow, currCol)) {
							actAttack();

							// If no other unit can move, end the turn
							currUnit.setCanMove(false);
							tempUnitList.remove(currUnit);
							currUnit = null;
							endRow = 51;
							endCol = 51;

							map.resetMapCanMove();

							gameOver();
							map.updateObservers();
							if (tempUnitList.isEmpty())
								endTurn();
						}

						else {
							JOptionPane.showMessageDialog(null,
									"Enemy out of attack Range.");
							System.out.println("Enemy out of attack range.");
						}
					} else
						JOptionPane.showMessageDialog(null,
								"Nothing to Attack!");
				} else
					JOptionPane.showMessageDialog(null,
							"You cannot attack your own teammates...");
			} else
				JOptionPane
						.showMessageDialog(null, "You can't attack youself!");
		} else
			JOptionPane.showMessageDialog(null,
					"Pick a unit to attack before you try attacking...");
	}

	// /**
	// * Move, and then check if the enemy is in range.
	// *
	// * @return If the current unit is in range of the target after a move
	// */
	// private boolean attackHelper(int movesLeft, int row, int col) {
	// if (movesLeft < 0)
	// return false;
	// else {
	//
	// boolean toReturn = this.inAttackRange(endRow, endCol);
	//
	// if (toReturn) {
	// map.moveUnit(currRow, currCol, col, row);
	// currCol = col;
	// currRow = row;
	// return true;
	// }
	//
	// else {
	// if (row > 0 && endRow < row && !toReturn)
	// toReturn = attackHelper(
	// (movesLeft - 1 - map.getSpace(row - 1, col)
	// .getMoveHinderance()), row - 1, col);
	// if (row < 49 && endRow > row && !toReturn)
	// toReturn = attackHelper(
	// (movesLeft - 1 - map.getSpace(row + 1, col)
	// .getMoveHinderance()), row + 1, col);
	// if (col > 0 && endCol < col && !toReturn)
	// toReturn = attackHelper(
	// (movesLeft - 1 - map.getSpace(row, col - 1)
	// .getMoveHinderance()), row, col - 1);
	// if (col < 49 && endCol > col && !toReturn)
	// toReturn = attackHelper(
	// (movesLeft - 1 - map.getSpace(row, col + 1)
	// .getMoveHinderance()), row, col + 1);
	// }
	//
	// return toReturn;
	// }
	// }

	/**
	 * Check to see if units are on the same team. If they are, return true.
	 * 
	 * @return whether or not the target and the current are on the same team.
	 */
	private boolean SameTeam() {

		List<Unit> tempList;

		// Checks the player lists depending on whose turn it is
		if (playerTurn) {
			tempList = player1.getTeam();
		} else {
			tempList = player2.getTeam();

		}

		// Goes through the lists, checking to see if the target unit is in the
		// temporary list. Return whether or not it is.
		for (Unit j : tempList) {
			if (j == map.getUnitAt(endRow, endCol)) {
				return true;
			}

		}
		return false;
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

		return false;
	}

	/**
	 * Actually attack the target. Only called if the currUnit can attack the
	 * targeted one.
	 */
	private void actAttack() {
		map.getUnitAt(endRow, endCol).reduceHealth(currUnit.getAttack());

		targetDead(endRow, endCol);
	}

	/**
	 * TODO Finish this method.
	 */
	public boolean currUnitHasItem(ItemType item){
		return currUnit.hasItem(item);
	}

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
	 * Checks both of the player's aliveUnits to see if all of their units are
	 * dead. If either of them are out of units they can move, return true and
	 * end the game. Checked after every move and attack.
	 * 
	 * @return if the game is over or not
	 */

	public boolean gameOver() {
		if (!gameOver) {

			if (checkWinConditions()) {
				for (Unit u : tempUnitList)
					u.setCanMove(false);
				tempUnitList.clear();

				gameOver = true;
				playerWon = true;
				// Display some kind of message telling player 2 won
				JOptionPane.showMessageDialog(null, "Player " + player1.getID()
						+ " won!");
				System.out.println("Player " + player1.getID() + " won!");
				System.out.println("Number of turns taken: " + turns);
				player1.gameFinished();
				System.out.println("Games you finished: "
						+ player1.getGamesFinished());
				System.out.println("Your team stats: ");
				System.out.println(player1.getTeamStats());

				return true;
			} else if (player1.everyonesDeadDave()) {
				for (Unit u : tempUnitList)
					u.setCanMove(false);
				tempUnitList.clear();

				gameOver = true;
				playerWon = false;
				// Display some kind of message telling player 1 won
				JOptionPane.showMessageDialog(null,
						"AI won! Better luck next time...");
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
	 * TODO FINISH
	 * 
	 * Determine if a game of a particular type has been won. Sends the relevant
	 * information to the right file to check.
	 * 
	 * @return Depending on the game type, if the game has been won
	 */
	public boolean checkWinConditions() {
		if (gameType instanceof gametype.CaptureTower) {

			if (((map.getSpace(currCol, currRow) instanceof space.TowerSpace && playerTurn) || player2
					.everyonesDeadDave())) {
				return true;
			} else
				return false;
		} else if (gameType instanceof gametype.Survive) {
			if (!player1.everyonesDeadDave()
					&& gameType.CheckWinCondition(turns))
				return true;
			else
				return false;
		}

		// TODO finish working on this
		else if (gameType instanceof gametype.FourCorners) {
			winConditions = new ArrayList<Space>();
			
			boolean toReturn = true;
			
			if (!((CaptureCornerSpace) map.getSpace(0, 0)).getHasBeenCaptured())
				toReturn = false;
			else if (!((CaptureCornerSpace) map.getSpace(49, 0))
					.getHasBeenCaptured())
				toReturn = false;
			else if (!((CaptureCornerSpace) map.getSpace(0, 49))
					.getHasBeenCaptured())
				toReturn = false;
			else if (!((CaptureCornerSpace) map.getSpace(49, 49))
					.getHasBeenCaptured())
				toReturn = false;

			return toReturn;
		}

		else
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

	/*
	 * \/**
	 * 
	 * When called, ends a turn. Checks to see whose turn it is, clears the
	 * temporary unit list, sets the current unit to null. Sets the can move to
	 * false.
	 *//*
		 * 
		 * public void endTurn() { map.resetMapCanMove(); if (!gameOver) { if
		 * (playerTurn) { // Remove all of the player's units from tempList
		 * playerTurn = false; for (Unit i : tempUnitList) i.setCanMove(false);
		 * tempUnitList.clear(); currUnit = null;
		 * 
		 * System.out.println("Player 1 ends turn.");
		 * 
		 * // Switch to AI tempUnitList = new
		 * ArrayList<Unit>(player2.allAliveUnits()); for (Unit i : tempUnitList)
		 * i.setCanMove(true);
		 * 
		 * map.updateObservers(); if (tempUnitList.isEmpty()) endTurn();
		 * 
		 * currRow = 0; currCol = 0;
		 * 
		 * // TODO: Update the enemy's with the player's current locations }
		 * else {
		 * 
		 * // Remove all of the AI's units from the tempList playerTurn = true;
		 * 
		 * // for (Unit i : tempUnitList) i.setCanMove(false);
		 * tempUnitList.clear(); currUnit = null;
		 * 
		 * // Switch to player, add one to turns tempUnitList = new
		 * ArrayList<Unit>(player1.allAliveUnits()); for (Unit i : tempUnitList)
		 * i.setCanMove(true); turns++;
		 * 
		 * System.out.println("Second Player (AI) ends its turn.");
		 * 
		 * currCol = currRow = 0;
		 * 
		 * gameOver();
		 * 
		 * map.updateObservers(); if (tempUnitList.isEmpty()) endTurn(); } } }
		 */

	/**
	 * When called, ends a turn. Checks to see whose turn it is, clears the
	 * temporary unit list, sets the current unit to null. Sets the can move to
	 * false.
	 * 
	 */
	public void endTurn() {
		map.resetMapCanMove();
		if (!gameOver) {
			if (playerTurn) {
				map.setIsPlayerTurn();

				// Remove all of the player's units from tempList
				playerTurn = false;
				for (Unit i : tempUnitList)
					i.setCanMove(false);
				tempUnitList.clear();
				currUnit = null;

				System.out.println("Player 1 ends turn.");

				playerLocals = map.getGoodUnitPositions();

				// Switch to AI
				tempUnitList = new ArrayList<Unit>(player2.allAliveUnits());
				for (Unit i : tempUnitList)
					i.setCanMove(true);

				map.updateObservers();
				enemyTurn();

				currRow = 0;
				currCol = 0;

				// TODO: Update the enemy's with the player's current locations
			} else {

				// Remove all of the AI's units from the tempList
				playerTurn = true;
				map.setIsPlayerTurn();

				//
				for (Unit i : tempUnitList)
					i.setCanMove(false);
				tempUnitList.clear();
				currUnit = null;

				enemyLocals = map.getEnemyUnitPositions();

				// Switch to player, add one to turns
				tempUnitList = new ArrayList<Unit>(player1.allAliveUnits());
				for (Unit i : tempUnitList)
					i.setCanMove(true);
				turns++;

				System.out.println("Second Player (AI) ends its turn.");

				currCol = currRow = 0;

				gameOver();

				map.updateObservers();
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

	/**
	 * Sets the new endColumn. Used in attack and movement.
	 * 
	 * @param endRow
	 *            , the new ending row
	 */
	public void setEndRow(int endRow) {
		System.out.println("New endRow: " + endRow);
		this.endRow = endRow;
	}

	/**
	 * Sets the new endColumn. Used in attack and movement.
	 * 
	 * @param endCol
	 *            , the new ending column
	 */
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
	private void setCanMove(int row, int col) {
		map.getSpace(currRow, currCol).setCanMoveTo(true);

		if (row < 49)
			if (map.getSpace(row + 1, currCol).getWalkable())
				canMoveHelper(currUnit.getMovement(), row + 1, col);
		if (row > 0)
			if (map.getSpace(row - 1, col).getWalkable())
				canMoveHelper(currUnit.getMovement(), row - 1, col);
		if (col < 49)
			if (map.getSpace(row, col + 1).getWalkable())
				canMoveHelper(currUnit.getMovement(), row, col + 1);
		if (col > 0)
			if (map.getSpace(row, col - 1).getWalkable())
				canMoveHelper(currUnit.getMovement(), row, col - 1);
	}

	/**
	 * TODO Get it working
	 * 
	 * Helper method for setCanMove.
	 * 
	 * @param movesAvail
	 * @param row
	 * @param col
	 */
	private void canMoveHelper(int movesAvail, int row, int col) {

		if (movesAvail >= map.getSpace(col, row).getMoveHinderance()
				&& map.getSpace(row, col).getWalkable()) {
			movesAvail = movesAvail
					- map.getSpace(col, row).getMoveHinderance();
			map.getSpace(row, col).setCanMoveTo(true);
			if (row < 49)
				if (map.getSpace(row + 1, col).getWalkable())
					canMoveHelper(movesAvail, row + 1, col);
			if (row > 0)
				if (map.getSpace(row - 1, col).getWalkable())
					canMoveHelper(movesAvail, row - 1, col);
			if (col < 49)
				if (map.getSpace(row, col + 1).getWalkable())
					canMoveHelper(movesAvail, row, col + 1);
			if (col > 0)
				if (map.getSpace(row, col - 1).getWalkable())
					canMoveHelper(movesAvail, row, col - 1);
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
	private void targetDead(int row, int col) {
		Unit temp = map.getUnitAt(row, col);
		if (!temp.isAlive()) {

			if (playerTurn) {

				JOptionPane.showMessageDialog(
						null,
						"The attacked " + map.getUnitAt(row, col).getUnitType()
								+ " was left with no health and has died!"
								+ '\n'
								+ "Number of units remaining on both sides: "
								+ player1.getID() + " - "
								+ player1.getAliveNum() + ", Zombies - "
								+ (player2.getAliveNum() - 1));

			} else {

				JOptionPane.showMessageDialog(
						null,
						"The attacked " + map.getUnitAt(row, col).getUnitType()
								+ " was left with no health and has died!"
								+ '\n'
								+ "Number of units remaining on both sides: "
								+ player1.getID() + " - "
								+ (player1.getAliveNum() - 1) + ", Zombies - "
								+ (player2.getAliveNum()));
			}

			map.removeUnit(col, row);

			// Remove them from the associated list
			if (player1.allAliveUnits().contains(temp))
				player1.unitKilled(temp);
			else
				player2.unitKilled(temp);

			// If the unit is in the temporary list, remove it.
			if (tempUnitList.contains(temp))
				tempUnitList.remove(temp);

			// Check to see if the game is over
			System.out.println("Unit " + temp.getUnitType() + " at (" + row
					+ ", " + col + ") is dead!");

		} else {
			JOptionPane.showMessageDialog(null, "The attacked "
					+ map.getUnitAt(endRow, endCol).getUnitType()
					+ " was left with "
					+ map.getUnitAt(endRow, endCol).getHealth()
					+ " health after the attack!");
		}
	}

	/**
	 * Checks to see if the game is over. If it is, return true;
	 * 
	 * @return if the game is over or not
	 */
	public boolean isGameOver() {
		return gameOver;
	}

	/**
	 * Called upon to check if the player has won. Returns true if the player
	 * wins the game.
	 * 
	 * @return Whether or not the game has won
	 */
	public boolean playerWon() {
		return playerWon;
	}

	/**
	 * Checks to see if the Player is currently moving. If the player is, return
	 * true.
	 * 
	 * @return Whether or not the player is moving.
	 */
	public boolean playerTurn() {
		return playerTurn;
	}

	/**
	 * TODO Test
	 * 
	 * Gets the point locations of all of the player locations. Used with enemy
	 * AI, gives them a list of targets that they can move to.
	 * 
	 * @return A List of Points with all of the locations of monsters.
	 */
	public List<Point> getPlayerUnits() {
		return map.getGoodUnitPositions();
	}

	/**
	 * 
	 */
	public void enemyTurn() {
		// TODO FINISH

		if (!playerTurn) {
			Point temp = null;

			for (Point u : enemyLocals) {
				// Goes through each member of the AI. Checks to see if there
				// are
				// any enemies within range.
				temp = this.nearestPlayerUnit(u);
				this.setCurrentUnit(u.y, u.x);
				this.endRow = temp.y;
				this.endCol = temp.x;
				// if so, attack.
				if (this.inAttackRange(currRow, currCol)) {

				}
				// If not, then move the AI closer to the player.

				else
					enemyMove(u);

				// TODO: empty curr list once all of the Ai has moved
				map.getUnitAt(u.y, u.x).setCanMove(false);
				tempUnitList.remove(map.getUnitAt(u.y, u.x));
			}

			tempUnitList.clear();

			endTurn();
		}
	}

	/**
	 * TODO Write this Method for automatically moving the enemy AI. Moves them
	 * toward the closest human based on their behavior. If they are near enough
	 * to a player's unit, attack.
	 */
	public void enemyMove(Point em) {
		/*
		 * TODO: Add these things 1) Nearest Player Method 2) List of Enemy Unit
		 * Locations 3) Player's XY values 4) Send these params to
		 * AIPathfinder.traverse(): AiROW, AI COLUMN, PlayerPointLIst
		 */

		playerLocals = getPlayerUnits();
		Point p = nearestPlayerUnit(em);

		// Added '7' as a placeholder for unit movement.
		// aiMove.traverse(p.x, p.y, em.x, em.y, 7);
		map.moveUnit(p.x, p.y, aiMove.traverse(p.x, p.y, em.x, em.y, 7).x,
				aiMove.traverse(p.x, p.y, em.x, em.y, 7).y);
	}

	/**
	 * Finds the nearest player location point closest to an enemy unit. Returns
	 * the nearest point based on how many moves would be needed to make it.
	 * 
	 * @param enemyLoc
	 * @return
	 */
	public Point nearestPlayerUnit(Point enemyLoc) {
		int spaceNear = 0;
		int tempSN = 0;
		Point toReturn = null;

		for (Point p : playerLocals) {
			tempSN = Math.abs(enemyLoc.x - p.x) + Math.abs(enemyLoc.y - p.y);
			if (tempSN < spaceNear || spaceNear == 0) {
				spaceNear = tempSN;
				toReturn = p;
			}
		}

		return toReturn;
	}

	/*
	 * A private Enum class, helps with movement. Prevents overlap and stack
	 * overflow during calculating the spaces where the player can move.
	 * 
	 * @author Brian Seaman
	 */
	/*
	 * private enum MoveDirection { UP, DOWN, LEFT, RIGHT; // NOX, NOY; }
	 */
}