package view;


import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;



public class TextView extends JPanel{

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
	
	
	
}
