package units;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import item.Item;
import item.ItemType;

/**
 * ZombieDogs are a {@link Unit} that is controlled by AI. Moves quickly, but
 * doesn't have great damage output.
 */
public class ZombieDogAI extends Unit {

	transient private BufferedImage zombieGen, zombieCantMove, zombie;

	/**
	 * Instantiates a new ZombieDog class. Will be controlled by the AI.
	 *
	 * @param difficulty
	 *            the difficulty
	 */
	public ZombieDogAI(double difficulty) {
		// Unit Type, Given Item, Attack, Defense, Health, Movement, Range,
		// Difficulty multiplier
		super("ZDog", new Item("None", ItemType.NONE), 35, 0, 100, 10, 1,
				difficulty);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see units.Unit#drawUnit(java.awt.Graphics)
	 */
	@Override
	public void drawUnit(Graphics g) {
		if (zombie == null) {
			try {
				zombieCantMove = ImageIO.read(new File("ZDogCantMove.png"));
				zombieGen = ImageIO.read(new File("ZDog.png"));
				zombie = zombieGen;
			} catch (IOException e) {
				System.out.println("Could not find picture file");
			}
		}
		if (!super.canMove()) // If the soldier cannot move
			zombie = zombieCantMove;
		g.drawImage(zombie, super.currentY * 100, super.currentX * 100, null);
	}

}
