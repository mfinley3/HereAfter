package gametype;

/**
 * Defines the DeathMatch game type. Used to define the rules.
 * 
 * @author Brian
 *
 */
public class DeathMatch implements GameTypeInterface{

	@Override
	public boolean CheckWinCondition(Object ob) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getGoal() {
		// TODO Auto-generated method stub
		return "The enemy numbers are set, and they don't respawn. \n"
				+ "Your goal is to find and kill them all. \n"
				+ "Put them out of their misery.";
	}

}
