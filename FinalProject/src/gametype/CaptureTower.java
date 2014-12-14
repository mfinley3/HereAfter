package gametype;

// TODO: Auto-generated Javadoc
/**
 * Game type where the goal is to capture the tower. Get all the living
 * team members to the tower to win.
 * 
 *
 */
public class CaptureTower implements GameTypeInterface{

	private boolean temp;
	
	/**
	 * Instantiates a new capture tower game type.
	 */
	public CaptureTower(){
		temp = false;
	}
	
	/* (non-Javadoc)
	 * @see gametype.GameTypeInterface#CheckWinCondition(java.lang.Object)
	 */
	@Override
	public boolean CheckWinCondition(Object ob) {
		// The calculations for this one are done in the game.
		
		return (boolean)ob;
	}

	/* (non-Javadoc)
	 * @see gametype.GameTypeInterface#getGoal()
	 */
	@Override
	public String getGoal() {
		// TODO Auto-generated method stub
		return "There is a tower far away on the other end of the map. \n"
				+ "Get all of your units to it and surround it to take it. \n"
				+ "Only one unit has to still be alive to finish the mission, \n"
				+ "but it would be better if the whole team survives."
				+ "Good luck.";
	}
	
}
