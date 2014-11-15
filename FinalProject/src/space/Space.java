package space;

public abstract class Space {

	private Boolean walkable;
	private Boolean occupied;
	private Boolean visable;
	private int moveHinderance;
	
	public Space (Boolean walkable, Boolean occupied, Boolean visable, int moveHinderance){
		
		this.walkable = walkable;
		this.occupied = occupied;
		this.setVisable(visable);
		this.moveHinderance = moveHinderance;
		
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
	 * @return the visable
	 */
	public Boolean getVisable() {
		return visable;
	}

	/**
	 * @param visable the visable to set
	 */
	public void setVisable(Boolean visable) {
		this.visable = visable;
	}


}
