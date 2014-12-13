package units;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import item.*;

/**
 * The Class Soldier.
 */
public class Soldier extends Unit {

	/**
	 * Instantiates a new soldier.
	 * 
	 * @param difficulty
	 *            the difficulty
	 */

	transient private BufferedImage soldGen, soldSelect, soldCantMove, sold;

	public Soldier(double difficulty) {
		// Unit Type, Given Item, Attack, Defense, Health, Movement, Range,
		// Difficulty multiplier

		// Attack is doubled at creation so Attack is actually 60
		super("Soldier", new Item("Combat Rifle", ItemType.ATK), 30, 20, 100, 6, 3, difficulty);
		this.addItem(new UsableItem("Salvaged Grenade", ItemType.GRENADE));
		this.addItem(new UsableItem("Military Grenade", ItemType.GRENADE));
	}

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
		if (super.isSelected())
			sold = soldSelect;
		if (!super.canMove()) //If the soldier cannot move
			sold = soldCantMove;
		g.drawImage(sold, super.currentX, super.currentY, null);

	}

} // end of Soldier