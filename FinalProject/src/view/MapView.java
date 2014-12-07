package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controller.GameController;

public class MapView extends JPanel {

	private BufferedImage survivalMap, TowerMap, CornerMap;
	private String GameType;

	public MapView() {
		try {
			
			survivalMap = ImageIO.read(new File("SurvivalMap.png"));
			TowerMap = ImageIO.read(new File("TowerMap.png"));
			CornerMap = ImageIO.read(new File("CornerMap.png"));

		} catch (IOException e) {
			System.out.println("Could not find picture file");
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		if (GameType.equalsIgnoreCase("survive"))
			g2.drawImage(survivalMap, 30, 30, null);

		if (GameType.equalsIgnoreCase("tower"))
			g2.drawImage(TowerMap, 30, 30, null);

		if (GameType.equalsIgnoreCase("corner"))
			g2.drawImage(CornerMap, 30, 30, null);
	}

	public void setGameType(String GameType) {
		this.GameType = GameType;

	}
}
