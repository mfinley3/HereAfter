package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import space.Space;
import units.Unit;
import controller.GameController;
import model.Difficulty;
import model.Map;
import model.Player;
import model.Shape;

public class GraphicalView extends JPanel implements Observer {

	private JPanel canvas;
	private JScrollPane scrollPanel;
	private int clickX, clickY, row, column;
	private Enum difficulty;
	private GameController controller;
	private boolean firstClick;
	private Map map;
	private Space[][] currentSpaces;
	private Unit[][] currentUnits;

	public GraphicalView() {
		firstClick = true;

		canvas = new JPanel();
		canvas.setSize(4800, 4800);

		scrollPanel = new JScrollPane(canvas);

		this.add(scrollPanel);

		MouseListener listener = new ListenToMouse();
		MouseMotionListener motionListener = new ListenToMouse();

		canvas.addMouseMotionListener(motionListener);
		canvas.addMouseListener(listener);
		
//----------------------------------------------------------------------------------------------------------------
		// Create a controller
		controller = new GameController(new Player("A"), Difficulty.EASY);

		controller.getMap().addObserver(this);

	}

	public void setDifficulty(Difficulty temp) {
		difficulty = temp;
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

				if (map.isOccupied(row, column)) {
					controller.setCurrentUnit(row, column);
					firstClick = false;

				} 
			} else {
				if(row > 0 && row < currentSpaces.length && column > 0 && column < currentSpaces.length ) {
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
		currentSpaces = map.getSpaces();
		currentUnits = map.getUnits();
		repaint();
		firstClick = true;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		

		for (Shape s : listOfShapes) {
			s.draw(g2);
		}
		


	}

}
