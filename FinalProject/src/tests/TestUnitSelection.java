package tests;

import static org.junit.Assert.*;
import model.*;
import controller.*;
import units.*;
import org.junit.Test;

public class TestUnitSelection {
	
	@Test
	public void TestThings(){
		Player p = new Player("EB");
		p.addUnits(new Soldier());
		p.addUnits(new Engineer());
		p.addUnits(new Doctor());
		p.addUnits(new Ranger());
		p.addUnits(new Sniper());

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
		Player p = new Player("EB");
		p.addUnits(new Soldier());
		p.addUnits(new Engineer());
		p.addUnits(new Doctor());
		p.addUnits(new Ranger());
		p.addUnits(new Sniper());
		
		GameController g = new GameController(p, Difficulty.EASY);
		Unit u = g.getUnitOnMap(0, 0);
		assertTrue(u!=null);
		g.setCurrentUnit(0, 0);
		assertTrue(u==g.getCurrentUnit());
		assertTrue(u.canMove());
		g.setEndRow(2);
		g.setEndColumn(2);
		assertTrue(g.getMap().getSpace(2, 2).getCanMoveTo());
		assertTrue(g.move());
		assertFalse(u.canMove());
		assertFalse(u==g.getUnitOnMap(0, 0));
		assertTrue(u==g.getUnitOnMap(2,2));
		assertTrue(g.setCurrentUnit(1, 0));
		assertFalse(g.setCurrentUnit(2, 2));
		assertTrue(g.getCurrentUnit()==g.getUnitOnMap(1, 0));
		g.setEndRow(2);
		g.setEndColumn(2);
		assertFalse(g.move());
		assertTrue(g.getCurrentUnit().canMove());
	}
	
	@Test
	public void testEndTurnAndNewTurn(){
		Player p = new Player("EB");
		p.addUnits(new Soldier());
		p.addUnits(new Engineer());
		p.addUnits(new Doctor());
		p.addUnits(new Ranger());
		p.addUnits(new Sniper());
		
		GameController g = new GameController(p, Difficulty.EASY);
		g.setCurrentUnit(0, 0);
	}
}
