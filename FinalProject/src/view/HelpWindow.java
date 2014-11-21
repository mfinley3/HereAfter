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
		goals.setText("GOALS: Your goal is to control the units in the top right hand corner of the map and either capture the tower near the bottom left of the map or kill all the enemies");
				
		movement = new JTextArea();
		movement.setEditable(false);
		movement.setBackground(Color.WHITE);
		movement.setLineWrap(true);
		movement.setText("MOVEMENT: To move first select the unit you wish to move then select the soace you wish to move that unit to. Once you have done that click the 'Move' button on the right hand side of the screen.");
		
		attacks = new JTextArea();
		attacks.setEditable(false);
		attacks.setBackground(Color.WHITE);
		attacks.setLineWrap(true);
		attacks.setText("ATTACKING: To attack first select the unit of yours you wish to have commit the attack. Then select the target you wish to attack. Once both have been done, press the 'Attack' button." );
		
		item = new JTextArea();
		item.setEditable(false);
		item.setBackground(Color.WHITE);
		item.setLineWrap(true);
		item.setText("ITEM: Using Items is not yet supported. It is HIGHLY recommended you do not press this button as no one knows what may happen if you do. It could break the universe... (But it will likely just break the game)");
		
		wait = new JTextArea();
		wait.setEditable(false);
		wait.setBackground(Color.WHITE);
		wait.setLineWrap(true);
		wait.setText("WAITING: To wait first select the unit you wish you have wait it out for the turn and then click on the 'Wait' Button. This is used to not move a unit for that turn if you already like where they are.");
		
		this.add(goals);
		this.add(movement);
		this.add(attacks);
		this.add(item);
		this.add(wait);
	}

}
