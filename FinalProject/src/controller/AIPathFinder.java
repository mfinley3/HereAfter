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
	private int row = 0, col = 0;

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
	 * @param currAIRow
	 *            row index of current location
	 * @param currAICol
	 *            column index of current location
	 * @return true if the current location is 'near' the other
	 */
	public Point traverse(int currAIRow, int currAICol, int plyrRow, int plyrCol, int aiMovement) {
		boolean isNearPlayer = false;
		moveRange = aiMovement;
		
		System.out.println("Current AI Location: " + currAIRow + ", " + currAICol + ": " + moveRange);
		//System.out.println("\t\tTarget Location: " + plyrRow + ", " + plyrCol);

		if ((currAIRow == plyrRow || currAIRow == plyrRow - 1 || currAIRow == plyrRow + 1) && (currAICol == plyrCol || currAICol == plyrCol - 1 || currAICol == plyrCol + 1))
			isNearPlayer = true; // the AI is near the target location
		
		if (moveRange == 0) {
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
		
		// Check if walkable
		// if (gameMap.getSpace(tgtRow, tgtCol).getWalkable() &&
		// !gameMap.getSpace(tgtRow, tgtCol).getOccupied() &&
		// !gameMap.getSpace(tgtRow, tgtCol).getSpaceType().equals("Wall")) {
		if (moveRange >= gameMap.getSpace(tgtRow, tgtCol).getMoveHinderance()
				&& gameMap.getSpace(tgtRow, tgtCol).getWalkable()) {

			moveRange = moveRange - gameMap.getSpace(tgtRow, tgtCol).getMoveHinderance();

			//System.out.println("\t\tHinderance: " + gameMap.getSpace(tgtRow, tgtCol).getMoveHinderance());
			gameMap.getSpace(tgtRow, tgtCol).setCanMoveTo(true);

			if (tgtRow < 49 && tgtRow > 0) {
				if (gameMap.getSpace(tgtRow + 1, tgtCol).getWalkable()
						&& gameMap.getSpace(tgtRow - 1, tgtCol).getWalkable()) {
					valid = true;
					row = tgtRow;
					col = tgtCol;
				}
			}

			if (tgtRow > 0 && tgtCol < 49) {
				if (gameMap.getSpace(tgtRow - 1, tgtCol).getWalkable()
						&& gameMap.getSpace(tgtRow, tgtCol + 1).getWalkable()) {
					valid = true;
					row = tgtRow;
					col = tgtCol;
				}
			}

			/*
			 * System.out.println(moveRange);
			 * valid = true;
			 * row = tgtRow;
			 * col = tgtCol;
			 */
		}
		
			/*// Check if locations are in the bounds of the map
		if (tgtRow > 50 || tgtRow < 0 || tgtCol > 50 || tgtCol < 0) {
			valid = false;
		}

		else {
			int moveHindrance = gameMap.getSpace(tgtRow, tgtCol).getMoveHinderance();

			// Check if walkable
			if (gameMap.getSpace(tgtRow, tgtCol).getWalkable() && !gameMap.getSpace(tgtRow, tgtCol).getOccupied() && !gameMap.getSpace(tgtRow, tgtCol).getSpaceType().equals("Wall")) {
				
				// Check for hindrance
				if (moveRange - moveHindrance >= 0) {
					moveRange = moveRange - moveHindrance;
					valid = true;
					row = tgtRow;
					col = tgtCol;
				}
			}
		} */
		return valid;
	}

} // end of class