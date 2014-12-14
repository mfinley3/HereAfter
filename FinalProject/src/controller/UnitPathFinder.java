package controller;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

import model.Map;

// TODO: Auto-generated Javadoc
/**
 * AIPathFinder attempts to recursively traverse the game Map. The goal is to
 * get from the given starting position to the nearest player location.
 * 
 * @author Chioke
 */
public class UnitPathFinder implements Serializable{
	
	/** The game map. */
	private Map gameMap;
	
	/** The move positions. */
	private ArrayList<Point> movePositions = new ArrayList<Point>();
	
	/** The col. */
	private int row = 0, col = 0;

	/**
	 * Constructor for the AIPathFinder class.
	 *
	 * @param gameMap the game map
	 */
	public UnitPathFinder(Map gameMap) {
		this.gameMap = gameMap;
	}

	/**
	 * Attempts to recursively traverse the game map from the first given X & Y
	 * locations to the next pair.
	 *
	 * @param currRow            row index of current location
	 * @param currCol            column index of current location
	 * @param tgtRow the tgt row
	 * @param tgtCol the tgt col
	 * @return true if the current location is 'near' the other
	 */
	public Point traverse(int currRow, int currCol, int tgtRow, int tgtCol) {


		System.out.println("Current AI Location: " + currRow + ", " + currCol);
		System.out.println("\t\tTarget Location: " + tgtRow + ", " + tgtCol);

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
		movePositions.add(new Point(row, col));
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
		if (tgtRow > 49 || tgtRow < 0 || tgtCol > 49 || tgtCol < 0 ) {
			valid = false;
		}
		else {
						valid = true;
						row = tgtRow;
						col = tgtCol;
		}

		return valid;
	}

	/**
	 * Gets the move positions.
	 *
	 * @return the movePositions
	 */
	public ArrayList<Point> getMovePositions() {
		return movePositions;
	}

	/**
	 * Sets the move positions.
	 *
	 * @param movePositions the movePositions to set
	 */
	public void setMovePositions(ArrayList<Point> movePositions) {
		this.movePositions = movePositions;
	}

} // end of class