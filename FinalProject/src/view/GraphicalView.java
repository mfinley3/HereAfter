package view;

import java.awt.Color;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import space.Space;
import units.Unit;
import controller.GameController;
import model.Difficulty;
import model.Map;
import model.Player;

public class GraphicalView extends JPanel implements Observer {

	private int clickX, clickY, row, column;
	private String userName;
	private GameController controller;
	private boolean firstClick;
	private Map map;
	private Space[][] currentSpaces;
	private Unit[][] currentUnits;
	private BufferedImage bridge, corner, mountain, path, tower, wall, waste,
			water;

	public GraphicalView() {
		firstClick = true;
		// currentSpaces = new Space[50][50];
		// currentUnits = new Unit[50][50];

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
		} catch (IOException e) {
			System.out.println("Could not find picture file");
		}

		//currentSpaces = map.getSpaces();
		//currentUnits = map.getUnits();
		
		//map = controller.getMap();

	}

	public void setController(GameController temp) {
		controller = temp;
	}

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

				// if (map.isOccupied(row, column)) {
				controller.setCurrentUnit(row, column);
				firstClick = false;

				// }
			} else {
				if (row > 0 && row < currentSpaces.length && column > 0
						&& column < currentSpaces.length) {
					controller.move(row, column);
				}
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

	@Override
	public void update(Observable arg0, Object arg1) {
		map = controller.getMap();
		currentSpaces = map.getSpaces();
		currentUnits = map.getUnits();
		repaint();
		firstClick = true;
		
		System.out.println("UPDATE");
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		int x = 0;
		int y = 0;

		
		
		if (currentSpaces != null) {
			System.out.println("hello");
			for (int col = 0; col < currentSpaces.length; col++) {
				x = 0;
				for (int row = 0; row < currentSpaces.length; row++) {
					g2.drawImage(waste, x, y, null);
					// if(currentSpaces[col][row] )

					x += 96;

				}
				y += 96;
			}
		}
	}

}
