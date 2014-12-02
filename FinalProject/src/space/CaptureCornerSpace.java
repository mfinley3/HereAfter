package space;

/**
 * The Class for a CaptureCorner Space.
 */
public class CaptureCornerSpace extends Space{

	private Boolean hasBeenCaptured;
	/**
	 * Instantiates a new capture corner space.
	 * SpaceType/IsTheSpaceWalkable/IsThe spaceOccupied/IsTheSpaceVisible/MoveHindranceOfTheSpace/VisiablityOfTheSpace
	 */
	public CaptureCornerSpace() {
		super("CaptureCorner", true, false, true, 1, 2);
		
	}
	/**
	 * @return the hasBeenCaptured
	 */
	public Boolean getHasBeenCaptured() {
		return hasBeenCaptured;
	}
	/**
	 * @param hasBeenCaptured the hasBeenCaptured to set
	 */
	public void setHasBeenCaptured(Boolean hasBeenCaptured) {
		this.hasBeenCaptured = hasBeenCaptured;
	}

}
