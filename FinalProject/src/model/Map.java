package model;

import java.util.Observable;
import java.util.Stack;

import space.Space;
import space.WasteLandSpace;
import units.Unit;

public class Map extends Observable{
	
	private Space[][] map;
	private Unit[][] unitsOnMap;
	
	public Map(double difficulty) {
		
			map = new Space[50][50];
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map.length; j++) {
					map[i][j] = new WasteLandSpace(); // Map of empty spaces
				}
				
			if(difficulty == .5){
				//make the easy map
				setChanged();
				notifyObservers();
			}
			if(difficulty == 1){
				//make the medium map
				setChanged();
				notifyObservers();
			}
			if(difficulty == 2){
				//make the hard map
				setChanged();
				notifyObservers();
			}				
				
			}		
	}
	
	public void addUnitsToMap(Stack<Unit> unitList){
		
		unitsOnMap = new Unit[50][50];
					
		int k = 0;
		int r = 2;
		
		while(!unitList.isEmpty()){
			unitsOnMap[k][r] = unitList.pop();
			map[k][r].setOccupied(true);
			if(r != 0){
				r--;
			}else{
				k++;
			}
				
		}
		setChanged();
		notifyObservers();
		
	}

	public void moveUnit(int startRow, int startCol, int moveToRow, int moveToCol){
		
		unitsOnMap[moveToRow][moveToCol] = unitsOnMap[startRow][startCol];
		unitsOnMap[startRow][startCol] = null;
		unitsOnMap[moveToRow][moveToCol].setCanMove();
		map[startRow][startCol].setOccupied(false);
		map[moveToRow][moveToCol].setOccupied(true);
		setChanged();
		notifyObservers();
		
		
	}
	
	public Unit getUnitAt(int row, int col){
		return unitsOnMap[row][col];
	}

	public Space[][] getSpaces() {
		// TODO Auto-generated method stub
		return map;
	}

	public Unit[][] getUnits() {
		// TODO Auto-generated method stub
		return unitsOnMap;
	}

	public boolean isOccupied(int row, int column) {
		//MAKE THIS LATER TONIGHT!
		return false;
	}
	
}
