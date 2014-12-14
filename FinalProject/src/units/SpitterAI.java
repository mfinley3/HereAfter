package units;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import item.Item;
import item.ItemType;

/**
 * The Class SpitterAI. Has a higher range, decent attack and a high movement
 * speed.
 */
public class SpitterAI extends Unit {

	transient private BufferedImage spitterGen, spitterCantMove, spitter;

	/**
	 * Instantiates a new spitter ai.
	 *
	 * @param difficulty
	 *            the difficulty
	 */
	public SpitterAI(double difficulty) {
		// Unit Type, Given Item, Attack, Defense, Health, Movement, Range,
		// Difficulty multiplier
		super("Spitter", new Item("None", ItemType.NONE), 40, 40, 100, 6, 3,
				difficulty);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see units.Unit#drawUnit(java.awt.Graphics)
	 */
	@Override
	public void drawUnit(Graphics g) {
		if (spitter == null) {
			try {
				spitterCantMove = ImageIO.read(new File("SpitterCantMove.png"));
				spitterGen = ImageIO.read(new File("Spitter.png"));
				spitter = spitterGen;
			} catch (IOException e) {
				System.out.println("Could not find picture file");
			}
		}
		if (!super.canMove()) // If the soldier cannot move
			spitter = spitterCantMove;
		g.drawImage(spitter, super.currentY * 100, super.currentX * 100, null);
	}

} // end of SpitterAI
