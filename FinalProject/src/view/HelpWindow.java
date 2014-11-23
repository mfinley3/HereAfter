package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JTextArea;

// TODO: Auto-generated Javadoc
/**
 * The Class HelpWindow.
 */
public class HelpWindow extends JFrame implements Runnable {

	private JTextArea goals;
	private JTextArea movement;
	private JTextArea attacks;
	private JTextArea item;
	private JTextArea wait;
	private JTextArea endTurn;
	
	@Override
	public void run() {
		
	}
	
	/**
	 * Instantiates a new help window. It creates different inner windows that print out different aspects of the game.  
	 * This is to help the user understand their goal, and how they are supposed to accomplish it.
	 */
	public HelpWindow() {

		this.setSize(375, 660);
		this.setVisible(true);
		this.setLocation(1105, 0);
		this.setTitle("HereAfter: Help Screen");	
		this.setLayout(new GridLayout(6,1));
		
		goals = new JTextArea();
		goals.setEditable(false);
		goals.setBackground(Color.WHITE);
		goals.setText("GOALS: The intent of the game is to cure the world of the new-age\n"
					+ "     Ebola. Your group contains the cure, however, it is low in\n"
					+ "     quantity. To circumvent this issue, your units in the top\n"
					+ "     corner of the map must either move on top, or 'capture', the\n"
					+ "     Beacon Tower near the bottom right of the map to release the\n"
					+ "     cure via air, or you can kill all of the enemies on the map.");
		
		movement = new JTextArea();
		movement.setEditable(false);
		movement.setBackground(Color.WHITE);
		movement.setText("MOVEMENT: First select the unit you wish to move, then select the\n"
					   + "     space in which you'd like that unit to move to. Once you have\n"
					   + "     done that, click the 'Move' button on the left-hand side of\n"
					   + "     the screen.");
		
		attacks = new JTextArea();
		attacks.setEditable(false);
		attacks.setBackground(Color.WHITE);
		attacks.setText("ATTACKING: First select the unit you wish to have commit to the\n"
					  + "     attack. Then select the target you wish to attack. Once both\n"
					  + "     have been done, press the 'Attack' button.");
		
		item = new JTextArea();
		item.setEditable(false);
		item.setBackground(Color.WHITE);
		item.setText("ITEM: Using Group-Inventory Items is not yet fully supported.\n"
				   + "     When it is, it will give the selected Item in your inventory\n"
				   + "     to every Unit's individual item lists.");
		
		wait = new JTextArea();
		wait.setEditable(false);
		wait.setBackground(Color.WHITE);
		wait.setText("WAITING: Used in replace of 'Move' or 'Attack', 'Wait' allows the\n"
				   + "     current Unit to remain where they are, by clicking the Unit\n"
				   + "     you'd like to 'Wait', then clicking the 'Wait' button."); 
		
		endTurn = new JTextArea();
		endTurn.setEditable(false);
		endTurn.setBackground(Color.WHITE);
		endTurn.setText("END TURN: Used to end your turn early. (Hey, sometimes it's\n"
				      + "     strategic!)");
		
		this.add(goals);
		this.add(movement);
		this.add(attacks);
		this.add(item);
		this.add(wait);
		this.add(endTurn);
	}

}
