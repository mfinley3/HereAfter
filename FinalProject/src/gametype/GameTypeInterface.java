package gametype;

import java.io.Serializable;

/**
 * The Interface GameTypeInterface.
 */
public interface GameTypeInterface extends Serializable{
	
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
