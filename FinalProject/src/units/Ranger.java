package units;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import item.*;

/**
 * The Ranger is a controllable {@link Unit} by the player. Has a high movement,
 * not a large mount of attack but it has a rifle witch increases attack, and
 * not a lot of range. Created upon start of a new game, controlled by the human
 * player.
 */
public class Ranger extends Unit {

	transient private BufferedImage rangGen, rangSelect, rangCantMove, rang;

	/**
	 * Instantiates a new ranger.
	 *
	 * @param difficulty
	 *            the difficulty
	 */
	public Ranger(double difficulty) {
		// Unit Type, Given Item, Attack, Defense, Health, Movement, Range,
		// Difficulty multiplier

		// Attack is doubled at creation so Attack is actually 50
		super("Ranger", new Item("Ranger Rifle", ItemType.ATK), 15, 15, 100, 8,
				3, difficulty);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see units.Unit#drawUnit(java.awt.Graphics)
	 */
	@Override
	public void drawUnit(Graphics g) {
		if (rang == null) {
			try {
				rangSelect = ImageIO.read(new File("Ranger1Selected.png"));
				rangCantMove = ImageIO.read(new File("Ranger1CantMove.png"));
				rangGen = ImageIO.read(new File("Ranger1.png"));
				rang = rangGen;
			} catch (IOException e) {
				System.out.println("Could not find picture file");
			}
		}
		if (super.isSelected)
			rang = rangSelect;
		else if (!super.canMove()) // If the soldier cannot move
			rang = rangCantMove;
		else
			rang = rangGen;
		g.drawImage(rang, super.currentY * 100, super.currentX * 100, null);
	}

} // end of Ranger
