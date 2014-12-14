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

// TODO: Auto-generated Javadoc
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
	
	/** The main frame. */
	private static JFrame mainFrame;
	
	/** The Setup panel. */
	private SetupPanel SetupPanel;
	
	/** The dont ask again. */
	private static boolean dontAskAgain;

	/**
	 * The main method. It creates a new TRPGGUI.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new TRPGGUI();
			}
		});
	}

	/**
	 * Instantiates a new TRPG gui, with the title and size.
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

		new Songs();

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

	/**
	 * Dispose.
	 */
	public static void dispose() {
		mainFrame.dispose();
		new TRPGGUI();

	}

	/**
	 * Validate.
	 */
	public static void validate() {
		mainFrame.validate();

	}

	/**
	 * The listener interface for receiving saveData events.
	 * The class that is interested in processing a saveData
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addSaveDataListener<code> method. When
	 * the saveData event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see SaveDataEvent
	 */
	private class SaveDataListener implements WindowListener {

		/* (non-Javadoc)
		 * @see java.awt.event.WindowListener#windowOpened(java.awt.event.WindowEvent)
		 */
		@Override
		public void windowOpened(WindowEvent e) {

		}

		/* (non-Javadoc)
		 * @see java.awt.event.WindowListener#windowClosing(java.awt.event.WindowEvent)
		 */
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

		/* (non-Javadoc)
		 * @see java.awt.event.WindowListener#windowClosed(java.awt.event.WindowEvent)
		 */
		@Override
		public void windowClosed(WindowEvent e) {

		}

		/* (non-Javadoc)
		 * @see java.awt.event.WindowListener#windowIconified(java.awt.event.WindowEvent)
		 */
		@Override
		public void windowIconified(WindowEvent e) {

		}

		/* (non-Javadoc)
		 * @see java.awt.event.WindowListener#windowDeiconified(java.awt.event.WindowEvent)
		 */
		@Override
		public void windowDeiconified(WindowEvent e) {

		}

		/* (non-Javadoc)
		 * @see java.awt.event.WindowListener#windowActivated(java.awt.event.WindowEvent)
		 */
		@Override
		public void windowActivated(WindowEvent e) {

		}

		/* (non-Javadoc)
		 * @see java.awt.event.WindowListener#windowDeactivated(java.awt.event.WindowEvent)
		 */
		@Override
		public void windowDeactivated(WindowEvent e) {

		}

	}
	
	/**
	 * Sets the dont ask again.
	 *
	 * @param dontAskAgain1 the new dont ask again
	 */
	public static void setdontAskAgain(boolean dontAskAgain1){
		dontAskAgain = dontAskAgain1;
	}

}