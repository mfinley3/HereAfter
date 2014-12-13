package units;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import item.*;

/**
 * The Class Engineer.
 */
public class Engineer extends Unit {

	
	transient private BufferedImage engGen, engSelect, engCantMove, eng;
	/**
	 * Instantiates a new engineer.
	 *
	 * @param difficulty the difficulty
	 */
	public Engineer(double difficulty) {
		// Unit Type, Given Item, Attack, Defense, Health, Movement, Range, Difficulty multiplier
		
		// Defense is doubled at creation so Defense is actually 50
		super("Engineer", new Item("Homemade Armor Suit", ItemType.DEF), 20, 25, 100, 4, 2, difficulty);	
		this.addItem(new UsableItem("Salvaged Mine", ItemType.MINE));
		this.addItem(new UsableItem("Homemade Mine", ItemType.MINE));
	}


	@Override
	public void drawUnit(Graphics g) {
		if (eng == null) {
			try {
				engSelect = ImageIO.read(new File("Engineer1Selected.png"));
				engCantMove = ImageIO.read(new File("Engineer1CantMove.png"));
				engGen = ImageIO.read(new File("Engineer1.png"));
				eng = engGen;
			} catch (IOException e) {
				System.out.println("Could not find picture file");
			}
		}
		if (super.isSelected)
			eng = engSelect;
		if (!super.canMove()) //If the soldier cannot move
			eng = engCantMove;
		g.drawImage(eng, super.currentX * 100, super.currentY * 100, null);
	}
	
} // end of Engineer