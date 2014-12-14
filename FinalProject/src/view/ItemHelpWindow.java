package view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * The Class ItemHelpWindow. Displays the types of items that can be used in the game. Displays types, what the different item types do, etc.
 */
public class ItemHelpWindow extends JFrame implements Runnable {

	private JTextArea description;
	private JTextArea attackBoost;
	private JTextArea defenceBoost;
	private JTextArea healthBoost;
	private JTextArea medKit;
	private JTextArea mine;
	private JTextArea gernade;
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {

	}

	/**
	 * Instantiates a new item help window. Displays all of the information.
	 */
	public ItemHelpWindow() {
		this.setSize(400, 750);
		this.setVisible(true);
		this.setLocation(900, 0);
		this.setTitle("HereAfter: Item Help Screen");	
		this.setLayout(new GridLayout(7,1));

		description = new JTextArea();
		description.setEditable(false);
		description.setBackground(Color.WHITE);
		description.setText( "About this Window: This Window shows what each item does.\n"
					+ "     An item is either a boost or useable item.\n"
					+ "     A useable item can be used by units in game to affect other units.\n"
					+ "     The usable items are Health kits, Gernades and Mines .\n"
					+ "     Boosts affect units base stats.\n"
					+ "     The boosts items are attack, defense and health.\n");

		attackBoost = new JTextArea();
		attackBoost.setEditable(false);
		attackBoost.setBackground(Color.WHITE);
		attackBoost.setText( "Item: Attack Boost.\n"
					+ "     This item can be found in the red item bags throughout the map.\n"
					+ "     The Attack Boost is a non-usable item that is automatically\n"
					+ "     applied to a units stats.\n"
					+ "     The attack boost takes the units base attack value and adds\n"
					+ "     that base value to the base again.\n"
					+ "     So, 1 attack boost doubles attack and 2 tripples attack power.\n");
		
		defenceBoost = new JTextArea();
		defenceBoost.setEditable(false);
		defenceBoost.setBackground(Color.WHITE);
		defenceBoost.setText( "Item: Defense Boost.\n"
					+ "     This item can be found in the red item bags throughout the map.\n"
					+ "     The Defense Boost is a non-usable item that is automatically\n"
					+ "     applied to a units stats.\n"
					+ "     The Defense boost takes the units base Defense value and adds\n"
					+ "     that base value to the base again.\n"
					+ "     So, 1 Defense boost doubles Defense and 2 tripples Defense power.\n");
		
		healthBoost = new JTextArea();
		healthBoost.setEditable(false);
		healthBoost.setBackground(Color.WHITE);
		healthBoost.setText( "Item: Health Boost.\n"
					+ "     This item can be found in the red item bags throughout the map.\n"
					+ "     The Health Boost is a non-usable item that is automatically\n"
					+ "     applied to a units stats.\n"
					+ "     The Health boost takes the units base Health value and adds\n"
					+ "     that base value to the base again.\n"
					+ "     So, 1 Health boost doubles Health and 2 tripples Health power.\n");
		
		medKit = new JTextArea();
		medKit.setEditable(false);
		medKit.setBackground(Color.WHITE);
		medKit.setText( "Item: Med Kit.\n"
					+ "     This item can be found in the blue item bags throughout the map.\n"
					+ "     The Med Kit is a usable item that is automatically\n"
					+ "     placed into a units inventory.\n"
					+ "     The Med Kit heals the targed unit to completly full health\n");
		
		mine = new JTextArea();
		mine.setEditable(false);
		mine.setBackground(Color.WHITE);
		mine.setText( "Item: Mine.\n"
					+ "     This item can be found in the blue item bags throughout the map.\n"
					+ "     The Mine is a usable item that is automatically\n"
					+ "     placed into a units inventory.\n"
					+ "     The mine can be placed on nearly any space and if a zombie\n"
					+ "     steps on it they will explode");
					
		gernade = new JTextArea();
		gernade.setEditable(false);
		gernade.setBackground(Color.WHITE);
		gernade.setText( "Item: Gernade.\n"
					+ "     This item can be found in the blue item bags throughout the map.\n"
					+ "     The Gernade is a usable item that is automatically\n"
					+ "     placed into a units inventory.\n"
					+ "     The gernade can be thown toward a group of zombies\n"
					+ "     to deal a good amount of dammage to each in the range of the shrapnel");
		
		
		this.add(description);
		this.add(attackBoost);
		this.add(defenceBoost);
		this.add(healthBoost);
		this.add(medKit);
		this.add(mine);
		this.add(gernade);
	}
}
