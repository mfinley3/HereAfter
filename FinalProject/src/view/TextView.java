package view;


import java.awt.Color;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;



public class TextView extends JPanel implements Observer{

	  private JLabel textMap;
	  private String stringOfMap;
	  
	  //setting up the layout of the text view
	  public TextView() {
		  stringOfMap = "Status of Units: ";
		  
		  
		  
		  textMap = new JLabel();
		  textMap.setText(stringOfMap);
		  textMap.setBackground(Color.WHITE);
		  textMap.setFont(new Font(Font.SERIF, Font.BOLD, 20));
		  add(textMap);
		  
	  }

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
