package view;


import java.awt.Color;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.GameController;



public class TextView extends JPanel implements Observer{

	  private JLabel mapText;
	  private GameController controller;
	  
	  //setting up the layout of the text view
	  public TextView() {
		  
		  mapText = new JLabel("");
		  mapText.setBackground(Color.WHITE);
		  mapText.setFont(new Font(Font.SERIF, Font.BOLD, 20));
		  add(mapText);
		  
	  }
	  
	  private void printStats() {
		  String temp = controller.getTeamStats();
		  mapText.setText("<html>" + temp + "</html>");
	  }

	@Override
	public void update(Observable o, Object arg) {
		printStats();
	}

	public void setController(GameController controller) {
		this.controller = controller;
		printStats();
		
	}
	
	
	
}
