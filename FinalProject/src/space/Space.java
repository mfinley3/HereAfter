package space;

public abstract class Space {

	private String spaceType;
	private Boolean walkable;
	private Boolean occupied;
	private Boolean visable;
	private boolean canMoveTo;
	private int moveHinderance;
	private int visablityModifier;
	
	
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
	 * @return the spaceType
	 */
	public String getSpaceType() {
		return spaceType;
	}

	/**
	 * @return the walkable
	 */
	public Boolean getWalkable() {
		return walkable;
	}

	/**
	 * @return the moveHinderance
	 */
	public int getMoveHinderance() {
		return moveHinderance;
	}

	/**
	 * @return the occupied
	 */
	public Boolean getOccupied() {
		return occupied;
	}

	/**
	 * @param occupied the occupied to set
	 */
	public void setOccupied(Boolean occupied) {
		this.occupied = occupied;
	}

	/**
	 * @return the visible
	 */
	public Boolean getVisable() {
		return visable;
	}

	/**
	 * @param visable the visible to set
	 */
	public void setVisable(Boolean visable) {
		this.visable = visable;
	}

	/**
	 * @return the visablityModifier
	 */
	public int getVisablityModifier() {
		return visablityModifier;
	}

	/**
	 * Sets if a current unit can move to this spot. Is called before
	 * and after a move occurs or a unit is selected/deselected.
	 */
	public void setCanMoveTo(){
		
		canMoveTo = !canMoveTo;
	}
	
	public boolean getCanMoveTo(){
		return canMoveTo;
	}
}
