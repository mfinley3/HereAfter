package units;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import item.Item;
import item.ItemType;

/**
 * The Hole is a {@link Unit} that is controllable by the AI. It doesn't do
 * anything, but can be covered up to allow the game to end if you are going to
 * kill all of the units.
 */
public class Hole extends Unit {

	transient private BufferedImage holeGen;

	/**
	 * Creates a new hole enemy unit. Difficulty doesn't matter.
	 *
	 * @param difficulty
	 *            the difficulty
	 */
	public Hole(double difficulty) {
		// unitType, their given item, attack, defense, health, movement, range,
		// difficulty multiplier
		super("Hole", new Item("None", ItemType.NONE), 0, 0, 1, 0, 0,
				difficulty);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see units.Unit#drawUnit(java.awt.Graphics)
	 */
	@Override
	public void drawUnit(Graphics g) {
		if (holeGen == null) {
			try {
				holeGen = ImageIO.read(new File("HoleSpace.png"));
			} catch (IOException e) {
				System.out.println("Could not find picture file");
			}
		}
		g.drawImage(holeGen, super.currentY * 100, super.currentX * 100, null);
	}

}
