package controller;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

import model.Map;

/**
 * AIPathFinder attempts to recursively traverse the game Map. The goal is to
 * get from the given starting position to the nearest player location.
 * 
 * @author Chioke
 */
public class UnitPathFinder implements Serializable{
	private Map gameMap;
	private ArrayList<Point> movePositions = new ArrayList<Point>();
	private int row = 0, col = 0;

	/**
	 * Constructor for the AIPathFinder class.
	 */
	public UnitPathFinder(Map gameMap) {
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
	public Point traverse(int currRow, int currCol, int tgtRow, int tgtCol) {
		movePositions.add(new Point(currRow, currCol));

		// System.out.println("Current AI Location: " + currAIRow + ", " + currAICol + ": " + moveRange);
		// System.out.println("\t\tTarget Location: " + plyrRow + ", " + plyrCol);

		// moves up
		if (currRow > tgtRow) {
			if (validPosition(currRow - 1, currCol)) {
				traverse(currRow - 1, currCol, tgtRow, tgtCol);
			}
		}

		// moves left
		if (currCol > tgtCol) {
			if (validPosition(currRow, currCol - 1)) {
				traverse(currRow, currCol - 1, tgtRow, tgtCol);
			}
		}

		// moves down
		if (currRow < tgtRow) {
			if (validPosition(currRow + 1, currCol)) {
				traverse(currRow + 1, currCol, tgtRow, tgtCol);
			}
		}

		// moves right
		if (currCol < tgtCol) {
			if (validPosition(currRow, currCol + 1)) {
				traverse(currRow, currCol + 1, tgtRow, tgtCol);
			}
		}

		// Returns the Point of where the AI should move.
		return new Point(row, col);
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
		
		// Check for hindrance and if walkable
		if (tgtRow > 50 || tgtRow < 0 || tgtCol > 50 || tgtCol < 0 ) {
			valid = false;
		}
		else if (!(gameMap.getSpace(tgtCol, tgtRow).getSpaceType().equals("Wall"))) {
			// if (getCurrentUnit() != null) {
			if (!gameMap.isOccupied(tgtRow, tgtCol)
					&& gameMap.getSpace(tgtRow, tgtCol).getCanMoveTo()) {

					valid = true;
					row = tgtRow;
					col = tgtCol;
			}
		}

		return valid;
	}

	/**
	 * @return the movePositions
	 */
	public ArrayList<Point> getMovePositions() {
		return movePositions;
	}

	/**
	 * @param movePositions the movePositions to set
	 */
	public void setMovePositions(ArrayList<Point> movePositions) {
		this.movePositions = movePositions;
	}

} // end of class