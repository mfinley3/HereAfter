package units;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import item.Item;
import item.ItemType;

public class ZombieDogAI extends Unit{

	transient private BufferedImage zombieGen, zombieCantMove, zombie;
	
	public ZombieDogAI(double difficulty) {
		// Unit Type, Given Item, Attack, Defense, Health, Movement, Range, Difficulty multiplier
		super("ZDog", new Item("None", ItemType.NONE), 35, 0, 100, 10, 1, difficulty);
		
	}

	@Override
	public void drawUnit(Graphics g) {
		if (zombie == null) {
			try {
				zombieCantMove = ImageIO.read(new File("carrierCantMove.png"));
				zombieGen = ImageIO.read(new File("carrier.png"));
				zombie = zombieCantMove;
			} catch (IOException e) {
				System.out.println("Could not find picture file");
			}
		}
		if (!super.canMove()) //If the soldier cannot move
			zombie = zombieCantMove;
		g.drawImage(zombie, super.currentX, super.currentY, null);
	}


}
