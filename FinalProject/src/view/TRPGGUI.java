package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * Sets up the GUI. Starts with the options of "New Game", "Continue" or "Quit"
 * and dynamically changes while buttons are selected.
 * 
 * 
 * @author Katelyn
 * 
 */

public class TRPGGUI extends JFrame {
	// Random serial number
	private static final long serialVersionUID = -703102129384036053L;

	private JPanel selections;
	private JLabel background;
	private boolean firstClick;

	// private Map map;

	public static void main(String[] args) {
		new TRPGGUI().setVisible(true);
	}

	public TRPGGUI() {

		// map = new Map();
		firstClick = true;

		setTitle("HereAfter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setSize(990, 660);
		this.setVisible(true);
		this.setLocation(100, 0);

		this.setLayout(new BorderLayout());

		startUp();

	}

	public void startUp() {
		// add the picture to the startUp
		selections = new JPanel();

		ImageIcon image = new ImageIcon("background.jpeg");

		background = new JLabel(image);
		this.add(background);

		// registerListeners();

	}

	private void registerListeners() {

		MouseListener listener = new ListenToMouse();
		MouseMotionListener motionListener = new ListenToMouse();

		selections.addMouseMotionListener(motionListener);
		selections.addMouseListener(listener);

	}

	/**
	 * Adds a listener to the mouse.
	 */
	private class ListenToMouse implements MouseMotionListener, MouseListener {

		/**
		 * Event were a mouse button is clicked, Is not used.
		 * 
		 * @param evt
		 *            Takes in a clicked event
		 */
		public void mouseClicked(MouseEvent evt) {
			int clickX = evt.getX();
			int clickY = evt.getY();

			if (firstClick) {

				if (clickX > ____ && clickX < ____ && clickY > ____ && clickY < ____) {
					// This means there is a new game
					// go to the second page with the level options
					ImageIcon image2 = new ImageIcon("background2.jpeg");
					background.setIcon(image2);
					firstClick = false;
				} else if(clickX > ____ && clickX < ____ && clickY > ____ && clickY < ____) {
					//This means they want to continue their game
					
					
					firstClick = false;
				} else if(clickX > ____ && clickX < ____ && clickY > ____ && clickY < ____) {
					//This means they want to quit the game
					System.exit(0);
				}
			} else {
				if (clickX > ____ && clickX < ____ && clickY > ____ && clickY < ____) {
					// This means the difficulty is easy
					map.setLevel("easy");
					background.setIcon(null);
				} else if(clickX > ____ && clickX < ____ && clickY > ____ && clickY < ____) {
					//This means the difficulty is medium
					map.setLevel("medium");
					background.setIcon(null);
				} else if(clickX > ____ && clickX < ____ && clickY > ____ && clickY < ____) {
					//This means the difficulty is hard
					map.setLevel("hard");
					background.setIcon(null);
				}
				
				
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {

		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {

		}

		@Override
		public void mouseDragged(MouseEvent e) {

		}

		@Override
		public void mouseMoved(MouseEvent e) {

		}

	}

}