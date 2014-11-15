package view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
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




public class GraphicalView extends JPanel implements Observer{

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
		canvas.setSize(4800,4800);
		
		scrollPanel = new JScrollPane(canvas);
		
		this.add(scrollPanel);
		
		MouseListener listener = new ListenToMouse();
		MouseMotionListener motionListener = new ListenToMouse();

		canvas.addMouseMotionListener(motionListener);
		canvas.addMouseListener(listener);
		
		// Create a controller
		controller = new GameController(new Player("A"),Difficulty.EASY);
		
		controller.getMap().addObserver(this);
		
		
	}
	
	public void setDifficulty(Difficulty temp) {
		difficulty = temp;
	}
	
	private class ListenToMouse implements MouseMotionListener, MouseListener {

		/**
		 * Event were a mouse button is clicked, Is not used. 
		 * 
		 * @param evt Takes in a clicked event
		 */
		public void mouseClicked(MouseEvent evt) {

		}

		/**
		 * Event were the mouse is moved. If the mouse moves and drawing is
		 * toggled then the shape will be drawn. 
		 * 
		 * @param evt Takes in a moved event
		 */
		public void mouseMoved(MouseEvent evt) {

		}

		/**
		 * Event were the mouse is pressed. Toggles drawing on and off.
		 * 
		 * @param evt Takes in a mouse pressed event.
		 */
		public void mousePressed(MouseEvent evt) {

			clickX = evt.getX();
			clickY = evt.getY();
			
			row = clickX / 96;
			column = clickY / 96;
			
			if(firstClick) {
				controller.setCurrentUnit(row, column);
				firstClick = false;
				
				
			} else {
				controller.setEndRow(row);
				controller.setEndColumn(column);
			}
			
			
			

		}

		/**
		 * Event were the mouse entering the screen. Is not used.
		 * 
		 * @param evt Takes in a mouse entered event.
		 */
		public void mouseEntered(MouseEvent evt) {
			// newX = evt.getX();
			// newY = evt.getY();
			// System.out.println(newX + " Entered " + newY);
		}

		/**
		 * Event were the mouse is released. Is not used.
		 * 
		 * @param evt Takes in a mouse released event.
		 */
		public void mouseReleased(MouseEvent evt) {
			// newX = evt.getX();
			// newY = evt.getY();
			// System.out.println(newX + " released " + newY);
		}

		/**
		 * Event were the mouse is Exiting the screen. Is not used.
		 * 
		 * @param evt Takes in a mouse exited event.
		 */
		public void mouseExited(MouseEvent evt) {
			// newX = evt.getX();
			// newY = evt.getY();
			// System.out.println(newX + " Exited " + newY);
		}

		/**
		 * Event were the mouse is dragged. Is not used.
		 * 
		 * @param evt Takes in a mouse dragged event.
		 */
		public void mouseDragged(MouseEvent evt) {
			// newX = evt.getX();
			// newY = evt.getY();
			// System.out.println(newX + " dragged " + newY);
		}
	} // end JPanel class
	
	
	@Override
	public void update(Observable arg0, Object arg1) {
		currentSpaces = map.getSpaces();
		currentUnits = map.getUnits();
		repaint();
		
	}


}
