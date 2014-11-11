package model;

import space.Space;
import space.WasteLandSpace;

public class Map {
	
	private Space[][] map;
	private Unit[][] unitsOnMap;
	
	public Map(double difficulty) {
		
			map = new Space[25][25];
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map.length; j++) {
					map[i][j] = new WasteLandSpace(); // Map of empty spaces
				}
			if(difficulty == .5){
				//make the easy map
			}
			if(difficulty == 1){
				//make the medium map
			}
			if(difficulty == 2){
				//make the hard map
			}				
				
			}		
	}
	
	public void addUnitToMap(){
		
		unitsOnMap = new Unit[25][25];
		
		
		
		
	}
	
}
