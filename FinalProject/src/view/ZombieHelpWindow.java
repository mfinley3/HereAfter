package view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * The Class ZombieHelpWindow.
 */
public class ZombieHelpWindow extends JFrame implements Runnable {

	private JTextArea description;
	private JTextArea zombie;
	private JTextArea carrier;
	private JTextArea alphaProtector;
	private JTextArea zombieDog;
	private JTextArea spitter;
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {

	}

	/**
	 * Instantiates a new zombie help window.
	 */
	public ZombieHelpWindow(){
		
		this.setSize(400, 750);
		this.setVisible(true);
		this.setLocation(900, 0);
		this.setTitle("HereAfter: Unit Help Screen");	
		this.setLayout(new GridLayout(6,1));
		
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
		
		zombie = new JTextArea();
		zombie.setEditable(false);
		zombie.setBackground(Color.WHITE);
		zombie.setText( "Unit: Zombie.\n"
					+ "     Attack - 45\n"
					+ "     Defense - 20\n"
					+ "     Range - 1\n"
					+ "     Movement - 5\n"
					+ "     Items - Spawns with nothing.\n");
		
		carrier = new JTextArea();
		carrier.setEditable(false);
		carrier.setBackground(Color.WHITE);
		carrier.setText( "Unit: Carrier.\n"
					+ "     Attack - 10\n"
					+ "     Defense - 20\n"
					+ "     Range - 1\n"
					+ "     Movement - 5\n"
					+ "     Items - Spawns with none, be careful when killing though!\n");
		
		alphaProtector = new JTextArea();
		alphaProtector.setEditable(false);
		alphaProtector.setBackground(Color.WHITE);
		alphaProtector.setText( "Unit: Alpha Protector.\n"
					+ "     Attack - 35\n"
					+ "     Defense - 65\n"
					+ "     Range - 8\n"
					+ "     Movement - 0\n"
					+ "     Items - Spawns with nothing\n");
		
		spitter = new JTextArea();
		spitter.setEditable(false);
		spitter.setBackground(Color.WHITE);
		spitter.setText( "Unit: Spitter.\n"
					+ "     Attack - 40\n"
					+ "     Defense - 40\n"
					+ "     Range - 6\n"
					+ "     Movement - 3\n"
					+ "     Items - Spawns with nothing.\n");
		
		zombieDog = new JTextArea();
		zombieDog.setEditable(false);
		zombieDog.setBackground(Color.WHITE);
		zombieDog.setText( "Unit: Zombie Dog.\n"
					+ "     Attack - 35\n"
					+ "     Defense - 0\n"
					+ "     Range - 1\n"
					+ "     Movement - 10\n"
					+ "     Items - Spawns with nothing.\n");
		
		
		this.add(description);
		this.add(zombie);
		this.add(carrier);
		this.add(alphaProtector);
		this.add(spitter);
		this.add(zombieDog);
		
	}
	

}
