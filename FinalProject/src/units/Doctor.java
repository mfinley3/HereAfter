package units;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import item.*;

// TODO: Auto-generated Javadoc
/**
 * The Class Doctor.
 */
public class Doctor extends Unit {

	
	/** The doc. */
	transient private BufferedImage docGen, docSelect, docCantMove, doc;
	
	/**
	 * Instantiates a new doctor.
	 *
	 * @param difficulty the difficulty
	 */
	public Doctor(double difficulty) {
		// Unit Type, Given Item, Attack, Defense, Health, Movement, Range, Difficulty multiplier
		
		// Health is doubled at creation so Health is actually 200
		super("Doctor", new Item("Emergency Kit", ItemType.HP), 20, 25, 100, 5, 2, difficulty);
		this.addItem(new UsableItem("Medkit", ItemType.MEDKIT));
		this.addItem(new UsableItem("Medkit", ItemType.MEDKIT));
		this.addItem(new UsableItem("Medkit", ItemType.MEDKIT));
	}

	/* (non-Javadoc)
	 * @see units.Unit#drawUnit(java.awt.Graphics)
	 */
	@Override
	public void drawUnit(Graphics g) {
		if (doc == null) {
			try {
				docSelect = ImageIO.read(new File("Doctor1Selected.png"));
				docCantMove = ImageIO.read(new File("Doctor1CantMove.png"));
				docGen = ImageIO.read(new File("Doctor1.png"));
				doc = docGen;
			} catch (IOException e) {
				System.out.println("Could not find picture file");
			}
		}
		if (super.isSelected)
			doc = docSelect;
		else if (!super.canMove()) //If the soldier cannot move
			doc = docCantMove;
		else doc = docGen;
		g.drawImage(doc, super.currentY * 100, super.currentX * 100, null);
	}
	
} // end of Doctor