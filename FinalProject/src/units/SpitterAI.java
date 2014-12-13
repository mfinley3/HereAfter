package units;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import item.Item;
import item.ItemType;

/**
 * The Class SpitterAI.
 */
public class SpitterAI extends Unit {
	
	transient private BufferedImage spitterGen, spitterCantMove, spitter;
	
	/**
	 * Instantiates a new spitter ai.
	 *
	 * @param difficulty the difficulty
	 */
	public SpitterAI(double difficulty) {
		// Unit Type, Given Item, Attack, Defense, Health, Movement, Range, Difficulty multiplier
		super("Spitter", new Item("None", ItemType.NONE), 40, 40, 100, 6, 3, difficulty);
	}


	@Override
	public void drawUnit(Graphics g) {
		if (spitter == null) {
			try {
				spitterCantMove = ImageIO.read(new File("carrierCantMove.png"));
				spitterGen = ImageIO.read(new File("carrier.png"));
				spitter = spitterGen;
			} catch (IOException e) {
				System.out.println("Could not find picture file");
			}
		}
		if (!super.canMove()) //If the soldier cannot move
			spitter = spitterCantMove;
		g.drawImage(spitter, super.currentX, super.currentY, null);
	}

} // end of SpitterAI
