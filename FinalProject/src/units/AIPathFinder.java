package units;

/**
 * MazeSolver attempts to recursively traverse a Maze. The goal is to get from the
 * given starting position to the bottom right, following a path of 1’s. Arbitrary
 * constants are used to represent locations in the maze that have been TRIED
 * and that are part of the solution PATH.
 *  @author Chioke
 */
public class AIPathFinder
{
   private AIMap gameMap;
   
   /**
    * Constructor for the AIPathFinder class.
    */
   public AIPathFinder(AIMap gameMap) {
      this.gameMap = gameMap;
   }
   
   /**
    * Attempts to recursively traverse the map. Inserts special
    * characters indicating locations that have been TRIED and that
    * eventually become part of the solution PATH.
    *  @param aiRow		row index of current location
    *  @param aiCol	column index of current location
    *  @return true		if the map has been solved
    */
   public boolean traverse(int aiRow, int aiCol)
   {
      boolean isFinished = false;
      
      if (gameMap.validPosition(aiRow, aiCol))
      {
         gameMap.tryPosition(aiRow, aiCol); // mark this cell as tried for the algorithm
         
         /*
          * AI location move to nearest player location
          * Keep count of AI movement length/attack range
          */
         if (aiRow == gameMap.getRows()-1 && aiCol == gameMap.getColumns()-1)
            isFinished = true; // the maze is solved
         else
         {
            isFinished = traverse(aiRow+1, aiCol); // down
            
            if (!isFinished)
               isFinished = traverse(aiRow, aiCol+1); // right
            
            if (!isFinished)
               isFinished = traverse(aiRow-1, aiCol);  // up
            
            if (!isFinished)
               isFinished = traverse(aiRow, aiCol-1); // left
         }
         
         if (isFinished) // this location is part of the final path
            gameMap.markPath(aiRow, aiCol);
      }
      return isFinished;
   }
   
} //end of class