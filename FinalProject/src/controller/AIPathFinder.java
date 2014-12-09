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
	private int row, col;

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
	public Point traverse(int currRow, int currCol, int plyrRow, int plyrCol, int aiMovement) {
		boolean isNearPlayer = false;
		moveRange = aiMovement;
		
		//System.out.println("Current AI Location: " + currRow + ", " + currCol + ": " + moveRange);
		//System.out.println("\t\tTarget Location: " + plyrRow + ", " + plyrCol);

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

		return new Point(row, col); // Returns the Point of where the AI
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
					row = tgtRow;
					col = tgtCol;
				}
			}
		}
		return valid;
	}

} // end of class