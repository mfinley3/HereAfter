package gametype;

// TODO: Auto-generated Javadoc
/**
 * Defines the DeathMatch game type. Used to define the rules.
 * 
 * @author Brian
 *
 */
public class DeathMatch implements GameTypeInterface{

	/* (non-Javadoc)
	 * @see gametype.GameTypeInterface#CheckWinCondition(java.lang.Object)
	 */
	@Override
	public boolean CheckWinCondition(Object ob) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see gametype.GameTypeInterface#getGoal()
	 */
	@Override
	public String getGoal() {
		// TODO Auto-generated method stub
		return "The enemy numbers are set, and they don't respawn. \n"
				+ "Your goal is to find and kill them all. \n"
				+ "Put them out of their misery.";
	}

}
