package controller;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

import model.Map;

/**
 * AIPathFinder attempts to recursively traverse the game Map. The goal is to
 * get from the given starting position to the nearest player location.
 * 
 */
public class AIPathFinder implements Serializable{
	
	private Map gameMap;
	private ArrayList<Point> mineLocations = new ArrayList<Point>();
	private ArrayList<Point> movePositions = new ArrayList<Point>();
	private int moveRange;
	private int row = 0, col = 0;

	/**
	 * Constructor for the AIPathFinder class.
	 *
	 * @param gameMap the game map
	 */
	public AIPathFinder(Map gameMap) {
		this.gameMap = gameMap;
	}

	/**
	 * Attempts to recursively traverse the game map from the first given X & Y
	 * locations to the next pair.
	 *
	 * @param currAIRow            row index of current location
	 * @param currAICol            column index of current location
	 * @param plyrRow the plyr row
	 * @param plyrCol the plyr col
	 * @param aiMovement the ai movement
	 * @return true if the current location is 'near' the other
	 */
	public Point traverse(int currAIRow, int currAICol, int plyrRow, int plyrCol, int aiMovement) {
		boolean isNearPlayer = false;
		moveRange = aiMovement;
		movePositions.add(new Point(currAIRow, currAICol));

		if ((currAIRow == plyrRow || currAIRow == plyrRow - 1 || currAIRow == plyrRow + 1) && (currAICol == plyrCol || currAICol == plyrCol - 1 || currAICol == plyrCol + 1))
			isNearPlayer = true; // the AI is near the target location
		
		if (moveRange == 0) {
			return new Point(currAIRow, currAICol);
		}
		
		// Stepped on a mine, ends movement
		if (gameMap.getSpace(currAICol, currAIRow).hasMine()) {
			mineLocations.add(new Point(currAIRow, currAICol));
			return new Point(currAIRow, currAICol);
		}

		else {
			// moves up
			if (!isNearPlayer && currAIRow > plyrRow) {
				if (validPosition(currAIRow - 1, currAICol)) {
					traverse(currAIRow - 1, currAICol, plyrRow, plyrCol, moveRange);
				}
			}

			// moves left
			if (!isNearPlayer && currAICol > plyrCol) {
				if (validPosition(currAIRow, currAICol - 1)) {
					traverse(currAIRow, currAICol - 1, plyrRow, plyrCol, moveRange);
				}
			}
				
			// moves down
			if (!isNearPlayer && currAIRow < plyrRow) {
				if (validPosition(currAIRow + 1, currAICol)) {
					traverse(currAIRow + 1, currAICol, plyrRow, plyrCol, moveRange);
				}
			}

			// moves right
			if (!isNearPlayer && currAICol < plyrCol) {
				if (validPosition(currAIRow, currAICol + 1)) {
					traverse(currAIRow, currAICol + 1, plyrRow, plyrCol, moveRange);
				}
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
			if (!gameMap.isOccupied(tgtRow, tgtCol)
					&& gameMap.getSpace(tgtRow, tgtCol).getCanMoveTo()) {
				if (moveRange > 0) {

					moveRange = moveRange - gameMap.getSpace(tgtRow, tgtCol).getMoveHinderance();

					valid = true;
					row = tgtRow;
					col = tgtCol;
				}
			}
		}

		return valid;
	}

	/**
	 * Gets the stepped on mines.
	 *
	 * @return the mineLocations
	 */
	public ArrayList<Point> getSteppedOnMines() {
		return mineLocations;
	}

	/**
	 * Clear stepped on mines.
	 */
	public void clearSteppedOnMines() {
		this.mineLocations = new ArrayList<Point>();
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