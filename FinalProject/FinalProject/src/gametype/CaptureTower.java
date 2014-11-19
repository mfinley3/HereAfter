package gametype;

/**
 * Game type where the goal is to capture the tower. Get all the living
 * team members to the tower to win.
 * 
 * @author Brian
 *
 */
public class CaptureTower implements GameTypeInterface{

	@Override
	public boolean CheckWinCondition(Object ob) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getGoal() {
		// TODO Auto-generated method stub
		return "There is a tower far away on the other end of the map. \n"
				+ "Get all of your units to it and surround it to take it. \n"
				+ "Only one unit has to still be alive to finish the mission, \n"
				+ "but it would be better if the whole unit survives."
				+ "Good luck.";
	}
	
}
