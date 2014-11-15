package model;

import java.io.File;
import java.io.FileNotFoundException;
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
import units.Unit;

public class Map extends Observable {

	private Space[][] map;
	private Unit[][] unitsOnMap;
	private Scanner scan;

	public Map(double difficulty) {

		map = new Space[50][50];

		if (difficulty == .5) {
			File easyMap = new File("Easy Map.txt");
			try {
				scan = new Scanner(easyMap);
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}

		}
		if (difficulty == 1) {
			File mediumMap = new File("Medium Map.txt");
			try {
				scan = new Scanner(mediumMap);
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}

		}
		if (difficulty == 2) {
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
		
		setChanged();
		notifyObservers();
		
	}

	public void addUnitsToMap(Stack<Unit> unitList) {

		unitsOnMap = new Unit[50][50];

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

	public void moveUnit(int startRow, int startCol, int moveToRow,
			int moveToCol) {

		unitsOnMap[moveToRow][moveToCol] = unitsOnMap[startRow][startCol];
		unitsOnMap[startRow][startCol] = null;
		unitsOnMap[moveToRow][moveToCol].setCanMove();
		map[startRow][startCol].setOccupied(false);
		map[moveToRow][moveToCol].setOccupied(true);
		setChanged();
		notifyObservers();

	}

	public Unit getUnitAt(int row, int col) {
		return unitsOnMap[row][col];
	}

	public Space[][] getSpaces() {

		return map;
	}

	public Unit[][] getUnits() {

		return unitsOnMap;
	}

	public boolean isOccupied(int row, int column) {
		if (map[row][column].getOccupied() == true) {
			return true;
		}
		return false;
	}

}
