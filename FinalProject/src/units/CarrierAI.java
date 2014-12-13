package units;

import java.awt.Graphics;

import item.Item;
import item.ItemType;

public class CarrierAI extends Unit{

	public CarrierAI(double difficulty) {
		// Unit Type, Given Item, Attack, Defense, Health, Movement, Range, Difficulty multiplier
		super("Carrier", new Item("Lard", ItemType.NONE), 10, 20, 100, 5, 1, difficulty);
	}

	@Override
	public void drawUnit(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
