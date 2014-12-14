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
 * The Class AlphaProtectorAI.
 */
public class AlphaProtectorAI extends Unit {

	/** The alpha. */
	transient private BufferedImage alphaGen, alphaCantMove, alpha;
	
	/**
	 * Instantiates a new alpha protector ai.
	 *
	 * @param difficulty the difficulty
	 */
	public AlphaProtectorAI(double difficulty) {
		// Unit Type, Given Item, Attack, Defense, Health, Movement, Range, Difficulty multiplier
		super("AlphaProtector", new Item("None", ItemType.NONE), 35, 65, 100, 0, 8, difficulty);
		
	}

	/* (non-Javadoc)
	 * @see units.Unit#drawUnit(java.awt.Graphics)
	 */
	@Override
	public void drawUnit(Graphics g) {
		// TODO Auto-generated method stub
		if (alpha == null) {
			try {
				alphaCantMove = ImageIO.read(new File("AlphaProtectorCantMove.png"));
				alphaGen = ImageIO.read(new File("AlphaProtector.png"));
				alpha = alphaGen;
			} catch (IOException e) {
				System.out.println("Could not find picture file");
			}
		}if (!super.canMove()) //If the soldier cannot move
			alpha = alphaCantMove;
		else
			alpha = alphaGen;
		g.drawImage(alpha, super.currentY * 100, super.currentX * 100, null);
	}

} // end of AlphaProtectorAI