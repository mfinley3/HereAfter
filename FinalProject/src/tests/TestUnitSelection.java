package tests;

import static org.junit.Assert.*;
import model.*;
import controller.*;
import units.Unit;

import org.junit.Test;

public class TestUnitSelection {
	
	@Test
	public void TestThings(){
		Player p = new Player("EB");
		GameController g = new GameController(p, Difficulty.EASY);
		Unit u = g.getUnitOnMap(2, 0);
		assertTrue(u!=null);
		u = g.getUnitOnMap(1, 0);
		assertTrue(u!=null);
		u = g.getUnitOnMap(0, 0);
		assertTrue(u!=null);
		u = g.getUnitOnMap(0, 1);
		assertTrue(u!=null);
		u = g.getUnitOnMap(0, 2);
		assertTrue(u!=null);
	}
}
