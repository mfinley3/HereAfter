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
		p.addUnits(new Soldier(Difficulty.EASY.getValue()));
		p.addUnits(new Engineer(Difficulty.EASY.getValue()));
		p.addUnits(new Doctor(Difficulty.EASY.getValue()));
		p.addUnits(new Ranger(Difficulty.EASY.getValue()));
		p.addUnits(new Sniper(Difficulty.EASY.getValue()));

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
		p.addUnits(new Soldier(Difficulty.EASY.getValue()));
		p.addUnits(new Engineer(Difficulty.EASY.getValue()));
		p.addUnits(new Doctor(Difficulty.EASY.getValue()));
		p.addUnits(new Ranger(Difficulty.EASY.getValue()));
		p.addUnits(new Sniper(Difficulty.EASY.getValue()));
		
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
		p.addUnits(new Soldier(Difficulty.EASY.getValue()));
		p.addUnits(new Engineer(Difficulty.EASY.getValue()));
		p.addUnits(new Doctor(Difficulty.EASY.getValue()));
		p.addUnits(new Ranger(Difficulty.EASY.getValue()));
		p.addUnits(new Sniper(Difficulty.EASY.getValue()));
		
		GameController g = new GameController(p, Difficulty.EASY);
		g.setCurrentUnit(0, 0);
	}
	
	@Test
	public void testAttackAndHeal(){
		// Test Attack
		Unit i = new Soldier(Difficulty.EASY.getValue());
		Unit b = new Soldier(Difficulty.EASY.getValue());
		Player p = new Player("EB");
		p.addUnits(b);
		p.addUnits(i);
		p.addUnits(new Doctor(Difficulty.EASY.getValue()));
		p.addUnits(new Ranger(Difficulty.EASY.getValue()));
		p.addUnits(new Sniper(Difficulty.EASY.getValue()));
		GameController g = new GameController(p ,Difficulty.EASY);
		g.setCurrentUnit(1, 0);
		g.setEndRow(1);
		g.setEndColumn(0);
		System.out.println(g.getCurrentUnit().getHealth());
		assertTrue(g.getCurrentUnit().getHealth()==100);
		g.setCurrentUnit(0, 0);
		g.setEndRow(1);
		g.setEndColumn(0);
		
		// Test isAlive
		
		g.attack();
		g.setCurrentUnit(1, 0);
		System.out.println(g.getCurrentUnit().getHealth());
		assertTrue(g.getCurrentUnit().getHealth()<100);
		assertTrue(g.getCurrentUnit().isAlive());
		g.setCurrentUnit(0, 1);
		g.attack();
		
		g.setCurrentUnit(1, 0);
		System.out.println(g.getCurrentUnit().getHealth());
		assertTrue(g.getCurrentUnit().getHealth()<100);
		assertTrue(g.getCurrentUnit().isAlive());
		Unit u=g.getCurrentUnit();
		
		g.setCurrentUnit(0, 2);
		g.attack();
		g.setCurrentUnit(1, 0);
		System.out.println(g.getCurrentUnit().getHealth());
		assertTrue(u.getHealth()<100);
		assertTrue(u.isAlive());
		
		g.setCurrentUnit(2, 0);
		g.attack();
		g.setCurrentUnit(1, 0);
		System.out.println(g.getCurrentUnit().getHealth());
		assertTrue(u.getHealth()<0);
		assertFalse(u.isAlive());
		
		assertTrue(!g.playerTurn());
	}
}
