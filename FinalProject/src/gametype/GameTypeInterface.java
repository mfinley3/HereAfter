package gametype;

/**
 * The Interface GameTypeInterface.
 */
public interface GameTypeInterface {
	
	/**
	 * Check win condition.
	 *
	 * @param ob the ob
	 * @return true, if successful
	 */
	public boolean CheckWinCondition(Object ob);
	
	/**
	 * Gets the goal.
	 *
	 * @return the goal
	 */
	public String getGoal();
}
