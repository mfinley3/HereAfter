package controller;

import java.awt.Point;

import model.Map;

/**
 * AIPathFinder attempts to recursively traverse the game Map. The goal
 * is to get from the given starting position to the nearest player location.
 *  @author Chioke
 */
public class AIPathFinder
{
   private Map gameMap;
   private int aiRow;
   private int aiCol;
   private int aiMovement;
   
   /**
    * Constructor for the AIPathFinder class.
    */
   public AIPathFinder(Map gameMap) {
      this.gameMap = gameMap;
   }
   
   /**
    * Attempts to recursively traverse the game map from
    * the first given X & Y locations to the next pair
    *  @param currRow	row index of current location
    *  @param currCol	column index of current location
    *  @return true		if the current location is 'near' the other
    */
   public Point traverse(int currRow, int currCol, int plyrRow, int plyrCol)
   {
      boolean isNearPlayer = false;
      /*
       * The initial start location is where the AI is
       * until it is moved after finding the best path.
       */
      aiRow = currRow;
      aiCol = currCol;
      aiMovement = gameMap.getUnitAt(aiRow, aiCol).getMovement();
      
      if (validPosition(plyrRow, plyrCol))
      {
    	  // mark the spot as tried for the algorithm
    	  // gameMap.tryPosition(startRow, startCol);

			/*
			 * AI location move to nearest player location. Keep count of AI
			 * movement length/attack range
			 */
			if ((currRow == plyrRow || currRow == plyrRow - 1 || currRow == plyrRow + 1)
					&& (currCol == plyrCol || currCol == plyrCol - 1 || currCol == plyrCol + 1))
				isNearPlayer = true; // the AI is near the target
			
			else {
				traverse(currRow - 1, currCol, plyrRow, plyrCol); // moves up

				if (!isNearPlayer)
					traverse(currRow, currCol - 1, plyrRow, plyrCol); // moves left
				
				if (!isNearPlayer)
					traverse(currRow + 1, currCol, plyrRow, plyrCol); // moves down

				if (!isNearPlayer)
					traverse(currRow, currCol + 1, plyrRow, plyrCol); // moves right
			} 
      }
      
      return new Point(currRow, currCol); // Returns the Point of where the AI should move.
   }
   
   /**
	 * Determines if a specific location is valid. A valid location is one that
	 * is on the grid, is not blocked, and has not been TRIED.
	 * 
	 * @param tgtRow	the target row to be checked
	 * @param tgtCol	the target column to be checked
	 * @return true 	if the location is valid
	 */
	public boolean validPosition(int tgtRow, int tgtCol) {
		boolean result = false;
		int moveHindrance =  gameMap.getSpace(tgtRow, tgtCol).getMoveHinderance();
		
		// Check if locations are in the bounds of the map
		if (tgtRow > 49 || tgtRow < 0 || tgtCol > 49 || tgtCol < 0 ) {
			result = false;
		}
		
		// Check if walkable
		if (gameMap.getSpace(tgtRow, tgtCol).getWalkable()) {
			
			// Check for hindrance
			if (aiMovement - moveHindrance > 0) {
				aiMovement = aiMovement - moveHindrance;
				result = true;
			}
		}
		
		return result;
	}
	
	/**
	 * Marks the specified position in the maze as TRIED
	 * 
	 * @param row	the index of the row to try
	 * @param col	the index of the column to try
	 */
	public void tryPosition(int row, int col) {
		//gameMap. = TRIED;
		
		/*
		 * This method was used to initially relabel the map.
		 * When I first wrote the code, it worked by labels, if something was
		 * not a Wasteland space or something of that sort, the recursive method
		 * would know to just keep going on to fully "try" each position for a
		 * possible solution. With me being able to actually relabel parts of
		 * our current map, I'm afraid the recursive algorithm won't work in
		 * the desired way.
		 */
		
		/*
		 * set up a method in space: isTried
		 * Usually set to false, but this would set it to true
		 * so it knows what has been 'tried' or not to keep going.
		 */
	}

} //end of class







/**
 * Attempts to recursively traverse the map. Inserts special
 * characters indicating locations that have been TRIED and that
 * eventually become part of the solution PATH.
 *  @param startRow	row index of current location
 *  @param startCol	column index of current location
 *  @return true		if the AI is near the player
 *//*
public boolean traverse(int startRow, int startCol, int plyrRow, int plyrCol)
{
   boolean isNearPlayer = false;
   
    * The initial start location is where the AI is
    * until it is moved after finding the best path.
    
   aiRow = startRow;
   aiCol = startCol;
   aiMovement = gameMap.getUnitAt(aiRow, aiCol).getMovement();
   
   if (validPosition(plyrRow, plyrCol))
   {
 	  // mark the spot as tried for the algorithm
 	  // gameMap.tryPosition(startRow, startCol);

			
			 * AI location move to nearest player location. Keep count of AI
			 * movement length/attack range
			 
			if ((startRow == plyrRow || startRow == plyrRow - 1 || startRow == plyrRow + 1)
					&& (startCol == plyrCol || startCol == plyrCol - 1 || startCol == plyrCol + 1))
				isNearPlayer = true; // the AI is near the target
			
			else {
				isNearPlayer = traverse(startRow - 1, startCol, plyrRow, plyrCol); // moves up

				if (!isNearPlayer)
					isNearPlayer = traverse(startRow, startCol - 1, plyrRow, plyrCol); // moves left
				
				if (!isNearPlayer)
					isNearPlayer = traverse(startRow + 1, startCol, plyrRow, plyrCol); // moves down

				if (!isNearPlayer)
					isNearPlayer = traverse(startRow, startCol + 1, plyrRow, plyrCol); // moves right
			}
      
      if (isNearPlayer) { // if AI can make it near or nearer to the player, move it
         gameMap
      }
      
   }
   return isNearPlayer; // Returns the boolean of if the AI moved or not.
}*/