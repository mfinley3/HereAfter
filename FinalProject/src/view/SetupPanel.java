package view;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class SetupPanel extends JPanel {

	private BufferedImage background, setUp1, setUp2;
	private JLabel title;
	private JTextArea userName, docNum, soldNum, engNum;
	private JButton select;
	private boolean selectLevel, startUp1, selectUnits;

	public SetupPanel() {

		this.setLayout(null);

		try {
			background = ImageIO.read(new File("unitSelect.jpeg"));
			setUp1 = ImageIO.read(new File("FinalStartScreenBackground.png"));
			setUp2 = ImageIO.read(new File("FinalStartScreenBackgroundDifficulty.png"));
		} catch (IOException e) {
			System.out.println("Could not find 'unitSelect.jpeg'");
		}
		
		startUp1 = true;
		selectLevel = false;
		selectUnits = false;
		
		registerListeners();

	}
	
	private void selectUnit() {
		
		title = new JLabel("HereAfter");
		title.setFont(new Font(Font.SERIF, Font.BOLD, 50));
		title.setSize(250, 70);
		title.setLocation(395, 15);
		this.add(title);

		JLabel userNameLabel = new JLabel("Enter user name: ");
		userNameLabel.setFont(new Font(Font.SERIF, Font.BOLD, 25));
		userNameLabel.setSize(200, 25);
		userNameLabel.setLocation(15, 100);
		this.add(userNameLabel);

		JLabel selectLabel = new JLabel(
				"Select your units.  Enter a number between 0 and 5 for ");
		selectLabel.setFont(new Font(Font.SERIF, Font.BOLD, 25));
		selectLabel.setSize(900, 25);
		selectLabel.setLocation(15, 170);
		this.add(selectLabel);

		JLabel selectLabel2 = new JLabel(
				"each type of unit.  Can have up to 5 units total.");
		selectLabel2.setFont(new Font(Font.SERIF, Font.BOLD, 25));
		selectLabel2.setSize(900, 25);
		selectLabel2.setLocation(15, 195);
		this.add(selectLabel2);

		userName = new JTextArea();
		userName.setFont(new Font(Font.SERIF, Font.BOLD, 25));
		userName.setSize(250, 25);
		userName.setLocation(220, 100);
		this.add(userName);

		select = new JButton("Make team!");
		select.setFont(new Font(Font.SERIF, Font.BOLD, 25));
		select.setSize(200, 35);
		select.setLocation(200, 580);

		select.addActionListener(new selectButtonListener());
		this.add(select);

		docNum = new JTextArea();
		docNum.setFont(new Font(Font.SERIF, Font.BOLD, 25));
		docNum.setSize(25, 25);
		docNum.setLocation(50, 325);
		this.add(docNum);

		soldNum = new JTextArea();
		soldNum.setFont(new Font(Font.SERIF, Font.BOLD, 25));
		soldNum.setSize(250, 25);
		soldNum.setLocation(220, 100);
		this.add(soldNum);
		
	}

	private class selectButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {

			// if() {

			// }
		}
	}
	
	private void registerListeners() {

		MouseListener listener = new ListenToMouse();
		MouseMotionListener motionListener = new ListenToMouse();

		this.addMouseMotionListener(motionListener);
		this.addMouseListener(listener);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D gr = (Graphics2D) g;
		if (selectUnits) {
			gr.drawImage(background, 0, 0, null);
		} else if(startUp1) {
			gr.drawImage(setUp1, 0, 0, null);
		} else if(selectLevel) {
			gr.drawImage(setUp2, 0, 0, null);
		}
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

		}

		@Override
		public void mousePressed(MouseEvent e) {
			int clickX = e.getX();
			int clickY = e.getY();

			if (startUp1) {

				if (clickX > 685 && clickX < 875 && clickY > 150 && clickY < 190) {
					// 190x40

					// This means there is a new game
					// go to the second page with the level options
					selectLevel = true;
					startUp1 = false;
					
					repaint();
				} else if (clickX > 645 && clickX < 925 && clickY > 210 && clickY < 250) {
					// This means they want to continue their game
					// 280x40

					startUp1 = false;
				} else if (clickX > 740 && clickX < 830 && clickY > 270 && clickY < 310) {
					// This means they want to quit the game
					// 90x40

					System.exit(0);
				}
			} else if (selectLevel) {
				if (clickX > 740 && clickX < 840 && clickY > 235 && clickY < 275) {
					// This means the difficulty is easy
					// map = new Map(Difficulty.EASY);
					
				} else if (clickX > 715 && clickX < 860 && clickY > 290 && clickY < 325) {
					// This means the difficulty is medium
					// map = new Map(Difficulty.MEDIUM);
					
				} else if (clickX > 740 && clickX < 840 && clickY > 340 && clickY < 380) {
					// This means the difficulty is hard
					// map = new Map(Difficulty.HARD);
					
				}
				selectUnit();
				selectLevel = false;
				selectUnits = true;
				repaint();
				
				// Create a controller
				//controller = new GameController();
			} else if(selectUnits) {
				
				
				
				
				
			}
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