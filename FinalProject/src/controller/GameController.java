package controller;

import gametype.*;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
public class GameController implements Serializable {
	private Player player1;
	private AI player2;
	private Map map;
	private List<Unit> tempUnitList;
	private Unit currUnit;
	private int turns;
	private boolean playerTurn;
	private boolean gameOver;
	private boolean playerWon;
	private boolean hasAttacked;
	private AIPathFinder aiMove;

	private int currRow;
	private int currCol;

	private int endRow = 51;
	private int endCol = 51;

	private int attackRow;
	private int attackCol;

	private Player currPlayer;

	private GameTypeInterface gameType;
	private Object winConditions;

	private boolean moveOn;
	private int rowValue;
	private int colValue;

	private boolean testing;
	boolean notShownNE;
	boolean notShownSW;
	boolean notShownSE;

	private ItemType usingItemType;

	/**
	 * Constructor for one player.
	 * 
	 * @param player1
	 * @param i
	 */
	public GameController(Player player1, Difficulty i, String gameT, boolean testing) {
		this.map = new Map(i.getValue(), gameT, testing);
		this.player1 = player1;
		this.player2 = new AI(i);

		this.testing = testing;
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
		map.addUnitsToMap(temp, testing);
		// Place the enemy on the map / Get Enemy from map
		player2.addListOfUnits(map.getEnemyUnits());

		tempUnitList = new ArrayList<Unit>(player1.allAliveUnits());
		for (Unit j : tempUnitList)
			j.setCanMove(true);
		turns = 0;
		playerTurn = true;
		currPlayer = player1;
		hasAttacked = false;

		// Give the enemy units behaviors.
		aiMove = new AIPathFinder(map);

		checkWinConditions();
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
				System.out.println("New CurrUnit " + currUnit.getUnitType() + " at: (" + currRow + ", " + currCol + ")");
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
		// Check to see if the end Row and end Col point to something
		if (endRow != 51 || endCol != 51) {
			if (!(map.getSpace(endCol, endRow).getSpaceType().equals("Wall"))) {
				System.out.println("(" + currRow + ", " + currCol + ") Move to (" + endRow + ", " + endCol + ")");
				if (currUnit != null) {
					if (currUnit.canMove() && (!map.isOccupied(endRow, endCol) || map.getUnitAt(endRow, endCol) == currUnit) && map.getSpace(endRow, endCol).getCanMoveTo()) {

						moveOn = false;

						map.resetMapCanMove();
						map.moveUnit(currRow, currCol, endRow, endCol);

						if (playerTurn)
							pickUpItem();

//						if(currUnit instanceof Doctor || currUnit instanceof Engineer || currUnit instanceof Ranger ||
//							currUnit instanceof Sniper || currUnit instanceof Soldier)
//							goodUnitMove();
						

						// Set the new CurrRow and CurrCol, and check
						currRow = endRow;
						currCol = endCol;
						gameOver();

						if (playerTurn)
							attackAfterMove();

						// Take the unit that can no longer move out of the
						// tempUnitList
						if (!moveOn) {
							try{
							currUnit.setCanMove(false);
							tempUnitList.remove(currUnit);
							setCurrentUnitSelected(false);
							currUnit = null;
							}catch (Exception e){
								
							}
						}
						
						endRow = 51;
						endCol = 51;
						
						map.updateObservers();

						if (tempUnitList.isEmpty())
							endTurn();

						
						return;
					}
				}

				if (currUnit == null){
					if(playerTurn)
					JOptionPane.showMessageDialog(null, "Please select a Unit to move first");
					
				} else if (!currUnit.canMove()) {
					if(playerTurn)
					JOptionPane.showMessageDialog(null, "Unit can't move anymore. Select a new unit.");
					
				} else if (map.isOccupied(endRow, endCol)) {
					if(playerTurn)
					JOptionPane.showMessageDialog(null, "Space is occupied, you can't move there");
					
				} else if (!map.getSpace(endRow, endCol).getCanMoveTo()) {
					if(playerTurn)
					JOptionPane.showMessageDialog(null, "Space is out of range.");
				}
				
				return;
				
			} else
				JOptionPane.showMessageDialog(null, "You can't move on top of walls!");
		} else
			JOptionPane.showMessageDialog(null, "Pick a space to move to before you try moving...");

	}
	
	
	
// Animate the move
// while not at end position
// change position
// repaint the graphical view, then Thread.sleep(20);

	private void goodUnitMove() {
		rowValue = aiMove.traverse(currCol, currRow, endCol, endRow, currUnit.getMovement()).x;
		colValue = aiMove.traverse(currCol, currRow, endCol, endRow, currUnit.getMovement()).y;

	//	endRow = rowValue;
	//	endCol = colValue;
		ArrayList<Point> positionsMoved = aiMove.getMovePositions();

		for(int i = 0; i < positionsMoved.size(); i++) {
			Point p = positionsMoved.get(i);
			int getToCol = p.y;
			int getToRow = p.x;
			System.out.println("goodMoveUnit- col: " + getToCol + " row: " + getToRow);
			
			
			currCol = getToCol;
			currRow = getToRow;
			currUnit.setCurrentPosition(currRow, currCol);
			
			map.updateObservers();
			
			endRow = rowValue;
			endCol = colValue;
//			try {
//				Thread.sleep(20);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			
		}
			
	}


	private void pickUpItem() {

		Item[][] itemsOnMap = map.getItems();

		if (!(itemsOnMap[endCol][endRow] == null)) {

			if (itemsOnMap[endCol][endRow] instanceof RandomItem) {

				Item newItem = RandomItem.generateItem();
				if (newItem.getItemType() == ItemType.MEDKIT)
					JOptionPane.showMessageDialog(null, "Your " + currUnit.getUnitType() + " picked up a basic medkit!");
				if (newItem.getItemType() == ItemType.MINE)
					JOptionPane.showMessageDialog(null, "Your " + currUnit.getUnitType() + " picked up a basic mine!");
				if (newItem.getItemType() == ItemType.GRENADE)
					JOptionPane.showMessageDialog(null, "Your " + currUnit.getUnitType() + " picked up a basic grenade!");
				currUnit.addItem(newItem);

			} else {

				Item newItem = RandomBoost.generateBoost();
				if (newItem.getItemType() == ItemType.HP)
					JOptionPane.showMessageDialog(null, "Your " + currUnit.getUnitType() + " picked up an HP boost!");
				if (newItem.getItemType() == ItemType.ATK)
					JOptionPane.showMessageDialog(null, "Your " + currUnit.getUnitType() + " picked up an attack boost!");
				if (newItem.getItemType() == ItemType.DEF)
					JOptionPane.showMessageDialog(null, "Your " + currUnit.getUnitType() + " picked up a defense boost!");

				currUnit.addItem(newItem);
				currUnit.UpdateBoosts();
			}
			map.removeItem(endRow, endCol);
		}

	}

	private void attackAfterMove() {

		if (!gameOver) {
			// TODO: Test

			for (Unit p : player2.allAliveUnits()) {

				if (inAttackRange((int) p.getY(), (int) p.getX())) {
					int answer = JOptionPane.showConfirmDialog(null, "There are possible Units to attack in range. Would you like to attack one of them?", "Attack?", JOptionPane.YES_NO_OPTION);
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

		if (!(currUnit == null)) {

			if (endRow != 51 || endCol != 51) {

				// Makes sure that the target is not the current unit
				if (currUnit != map.getUnitAt(endRow, endCol)) {

					// Checks to see if on the same side.
					if (!SameTeam()) {

						// Checks to see if either of the units are false.
						if (currUnit != null && map.getUnitAt(endRow, endCol) != null) {
							// if both exist, check if one can move
							if (inAttackRange(currRow, currCol)) {
								actAttack();

								// If no other unit can move, end the turn
								currUnit.setCanMove(false);
								currUnit.setIsSelected(false);
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
								if(playerTurn)
									JOptionPane.showMessageDialog(null, "Enemy out of attack Range.");
								System.out.println("Enemy out of attack range.");
							}
						} else
							JOptionPane.showMessageDialog(null, "Nothing to Attack!");
					} else
						JOptionPane.showMessageDialog(null, "You cannot attack your own teammates...");
				} else
					JOptionPane.showMessageDialog(null, "You can't attack youself!");
			} else
				JOptionPane.showMessageDialog(null, "Pick a unit to attack before you try attacking...");
		} else
			JOptionPane.showMessageDialog(null, "Pick a unit to commit the attack before you try attacking...");
		
		if(currUnit !=null && !playerTurn){
			currUnit.setCanMove(false);
			tempUnitList.remove(currUnit);
			currUnit = null;
			if (tempUnitList.isEmpty())
				endTurn();
		}
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

		hasAttacked = true;
		attackRow = endRow;
		attackCol = endCol;
	}

	public int getAttackRow() {
		return attackRow;
	}

	public int getAttackCol() {
		return attackCol;
	}

	public boolean getHasAttacked() {
		return hasAttacked;
	}

	/**
	 * TODO Finish this method.
	 */
	public boolean currUnitHasItem(ItemType item) {
		return currUnit.hasItem(item);
	}

	/**
	 * TODO Finish this method
	 * 
	 * Use the item of a selected unit.
	 * 
	 * @return if the item was used.
	 */
	public void useItem() {

		if (!(currUnit == null)) {
			if (endRow != 51 || endCol != 51) {
				if (inAttackRange(currRow, currCol)) {

					Object[] options = { "Health Kit", "Mine", "Grenade", "Cancel" };
					int answer = JOptionPane.showOptionDialog(null, "What item would you like to use?", "Use Item?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

					if (answer == JOptionPane.YES_OPTION) {
						usingItemType = ItemType.MEDKIT;

					} else if (answer == JOptionPane.NO_OPTION) {
						usingItemType = ItemType.MINE;

					} else if (answer == JOptionPane.CANCEL_OPTION) {
						usingItemType = ItemType.GRENADE;

					} else {

					}

					if (currUnit.hasItem(usingItemType)) {

						if (usingItemType == ItemType.MEDKIT) {

							if (!(map.getUnitAt(endRow, endCol) == null)) {

								if (SameTeam()) {

									map.getUnitAt(endRow, endCol).restoreHealth();
									JOptionPane.showMessageDialog(null, "The " + map.getUnitAt(endRow, endCol).getUnitType() + " you selected has had their health fully restored.");
									currUnit.setCanMove(false);
									currUnit.removeItem(usingItemType);
									currUnit.setIsSelected(false);
									tempUnitList.remove(currUnit);
									currUnit = null;
									endRow = 51;
									endCol = 51;
									map.resetMapCanMove();
									map.updateObservers();

								} else
									JOptionPane.showMessageDialog(null, "You don't want to heal a " + map.getUnitAt(endRow, endCol).getUnitType() + "!");

							} else
								JOptionPane.showMessageDialog(null, "There is nothing there to heal!");
						} // end health kit

						if (usingItemType == ItemType.MINE) {

							JOptionPane.showMessageDialog(null, "A Mine has been placed");
							map.getSpace(endCol, endRow).setHasMine(true);
							currUnit.setCanMove(false);
							currUnit.removeItem(usingItemType);
							currUnit.setIsSelected(false);
							tempUnitList.remove(currUnit);
							currUnit = null;
							endRow = 51;
							endCol = 51;
							map.resetMapCanMove();
							map.updateObservers();

						} // end Mine

						if (usingItemType == ItemType.GRENADE) {

							JOptionPane.showMessageDialog(null, "Your " + map.getUnitAt(currRow, currCol).getUnitType() + " threw a grenade.");
							blowShitUp(endRow, endCol);
							currUnit.setCanMove(false);
							currUnit.removeItem(usingItemType);
							currUnit.setIsSelected(false);
							tempUnitList.remove(currUnit);
							currUnit = null;
							endRow = 51;
							endCol = 51;
							map.resetMapCanMove();
							map.updateObservers();
						}

					} else
						JOptionPane.showMessageDialog(null, "The unit you selected does not have that item.");
				} else
					JOptionPane.showMessageDialog(null, "The place you are tying to use the item is out of this units range");

			} else
				JOptionPane.showMessageDialog(null, "Pick a space to use the Item first");

		} else
			JOptionPane.showMessageDialog(null, "Pick a unit to use an Item first");
		
		checkWinConditions();

	}

	/**
	 * TODO Finish and Test Blow those zombies up, kid. Just try not to blow
	 * yourself up.
	 * 
	 * @param it
	 *            , the ItemType of the explosive used
	 */
	private void blowShitUp(int row, int col) {

		int baseRow = row;
		int baseCol = col;

		if (map.getSpace(col, row).getOccupied()) {
			if (!(SameTeam())) {
				map.getUnitAt(row, col).reduceHealth(100);
				targetDead(row, col);
				row = baseRow;
				col = baseCol;

			}
		}

		if (map.getSpace(col, row + 1).getOccupied()) {
			if (!(SameTeam())) {
				map.getUnitAt(row + 1, col).reduceHealth(75);
				targetDead(row + 1, col);
				row = baseRow;
				col = baseCol;
			}
		}

		if (map.getSpace(col, row - 1).getOccupied()) {
			if (!(SameTeam())) {
				map.getUnitAt(row - 1, col).reduceHealth(75);
				targetDead(row - 1, col);
				row = baseRow;
				col = baseCol;
			}
		}

		if (map.getSpace(col + 1, row).getOccupied()) {
			if (!(SameTeam())) {
				map.getUnitAt(row, col + 1).reduceHealth(75);
				targetDead(row, col + 1);
				row = baseRow;
				col = baseCol;
			}
		}

		if (map.getSpace(col - 1, row).getOccupied()) {
			if (!(SameTeam())) {
				map.getUnitAt(row, col - 1).reduceHealth(75);
				targetDead(row, col - 1);
				row = baseRow;
				col = baseCol;
			}
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
				// System.out.println("Player " + player1.getID() + " won!");
				// System.out.println("Number of turns taken: " + turns);
				player1.gameFinished();
				// System.out.println("Games you finished: " +
				// player1.getGamesFinished());
				// System.out.println("Your team stats: ");
				// System.out.println(player1.getTeamStats());

				return true;
			} else if (player1.everyonesDeadDave()) {
				for (Unit u : tempUnitList)
					u.setCanMove(false);
				tempUnitList.clear();

				gameOver = true;
				playerWon = false;
				// Display some kind of message telling player 1 won
				JOptionPane.showMessageDialog(null, "AI won! Better luck next time...");
				System.out.println("AI won! Better luck next time...");
				System.out.println("Number of turns taken: " + turns);
				player1.gameFinished();
				System.out.println("Games you've finished: " + player1.getGamesFinished());
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

			if (((map.getSpace(currCol, currRow) instanceof space.TowerSpace && playerTurn))) {
				JOptionPane.showMessageDialog(null, "Congrats you captured the tower! You win!");
				return true;

			} else if (player2.everyonesDeadDave()) {
				JOptionPane.showMessageDialog(null, "Congrats you killed all the Zombies! You win!");
				return true;

			} else {
				return false;
			}

		} else if (gameType instanceof gametype.Survive) {
			if ((!player1.everyonesDeadDave() && gameType.CheckWinCondition(turns))) {
				JOptionPane.showMessageDialog(null, "Congrats you survived the zombie attack! You win!");
				return true;
			}
			if (player2.everyonesDeadDave()) {
				JOptionPane.showMessageDialog(null, "Congrats you killed all the Zombies! You win!");
				return true;
			}

			else
				return false;
		}

		else if (gameType instanceof gametype.FourCorners) {
			if (!testing) {
				if (map.getSpace(0, 0).getOccupied()) {
					((CaptureCornerSpace) map.getSpace(0, 0)).setHasBeenCaptured(true);
					// JOptionPane.showMessageDialog(null,
					// "Northwest Tower captured.");
				}
				if (map.getSpace(0, 49).getOccupied()) {
					((CaptureCornerSpace) map.getSpace(0, 49)).setHasBeenCaptured(true);
					if (!notShownNE) {
						JOptionPane.showMessageDialog(null, "Northeast Tower captured.");
						notShownNE = true;
					}
				}
				if (map.getSpace(49, 0).getOccupied()) {
					((CaptureCornerSpace) map.getSpace(49, 0)).setHasBeenCaptured(true);
					if (!notShownSW) {
						JOptionPane.showMessageDialog(null, "Southwest Tower captured.");
						notShownSW = true;
					}
				}
				if (map.getSpace(49, 49).getOccupied()) {
					((CaptureCornerSpace) map.getSpace(49, 49)).setHasBeenCaptured(true);
					if (!notShownSE) {
						JOptionPane.showMessageDialog(null, "Southeast Tower captured.");
						notShownSE = true;
					}
				}

				if (player2.everyonesDeadDave()) {
					JOptionPane.showMessageDialog(null, "Congrats you killed all the Zombies! You win!");
					return true;
				}
				if (((CaptureCornerSpace) map.getSpace(0, 0)).getHasBeenCaptured())
					if (((CaptureCornerSpace) map.getSpace(49, 0)).getHasBeenCaptured())
						if (((CaptureCornerSpace) map.getSpace(0, 49)).getHasBeenCaptured())
							if (((CaptureCornerSpace) map.getSpace(49, 49)).getHasBeenCaptured()) {
								JOptionPane.showMessageDialog(null, "Congrats you secured all the towers! You win!");
								return true;
							}
			} else {

				if (map.getSpace(1, 1).getOccupied()) {
					((CaptureCornerSpace) map.getSpace(1, 1)).setHasBeenCaptured(true);
					// JOptionPane.showMessageDialog(null,
					// "Northwest Tower captured.");
				}
				if (map.getSpace(1, 8).getOccupied()) {
					((CaptureCornerSpace) map.getSpace(1, 8)).setHasBeenCaptured(true);
					if (!notShownNE) {
						JOptionPane.showMessageDialog(null, "Northeast Tower captured.");
						notShownNE = true;
					}
				}
				if (map.getSpace(8, 1).getOccupied()) {
					((CaptureCornerSpace) map.getSpace(8, 1)).setHasBeenCaptured(true);
					if (!notShownSW) {
						JOptionPane.showMessageDialog(null, "Southwest Tower captured.");
						notShownSW = true;
					}
				}
				if (map.getSpace(8, 8).getOccupied()) {
					((CaptureCornerSpace) map.getSpace(8, 8)).setHasBeenCaptured(true);
					if (!notShownSE) {
						JOptionPane.showMessageDialog(null, "Southeast Tower captured.");
						notShownSE = true;
					}
				}

				if (player2.everyonesDeadDave()) {
					JOptionPane.showMessageDialog(null, "Congrats you killed all the Zombies! You win!");
					return true;
				}
				if (((CaptureCornerSpace) map.getSpace(1, 1)).getHasBeenCaptured())
					if (((CaptureCornerSpace) map.getSpace(1, 8)).getHasBeenCaptured())
						if (((CaptureCornerSpace) map.getSpace(8, 1)).getHasBeenCaptured())
							if (((CaptureCornerSpace) map.getSpace(8, 8)).getHasBeenCaptured()) {
								JOptionPane.showMessageDialog(null, "Congrats you secured all the towers! You win!");
								return true;
							}
			}
			return false;
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

				// Switch to AI
				tempUnitList = new ArrayList<Unit>(player2.allAliveUnits());
				for (Unit i : tempUnitList)
					i.setCanMove(true);

				map.updateObservers();
				enemyTurn();

				// TODO: Update the enemy's with the player's current locations
			} else {

				// Remove all of the AI's units from the tempList
				playerTurn = true;
				map.setIsPlayerTurn();

				//
				for (Unit i : player2.allAliveUnits())
					i.setCanMove(false);
				tempUnitList.clear();
				currUnit = null;

				// Switch to player, add one to turns
				tempUnitList = new ArrayList<Unit>(player1.allAliveUnits());
				for (Unit i : tempUnitList)
					i.setCanMove(true);
				turns++;

				System.out.println("Second Player (AI) ends its turn.");

				currCol = currRow = 0;

				ArrayList<Point> mines = aiMove.getSteppedOnMines();

				for (Point p : mines) {
					blowShitUp(p.x, p.y);
					map.getSpace(p.y, p.x).setHasMine(false);

				}
				aiMove.clearSteppedOnMines();
				gameOver();

				map.updateObservers();
				if (tempUnitList.isEmpty())
					endTurn();
			}
		}
	}

	/*
	 * TODO Check item type, see if it is on same side
	 * 
	 * Heal a friendly unit. Checks to see if on the same side, and if the unit
	 * can heal.
	 * 
	 * @param er , target row
	 * 
	 * @param ec , target col
	 * 
	 * @return can heal, or can't heal
	 * 
	 * public boolean heal(int targetRow, int targetCol) { return false; }
	 */

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
	 * Gets the new endColumn. Used in attack and movement.
	 * 
	 * @param endRow
	 *            , the new ending row
	 */
	public int getEndRow() {
		return endRow;
	}

	/**
	 * Gets the new endColumn. Used in attack and movement.
	 * 
	 * @param endCol
	 *            , the new ending column
	 */
	public int getEndColumn() {
		return endCol;
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
			if (map.getSpace(row + 1, col).getWalkable())
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

		if (movesAvail >= map.getSpace(col, row).getMoveHinderance() && map.getSpace(row, col).getWalkable()) {
			movesAvail = movesAvail - map.getSpace(col, row).getMoveHinderance();
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
		if (map.getUnitAt(row, col) instanceof Hole) {
			map.coverUpHole(row, col);
		}
		if (!temp.isAlive()) {

			if (playerTurn) {

				if (map.getUnitAt(endCol, endRow) instanceof Hole)
					JOptionPane.showMessageDialog(null, "You threw a bomb down the hole and stopped zombies from crawling out of it!");
				else
					JOptionPane.showMessageDialog(null, "The attacked " + map.getUnitAt(row, col).getUnitType() + " was left with no health and has died!" + '\n' + "Number of units remaining on both sides: " + player1.getID() + " - " + player1.getAliveNum() + ", Zombies - " + (player2.getAliveNum() - 1));

			} else {

				JOptionPane.showMessageDialog(null, "The attacked " + map.getUnitAt(row, col).getUnitType() + " was left with no health and has died!" + '\n' + "Number of units remaining on both sides: " + player1.getID() + " - " + (player1.getAliveNum() - 1) + ", Zombies - " + (player2.getAliveNum()));
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
			System.out.println("Unit " + temp.getUnitType() + " at (" + row + ", " + col + ") is dead!");

		} else {
			
			if(map.getUnitAt(row, col).getNoDamage()){
				JOptionPane.showMessageDialog(null, "The attacked " + map.getUnitAt(row, col).getUnitType() + " had too high of a defense for it to be harmed");
				map.getUnitAt(row, col).setNoDamage(false);
			} else {
				JOptionPane.showMessageDialog(null, "The attacked " + map.getUnitAt(row, col).getUnitType() + " was left with " + map.getUnitAt(row, col).getHealth() + " health after the attack!");

			}
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
	public List<Unit> getPlayerUnits() {
		return player1.allAliveUnits();
	}
	
	/**
	 * 
	 */
	public synchronized void enemyTurn() {
		// TODO FINISH
		List<Unit> enemyUnitList = new ArrayList<Unit>(player2.allAliveUnits());

		if (!playerTurn) {
			Point temp = null;

			for (Unit u : enemyUnitList) {
				// Goes through each member of the AI. Checks to see if there
				// are any enemies within range.
				temp = this.nearestPlayerUnit(new Point(u.getX(), u.getY()));
				this.setCurrentUnit(u.getY(), u.getX());
				this.endRow = temp.y;
				this.endCol = temp.x;

				if (this.inAttackRange(temp.x, temp.y)) {
					attack();
				}

				// If not, then move the AI closer to the player.
				else
					enemyMove(new Point(u.getX(), u.getY()));

				// TODO: empty curr list once all of the Ai has moved
				// map.getUnitAt(u.y, u.x).setCanMove(false);
				// tempUnitList.remove(map.getUnitAt(u.y, u.x));
				// map.getUnitAt(rowValue, colValue).setCanMove(false);
			}
		}
		if(!playerTurn){
			endTurn();
		}
	}

	/**
	 * TODO Write this Method for automatically moving the enemy AI. Moves them
	 * toward the closest human based on their behavior. If they are near enough
	 * to a player's unit, attack.
	 * 
	 * @return
	 */
	public synchronized void enemyMove(Point em) {
		/*
		 * TODO: Add these things 1) Nearest Player Method 2) List of Enemy Unit
		 * Locations 3) Player's XY values 4) Send these params to
		 * AIPathfinder.traverse(): AiROW, AI COLUMN, PlayerPointLIst
		 */
		Point p = nearestPlayerUnit(em);

		rowValue = aiMove.traverse(em.y, em.x, p.y, p.x, currUnit.getMovement()).x;
		colValue = aiMove.traverse(em.y, em.x, p.y, p.x, currUnit.getMovement()).y;

		endRow = rowValue;
		endCol = colValue;
		move();
		// map.moveUnit(em.y, em.x, rowValue, colValue);
		System.out.println("Location being sent: " + em.y + ", " + em.x + " | " + rowValue + ", " + colValue);
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

		for (Unit p : player1.allAliveUnits()) {
			tempSN = Math.abs(enemyLoc.x - p.getX()) + Math.abs(enemyLoc.y - p.getY());
			if (tempSN < spaceNear || spaceNear == 0) {
				spaceNear = tempSN;
				toReturn = new Point(p.getX(), p.getY());
			}
		}

		return toReturn;
	}

	/**
	 * TODO
	 */

	public void setCurrentPlayer(Player player) {
		currPlayer = player;
	}

	public void setPlayerTurn(boolean whosTurn) {
		playerTurn = whosTurn;

	}

	public void setHasAttacked(boolean hasAttacked) {
		// TODO Auto-generated method stub
		this.hasAttacked = hasAttacked;
	}

	// TODO: add unit .isSelected to change
	// add unit .cantMove

	public void setCurrentUnitSelected(boolean v) {
		if (currUnit != null) {
			currUnit.setIsSelected(v);
		}
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