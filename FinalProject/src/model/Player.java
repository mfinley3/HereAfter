package model;

import java.util.LinkedList;
import java.util.List;

public class Player {
	public int missionsDone;
	public int livingTeam;
	private List<Unit> livingUnits;
	private List<Unit> deadUnits;
	
	public Player(){
		livingUnits = new LinkedList<Unit>;
	}

	public Object getTeamStats() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Unit> allAliveUnits() {
		// TODO Auto-generated method stub
		return livingUnits;
	}
}
