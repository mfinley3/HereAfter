package units;

import model.Map;

/**
 * MazeSolver attempts to recursively traverse a Maze. The goal is to get from the
 * given starting position to the bottom right, following a path of 1’s. Arbitrary
 * constants are used to represent locations in the maze that have been TRIED
 * and that are part of the solution PATH.
 *  @author Chioke
 */
public class AIPathFinder
{
   private Map gameMap;
   
   /**
    * Constructor for the AIPathFinder class.
    */
   public AIPathFinder(Map gameMap) {
      this.gameMap = gameMap;
   }
   
   /**
    * Attempts to recursively traverse the map. Inserts special
    * characters indicating locations that have been TRIED and that
    * eventually become part of the solution PATH.
    *  @param aiRow		row index of current location
    *  @param aiCol	column index of current location
    *  @return true		if the AI is near the player
    */
   public boolean traverse(int aiRow, int aiCol, int plyrRow, int plyrCol)
   {
      boolean isNearPlayer = false;
      
      if (validPosition(aiRow, aiCol, plyrRow, plyrCol))
      {
			gameMap.tryPosition(aiRow, aiCol); // mark this cell as tried for
												// the algorithm

			/*
			 * AI location move to nearest player location. Keep count of AI
			 * movement length/attack range
			 */
			if ((aiRow == plyrRow || aiRow == plyrRow - 1 || aiRow == plyrRow + 1)
					&& (aiCol == plyrCol || aiCol == plyrCol - 1 || aiCol == plyrCol + 1))
				isNearPlayer = true; // the AI is near the target
			
			else {
				isNearPlayer = traverse(aiRow + 1, aiCol, plyrRow, plyrCol); // moves down

				if (!isNearPlayer)
					isNearPlayer = traverse(aiRow, aiCol + 1, plyrRow, plyrCol); // moves right

				if (!isNearPlayer)
					isNearPlayer = traverse(aiRow - 1, aiCol, plyrRow, plyrCol); // moves up

				if (!isNearPlayer)
					isNearPlayer = traverse(aiRow, aiCol - 1, plyrRow, plyrCol); // moves left
			}
         
         if (isNearPlayer) { // if AI can make it near or nearer to the player, move it
            //currAI.move(aiRow, aiCol);
         }
         
      }
      return isNearPlayer; // Returns the boolean of if the AI moved or not.
   }
   
   /**
	 * Determines if a specific location is valid. A valid location is one that
	 * is on the grid, is not blocked, and has not been TRIED.
	 * 
	 * @param row
	 *            the row to be checked
	 * @param column
	 *            the column to be checked
	 * @return true if the location is valid
	 */
	public boolean validPosition(int aiRow, int aiCol, int plyrRow, int plyrCol) {
		boolean result = false;

		// check if locations are in the bounds of the matrix
		if ((aiRow >= 0) && (aiRow < 50) &&
				(aiCol >= 0) && (aiCol < 50) &&
				(plyrRow >= 0) && (plyrRow < 50) &&
				(plyrCol >= 0) && (plyrCol < 50)) {

			/*
			 * TODO:
			 * 	1. Check if occupied
			 * 	2. Not a wall
			 * 	3. Check if in boundaries of AI movement?
			 */

			/*if (gameMap[row][column].equals("W") || grid[row][column].equals("P") || grid[row][column].equals("B")) {
				result = true;
			}*/
		}

		return result;
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
		grid[row][col] = TRIED;
	}

} //end of class