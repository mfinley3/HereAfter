package space;

// TODO: Auto-generated Javadoc
/**
 * The Class for a CaptureCorner Space.
 */
public class CaptureCornerSpace extends Space{

	/** The has been captured. */
	private Boolean hasBeenCaptured;
	/**
	 * Instantiates a new capture corner space.
	 * SpaceType/IsTheSpaceWalkable/IsThe spaceOccupied/IsTheSpaceVisible/MoveHindranceOfTheSpace/VisiablityOfTheSpace
	 */
	public CaptureCornerSpace() {
		super("CaptureCorner", true, false, true, 1, 2);
		hasBeenCaptured = false;
	}
	
	/**
	 * Gets the checks for been captured.
	 *
	 * @return the hasBeenCaptured
	 */
	public Boolean getHasBeenCaptured() {
		return hasBeenCaptured;
	}
	
	/**
	 * Sets the checks for been captured.
	 *
	 * @param hasBeenCaptured the hasBeenCaptured to set
	 */
	public void setHasBeenCaptured(Boolean hasBeenCaptured) {
		this.hasBeenCaptured = hasBeenCaptured;
	}
}