package gametype;

import java.util.ArrayList;

import space.Space;

/**
 * 
 * @author Brian Seaman
 *
 */
public class FourCorners implements GameTypeInterface {

	@SuppressWarnings("unchecked")
	@Override
	public boolean CheckWinCondition(Object ob) {
		// TODO Auto-generated method stub
		if(((ArrayList<Space>) ob).isEmpty())
			return true;
		else
			return false;
	}

	@Override
	public String getGoal() {
		// TODO Auto-generated method stub
		return "Travel to all four corners of the map and land on the corner spaces. \n"
				+ "The enemies respawn, and the maps are large. \n"
				+ "Good luck.";
	}

}
