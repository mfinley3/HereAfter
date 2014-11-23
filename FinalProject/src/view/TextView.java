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

  	private JTextArea mapText;
  	private GameController controller;
	  
	 /**
  	 * Instantiates a new text view.  This is where all of the stats of the units are printed so that the user 
  	 * knows how much health they have as well as their attacks and more.
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
  	 * Update the stats that are in the text view once a unit has moved.
  	 */
  	private void printStats() {
		  String temp = controller.getTeamStats();
		  mapText.setText(temp);
	  }


  	/**
  	 * The map will call notifyObervers, and this method will update the stats of the Player's units.
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
