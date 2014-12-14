package model;

// TODO: Auto-generated Javadoc
/**
 * Enum file for AI difficulty. Will be used when creating the enemy mobs and
 * putting them on the map, and while creating them map. Called by the GUI,
 * used in the controller.
 *
 */
public enum Difficulty {
	
	/** The easy. */
	EASY(1.0), 
 /** The medium. */
 MEDIUM(2.0), 
 /** The hard. */
 HARD(3.0);
	
	/** The d. */
	private double d;
	
	/**
	 * Sets the Double value associated with the Enum value.
	 *
	 * @param d the d
	 */
	Difficulty(Double d){
		this.d = d;
	}
	
	/**
	 * Gets the Double value associated with the Enum value.
	 *
	 * @return the value
	 */
	public double getValue(){
		return d;
	}
}
