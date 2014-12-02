package units;

/**
 * MazeSolver attempts to recursively traverse a Maze. The goal is to get from the
 * given starting position to the bottom right, following a path of 1’s. Arbitrary
 * constants are used to represent locations in the maze that have been TRIED
 * and that are part of the solution PATH.
 *  @author Chioke
 */
public class BestPathFinder
{
   private AIMap gameMap;
   
   /**
    * Constructor for the MazeSolver class.
    */
   public BestPathFinder(AIMap gameMap) {
      this.gameMap = gameMap;
   }
   
   /**
    * Attempts to recursively traverse the maze. Inserts special
    * characters indicating locations that have been TRIED and that
    * eventually become part of the solution PATH.
    *  @param row row index of current location
    *  @param column column index of current location
    *  @return true if the maze has been solved
    */
   public boolean traverse(int row, int column)
   {
      boolean isFinished = false;
      
      if (gameMap.validPosition(row, column))
      {
         gameMap.tryPosition(row, column); // mark this cell as tried
         
         if (row == gameMap.getRows()-1 && column == gameMap.getColumns()-1)
            isFinished = true; // the maze is solved
         else
         {
            isFinished = traverse(row+1, column); // down
            
            if (!isFinished)
               isFinished = traverse(row, column+1); // right
            
            if (!isFinished)
               isFinished = traverse(row-1, column);  // up
            
            if (!isFinished)
               isFinished = traverse(row, column-1); // left
         }
         
         if (isFinished) // this location is part of the final path
            gameMap.markPath(row, column);
      }
      return isFinished;
   }
   
} //end of class