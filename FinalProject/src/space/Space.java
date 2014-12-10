package space;

import java.io.Serializable;

/**
 * The abstract space class. Is used to easily create any amount of different types of
 * spaces. Holds all of a spaces information to allow easy addition of new spaces. 
 */
public abstract class Space implements Serializable{

	private String spaceType;
	private Boolean walkable;
	private Boolean occupied;
	private Boolean visable;
	private boolean canMoveTo;
	private int moveHinderance;
	private int visablityModifier;
	private boolean hasMine;
	
	/**
	 * Instantiates a new space.
	 *
	 * @param spaceType The space type
	 * @param walkable The param that gets if the space can be walked on.
	 * @param occupied If the space is occupied or not.
	 * @param visable Whether or not the spaces is visible to the player.
	 * @param moveHinderance The move hindrance of the space.
	 * @param visablityModifier The visibility modifier of the space.
	 */
	public Space (String spaceType, Boolean walkable, Boolean occupied, Boolean visable, int moveHinderance, int visablityModifier){
		this.hasMine = false;
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
	 * @return The space type.
	 */
	public String getSpaceType() {
		return spaceType;
	}

	/**
	 * Gets if the space can be walked on or not.
	 *
	 * @return if the space is walkable or not.
	 */
	public Boolean getWalkable() {
		return walkable;
	}

	/**
	 * Gets the move hindrance of the space.
	 *
	 * @return The move Hindrance of the space.
	 */
	public int getMoveHinderance() {
		return moveHinderance;
	}

	/**
	 * Gets if the space occupied or not.
	 *
	 * @return true, if the space is occupied.
	 */
	public Boolean getOccupied() {
		return occupied;
	}

	/**
	 * Sets the space to be occupied or not.
	 *
	 * @param Occupied is what the spaces occupied variable needs to be changed to.
	 */
	public void setOccupied(Boolean occupied) {
		this.occupied = occupied;
	}

	/**
	 * Gets whether or not the space is visible.
	 *
	 * @return if the space is visible or not.
	 */
	public Boolean getVisable() {
		return visable;
	}

	/**
	 * Sets the space to be visible or not.
	 *
	 * @param visable is what the spaces visible variable needs to be changed to.
	 */
	public void setVisable(Boolean visable) {
		this.visable = visable;
	}

	/**
	 * Gets the visibility modifier.
	 *
	 * @return the visibility Modifier of the space
	 */
	public int getVisablityModifier() {
		return visablityModifier;
	}

	/**
	 * Sets if a current unit can move to this spot. Is called before
	 * and after a move occurs or a unit is selected/deselected.
	 *
	 * @param canMove The new can move to.
	 */
	public void setCanMoveTo(boolean canMove){
		
		canMoveTo = canMove;
	}
	
	/**
	 * Gets the can move to.
	 *
	 * @return if the space can be moved to.
	 */
	public boolean getCanMoveTo(){
		return canMoveTo;
	}
	
	/**
	 * Checks to see if the space has a mine.
	 * @return if a mine is placed at that space.
	 */
	public boolean hasMine(){
		return hasMine;
	}
	
	/**
	 * Sets if it has a mine. Called when adding and removing a mine.
	 */
	public void setHasMine(boolean hasMine){
		this.hasMine = hasMine;
	}
}
