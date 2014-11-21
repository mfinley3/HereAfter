package view;



import javax.swing.JFrame;

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
	

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	new TRPGGUI();
            }
        });
	}

	public TRPGGUI() {	
		
		this.setSize(998, 660);
		this.setLocation(100, 10);
		
		setTitle("HereAfter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		this.add(new SetupPanel());
		this.setVisible(true);
	
	}

}