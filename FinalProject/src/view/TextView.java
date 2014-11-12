package view;


import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextArea;



public class TextView extends JPanel{

	  private JTextArea textMap;
	  private String stringOfMap;
	  
	  //setting up the layout of the text view
	  public TextView() {
		  stringOfMap = "hello";
		  
		  textMap = new JTextArea();
		  textMap.setText(stringOfMap);
		  textMap.setBackground(Color.WHITE);
		  textMap.setFont(new Font("Courier", Font.BOLD, 19));
		  textMap.setSize(1100, 700);
		  textMap.setLocation(0, 0);
		  add(textMap);
		  
	  }
	
	
	
}
