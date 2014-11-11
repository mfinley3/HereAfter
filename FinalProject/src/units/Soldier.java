package units;

import item.Item;
import item.ItemType;

public class Soldier implements Unit {

	public Soldier() {
		Item givenWeapon = new Item("Rifle", ItemType.ATK);
		itemList.add(givenWeapon);
	}
	
	@Override
	public int health() {
		// Health level * health modifier
		int hpMod = 1;
		//itemList.contains(healthItem);
		return 100 * hpMod;
	}

	@Override
	public int movesAvailable() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visibility() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getUR() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setUR(int uR) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getUC() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setHC(int uC) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isAlive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getStats() {
		// TODO Auto-generated method stub
		return null;
	}

}
