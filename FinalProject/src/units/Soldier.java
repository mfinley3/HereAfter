package units;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import item.*;

/**
 * The Soldier is a controllable {@link Unit} by the player. Has a high
 * movement, and a high attack, decent defense. A good standard class. Created
 * upon start of a new game, controlled by the human player.
 */
public class Soldier extends Unit {
	transient private BufferedImage soldGen, soldSelect, soldCantMove, sold;

	/**
	 * Instantiates a new soldier.
	 *
	 * @param difficulty
	 *            the difficulty
	 */
	public Soldier(double difficulty) {
		// Unit Type, Given Item, Attack, Defense, Health, Movement, Range,
		// Difficulty multiplier

		// Attack is doubled at creation so Attack is actually 60
		super("Soldier", new Item("Combat Rifle", ItemType.ATK), 30, 20, 100,
				6, 3, difficulty);
		this.addItem(new UsableItem("Salvaged Grenade", ItemType.GRENADE));
		this.addItem(new UsableItem("Military Grenade", ItemType.GRENADE));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see units.Unit#drawUnit(java.awt.Graphics)
	 */
	@Override
	public void drawUnit(Graphics g) {
		
		if (sold == null) {
			try {
				soldSelect = ImageIO.read(new File("soldier1Selected.png"));
				soldCantMove = ImageIO.read(new File("soldier1CantMove.png"));
				soldGen = ImageIO.read(new File("soldier1.png"));
				sold = soldGen;
			} catch (IOException e) {
				System.out.println("Could not find picture file");
			}
		}
		if (super.isSelected)
			sold = soldSelect;
		else if (!super.canMove()) // If the soldier cannot move
			sold = soldCantMove;
		else
			sold = soldGen;
		g.drawImage(sold, (int) (super.currentY * 100), (int) (super.currentX * 100), null);
	}

} // end of Soldier