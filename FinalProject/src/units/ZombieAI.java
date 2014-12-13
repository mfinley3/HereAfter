package units;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import item.Item;
import item.ItemType;

/**
 * The Class RunnerAI.
 */
public class ZombieAI extends Unit {
	
	transient private BufferedImage zombieGen, zombieCantMove, zombie;
	
	/**
	 * Instantiates a new runner ai.
	 *
	 * @param difficulty the difficulty
	 */
	public ZombieAI(double difficulty) {
		// Unit Type, Given Item, Attack, Defense, Health, Movement, Range, Difficulty multiplier
		super("Zombie", new Item("None", ItemType.NONE), 45, 20, 100, 5, 1, difficulty);
	}

	@Override
	public void drawUnit(Graphics g) {
		if (zombie == null) {
			try {
				zombieCantMove = ImageIO.read(new File("ZombieCantMove.png"));
				zombieGen = ImageIO.read(new File("Zombie.png"));
				zombie = zombieGen;
			} catch (IOException e) {
				System.out.println("Could not find picture file");
			}
		}
		if (!super.canMove()) //If the soldier cannot move
			zombie = zombieCantMove;
		g.drawImage(zombie, super.currentY * 100, super.currentX * 100, null);
	}

} // end of RunnerAI