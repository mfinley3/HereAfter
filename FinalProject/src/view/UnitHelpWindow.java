package view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JTextArea;

// TODO: Auto-generated Javadoc
/**
 * The Class UnitHelpWindow.
 */
public class UnitHelpWindow extends JFrame implements Runnable {

	/** The description. */
	private JTextArea description;
	
	/** The soldier. */
	private JTextArea soldier;
	
	/** The doctor. */
	private JTextArea doctor;
	
	/** The engineer. */
	private JTextArea engineer;
	
	/** The sniper. */
	private JTextArea sniper;
	
	/** The ranger. */
	private JTextArea ranger;
	
	/** The zombie. */
	private JTextArea zombie;
	
	/** The carrier. */
	private JTextArea carrier;
	
	/** The alpha protector. */
	private JTextArea alphaProtector;
	
	/** The zombie dog. */
	private JTextArea zombieDog;
	
	/** The spitter. */
	private JTextArea spitter;
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	/**
	 * Instantiates a new unit help window.
	 */
	public UnitHelpWindow(){
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
		
		soldier = new JTextArea();
		soldier.setEditable(false);
		soldier.setBackground(Color.WHITE);
		soldier.setText( "Unit: Soldier.\n"
					+ "     Attack - 30\n"
					+ "     Defense - 20\n"
					+ "     Range - 3\n"
					+ "     Movement - 6\n"
					+ "     Items - Spawns with an ATK Boost and 2 Grenades.\n");
		
		doctor = new JTextArea();
		doctor.setEditable(false);
		doctor.setBackground(Color.WHITE);
		doctor.setText( "Unit: Doctor.\n"
					+ "     Attack - 20\n"
					+ "     Defense - 25\n"
					+ "     Range - 2\n"
					+ "     Movement - 5\n"
					+ "     Items - Spawns with a Health Boost and 3 Med Kits.\n");
		
		sniper = new JTextArea();
		sniper.setEditable(false);
		sniper.setBackground(Color.WHITE);
		sniper.setText( "Unit: Sniper.\n"
					+ "     Attack - 35\n"
					+ "     Defense - 15\n"
					+ "     Range - 7\n"
					+ "     Movement - 5\n"
					+ "     Items - Spawns with an ATK Boost.\n");
		
		engineer = new JTextArea();
		engineer.setEditable(false);
		engineer.setBackground(Color.WHITE);
		engineer.setText( "Unit: Engineer.\n"
					+ "     Attack - 20\n"
					+ "     Defense - 25\n"
					+ "     Range - 2\n"
					+ "     Movement - 4\n"
					+ "     Items - Spawns with a Def Boost and 2 land mines.\n");
		
		ranger = new JTextArea();
		ranger.setEditable(false);
		ranger.setBackground(Color.WHITE);
		ranger.setText( "Unit: Ranger.\n"
					+ "     Attack - 15\n"
					+ "     Defense - 15\n"
					+ "     Range - 1\n"
					+ "     Movement - 8\n"
					+ "     Items - Spawns with an ATK Boost.\n");
		
		
		this.add(description);
		this.add(soldier);
		this.add(doctor);
		this.add(sniper);
		this.add(engineer);
		this.add(ranger);
		
	}
	
	
	
}
