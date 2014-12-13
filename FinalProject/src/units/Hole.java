package units;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import item.Item;
import item.ItemType;

public class Hole extends Unit{

	transient private BufferedImage holeGen;
	
	public Hole(double difficulty) {
		// unitType, their given item, attack, defense, health, movement, range, difficulty multiplier
		super("Hole", new Item("None", ItemType.NONE), 0, 0, 1, 0, 0, difficulty);
		// TODO Auto-generated constructor stub
	}






	@Override
	public void drawUnit(Graphics g) {
		if (holeGen == null) {
			try {
				holeGen = ImageIO.read(new File("HoleSpace.png"));
			} catch (IOException e) {
				System.out.println("Could not find picture file");
			}
		}
		g.drawImage(holeGen, super.currentX * 100, super.currentY * 100, null);
	}

	

}
