package model;

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
import units.ZombieAI;
import units.SpitterAI;
import units.Unit;
import units.ZombieDogAI;

/**
 * The Map class, creates a new map.
 * Also handles moving units, adding/removing units on the map
 * and checking to see if a space is occupied.
 */
public class Map extends Observable {

	private Space[][] map;
	private Unit[][] unitsOnMap;
	private Scanner scan;
	private List<Unit> enemyList;

	/**
	 * Instantiates a new map.
	 *
	 * @param difficulty Takes in a double representing the difficulty. Is used to pick what map to make.
	 */
	public Map(double difficulty, String gameType) {

		map = new Space[50][50];
		unitsOnMap= new Unit[50][50];

		if (gameType.equalsIgnoreCase("Tower")) {
			File easyMap = new File("Tower.txt");
			try {
				scan = new Scanner(easyMap);
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}

		}
		if (gameType.equalsIgnoreCase("Corner")) {
			File mediumMap = new File("Medium Map.txt");
			try {
				scan = new Scanner(mediumMap);
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}

		}
		if (gameType.equalsIgnoreCase("Survive")) {
			File hardMap = new File("Hard Map.txt");
			try {
				scan = new Scanner(hardMap);
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}

		}

		while (scan.hasNext()){
			for (int m = 0; m < map.length; m++) {
				for (int n = 0; n < map.length; n++) {

					String mapLetterEquivalence = scan.next();

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
		
		addEnemies(difficulty);
		setChanged();
		notifyObservers();
		
	}

	//IMPORTANT DO NOT TOUCH - this method is only temporary and is only to only be used for the first iteration.
	//I will be adding the enemies to the map via a file reader for the second iteration. Meaning this method will be deleted
	//along with anything inside of it. So I would highly recommend not adding functionality to it because I will just be deleting it.
	/**
	 * Adds the enemies to the map.
	 *
	 * @param difficulty The difficulty level to set the enemies to.
	 */
	private void addEnemies(double difficulty) {
	
		enemyList = new ArrayList<Unit>();
		//IMPORTANT READ ABOVE
		unitsOnMap[40][38] = new AlphaProtectorAI(difficulty);
		map[40][38].setOccupied(true);
		enemyList.add(unitsOnMap[40][38]);
		//IMPORTANT READ ABOVE
		unitsOnMap[40][39] = new ZombieAI(difficulty);
		map[40][39].setOccupied(true);
		enemyList.add(unitsOnMap[40][39]);
		//IMPORTANT READ ABOVE
		unitsOnMap[40][40] = new ZombieAI(difficulty);
		map[40][40].setOccupied(true);
		enemyList.add(unitsOnMap[40][40]);
		//IMPORTANT READ ABOVE
		unitsOnMap[40][41] = new SpitterAI(difficulty);
		map[40][41].setOccupied(true);
		enemyList.add(unitsOnMap[40][41]);
		//IMPORTANT READ ABOVE
		unitsOnMap[41][38] = new ZombieDogAI(difficulty);
		map[41][38].setOccupied(true);
		enemyList.add(unitsOnMap[41][38]);
		//IMPORTANT READ ABOVE
		unitsOnMap[42][38] = new ZombieDogAI(difficulty);
		map[42][38].setOccupied(true);
		enemyList.add(unitsOnMap[42][38]);
		//IMPORTANT READ ABOVE
		unitsOnMap[43][38] = new SpitterAI(difficulty);
		map[43][38].setOccupied(true);
		enemyList.add(unitsOnMap[43][38]);

	}

	/**
	 * Adds the players picked units to map.
	 *
	 * @param unitList The list of units to be added to the map.
	 */
	public void addUnitsToMap(Stack<Unit> unitList) {

		int k = 0;
		int r = 2;

		while (!unitList.isEmpty()) {
			unitsOnMap[k][r] = unitList.pop();
			map[k][r].setOccupied(true);
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
	 * @param startRow the start row used to pick the unit to move.
	 * @param startCol the start col used to pick the unit to move.
	 * @param moveToRow the move to row used to pick the space to move the unit to.
	 * @param moveToCol the move to col used to pick the space to move the unit to.
	 */
	public void moveUnit(int startRow, int startCol, int moveToRow, int moveToCol) {
		if(startCol != moveToCol || startRow != moveToRow){
		    unitsOnMap[moveToCol][moveToRow] = unitsOnMap[startCol][startRow];
			unitsOnMap[startCol][startRow] = null;
			map[startCol][startRow].setOccupied(false);
			map[moveToCol][moveToRow].setOccupied(true);
		}
		
		setChanged();
		notifyObservers();

	}

	/**
	 * Gets the unit at the requested location.
	 *
	 * @param row The row of the requested unit.
	 * @param col The column of the requested unit.
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
	 * @param row The row of the requested space.
	 * @param column the column of the requested space.
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
	 * @param row The row of the requested space. 
	 * @param column The column of the requested space.
	 * @return The space at the requested spot.
	 */
	public Space getSpace(int row, int column){
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
	public void resetMapCanMove(){
		for(int i =0; i < map.length; i++)
			for(int j =0; j < map[i].length; j++)
				map[i][j].setCanMoveTo(false);
			
	}

	/**
	 * Removes the unit from the map.
	 *
	 * @param row the row of the unit that needs to be removed.
	 * @param col the column of the unit that needs to be removed.
	 */
	public void removeUnit(int row, int col) {
	
		unitsOnMap[row][col] = null;
		map[row][col].setOccupied(false);
		setChanged();
		notifyObservers();
		
	}
	
	/**
	 * A method that can be called from any class to get the the GUI to update the map.
	 */
	public void updateObservers(){
		setChanged();
		notifyObservers();
	}

}
