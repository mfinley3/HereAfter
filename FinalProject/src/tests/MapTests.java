package tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Stack;

import model.Map;

import org.junit.Test;

import space.CaptureCornerSpace;
import space.Space;
import units.Soldier;
import units.Unit;

public class MapTests {

	@Test
	public void testMapSetup() {

		Map testMap = new Map(.5);
		Space[][] testSpaceMap = testMap.getSpaces();

		// tests one of each space type on the map
		assertTrue(testSpaceMap[49][0].getSpaceType().equals("CaptureCorner"));
		assertTrue(testSpaceMap[21][47].getSpaceType().equals("Wasteland"));
		assertTrue(testSpaceMap[8][34].getSpaceType().equals("Water"));
		assertTrue(testSpaceMap[22][11].getSpaceType().equals("Wall"));
		assertTrue(testSpaceMap[4][19].getSpaceType().equals("Path"));
		assertTrue(testSpaceMap[33][14].getSpaceType().equals("Bridge"));
		assertTrue(testSpaceMap[15][28].getSpaceType().equals("Mountain"));
		assertTrue(testSpaceMap[42][40].getSpaceType().equals("Tower"));

		// tests main diagonal
		assertTrue(testSpaceMap[0][0].getSpaceType().equals("CaptureCorner"));
		assertTrue(testSpaceMap[1][1].getSpaceType().equals("Wasteland"));
		assertTrue(testSpaceMap[2][2].getSpaceType().equals("Wasteland"));
		assertTrue(testSpaceMap[3][3].getSpaceType().equals("Wasteland"));
		assertTrue(testSpaceMap[3][3].getSpaceType().equals("Wasteland"));
		assertTrue(testSpaceMap[4][4].getSpaceType().equals("Wasteland"));
		assertTrue(testSpaceMap[5][5].getSpaceType().equals("Wasteland"));
		assertTrue(testSpaceMap[6][6].getSpaceType().equals("Wasteland"));
		assertTrue(testSpaceMap[7][7].getSpaceType().equals("Wasteland"));
		assertTrue(testSpaceMap[8][8].getSpaceType().equals("Wasteland"));
		assertTrue(testSpaceMap[9][9].getSpaceType().equals("Wasteland"));
		assertTrue(testSpaceMap[10][10].getSpaceType().equals("Wasteland"));
		assertTrue(testSpaceMap[11][11].getSpaceType().equals("Wasteland"));
		assertTrue(testSpaceMap[12][12].getSpaceType().equals("Wasteland"));
		assertTrue(testSpaceMap[13][13].getSpaceType().equals("Wasteland"));
		assertTrue(testSpaceMap[14][14].getSpaceType().equals("Wasteland"));
		assertTrue(testSpaceMap[15][15].getSpaceType().equals("Wasteland"));
		assertTrue(testSpaceMap[16][16].getSpaceType().equals("Wasteland"));
		assertTrue(testSpaceMap[17][17].getSpaceType().equals("Wasteland"));
		assertTrue(testSpaceMap[18][18].getSpaceType().equals("Wasteland"));
		assertTrue(testSpaceMap[19][19].getSpaceType().equals("Wasteland"));
		assertTrue(testSpaceMap[20][20].getSpaceType().equals("Wasteland"));
		assertTrue(testSpaceMap[21][21].getSpaceType().equals("Wasteland"));
		assertTrue(testSpaceMap[22][22].getSpaceType().equals("Mountain"));
		assertTrue(testSpaceMap[23][23].getSpaceType().equals("Mountain"));
		assertTrue(testSpaceMap[24][24].getSpaceType().equals("Mountain"));
		assertTrue(testSpaceMap[25][25].getSpaceType().equals("Mountain"));
		assertTrue(testSpaceMap[26][26].getSpaceType().equals("Wasteland"));
		assertTrue(testSpaceMap[27][27].getSpaceType().equals("Wasteland"));
		assertTrue(testSpaceMap[28][28].getSpaceType().equals("Wasteland"));
		assertTrue(testSpaceMap[29][29].getSpaceType().equals("Wasteland"));
		assertTrue(testSpaceMap[30][30].getSpaceType().equals("Path"));
		assertTrue(testSpaceMap[31][31].getSpaceType().equals("Path"));
		assertTrue(testSpaceMap[32][32].getSpaceType().equals("Wasteland"));
		assertTrue(testSpaceMap[33][33].getSpaceType().equals("Path"));
		assertTrue(testSpaceMap[34][34].getSpaceType().equals("Path"));
		assertTrue(testSpaceMap[35][35].getSpaceType().equals("Path"));
		assertTrue(testSpaceMap[36][36].getSpaceType().equals("Wasteland"));
		assertTrue(testSpaceMap[37][37].getSpaceType().equals("Wasteland"));
		assertTrue(testSpaceMap[38][38].getSpaceType().equals("Wasteland"));
		assertTrue(testSpaceMap[39][39].getSpaceType().equals("Wasteland"));
		assertTrue(testSpaceMap[40][40].getSpaceType().equals("Wasteland"));
		assertTrue(testSpaceMap[41][41].getSpaceType().equals("Wasteland"));
		assertTrue(testSpaceMap[42][42].getSpaceType().equals("Wasteland"));
		assertTrue(testSpaceMap[43][43].getSpaceType().equals("Wasteland"));
		assertTrue(testSpaceMap[44][44].getSpaceType().equals("Wasteland"));
		assertTrue(testSpaceMap[45][45].getSpaceType().equals("Wasteland"));
		assertTrue(testSpaceMap[46][46].getSpaceType().equals("Wasteland"));
		assertTrue(testSpaceMap[47][47].getSpaceType().equals("Wasteland"));
		assertTrue(testSpaceMap[48][48].getSpaceType().equals("Wasteland"));
		assertTrue(testSpaceMap[49][49].getSpaceType().equals("CaptureCorner"));

		// makes sure no space is missed and left as null
		for (int m = 0; m < testSpaceMap.length; m++) {
			for (int n = 0; n < testSpaceMap.length; n++) {
				assertFalse(testSpaceMap[m][n] == null);
			}
		}

	}

	@Test
	public void testIsOccupied() {

		Map testMap = new Map(.5);
		Space[][] testSpaceMap = testMap.getSpaces();

		Stack<Unit> unitList = new Stack<Unit>();
		unitList.add(new Soldier());
		unitList.add(new Soldier());
		unitList.add(new Soldier());
		unitList.add(new Soldier());
		unitList.add(new Soldier());
		testMap.addUnitsToMap(unitList);

		for (int m = 0; m < testSpaceMap.length; m++) {
			for (int n = 0; n < testSpaceMap.length; n++) {
				if (m == 0 && n == 0 || m == 1 && n == 0 || m == 2 && n == 0 || m == 0 && n == 1 || m == 0 && n == 2) {
					assertTrue(testMap.isOccupied(m, n));
				} else {
					assertFalse(testMap.isOccupied(m, n));

				}
			}
		}
	}

}
