package units;

import java.util.*;
import java.io.*;

/**
 * Maze represents a maze of characters. The goal is to get from the top left
 * corner to the bottom right, following a path of 1’s. Arbitrary constants are
 * used to represent locations in the maze that have been TRIED and that are
 * part of the solution PATH.
 * 
 * @author Chioke
 */
public class AIMap {
	private static final String TRIED = "0";
	private static final String PATH = "1";
	private int numOfRows = 50;
	private int numOfCols = 50;

	private String[][] grid;

	/**
	 * Constructor for the AIMap class. Loads a maze from the given file.
	 * 
	 * @param filename
	 *            the name of the file to load
	 */
	public AIMap(String filename) {
		try {
		Scanner scan = new Scanner(new File(filename));

		grid = new String[numOfRows][numOfCols];
		
			while (scan.hasNext()) {
				for (int i = 0; i < numOfRows; i++)
					for (int j = 0; j < numOfCols; j++)
						grid[i][j] = scan.next();
			}
		
			scan.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	/**
	 * Return the number of rows in this maze
	 * 
	 * @return the number of rows in this maze
	 */
	public int getRows() {
		return numOfRows;
	}

	/**
	 * Return the number of columns in this maze
	 * 
	 * @return the number of columns in this maze
	 */
	public int getColumns() {
		return numOfCols;
	}

	/**
	 * Marks a given position in the maze as part of the PATH
	 * 
	 * @param row
	 *            the index of the row to mark as part of the PATH
	 * @param col
	 *            the index of the column to mark as part of the PATH
	 */
	public void markPath(int row, int col) {
		grid[row][col] = PATH;
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
	public boolean validPosition(int row, int column) {
		boolean result = false;

		// check if cell is in the bounds of the matrix

		if ((row >= 0) && (row < numOfRows) && (column >= 0) && (column < numOfRows)) {
			//System.out.println("Made it here");

			// check if cell is not blocked and not previously tried

			if (grid[row][column].equals("W") || grid[row][column].equals("P") || grid[row][column].equals("B")) {
				result = true;
			}
		}

		return result;
	}

	/**
	 * Returns the maze as a string.
	 * 
	 * @return a string representation of the maze
	 */
/*	public String toString() {
		String result = "\n";

		for (int row = 0; row < numOfRows; row++) {
			for (int column = 0; column < numOfCols; column++) {
				result += grid[row][column] + "";
				;
			}
			result += "\n";
		}

		return result;
	}*/

} // end of class