package space;

/**
 * The Class for a WallSpace.
 */
public class WallSpace extends Space{

	/**
	 * Instantiates a new wall space.
	 * SpaceType/IsTheSpaceWalkable/IsThe spaceOccupied/IsTheSpaceVisible/MoveHindranceOfTheSpace/VisiablityOfTheSpace
	 */
	public WallSpace(){
		super("Wall", true, false, true, 10000, -10000);
		
	}

}
