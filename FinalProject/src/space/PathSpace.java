package space;

/**
 * The Class for a Path Space.
 */
public class PathSpace extends Space{

	/**
	 * Instantiates a new path space.
	 * SpaceType/IsTheSpaceWalkable/IsThe spaceOccupied/IsTheSpaceVisible/MoveHindranceOfTheSpace/VisiablityOfTheSpace
	 */
	public PathSpace() {
		super("Path", true, false, true, -1, 0);
		
	}

}
