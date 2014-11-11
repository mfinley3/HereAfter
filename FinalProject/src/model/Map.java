package model;


public class Map {
	
	private ItemSpace[][] map;
	
	
	
	public Map(double difficulty) {
		
			map = new ItemSpace[50][50];
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map.length; j++) {
					map[i][j] = new ItemSpace(); // Map of empty spaces
				}
			}
		
	}
	
	public void setDifficulty(){
		
		
	}
	
}
