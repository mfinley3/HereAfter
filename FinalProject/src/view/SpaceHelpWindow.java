package view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * The Class SpaceHelpWindow. Displays the information about each of the Spaces
 * that are on the map. Talks about what each does.
 */
public class SpaceHelpWindow extends JFrame implements Runnable {
	private JTextArea description;
	private JTextArea wastelandAndPaths;
	private JTextArea water;
	private JTextArea bridge;
	private JTextArea mountain;
	private JTextArea wall;
	private JTextArea holes;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
	}

	/**
	 * Instantiates a new space help window. This is where all of the
	 * information can be displayed.
	 */
	public SpaceHelpWindow() {
		this.setSize(400, 750);
		this.setVisible(true);
		this.setLocation(900, 0);
		this.setTitle("HereAfter: Space Help Screen");
		this.setLayout(new GridLayout(7, 1));

		description = new JTextArea();
		description.setEditable(false);
		description.setBackground(Color.WHITE);
		description
				.setText("About this Window: This Window shows a little information on space types.\n"
						+ "     Description of the space: what the space looks like\n"
						+ "     Move Hindrance - How much the space affects movement.\n"
						+ "     Special - Anything different about this space.\n");

		wastelandAndPaths = new JTextArea();
		wastelandAndPaths.setEditable(false);
		wastelandAndPaths.setBackground(Color.WHITE);
		wastelandAndPaths
				.setText("Space: Wasteland And Paths.\n"
						+ "     Description of the space: These are two very common tiles.\n"
						+ "     they are riddled around the map.\n"
						+ "     Move Hindrance - Does not affect movement.\n"
						+ "     Special - Slight color change in the survival gamemode.\n");

		water = new JTextArea();
		water.setEditable(false);
		water.setBackground(Color.WHITE);
		water.setText("Space: Water.\n"
				+ "     Description of the space: These spaces look like murky water.\n"
				+ "     they are found in large groups through out the map to form rivers.\n"
				+ "     Move Hindrance - Affects movement by 5 movement.\n"
				+ "     Special - Mines placed in water become Naval mines.\n");

		bridge = new JTextArea();
		bridge.setEditable(false);
		bridge.setBackground(Color.WHITE);
		bridge.setText("Space: Bridge.\n"
				+ "     Description of the space: These spaces are found near water.\n"
				+ "     They make for good choke points as it is hard to cross water.\n"
				+ "     Move Hindrance - Affects movement by 2 movement.\n"
				+ "     Special - It may not always be worth it to cross bridges.\n");

		mountain = new JTextArea();
		mountain.setEditable(false);
		mountain.setBackground(Color.WHITE);
		mountain.setText("Space: Mountain.\n"
				+ "     Description of the space: These spaces are usually .\n"
				+ "     found in the middle of the map and are hard to cross.\n"
				+ "     Move Hindrance - Affects movement by 6 movement.\n"
				+ "     Special - Only two types of units can cross with ease.\n");

		wall = new JTextArea();
		wall.setEditable(false);
		wall.setBackground(Color.WHITE);
		wall.setText("Space: Wall.\n"
				+ "     Description of the space: These cannot be passed through\n"
				+ "     or walked on they tend to be in groups forming shelter.\n"
				+ "     Move Hindrance - Can't be moved through.\n"
				+ "     Special - Slight color change in the survival gamemode.\n");

		holes = new JTextArea();
		holes.setEditable(false);
		holes.setBackground(Color.WHITE);
		holes.setText("Space: Hole.\n"
				+ "     Description of the space: These cannot be passed through\n"
				+ "     or walked on until they are blown up.\n"
				+ "     Move Hindrance - Can't be moved through.\n"
				+ "     Special - Will spawn new zombies until destroyed.\n");

		this.add(description);
		this.add(wastelandAndPaths);
		this.add(water);
		this.add(bridge);
		this.add(mountain);
		this.add(wall);
		this.add(holes);
	}

}
