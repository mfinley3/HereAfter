package view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class ItemHelpWindow extends JFrame implements Runnable {

	private JTextArea description;
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
		description.setText( "About this Window: This Window shows what each item does.\n"
					+ "     An item is either a boost or useable item.\n"
					+ "     A useable item can be used by units in game to affect other units.\n"
					+ "     The usable items are Health kits, Gernades and Mines .\n"
					+ "     Boosts affect units base stats.\n"
					+ "     The boosts items are attack, defense and health.\n");

	}
}
