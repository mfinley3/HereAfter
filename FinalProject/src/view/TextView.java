package view;


import java.awt.Color;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controller.GameController;



// TODO: Auto-generated Javadoc
/**
 * The Class TextView.
 */
public class TextView extends JPanel implements Observer{

	  /** The map text. */
  	private JTextArea mapText;
	  
  	/** The controller. */
  	private GameController controller;
	  
	  //setting up the layout of the text view
	  /**
  	 * Instantiates a new text view.
  	 */
  	public TextView() {
			
		  mapText = new JTextArea();
		  mapText.setEditable(false);
		  mapText.setFont(new Font(Font.SERIF, Font.BOLD, 20));
		  mapText.setText("");
		  this.setBackground(Color.WHITE);
		  add(mapText);
		  
	  }
	  
	  /**
  	 * Prints the stats.
  	 */
  	private void printStats() {
		  String temp = controller.getTeamStats();
		  mapText.setText(temp);
	  }

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		printStats();
	}

	/**
	 * Sets the controller.
	 *
	 * @param controller the new controller
	 */
	public void setController(GameController controller) {
		this.controller = controller;
		printStats();
		
	}
	
	
	
}
