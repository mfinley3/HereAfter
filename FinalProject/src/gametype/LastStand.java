package gametype;

import units.*;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class LastStand.
 */
public class LastStand implements GameTypeInterface {

	/* (non-Javadoc)
	 * @see gametype.GameTypeInterface#CheckWinCondition(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean CheckWinCondition(Object ob) {
		// TODO Auto-generated method stub
		if(((ArrayList<Unit>)ob).isEmpty()){
			return true;
		}
		
		return false;
	}

	/* (non-Javadoc)
	 * @see gametype.GameTypeInterface#getGoal()
	 */
	@Override
	public String getGoal() {
		// TODO Auto-generated method stub
		return "The monsters keep coming, and help isn't coming. \n"
				+ "Your goal is to last as long as you can, and take the enemy with you. \n"
				+ "Items will drop around the map to help you survive longer. \n"
				+ "Don't go gently into that good night.";
	}

}
