package space;

public abstract class Space {

	private Boolean walkable;
	private Boolean occupied;
	private int moveHinderance;
	
	public Space (Boolean walkable, Boolean occupied, int moveHinderance){
		
		this.walkable = walkable;
		this.setOccupied(occupied);
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


}
