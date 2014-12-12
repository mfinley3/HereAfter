package view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class ItemHelpWindow extends JFrame implements Runnable {

	private JTextArea attackBoost;
	private JTextArea defenceBoost;
	private JTextArea healthBoost;
	private JTextArea mine;
	private JTextArea gernade;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	public ItemHelpWindow() {
		this.setSize(400, 750);
		this.setVisible(true);
		this.setLocation(900, 0);
		this.setTitle("HereAfter: Item Help Screen");	
		this.setLayout(new GridLayout(7,1));
		
		
		description = new JTextArea();
		description.setEditable(false);
		description.setBackground(Color.WHITE);
		description.setText( "About this Window: This Window shows the stats of each unit type.\n"
					+ "     Attack - How much damage a unit can do.\n"
					+ "     Defense - How much damage is negaged when attacked.\n"
					+ "     Health - All Units Start with a base 100 health.\n"
					+ "     Range - How far a unit can interact with things.\n"
					+ "     Movement - How far a unit can move on the map in one turn.\n"
					+ "     Items - The items a unit is currently holding.\n");
	}
}
