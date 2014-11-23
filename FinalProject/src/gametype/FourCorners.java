package gametype;

import java.util.ArrayList;

import space.Space;

/**
 * The Class FourCorners.
 *
 */
public class FourCorners implements GameTypeInterface {

	/* (non-Javadoc)
	 * @see gametype.GameTypeInterface#CheckWinCondition(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean CheckWinCondition(Object ob) {
		// TODO Auto-generated method stub
		if(((ArrayList<Space>) ob).isEmpty())
			return true;
		else
			return false;
	}

	/* (non-Javadoc)
	 * @see gametype.GameTypeInterface#getGoal()
	 */
	@Override
	public String getGoal() {
		// TODO Auto-generated method stub
		return "Travel to all four corners of the map and land on the corner spaces. \n"
				+ "The enemies respawn, and the maps are large. \n"
				+ "Good luck.";
	}

}
