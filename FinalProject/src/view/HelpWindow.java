package view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class HelpWindow extends JFrame implements Runnable {

	private JTextArea goals;
	private JTextArea movement;
	private JTextArea attacks;
	private JTextArea item;
	private JTextArea wait;
	
	
	@Override
	public void run() {
		
	}
	
	public HelpWindow() {

		this.setSize(350, 660);
		this.setVisible(true);
		this.setLocation(1105, 0);
		this.setTitle("HereAfterHelp");	
		this.setLayout(new GridLayout(5,1));
		
		goals = new JTextArea();
		goals.setEditable(false);
		goals.setBackground(Color.WHITE);
		goals.setLineWrap(true);
		goals.setText("GOALS: The intent of the game is to cure the world of the new-age Ebola. Your group contains the cure, however, it is low in quantity. To circumvent this issue, your units in the top left-hand corner of the map must either move on top, or 'capture', the Beacon Tower near the bottom right of the map to release the cure via air, or you can kill all of the enemies on the map.");
		
		movement = new JTextArea();
		movement.setEditable(false);
		movement.setBackground(Color.WHITE);
		movement.setLineWrap(true);
		movement.setText("MOVEMENT: First select the unit you wish to move, then select the space in which you'd like the that unit to move to. Once you have done that, click the 'Move' button on the left-hand side of the screen.");
		
		attacks = new JTextArea();
		attacks.setEditable(false);
		attacks.setBackground(Color.WHITE);
		attacks.setLineWrap(true);
		attacks.setText("ATTACKING: First select the unit you wish to have commit to the attack. Then select the target you wish to attack. Once both have been done, press the 'Attack' button.");
		
		item = new JTextArea();
		item.setEditable(false);
		item.setBackground(Color.WHITE);
		item.setLineWrap(true);
		item.setText("ITEM: Using Group-Inventory Items is not yet fully supported. When it is, it will give the selected Item in your inventory to every Unit's individual item lists.");
		
		wait = new JTextArea();
		wait.setEditable(false);
		wait.setBackground(Color.WHITE);
		wait.setLineWrap(true);
		wait.setText("WAITING: Used in replace of 'Move' or 'Attack', 'Wait' allows the current Unit to remain where they are, by clicking the Unit you'd like to 'Wait', then clicking the 'Wait' button."); 
		
		this.add(goals);
		this.add(movement);
		this.add(attacks);
		this.add(item);
		this.add(wait);
	}

}
