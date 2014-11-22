package space;

// TODO: Auto-generated Javadoc
/**
 * The Class Space.
 */
public abstract class Space {

	/** The space type. */
	private String spaceType;
	
	/** The walkable. */
	private Boolean walkable;
	
	/** The occupied. */
	private Boolean occupied;
	
	/** The visable. */
	private Boolean visable;
	
	/** The can move to. */
	private boolean canMoveTo;
	
	/** The move hinderance. */
	private int moveHinderance;
	
	/** The visablity modifier. */
	private int visablityModifier;
	
	
	/**
	 * Instantiates a new space.
	 *
	 * @param spaceType the space type
	 * @param walkable the walkable
	 * @param occupied the occupied
	 * @param visable the visable
	 * @param moveHinderance the move hinderance
	 * @param visablityModifier the visablity modifier
	 */
	public Space (String spaceType, Boolean walkable, Boolean occupied, Boolean visable, int moveHinderance, int visablityModifier){
		
		this.spaceType = spaceType;
		this.walkable = walkable;
		this.occupied = occupied;
		this.visable = visable;
		this.moveHinderance = moveHinderance;
		this.visablityModifier = visablityModifier;
		this.canMoveTo = false;
	}
	
	/**
	 * Gets the space type.
	 *
	 * @return the spaceType
	 */
	public String getSpaceType() {
		return spaceType;
	}

	/**
	 * Gets the walkable.
	 *
	 * @return the walkable
	 */
	public Boolean getWalkable() {
		return walkable;
	}

	/**
	 * Gets the move hinderance.
	 *
	 * @return the moveHinderance
	 */
	public int getMoveHinderance() {
		return moveHinderance;
	}

	/**
	 * Gets the occupied.
	 *
	 * @return the occupied
	 */
	public Boolean getOccupied() {
		return occupied;
	}

	/**
	 * Sets the occupied.
	 *
	 * @param occupied the occupied to set
	 */
	public void setOccupied(Boolean occupied) {
		this.occupied = occupied;
	}

	/**
	 * Gets the visable.
	 *
	 * @return the visible
	 */
	public Boolean getVisable() {
		return visable;
	}

	/**
	 * Sets the visable.
	 *
	 * @param visable the visible to set
	 */
	public void setVisable(Boolean visable) {
		this.visable = visable;
	}

	/**
	 * Gets the visablity modifier.
	 *
	 * @return the visablityModifier
	 */
	public int getVisablityModifier() {
		return visablityModifier;
	}

	/**
	 * Sets if a current unit can move to this spot. Is called before
	 * and after a move occurs or a unit is selected/deselected.
	 *
	 * @param canMove the new can move to
	 */
	public void setCanMoveTo(boolean canMove){
		
		canMoveTo = canMove;
	}
	
	/**
	 * Gets the can move to.
	 *
	 * @return the can move to
	 */
	public boolean getCanMoveTo(){
		return canMoveTo;
	}
}
