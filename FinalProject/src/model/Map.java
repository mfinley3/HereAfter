package model;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;
import java.util.Stack;

import space.BridgeSpace;
import space.CaptureCornerSpace;
import space.MountainSpace;
import space.PathSpace;
import space.Space;
import space.TowerSpace;
import space.WallSpace;
import space.WastelandSpace;
import space.WaterSpace;
import units.AlphaProtectorAI;
import units.CarrierAI;
import units.ZombieAI;
import units.SpitterAI;
import units.Unit;
import units.ZombieDogAI;

/**
 * The Map class, creates a new map. Also handles moving units, adding/removing
 * units on the map and checking to see if a space is occupied.
 */
public class Map extends Observable {

	private Space[][] map;
	private Unit[][] unitsOnMap;
	private Scanner mapScan;
	private List<Unit> enemyList;
	private List<Point> goodUnitPositions;
	private List<Point> enemyUnitPositions;
	private Scanner unitScan;
	private Boolean isPlayerTurn;

	/**
	 * Instantiates a new map by reading in a text file that is determined by a
	 * variable sent in.
	 *
	 * @param difficulty
	 *            Takes in a double representing the difficulty. Is used to pick
	 *            what map to make.
	 */
	public Map(double difficulty, String gameType) {

		map = new Space[50][50];
		unitsOnMap = new Unit[50][50];
		System.out.print(gameType);

		if (gameType.equalsIgnoreCase("Tower")) {
			File easyMap = new File("Tower.txt");
			try {
				mapScan = new Scanner(easyMap);
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}

		}
		if (gameType.equalsIgnoreCase("Corner")) {
			File mediumMap = new File("Corner.txt");
			try {
				mapScan = new Scanner(mediumMap);
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}

		}
		if (gameType.equalsIgnoreCase("Survive")) {
			File hardMap = new File("Hard Map.txt");
			try {
				mapScan = new Scanner(hardMap);
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}

		}

		while (mapScan.hasNext()) {
			for (int m = 0; m < map.length; m++) {
				for (int n = 0; n < map.length; n++) {

					String mapLetterEquivalence = mapScan.next();

					if (mapLetterEquivalence.equals("W"))
						map[m][n] = new WastelandSpace();

					if (mapLetterEquivalence.equals("P"))
						map[m][n] = new PathSpace();

					if (mapLetterEquivalence.equals("R"))
						map[m][n] = new WaterSpace();

					if (mapLetterEquivalence.equals("U"))
						map[m][n] = new WallSpace();

					if (mapLetterEquivalence.equals("B"))
						map[m][n] = new BridgeSpace();

					if (mapLetterEquivalence.equals("M"))
						map[m][n] = new MountainSpace();

					if (mapLetterEquivalence.equals("T"))
						map[m][n] = new TowerSpace();

					if (mapLetterEquivalence.equals("C"))
						map[m][n] = new CaptureCornerSpace();

				}
			}
		}

		addEnemies(difficulty, gameType);
		setChanged();
		notifyObservers();

	}

	/**
	 * Adds the enemies to the map by reading locations from a text file
	 * determined by the game type.
	 *
	 * @param difficulty
	 *            The difficulty level to set the enemies to.
	 */
	private void addEnemies(double difficulty, String gameType) {

		setIsPlayerTurn();
		enemyList = new ArrayList<Unit>();
		enemyUnitPositions = new ArrayList<Point>();

		if (gameType.equalsIgnoreCase("Tower")) {
			File TowerEnemies = new File("TowerEnemies.txt");
			try {
				unitScan = new Scanner(TowerEnemies);
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}

		}
		if (gameType.equalsIgnoreCase("Corner")) {
			File mediumMap = new File("CornerEnemies.txt");
			try {
				unitScan = new Scanner(mediumMap);
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}

		}
		if (gameType.equalsIgnoreCase("Survive")) {
			File hardMap = new File("Hard Map.txt");
			try {
				unitScan = new Scanner(hardMap);
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}

		}
		while (unitScan.hasNext()) {
			for (int m = 0; m < map.length; m++) {
				for (int n = 0; n < map.length; n++) {

					String unitLetterEquivalence = unitScan.next();

					if (unitLetterEquivalence.equals("Z")) {
						unitsOnMap[m][n] = new ZombieAI(difficulty);
						map[m][n].setOccupied(true);
						enemyList.add(unitsOnMap[m][n]);
						enemyUnitPositions.add(new Point(m, n));
					}

					if (unitLetterEquivalence.equals("A")) {
						unitsOnMap[m][n] = new AlphaProtectorAI(difficulty);
						map[m][n].setOccupied(true);
						enemyList.add(unitsOnMap[m][n]);
						enemyUnitPositions.add(new Point(m, n));
					}

					if (unitLetterEquivalence.equals("C")) {
						unitsOnMap[m][n] = new CarrierAI(difficulty);
						map[m][n].setOccupied(true);
						enemyList.add(unitsOnMap[m][n]);
						enemyUnitPositions.add(new Point(m, n));
					}

					if (unitLetterEquivalence.equals("D")) {
						unitsOnMap[m][n] = new ZombieDogAI(difficulty);
						map[m][n].setOccupied(true);
						enemyList.add(unitsOnMap[m][n]);
						enemyUnitPositions.add(new Point(m, n));
					}

					if (unitLetterEquivalence.equals("S")) {
						unitsOnMap[m][n] = new SpitterAI(difficulty);
						map[m][n].setOccupied(true);
						enemyList.add(unitsOnMap[m][n]);
						enemyUnitPositions.add(new Point(m, n));
					}
				}
			}
		}
	}

	/**
	 * Adds the players picked units to map.
	 *
	 * @param unitList
	 *            The list of units to be added to the map.
	 */
	public void addUnitsToMap(Stack<Unit> unitList) {

		goodUnitPositions = new ArrayList<Point>();

		int k = 0;
		int r = 2;

		while (!unitList.isEmpty()) {
			unitsOnMap[k][r] = unitList.pop();
			map[k][r].setOccupied(true);
			goodUnitPositions.add(new Point(k, r));
			if (r != 0) {
				r--;
			} else {
				k++;
			}

		}
		setChanged();
		notifyObservers();

	}

	/**
	 * When called it moves the selected unit to the selected spot.
	 *
	 * @param startRow
	 *            the start row used to pick the unit to move.
	 * @param startCol
	 *            the start col used to pick the unit to move.
	 * @param moveToRow
	 *            the move to row used to pick the space to move the unit to.
	 * @param moveToCol
	 *            the move to col used to pick the space to move the unit to.
	 */
	public void moveUnit(int startRow, int startCol, int moveToRow, int moveToCol) {
		
		if (startCol != moveToCol || startRow != moveToRow) {
			unitsOnMap[moveToCol][moveToRow] = unitsOnMap[startCol][startRow];
			unitsOnMap[startCol][startRow] = null;
			map[startCol][startRow].setOccupied(false);
			map[moveToCol][moveToRow].setOccupied(true);

			if (isPlayerTurn) {
				for (Point p : goodUnitPositions) {
					if (p.getX() == startCol && p.getY() == startRow) {
						goodUnitPositions.remove(p);
						goodUnitPositions.add(new Point(moveToCol, moveToRow));
						break;
					}
				}
				
			} else {

				for (Point p : enemyUnitPositions) {
					if (p.getX() == startCol && p.getY() == startRow) {
						enemyUnitPositions.remove(p);
						enemyUnitPositions.add(new Point(moveToCol, moveToRow));
						break;
					}
				}
			}
		}

		setChanged();
		notifyObservers();

	}

	/**
	 * @return the goodUnitPositions
	 */
	public List<Point> getGoodUnitPositions() {
		return goodUnitPositions;
	}
	
	/**
	 * @return the enemyUnitPositions
	 */
	public List<Point> getEnemyUnitPositions() {
		return enemyUnitPositions;
	}

	/**
	 * Gets the unit at the requested location.
	 *
	 * @param row
	 *            The row of the requested unit.
	 * @param col
	 *            The column of the requested unit.
	 * @return Returns the 'full' Unit at requested spot.
	 */
	public Unit getUnitAt(int row, int col) {
		return unitsOnMap[col][row];
	}

	/**
	 * Method to get the array of spaces in other words the actual map.
	 *
	 * @return map Map is the Space[][].
	 */
	public Space[][] getSpaces() {

		return map;
	}

	/**
	 * Method to get the array of Units in other words the Units on the map.
	 *
	 * @return unitsOnMap unitsOnMap is the Unit[][]
	 */
	public Unit[][] getUnits() {

		return unitsOnMap;
	}

	/**
	 * Checks if the requested space is occupied.
	 *
	 * @param row
	 *            The row of the requested space.
	 * @param column
	 *            the column of the requested space.
	 * @return True If the space is occupied.
	 */
	public boolean isOccupied(int row, int column) {
		if (map[column][row].getOccupied() == true) {
			return true;
		}
		return false;
	}

	/**
	 * Gets the space in the map at the requested spot.
	 *
	 * @param row
	 *            The row of the requested space.
	 * @param column
	 *            The column of the requested space.
	 * @return The space at the requested spot.
	 */
	public Space getSpace(int row, int column) {
		return map[row][column];
	}

	/**
	 * Gets the enemy units.
	 *
	 * @return The list of enemy units
	 */
	public List<Unit> getEnemyUnits() {
		return enemyList;
	}

	/**
	 * Resets all the spaces in map so they can't be moved to.
	 */
	public void resetMapCanMove() {
		for (int i = 0; i < map.length; i++)
			for (int j = 0; j < map[i].length; j++)
				map[i][j].setCanMoveTo(false);

	}

	/**
	 * Removes the unit from the map.
	 *
	 * @param row
	 *            the row of the unit that needs to be removed.
	 * @param col
	 *            the column of the unit that needs to be removed.
	 */
	public void removeUnit(int row, int col) {

		unitsOnMap[row][col] = null;
		map[row][col].setOccupied(false);
		

		if (!isPlayerTurn) {
			for (Point p : goodUnitPositions) {
				if (p.getX() == col && p.getY() == row) {
					goodUnitPositions.remove(p);
					break;
				}
			}
			
		} else {

			for (Point p : enemyUnitPositions) {
				if (p.getX() == col && p.getY() == row) {
					enemyUnitPositions.remove(p);
					break;
				}
			}
		}
		
		
		setChanged();
		notifyObservers();

	}

	/**
	 * @param isPlayerTurn
	 *            the isPlayerTurn to set
	 */
	public void setIsPlayerTurn() {
		isPlayerTurn = !isPlayerTurn;
	}

	/**
	 * A method that can be called from any class to get the the GUI to update
	 * the map.
	 */
	public void updateObservers() {
		setChanged();
		notifyObservers();
	}

}
