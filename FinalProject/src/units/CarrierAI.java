package units;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import item.Item;
import item.ItemType;
import item.UsableItem;

// TODO: Auto-generated Javadoc
/**
 * The Class CarrierAI.
 */
public class CarrierAI extends Unit{
	
	/** The carrier. */
	transient private BufferedImage carrierGen, carrierCantMove, carrier;
	
	/**
	 * Creates a new FatZombie AI. Difficulty affects its stats.
	 *
	 * @param difficulty the difficulty
	 */
	public CarrierAI(double difficulty) {
		// Unit Type, Given Item, Attack, Defense, Health, Movement, Range, Difficulty multiplier
		super("Carrier", new Item("Lard", ItemType.NONE), 10, 20, 100, 5, 1, difficulty);
	}




	/* (non-Javadoc)
	 * @see units.Unit#drawUnit(java.awt.Graphics)
	 */
	@Override
	public void drawUnit(Graphics g) {
		if (carrier == null) {
			try {
				carrierCantMove = ImageIO.read(new File("carrierCantMove.png"));
				carrierGen = ImageIO.read(new File("carrier.png"));
				carrier = carrierGen;
			} catch (IOException e) {
				System.out.println("Could not find picture file");
			}
		}
		if (!super.canMove()) //If the soldier cannot move
			carrier = carrierCantMove;
		g.drawImage(carrier, super.currentY * 100, super.currentX * 100, null);
	}

}
