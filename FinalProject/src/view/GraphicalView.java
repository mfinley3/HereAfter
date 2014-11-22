package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
import units.Doctor;
import units.Engineer;
import units.Ranger;
import units.RunnerAI;
import units.Sniper;
import units.Soldier;
import units.Unit;
import controller.GameController;

// TODO: Auto-generated Javadoc
/**
 * The Class GraphicalView.
 */
public class GraphicalView extends JPanel implements Observer {

	/** The column. */
	private int clickX, clickY, row, column;
	
	/** The controller. */
	private GameController controller;
	
	/** The first click. */
	private boolean firstClick;
	
	/** The map. */
	private Map map;
	
	/** The current spaces. */
	private Space[][] currentSpaces;
	
	/** The current units. */
	private Unit[][] currentUnits;
	
	/** The runner cant move. */
	private BufferedImage bridge, corner, mountain, path, tower, wall, waste,
			water, doctor, engineer, ranger, sniper, soldier, runner,
			docCantMove, engCantMove, rangCantMove, snipCantMove, soldCantMove, runnerCantMove;

	/**
	 * Instantiates a new graphical view.
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
			runner = ImageIO.read(new File("Zombie.png"));
			
			docCantMove = ImageIO.read(new File("Doctor1CantMove.png"));
			engCantMove = ImageIO.read(new File("Engineer1CantMove.png"));
			rangCantMove = ImageIO.read(new File("Socut1CantMove.png"));
			snipCantMove = ImageIO.read(new File("sniper1CantMove.PNG"));
			soldCantMove = ImageIO.read(new File("soldier1CantMove.png"));
		} catch (IOException e) {
			System.out.println("Could not find picture file");
		}

		

	}

	/**
	 * Sets the controller.
	 *
	 * @param temp the new controller
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

		/**
		 * Event were a mouse button is clicked, Is not used.
		 * 
		 * @param evt
		 *            Takes in a clicked event
		 */
		public void mouseClicked(MouseEvent evt) {

		}

		/**
		 * Event were the mouse is moved. If the mouse moves and drawing is
		 * toggled then the shape will be drawn.
		 * 
		 * @param evt
		 *            Takes in a moved event
		 */
		public void mouseMoved(MouseEvent evt) {

		}

		/**
		 * Event were the mouse is pressed. Toggles drawing on and off.
		 * 
		 * @param evt
		 *            Takes in a mouse pressed event.
		 */
		public void mousePressed(MouseEvent evt) {

			clickX = evt.getX();
			clickY = evt.getY();

			row = clickX / 96;
			column = clickY / 96;

			if (firstClick) {
				System.out.println("NESDF");
				controller.setCurrentUnit(row, column);
				if(controller.getCurrentUnit()!=null){
					if(controller.getCurrentUnit().canMove())
						firstClick = false;
					else
						System.out.println("Unit can't move; select a new one.");
				}
				else
					System.out.println("No unit to select; please select a new unit.");
				
			} else {
				if(controller.getCurrentUnit() == null)
					firstClick = true;
				if (row >= 0 && row < currentSpaces.length && column >= 0
						&& column < currentSpaces.length) {
					controller.setEndRow(row);
					controller.setEndColumn(column);
				}
				
				firstClick = false;
			}
		}

		/**
		 * Event were the mouse entering the screen. Is not used.
		 * 
		 * @param evt
		 *            Takes in a mouse entered event.
		 */
		public void mouseEntered(MouseEvent evt) {

		}

		/**
		 * Event were the mouse is released. Is not used.
		 * 
		 * @param evt
		 *            Takes in a mouse released event.
		 */
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

		/**
		 * Event were the mouse is dragged. Is not used.
		 * 
		 * @param evt
		 *            Takes in a mouse dragged event.
		 */
		public void mouseDragged(MouseEvent evt) {

		}
	} // end JPanel class

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		map = controller.getMap();
		currentSpaces = map.getSpaces();
		currentUnits = map.getUnits();
		repaint();
		System.out.println("firstCLICK");
		firstClick = true;
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
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
					if(currentSpaces[col][row].getSpaceType().equals("Bridge")) {
						g2.drawImage(bridge, x, y, null);
					} else if(currentSpaces[col][row].getSpaceType().equals("Mountain")) {
						g2.drawImage(mountain, x, y, null);
					} else if(currentSpaces[col][row].getSpaceType().equals("Path")) {
						g2.drawImage(path, x, y, null);
					} else if(currentSpaces[col][row].getSpaceType().equals("Tower")) {
						g2.drawImage(tower, x, y, null);
					} else if(currentSpaces[col][row].getSpaceType().equals("Wall")) {
						g2.drawImage(wall, x, y, null);
					} else if(currentSpaces[col][row].getSpaceType().equals("Wasteland")) {
						g2.drawImage(waste, x, y, null);
					} else if(currentSpaces[col][row].getSpaceType().equals("Water")) {
						g2.drawImage(water, x, y, null);
					} else if(currentSpaces[col][row].getSpaceType().equals("CaptureCorner")) {
						g2.drawImage(corner, x, y, null);
					}
					
					if(currentUnits[col][row] instanceof Doctor) {
						if(currentUnits[col][row].canMove())
							g2.drawImage(doctor, x, y, null);
						else
							g2.drawImage(docCantMove, x, y, null);
					} else if(currentUnits[col][row] instanceof Engineer) {
						if(currentUnits[col][row].canMove())
							g2.drawImage(engineer, x, y, null);
						else
							g2.drawImage(engCantMove, x, y, null);
					} else if(currentUnits[col][row] instanceof Ranger) {
						if(currentUnits[col][row].canMove())
							g2.drawImage(ranger, x, y, null);
						else
							g2.drawImage(rangCantMove, x, y, null);
					} else if(currentUnits[col][row] instanceof Sniper) {
						if(currentUnits[col][row].canMove())
							g2.drawImage(sniper, x, y, null);
						else
							g2.drawImage(snipCantMove, x, y, null);
					} else if(currentUnits[col][row] instanceof Soldier) {
						if(currentUnits[col][row].canMove())
							g2.drawImage(soldier, x, y, null);
						else
							g2.drawImage(soldCantMove, x, y, null);
					} else if(currentUnits[col][row] instanceof RunnerAI) {
						if(currentUnits[col][row].canMove())
							g2.drawImage(runner, x, y, null);
						else
							g2.drawImage(runner, x, y, null);
					}
					x += 96;

				}
				y += 96;
			}
		}
	}
}
