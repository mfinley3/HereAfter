package units;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import item.*;

/**
 * The Class Sniper.
 */
public class Sniper extends Unit {
	
	transient private BufferedImage snipGen, snipSelect, snipCantMove, snip;	
	
	/**
	 * Instantiates a new sniper unit.
	 *
	 * @param difficulty, augments the stats
	 */
	public Sniper(double difficulty) {
		// Unit Type, Given Item, Attack, Defense, Health, Movement, Range, Difficulty multiplier
		
		// Attack is doubled at creation so Attack is actually 70
		super("Sniper", new Item("MLG's Rifle of Quickscoping 9001", ItemType.ATK), 35, 10, 100, 5, 7, difficulty);
	}



	@Override
	public void drawUnit(Graphics g) {
		if (snip == null) {
			try {
				snipSelect = ImageIO.read(new File("sniper1Selected.PNG"));
				snipCantMove = ImageIO.read(new File("sniper1CantMove.PNG"));
				snipGen = ImageIO.read(new File("sniper1.PNG"));
				snip = snipGen;
			} catch (IOException e) {
				System.out.println("Could not find picture file");
			}
		}
		if (super.isSelected)
			snip = snipSelect;
		else if (!super.canMove()) //If the soldier cannot move
			snip = snipCantMove;
		else 
			snip = snipGen;
		g.drawImage(snip, super.currentY * 100, super.currentX * 100, null);
	}

} // end of Sniper