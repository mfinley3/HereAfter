package gametype;

/**
 * The Class Survive.
 */
public class Survive implements GameTypeInterface{

	private int turnsNeeded;
	
	/**
	 * Instantiates a new survive game. Sets the number of turns needed to win.
	 *
	 * @param turnsToSurvive the turns to survive
	 */
	public Survive(int turnsToSurvive){
		turnsNeeded = turnsToSurvive;
	}
	
	/* (non-Javadoc)
	 * @see gametype.GameTypeInterface#CheckWinCondition(java.lang.Object)
	 */
	@Override
	public boolean CheckWinCondition(Object ob) {
		// TODO Auto-generated method stub
		return (int)ob >= turnsNeeded;
	}

	/* (non-Javadoc)
	 * @see gametype.GameTypeInterface#getGoal()
	 */
	@Override
	public String getGoal() {
		// TODO Auto-generated method stub
		return "Monsters are closing in on you. \n"
				+ "Help will come, but you have to stay alive until it arrives."
				+ "Survive for " +turnsNeeded+ " turns against the respawning horde."
				+ "Best of luck";
	}

}
