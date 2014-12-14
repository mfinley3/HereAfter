package units;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import item.Item;
import item.ItemType;

// TODO: Auto-generated Javadoc
/**
 * The Class Hole.
 */
public class Hole extends Unit{

	/** The hole gen. */
	transient private BufferedImage holeGen;
	
	/**
	 * Creates a new hole enemy unit. Difficulty doesn't matter.
	 *
	 * @param difficulty the difficulty
	 */
	public Hole(double difficulty) {
		// unitType, their given item, attack, defense, health, movement, range, difficulty multiplier
		super("Hole", new Item("None", ItemType.NONE), 0, 0, 1, 0, 0, difficulty);
		// TODO Auto-generated constructor stub
	}






	/* (non-Javadoc)
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
