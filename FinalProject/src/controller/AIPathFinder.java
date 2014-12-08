package controller;

import java.awt.Point;
import java.io.Serializable;

import model.Map;

/**
 * AIPathFinder attempts to recursively traverse the game Map. The goal is to
 * get from the given starting position to the nearest player location.
 * 
 * @author Chioke
 */
public class AIPathFinder implements Serializable{
	private Map gameMap;
	private int moveRange;

	/**
	 * Constructor for the AIPathFinder class.
	 */
	public AIPathFinder(Map gameMap) {
		this.gameMap = gameMap;
	}

	/**
	 * Attempts to recursively traverse the game map from the first given X & Y
	 * locations to the next pair
	 * 
	 * @param currRow
	 *            row index of current location
	 * @param currCol
	 *            column index of current location
	 * @return true if the current location is 'near' the other
	 */
	public Point traverse(int plyrRow, int plyrCol, int currRow, int currCol, int aiMovement) {
		boolean isNearPlayer = false;
		moveRange = aiMovement;
		
		System.out.println("AI: " + currRow + ", " + currCol + ": " + aiMovement);
		System.out.println("\tTarget: " + plyrRow + ", " + plyrCol);

		if ((currRow == plyrRow || currRow == plyrRow - 1 || currRow == plyrRow + 1) && (currCol == plyrCol || currCol == plyrCol - 1 || currCol == plyrCol + 1))
			isNearPlayer = true; // the AI is near the target location

		else {
			// moves up
			if (validPosition(currRow - 1, currCol)) {
				traverse(currRow - 1, currCol, plyrRow, plyrCol, moveRange);
			}

			// moves left
			if (!isNearPlayer && validPosition(currRow, currCol - 1)) {
				traverse(currRow, currCol - 1, plyrRow, plyrCol, moveRange);
			}

			// moves down
			if (!isNearPlayer && validPosition(currRow + 1, currCol)) {
				traverse(currRow + 1, currCol, plyrRow, plyrCol, moveRange);
			}

			// moves right
			if (!isNearPlayer && validPosition(currRow, currCol + 1)) {
				traverse(currRow, currCol + 1, plyrRow, plyrCol, moveRange);
			}
		}
		return new Point(currRow, currCol); // Returns the Point of where the AI
											// should move.
	}

	/**
	 * Determines if a specific location is valid. A valid location is one that
	 * is on the grid, is not blocked, and has not been TRIED.
	 * 
	 * @param tgtRow
	 *            the target row to be checked
	 * @param tgtCol
	 *            the target column to be checked
	 * @return true if the location is valid
	 */
	public boolean validPosition(int tgtRow, int tgtCol) {
		boolean valid = false;

		// Check if locations are in the bounds of the map
		if (tgtRow > 49 || tgtRow < 0 || tgtCol > 49 || tgtCol < 0) {
			valid = false;
		}

		else {
			int moveHindrance = gameMap.getSpace(tgtRow, tgtCol).getMoveHinderance();

			// Check if walkable
			if (gameMap.getSpace(tgtRow, tgtCol).getWalkable()) {

				// Check for hindrance
				if (moveRange - moveHindrance > 0) {
					moveRange = moveRange - moveHindrance;
					valid = true;
				}
			}
		}
		return valid;
	}

	/**
	 * Marks the specified position in the maze as TRIED
	 * 
	 * @param row
	 *            the index of the row to try
	 * @param col
	 *            the index of the column to try
	 */
	public void tryPosition(int row, int col) {
		// gameMap. = TRIED;

		/*
		 * This method was used to initially relabel the map. When I first wrote
		 * the code, it worked by labels, if something was not a Wasteland space
		 * or something of that sort, the recursive method would know to just
		 * keep going on to fully "try" each position for a possible solution.
		 * With me being able to actually relabel parts of our current map, I'm
		 * afraid the recursive algorithm won't work in the desired way.
		 */

		/*
		 * set up a method in space: isTried Usually set to false, but this
		 * would set it to true so it knows what has been 'tried' or not to keep
		 * going.
		 */
	}

} // end of class