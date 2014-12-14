package gametype;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Interface GameTypeInterface, used in the controller to decide
 * the wind conditions.
 */
public interface GameTypeInterface extends Serializable{
	
	/**
	 * Check win conditions.
	 *
	 * @param ob the ob
	 * @return true, if successful
	 */
	public boolean CheckWinCondition(Object ob);
	
	/**
	 * Gets the string containing the goal of the game goal.
	 * Not used.
	 *
	 * @return the goal
	 */
	public String getGoal();
}
