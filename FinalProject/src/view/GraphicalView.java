package view;

import item.Item;
import item.RandomBoost;
import item.RandomItem;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.Map;
import space.Space;
import units.AlphaProtectorAI;
import units.CarrierAI;
import units.Doctor;
import units.Engineer;
import units.Hole;
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
	private Item[][] currentItems;
	private BufferedImage bridge, corner, mountain, path, indoorPathSpace, indoorPath, tower, wall, indoorWall, waste, indoorWaste, water, hole, holeCovered, holeCoveredIndoor, doctor, engineer, ranger, sniper, soldier, Zombie, alpha, zDog, spitter, carrier, docCantMove, engCantMove, rangCantMove, snipCantMove, soldCantMove, ZombieCantMove, alphaCantMove, zDogCantMove, spitterCantMove, carrierCantMove, docSelected, engSelected, rangSelected, sinpSelected, soldSelected, ZombieSelected, alphaSelected, zDogSelected, spitterSelected, carrierSelected, randomItem, randomBoost;
	private BufferedImage bridgeWithMine, cornerWithMine, mountainWithMine, pathWithMine, indoorPathSpaceWithMine, indoorPathWithMine, towerWithMine, wasteWithMine, indoorWasteWithMine, waterWithMine, holeWithMine, holeCoveredWithMine, holeCoveredIndoorWithMine;
	
	private double scaleFactor = 1;
	private String type;

	/**
	 * Instantiates a new graphical view. It also loads all of the images that
	 * are going to be used in the game, such as the map grounds, and the
	 * different units.
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
			hole = ImageIO.read(new File("HoleSpace.png"));
			holeCovered = ImageIO.read(new File("HoleSpaceCovered.png"));
			holeCoveredIndoor = ImageIO.read(new File("HoleSpaceCoveredIndoor.png"));
			
			wasteWithMine = ImageIO.read(new File("WasteLandSpaceWithMine.jpg"));
			indoorWasteWithMine = ImageIO.read(new File("IndoorWasteLandSpaceWithMine.jpg"));
			bridgeWithMine = ImageIO.read(new File("BridgeSpaceWithMine.jpg"));
			cornerWithMine = ImageIO.read(new File("CornerSpaceWithMine.jpg"));
			mountainWithMine = ImageIO.read(new File("MountainSpaceWithMine.jpg"));
			pathWithMine = ImageIO.read(new File("PathSpaceWithMine.jpg"));
			indoorPathSpaceWithMine = ImageIO.read(new File("IndoorPathSpaceWithMine.png"));
			indoorPathWithMine = ImageIO.read(new File("IndoorPathWithMine.jpg"));
			towerWithMine = ImageIO.read(new File("TowerSpaceWithMine.jpg"));
			waterWithMine = ImageIO.read(new File("WaterSpaceWithMine.jpg"));
			holeCoveredWithMine = ImageIO.read(new File("HoleSpaceCoveredWithMine.png"));
			holeCoveredIndoorWithMine = ImageIO.read(new File("HoleSpaceCoveredIndoorWithMine.png"));
			
			randomItem = ImageIO.read(new File("RandomItem.png"));
			randomBoost = ImageIO.read(new File("RandomBoost.png"));

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
			e.printStackTrace();
			System.out.println("Could not find picture file");
		}

	}

	/**
	 * Sets the controller. It is called my SetupPanel, which sends it the
	 * controller so that the Graphical view can interact with the controller.
	 *
	 * @param temp
	 *            the new controller
	 */
	public void setController(GameController temp) {
		controller = temp;
		map = controller.getMap();
		currentSpaces = map.getSpaces();
		currentUnits = map.getUnits();
		currentItems = map.getItems();
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
		 * When the mouse is pressed, it will take the coordinates of the mouse
		 * and divide by 96, which is the number of pixels that each space is
		 * wide and long. This way, the row and column in which the user clicked
		 * it kept. If it is the first click, then it will save that unit as the
		 * current unit, sending the controller it's location. If it is the
		 * second click, that is the destination row and column, which is sent
		 * to the controller so that the controller can tell the map to change
		 * the position of the unit.
		 */
		public void mousePressed(MouseEvent evt) {

			try {
				clickX = evt.getX();
				clickY = evt.getY();

				row = clickX / (int) (96 * scaleFactor);
				column = clickY / (int) (96 * scaleFactor);

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
			} catch (ArrayIndexOutOfBoundsException e) {

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
	 * The map calls notify Observers, and the graphical view will repaint the
	 * map to have the changed that occurred.
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		map = controller.getMap();
		currentSpaces = map.getSpaces();
		currentUnits = map.getUnits();
		currentItems = map.getItems();
		repaint();
		firstClick = true;
	}

	/**
	 * This will first print ever space with the default map background. It will
	 * then print a unit if applicable.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.scale(1 * scaleFactor, 1 * scaleFactor);
		Dimension maxSize = new Dimension((int) (4800 * scaleFactor), (int) (4800 * scaleFactor));
		this.setPreferredSize(maxSize);
		this.revalidate();

		int x = 0;
		int y = 0;

		
		
		g2.drawImage(map.getBackground(), 0, 0, null);
		
		

		
		if (currentSpaces != null) {
			for (int col = 0; col < currentSpaces.length; col++) {
				x = 0;
				for (int row = 0; row < currentSpaces.length; row++) {

					if (currentSpaces[col][row].getSpaceType().equals("Bridge")) {
						if(currentSpaces[col][row].hasMine())
							g2.drawImage(bridgeWithMine, x, y, null);
					} else if (currentSpaces[col][row].getSpaceType().equals("IndoorPathSpace")) {
						if(currentSpaces[col][row].hasMine())
							g2.drawImage(indoorPathSpaceWithMine, x, y, null);
					} else if (currentSpaces[col][row].getSpaceType().equals("Mountain")) {
						if(currentSpaces[col][row].hasMine())
							g2.drawImage(mountainWithMine, x, y, null);
					} else if (currentSpaces[col][row].getSpaceType().equals("Path")) {
						if(currentSpaces[col][row].hasMine())
							g2.drawImage(pathWithMine, x, y, null);
					} else if (currentSpaces[col][row].getSpaceType().equals("IndoorPath")) {
						if(currentSpaces[col][row].hasMine())
							g2.drawImage(indoorPathWithMine, x, y, null);
					} else if (currentSpaces[col][row].getSpaceType().equals("Tower")) {
						if(currentSpaces[col][row].hasMine())
							g2.drawImage(towerWithMine, x, y, null);
					} else if (currentSpaces[col][row].getSpaceType().equals("Wasteland")) {
						if(currentSpaces[col][row].hasMine())
							g2.drawImage(wasteWithMine, x, y, null);
					} else if (currentSpaces[col][row].getSpaceType().equals("IndoorWasteland")) {
						if(currentSpaces[col][row].hasMine())
							g2.drawImage(indoorWasteWithMine, x, y, null);
					} else if (currentSpaces[col][row].getSpaceType().equals("Water")) {
						if(currentSpaces[col][row].hasMine())
							g2.drawImage(waterWithMine, x, y, null);
					} else if (currentSpaces[col][row].getSpaceType().equals("CaptureCorner")) {
						if(currentSpaces[col][row].hasMine())
							g2.drawImage(cornerWithMine, x, y, null);
					} else if (currentSpaces[col][row].getSpaceType().equals("HoleCovered")) {
						
						if (!type.equalsIgnoreCase("survive")) {
							if(currentSpaces[col][row].hasMine())
								g2.drawImage(holeCoveredWithMine, x, y, null);
							else
							g2.drawImage(holeCovered, x, y, null);
						} else {
							if(currentSpaces[col][row].hasMine())
								g2.drawImage(holeCoveredIndoorWithMine, x, y, null);
							else
							g2.drawImage(holeCoveredIndoor, x, y, null);
						}
					}

					if (currentItems != null) {
						if (currentItems[col][row] instanceof RandomItem) {
							g2.drawImage(randomItem, x, y, null);
						} else if (currentItems[col][row] instanceof RandomBoost) {
							g2.drawImage(randomBoost, x, y, null);
						}
					}

					//if (currentUnits[col][row] == controller.getCurrentUnit()) {
						if(currentUnits[col][row] != null){
							currentUnits[col][row].drawUnit(g2);
						}
//						if (currentUnits[col][row] instanceof Doctor) {
//							g2.drawImage(docSelected, x, y, null);
//						} else if (currentUnits[col][row] instanceof Engineer) {
//							g2.drawImage(engSelected, x, y, null);
//						} else if (currentUnits[col][row] instanceof Ranger) {
//							g2.drawImage(rangSelected, x, y, null);
//						} else if (currentUnits[col][row] instanceof Sniper) {
//							g2.drawImage(sinpSelected, x, y, null);
//						} else if (currentUnits[col][row] instanceof Soldier) {
//							g2.drawImage(soldSelected, x, y, null);
//						} else if (currentUnits[col][row] instanceof ZombieAI) {
//							g2.drawImage(ZombieSelected, x, y, null);
//						} else if (currentUnits[col][row] instanceof AlphaProtectorAI) {
//							g2.drawImage(alphaSelected, x, y, null);
//						} else if (currentUnits[col][row] instanceof ZombieDogAI) {
//							g2.drawImage(zDogSelected, x, y, null);
//						} else if (currentUnits[col][row] instanceof SpitterAI) {
//							g2.drawImage(spitterSelected, x, y, null);
//						} else if (currentUnits[col][row] instanceof CarrierAI) {
//							g2.drawImage(carrierSelected, x, y, null);
//						} else if (currentUnits[col][row] instanceof Hole) {
//							g2.drawImage(hole, x, y, null);
//
//						}
//
//					} else {
//
//						if (currentUnits[col][row] instanceof Doctor) {
//							if (currentUnits[col][row].canMove())
//								g2.drawImage(doctor, x, y, null);
//							else
//								g2.drawImage(docCantMove, x, y, null);
//						} else if (currentUnits[col][row] instanceof Engineer) {
//							if (currentUnits[col][row].canMove())
//								g2.drawImage(engineer, x, y, null);
//							else
//								g2.drawImage(engCantMove, x, y, null);
//						} else if (currentUnits[col][row] instanceof Ranger) {
//							if (currentUnits[col][row].canMove())
//								g2.drawImage(ranger, x, y, null);
//							else
//								g2.drawImage(rangCantMove, x, y, null);
//						} else if (currentUnits[col][row] instanceof Sniper) {
//							if (currentUnits[col][row].canMove())
//								g2.drawImage(sniper, x, y, null);
//							else
//								g2.drawImage(snipCantMove, x, y, null);
//						} else if (currentUnits[col][row] instanceof Soldier) {
//							if (currentUnits[col][row].canMove())
//								g2.drawImage(soldier, x, y, null);
//							else
//								g2.drawImage(soldCantMove, x, y, null);
//						} else if (currentUnits[col][row] instanceof ZombieAI) {
//							if (currentUnits[col][row].canMove())
//								g2.drawImage(Zombie, x, y, null);
//							else
//								g2.drawImage(ZombieCantMove, x, y, null);
//						} else if (currentUnits[col][row] instanceof AlphaProtectorAI) {
//							if (currentUnits[col][row].canMove())
//								g2.drawImage(alpha, x, y, null);
//							else
//								g2.drawImage(alphaCantMove, x, y, null);
//						} else if (currentUnits[col][row] instanceof ZombieDogAI) {
//							if (currentUnits[col][row].canMove())
//								g2.drawImage(zDog, x, y, null);
//							else
//								g2.drawImage(zDogCantMove, x, y, null);
//						} else if (currentUnits[col][row] instanceof SpitterAI) {
//							if (currentUnits[col][row].canMove())
//								g2.drawImage(spitter, x, y, null);
//							else
//								g2.drawImage(spitterCantMove, x, y, null);
//						} else if (currentUnits[col][row] instanceof CarrierAI) {
//							if (currentUnits[col][row].canMove())
//								g2.drawImage(carrier, x, y, null);
//							else
//								g2.drawImage(carrierCantMove, x, y, null);
//						} else if (currentUnits[col][row] instanceof Hole) {
//							if (currentUnits[col][row].canMove())
//								g2.drawImage(hole, x, y, null);
//							else
//								g2.drawImage(hole, x, y, null);
//						}

					}

					// /////
					// TODO Display which spaces can be moved to
					if (currentSpaces[row][col].getCanMoveTo()) {
						g2.setColor(Color.green);
						Stroke oldStroke = g2.getStroke();
						g2.setStroke(new BasicStroke((float) (2 / scaleFactor)));
						g2.drawRect(x, y, 100, 100);
						g2.setStroke(oldStroke);
					}
					// ///////

					x += 100;
				}
				y += 100;
			}
		} // end of if currentSpace != null
	//}

	public void setZoomInScale() {
		scaleFactor = (scaleFactor * 2);
	}

	public void setZoomOutScale() {
		scaleFactor = (scaleFactor / 2);
	}

	public double getScaleFactor() {
		return scaleFactor;
	}

	public void setScaleFactor(double scaleFactor) {
		this.scaleFactor = scaleFactor;
		map.setScaleFactor(scaleFactor);
	}

	public void setGameType(String type) {
		this.type = type;

	}
}
