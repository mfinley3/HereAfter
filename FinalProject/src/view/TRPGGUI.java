package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import songplayer.Songs;

/**
 * 
 * Sets up the GUI with the title of the frame. This is in charge of the entire
 * frame, and is what is run when the game is run.
 * 
 */

public class TRPGGUI {
	// Random serial number
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -703102129384036053L;
	private static JFrame mainFrame;
	private SetupPanel SetupPanel;
	private static boolean dontAskAgain;

	/**
	 * The main method. It creates a new TRPGGUI.
	 */
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new TRPGGUI();
			}
		});
	}

	/**
	 * Instantiates a new TRPG gui, with the title and size
	 */
	public TRPGGUI() {
		mainFrame = new JFrame();
		SetupPanel = new SetupPanel();

		if (!dontAskAgain) {
			int answer = JOptionPane.showConfirmDialog(null, "Start game in testing mode?", "Player Mode Selection", JOptionPane.YES_NO_OPTION);
			if (answer == JOptionPane.YES_OPTION) {
				SetupPanel.setTestingMode(true);
			}
		}

		//new Songs();

		mainFrame.setSize(996, 669);
		mainFrame.setLocation(100, 10);

		mainFrame.setTitle("HereAfter");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setResizable(false);

		mainFrame.addWindowListener(new SaveDataListener());

		mainFrame.add(SetupPanel);
		mainFrame.addComponentListener(new ComponentListener() {
			@Override
			public void componentResized(ComponentEvent e) {
				SetupPanel.repaint();
			}

			@Override
			public void componentHidden(ComponentEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void componentMoved(ComponentEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void componentShown(ComponentEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		mainFrame.setVisible(true);

	}

	/**
	 * This is called once the unit team has been selected, so that the user can
	 * make the game bigger, and is able to see more of the map.
	 */
	public static void canResize() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		mainFrame.setSize(d.width - 200, d.height - 100);
		mainFrame.setResizable(true);
	}

	public static void dispose() {
		mainFrame.dispose();
		new TRPGGUI();

	}

	public static void validate() {
		mainFrame.validate();

	}

	private class SaveDataListener implements WindowListener {

		@Override
		public void windowOpened(WindowEvent e) {

		}

		@Override
		public void windowClosing(WindowEvent e) {

			if (SetupPanel.getGameIsRunning()) {
				int answer = JOptionPane.showConfirmDialog(null, "Would you like to save before you quit?", "Before you go...", JOptionPane.YES_NO_OPTION);
				if (answer == JOptionPane.YES_OPTION) {
					SetupPanel.saveTheGame(true);
					dontAskAgain = true;

				} else {
					dontAskAgain = true;
					dispose();
				}
			}
		}

		@Override
		public void windowClosed(WindowEvent e) {

		}

		@Override
		public void windowIconified(WindowEvent e) {

		}

		@Override
		public void windowDeiconified(WindowEvent e) {

		}

		@Override
		public void windowActivated(WindowEvent e) {

		}

		@Override
		public void windowDeactivated(WindowEvent e) {

		}

	}
	
	public static void setdontAskAgain(boolean dontAskAgain1){
		dontAskAgain = dontAskAgain1;
	}

}