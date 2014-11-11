package units;

import item.Item;
import item.ItemType;

public class Soldier implements Unit {

	private boolean canMove = false;
	
	public Soldier() {
		Item givenWeapon = new Item("Rifle", ItemType.ATK);
		itemList.add(givenWeapon);
	}

	@Override
	public int getHealth() {
		// Health level * health modifier
		int hpMod = 1;
		int totalHealth = 100;
		
		for (Item item : itemList) {
			if (item.isHealthItem()) {
				hpMod += 1;
				totalHealth = 100 * hpMod;
			}
		}
		return totalHealth;
	}

	@Override
	public int attack() {
		// Attack level * attack modifier
		int atkMod = 1;
		for (Item item : itemList) {
			if (item.isAtkItem()) {
				atkMod = 2;
			}
		}
		return 10 * atkMod;
	}
	
	@Override
	public int defense() {
		// Defense level * attack modifier
		int atkMod = 1;
		for (Item item : itemList) {
			if (item.isAtkItem()) {
				atkMod = 2;
			}
		}
		return 10 * atkMod;
	}

	@Override
	public void visibility() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public int getMovement() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean canMove() {
		// TODO Auto-generated method stub
		return canMove;
	}
	
	@Override
	public void setCanMove() {
		canMove = !canMove;
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
