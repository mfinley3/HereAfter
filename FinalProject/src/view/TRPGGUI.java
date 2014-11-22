package view;

import javax.swing.JFrame;

/**
 * 
 * Sets up the GUI with the title of the frame.  This is in charge of the entire frame, and is what is run when the game is run.
 * 
 * 
 * @author Katelyn
 * 
 */

public class TRPGGUI extends JFrame {
	// Random serial number
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -703102129384036053L;
	

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
		
		this.setSize(998, 660);
		this.setLocation(100, 10);
		
		setTitle("HereAfter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// this.setResizable(false);
		
		this.add(new SetupPanel());
		this.setVisible(true);
	
	}

}