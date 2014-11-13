package view;


import javax.swing.JTabbedPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.GameController;

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

	private JLabel background;
	private boolean firstPage;
	private boolean secondPage;
	private JTabbedPane views;

	private JPanel mainPanel;
	private JPanel pickUnitPanel;

	private JPanel text = new TextView();
	private JPanel graphical = new GraphicalView();
	private GameController controller;

	public static void main(String[] args) {
		new TRPGGUI().setVisible(true);
	}

	public TRPGGUI() {	
		this.setSize(1000, 660);
		this.setVisible(true);
		this.setLocation(100, 0);
		
		setTitle("HereAfter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		firstPage = true;
		secondPage = false;





		// Start up page
		//ImageIcon image = new ImageIcon("FinalStartScreenBackground.png");
		//background = new JLabel(image);
		//mainPanel.add(background, BorderLayout.CENTER);

		//this.add(mainPanel, BorderLayout.CENTER);
//selectUnits();

		
		//registerListeners();

		this.add(new SetupPanel());
	}

	private void selectUnits() {
		this.add(new SetupPanel());
		
	}
	
	
	private void actualMap() {

		views = new JTabbedPane();
		views.add(graphical, "Graphical");
		views.add(text, "Text");
		mainPanel.add(views, BorderLayout.CENTER);
		
		JLabel title = new JLabel("HereAfter");
		title.setFont(new Font("Verdana", Font.PLAIN, 50));
		mainPanel.add(title, BorderLayout.NORTH);
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(8,1));
		JButton move = new JButton("Move");
		JButton attack = new JButton("Attack");
		JButton item = new JButton("Use an Item");
		JButton wait = new JButton("Wait");
		
		buttons.add(new JPanel());
		buttons.add(new JPanel());
		buttons.add(move);
		buttons.add(attack);
		buttons.add(item);
		buttons.add(wait);
		buttons.add(new JPanel());
		buttons.add(new JPanel());
		
		
		mainPanel.add(buttons, BorderLayout.WEST);


		revalidate();
		repaint();

	}

	private void registerListeners() {

		MouseListener listener = new ListenToMouse();
		MouseMotionListener motionListener = new ListenToMouse();

		mainPanel.addMouseMotionListener(motionListener);
		mainPanel.addMouseListener(listener);

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

			if (firstPage) {

				if (clickX > 685 && clickX < 875 && clickY > 150 && clickY < 190) {
					// 190x40

					// This means there is a new game
					// go to the second page with the level options
					ImageIcon image2 = new ImageIcon("FinalStartScreenBackgroundDifficulty.png");
					background.setIcon(image2);
					firstPage = false;
					secondPage = true;
				} else if (clickX > 645 && clickX < 925 && clickY > 210 && clickY < 250) {
					// This means they want to continue their game
					// 280x40

					firstPage = false;
				} else if (clickX > 740 && clickX < 830 && clickY > 270 && clickY < 310) {
					// This means they want to quit the game
					// 90x40

					System.exit(0);
				}
			} else if (secondPage) {
				if (clickX > 740 && clickX < 840 && clickY > 235 && clickY < 275) {
					// This means the difficulty is easy
					// map = new Map(Difficulty.EASY);

					System.out.println("easy");

				} else if (clickX > 715 && clickX < 860 && clickY > 290 && clickY < 325) {
					// This means the difficulty is medium
					// map = new Map(Difficulty.MEDIUM);
					System.out.println("medium");
				} else if (clickX > 740 && clickX < 840 && clickY > 340 && clickY < 380) {
					// This means the difficulty is hard
					// map = new Map(Difficulty.HARD);
					System.out.println("hard");
				}
				//selectUnits();
				mainPanel.remove(background);
				revalidate();
				repaint();
				//actualMap();
				selectUnits();
				
				
				secondPage = false;
				// Create a controller
				//controller = new GameController();
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