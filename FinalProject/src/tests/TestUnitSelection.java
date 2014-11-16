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
		System.out.println(g.getMap().toString());
		Unit u = g.getUnitOnMap(2, 0);
		assertTrue(u!=null);
		u = g.getUnitOnMap(2, 2);
		assertNull(u); 
		u = g.getUnitOnMap(1, 0);
		assertTrue(u!=null);
		u = g.getUnitOnMap(0, 0);
		assertTrue(u!=null);
		u = g.getUnitOnMap(0, 1);
		assertTrue(u!=null);
		u = g.getUnitOnMap(0, 2);
		assertTrue(u!=null);
	}
	
	@Test
	public void TestMovement(){
		GameController g = new GameController(new Player("EM"), Difficulty.EASY);
		Unit u = g.getUnitOnMap(0, 0);
		assertTrue(u!=null);
		g.setCurrentUnit(0, 0);
		assertTrue(u==g.getCurrentUnit());
		assertTrue(u.canMove());
		assertTrue(g.move(2, 2));
		assertFalse(u.canMove());
		assertFalse(u==g.getUnitOnMap(0, 0));
		assertTrue(u==g.getUnitOnMap(2,2));
		assertTrue(g.setCurrentUnit(1, 0));
		assertFalse(g.setCurrentUnit(2, 2));
		assertTrue(g.getCurrentUnit()==g.getUnitOnMap(1, 0));
		assertFalse(g.move(2,2));
		assertTrue(g.getCurrentUnit().canMove());
	}
	
	@Test
	public void testEndTurnAndNewTurn(){
		GameController g = new GameController(new Player("EM"), Difficulty.EASY);
		g.setCurrentUnit(0, 0)
	}
}
