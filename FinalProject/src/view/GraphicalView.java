package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.Map;
import space.Space;
import units.AlphaProtectorAI;
import units.CarrierAI;
import units.Doctor;
import units.Engineer;
import units.Ranger;
import units.ZombieAI;
import units.Sniper;
import units.Soldier;
import units.SpitterAI;
import units.Unit;
import units.ZombieDogAI;
import controller.GameController;

/**
 * The Class GraphicalView.
 */
public class GraphicalView extends JPanel implements Observer {

	private int clickX, clickY, row, column;
	private GameController controller;
	private boolean firstClick;
	private Map map;

	private Space[][] currentSpaces;
	private Unit[][] currentUnits;
	private BufferedImage bridge, corner, mountain, path, tower, wall, waste, water, doctor, engineer, ranger, sniper, soldier, Zombie, alpha, zDog, spitter, carrier, docCantMove, engCantMove, rangCantMove, snipCantMove, soldCantMove, ZombieCantMove, alphaCantMove, zDogCantMove, spitterCantMove, carrierCantMove, docSelected, engSelected, rangSelected, sinpSelected, soldSelected, ZombieSelected, alphaSelected, zDogSelected, spitterSelected, carrierSelected;

	/**
	 * Instantiates a new graphical view.  It also loads all of the images that are going to be used in the game,
	 * such as the map grounds, and the different units.
	 */
	public GraphicalView() {
		firstClick = true;

		this.setLayout(null);

		Dimension maxSize = new Dimension(4800, 4800);
		this.setPreferredSize(maxSize);
		this.setVisible(true);

		MouseListener listener = new ListenToMouse();
		MouseMotionListener motionListener = new ListenToMouse();

		this.addMouseMotionListener(motionListener);
		this.addMouseListener(listener);

		try {
			waste = ImageIO.read(new File("WasteLandSpace.jpg"));
			bridge = ImageIO.read(new File("BridgeSpace.jpg"));
			corner = ImageIO.read(new File("CornerSpace.jpg"));
			mountain = ImageIO.read(new File("MountainSpace.jpg"));
			path = ImageIO.read(new File("PathSpace.jpg"));
			tower = ImageIO.read(new File("TowerSpace.jpg"));
			wall = ImageIO.read(new File("WallSpace.jpg"));
			water = ImageIO.read(new File("WaterSpace.jpg"));

			doctor = ImageIO.read(new File("Doctor1.png"));
			engineer = ImageIO.read(new File("Engineer1.png"));
			ranger = ImageIO.read(new File("Ranger1.png"));
			sniper = ImageIO.read(new File("sniper1.PNG"));
			soldier = ImageIO.read(new File("soldier1.png"));
			Zombie = ImageIO.read(new File("Zombie.png"));
			alpha = ImageIO.read(new File("AlphaProtector.png"));
			zDog = ImageIO.read(new File("zDog.png"));
			spitter = ImageIO.read(new File("Spitter.png"));
			carrier = ImageIO.read(new File("carrier.png"));

			docCantMove = ImageIO.read(new File("Doctor1CantMove.png"));
			engCantMove = ImageIO.read(new File("Engineer1CantMove.png"));
			rangCantMove = ImageIO.read(new File("Ranger1CantMove.png"));
			snipCantMove = ImageIO.read(new File("sniper1CantMove.PNG"));
			soldCantMove = ImageIO.read(new File("soldier1CantMove.png"));
			ZombieCantMove = ImageIO.read(new File("ZombieCantMove.png"));
			alphaCantMove = ImageIO.read(new File("AlphaProtectorCantMove.png"));
			zDogCantMove = ImageIO.read(new File("zDogCantMove.png"));
			spitterCantMove = ImageIO.read(new File("SpitterCantMove.png"));
			carrierCantMove = ImageIO.read(new File("carrierCantMove.png"));
			
			docSelected = ImageIO.read(new File("Doctor1Selected.png"));
			engSelected = ImageIO.read(new File("Engineer1Selected.png"));
			rangSelected = ImageIO.read(new File("Ranger1Selected.png"));
			sinpSelected = ImageIO.read(new File("sniper1Selected.png"));
			soldSelected = ImageIO.read(new File("soldier1Selected.png"));
			ZombieSelected = ImageIO.read(new File("ZombieSelected.png"));
			alphaSelected = ImageIO.read(new File("AlphaProtectorSelected.png"));
			zDogSelected = ImageIO.read(new File("zDogSelected.png"));
			spitterSelected = ImageIO.read(new File("SpitterSelected.png"));
			carrierSelected = ImageIO.read(new File("carrierSelected.png"));
			

		} catch (IOException e) {
			System.out.println("Could not find picture file");
		}

	}

	/**
	 * Sets the controller.  It is called my SetupPanel, which sends it the controller so that the Graphical view
	 * can interact with the controller.
	 *
	 * @param temp
	 *            the new controller
	 */
	public void setController(GameController temp) {
		controller = temp;
		map = controller.getMap();
		currentSpaces = map.getSpaces();
		currentUnits = map.getUnits();
	}

	/**
	 * The Class ListenToMouse.
	 */
	private class ListenToMouse implements MouseMotionListener, MouseListener {

		public void mouseClicked(MouseEvent evt) {

		}

		public void mouseMoved(MouseEvent evt) {

		}

		/**
		 * When the mouse is pressed, it will take the coordinates of the mouse and divide by 96, which is the number
		 * of pixels that each space is wide and long.  This way, the row and column in which the user clicked it kept.
		 * If it is the first click, then it will save that unit as the current unit, sending the controller it's location.
		 * If it is the second click, that is the destination row and column, which is sent to the controller so that the
		 * controller can tell the map to change the position of the unit.
		 */
		public void mousePressed(MouseEvent evt) {

			clickX = evt.getX();
			clickY = evt.getY();

			row = clickX / 96;
			column = clickY / 96;

			if (firstClick) {
				controller.setCurrentUnit(row, column);
				if (controller.getCurrentUnit() != null) {
					if (controller.getCurrentUnit().canMove())
						firstClick = false;
					else
						System.out.println("Unit can't move; select a new one.");
				} else
					System.out.println("No unit to select; please select a new unit.");

			} else {
				if (controller.getCurrentUnit() == null)
					firstClick = true;
				if (row >= 0 && row < currentSpaces.length && column >= 0 && column < currentSpaces.length) {
					controller.setEndRow(row);
					controller.setEndColumn(column);
				}

				firstClick = false;
			}
		}

		public void mouseEntered(MouseEvent evt) {

		}


		public void mouseReleased(MouseEvent evt) {

		}

		/**
		 * Event were the mouse is Exiting the screen. Is not used.
		 * 
		 * @param evt
		 *            Takes in a mouse exited event.
		 */
		public void mouseExited(MouseEvent evt) {

		}

		public void mouseDragged(MouseEvent evt) {

		}
	} 

	/**
	 * The map calls notify Observers, and the graphical view will repaint the map to have the changed that occurred.
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		map = controller.getMap();
		currentSpaces = map.getSpaces();
		currentUnits = map.getUnits();
		repaint();
		firstClick = true;
	}

	/**
	 * This will first print ever space with the default map background.  It will then print a unit if applicable.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		int x = 0;
		int y = 0;

		if (currentSpaces != null) {
			for (int col = 0; col < currentSpaces.length; col++) {
				x = 0;
				for (int row = 0; row < currentSpaces.length; row++) {
					if (currentSpaces[col][row].getSpaceType().equals("Bridge")) {
						g2.drawImage(bridge, x, y, null);
					} else if (currentSpaces[col][row].getSpaceType().equals("Mountain")) {
						g2.drawImage(mountain, x, y, null);
					} else if (currentSpaces[col][row].getSpaceType().equals("Path")) {
						g2.drawImage(path, x, y, null);
					} else if (currentSpaces[col][row].getSpaceType().equals("Tower")) {
						g2.drawImage(tower, x, y, null);
					} else if (currentSpaces[col][row].getSpaceType().equals("Wall")) {
						g2.drawImage(wall, x, y, null);
					} else if (currentSpaces[col][row].getSpaceType().equals("Wasteland")) {
						g2.drawImage(waste, x, y, null);
					} else if (currentSpaces[col][row].getSpaceType().equals("Water")) {
						g2.drawImage(water, x, y, null);
					} else if (currentSpaces[col][row].getSpaceType().equals("CaptureCorner")) {
						g2.drawImage(corner, x, y, null);
					}

					if (currentUnits[col][row] == controller.getCurrentUnit()) {
						if (currentUnits[col][row] instanceof Doctor) {
							g2.drawImage(docSelected, x, y, null);
						} else if (currentUnits[col][row] instanceof Engineer) {
							g2.drawImage(engSelected, x, y, null);
						} else if (currentUnits[col][row] instanceof Ranger) {
							g2.drawImage(rangSelected, x, y, null);
						} else if (currentUnits[col][row] instanceof Sniper) {
							g2.drawImage(sinpSelected, x, y, null);
						} else if (currentUnits[col][row] instanceof Soldier) {
							g2.drawImage(soldSelected, x, y, null);
						} else if (currentUnits[col][row] instanceof ZombieAI) {
							g2.drawImage(ZombieSelected, x, y, null);
						} else if (currentUnits[col][row] instanceof AlphaProtectorAI) {
							g2.drawImage(alphaSelected, x, y, null);
						} else if (currentUnits[col][row] instanceof ZombieDogAI) {
							g2.drawImage(zDogSelected, x, y, null);
						} else if (currentUnits[col][row] instanceof SpitterAI) {
							g2.drawImage(spitterSelected, x, y, null);
						} else if (currentUnits[col][row] instanceof CarrierAI) {
							g2.drawImage(carrierSelected, x, y, null);
						}

					} else {

						if (currentUnits[col][row] instanceof Doctor) {
							if (currentUnits[col][row].canMove())
								g2.drawImage(doctor, x, y, null);
							else
								g2.drawImage(docCantMove, x, y, null);
						} else if (currentUnits[col][row] instanceof Engineer) {
							if (currentUnits[col][row].canMove())
								g2.drawImage(engineer, x, y, null);
							else
								g2.drawImage(engCantMove, x, y, null);
						} else if (currentUnits[col][row] instanceof Ranger) {
							if (currentUnits[col][row].canMove())
								g2.drawImage(ranger, x, y, null);
							else
								g2.drawImage(rangCantMove, x, y, null);
						} else if (currentUnits[col][row] instanceof Sniper) {
							if (currentUnits[col][row].canMove())
								g2.drawImage(sniper, x, y, null);
							else
								g2.drawImage(snipCantMove, x, y, null);
						} else if (currentUnits[col][row] instanceof Soldier) {
							if (currentUnits[col][row].canMove())
								g2.drawImage(soldier, x, y, null);
							else
								g2.drawImage(soldCantMove, x, y, null);
						} else if (currentUnits[col][row] instanceof ZombieAI) {
							if (currentUnits[col][row].canMove())
								g2.drawImage(Zombie, x, y, null);
							else
								g2.drawImage(ZombieCantMove, x, y, null);
						} else if (currentUnits[col][row] instanceof AlphaProtectorAI) {
							if (currentUnits[col][row].canMove())
								g2.drawImage(alpha, x, y, null);
							else
								g2.drawImage(alphaCantMove, x, y, null);
						} else if (currentUnits[col][row] instanceof ZombieDogAI) {
							if (currentUnits[col][row].canMove())
								g2.drawImage(zDog, x, y, null);
							else
								g2.drawImage(zDogCantMove, x, y, null);
						} else if (currentUnits[col][row] instanceof SpitterAI) {
							if (currentUnits[col][row].canMove())
								g2.drawImage(spitter, x, y, null);
							else
								g2.drawImage(spitterCantMove, x, y, null);
						} else if (currentUnits[col][row] instanceof CarrierAI) {
							if (currentUnits[col][row].canMove())
								g2.drawImage(carrier, x, y, null);
							else
								g2.drawImage(carrierCantMove, x, y, null);
						}
						
					}
					
					///////
					// TODO Display which spaces can be moved to
					if(currentSpaces[row][col].getCanMoveTo()){
						g2.setColor(Color.green);
						Stroke oldStroke = g2.getStroke();
						g2.setStroke(new BasicStroke(2));
						g2.drawRect(x, y, 96, 96);
						g2.setStroke(oldStroke);
					}
					/////////
					
					x += 96;
				}
				y += 96;
			}
		}
	}
}
