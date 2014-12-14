package view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * The Class HelpWindow.
 */
public class HelpWindow extends JFrame implements Runnable {

	private JTextArea goals;
	private JTextArea movement;
	private JTextArea attacks;
	private JTextArea item;
	private JTextArea wait;
	private JTextArea turns;
	private JTextArea endTurn;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {

	}

	/**
	 * Instantiates a new help window. It creates different inner windows that
	 * print out different aspects of the game. This is to help the user
	 * understand their goal, and how they are supposed to accomplish it.
	 */
	public HelpWindow() {

		this.setSize(400, 750);
		this.setVisible(true);
		this.setLocation(900, 0);
		this.setTitle("HereAfter: Help Screen");
		this.setLayout(new GridLayout(7, 1));

		goals = new JTextArea();
		goals.setEditable(false);
		goals.setBackground(Color.WHITE);
		goals.setText("GOALS: The intent of the game is to cure the world of the new-age\n"
				+ "     Ebola. Your group contains the cure, however, it is low in\n"
				+ "     quantity. To circumvent this issue, your units must move the top\n"
				+ "     each corner of the map, on top of the Beacon Tower, or just\n"
				+ "     survive for 25 turns. This depends on the game type.\n");

		movement = new JTextArea();
		movement.setEditable(false);
		movement.setBackground(Color.WHITE);
		movement.setText("MOVEMENT: First select the unit you wish to move, then select the\n"
				+ "     space (lighted in green) in which you'd like that unit to move to.\n"
				+ "     Once you have done that, click the 'Move' button on the \n"
				+ "     left-hand side of the screen.");

		attacks = new JTextArea();
		attacks.setEditable(false);
		attacks.setBackground(Color.WHITE);
		attacks.setText("ATTACKING: First select the unit you wish to have commit to the\n"
				+ "     attack. Then select the target you wish to attack. Once both\n"
				+ "     have been done, press the 'Attack' button.");

		item = new JTextArea();
		item.setEditable(false);
		item.setBackground(Color.WHITE);
		item.setText("ITEM: First select the unit you wish to have use the item.\n"
				+ "     Then select the target you wish to use the item on.\n"
				+ "     A unit can use items on items on itself remember!");

		wait = new JTextArea();
		wait.setEditable(false);
		wait.setBackground(Color.WHITE);
		wait.setText("WAITING: Used in replace of 'Use Item', 'Move' or 'Attack', 'Wait'\n"
				+ "     allows the current Unit to remain where they are, by clicking the Unit\n"
				+ "     you'd like to 'Wait', then clicking the 'Wait' button.");

		turns = new JTextArea();
		turns.setEditable(false);
		turns.setBackground(Color.WHITE);
		turns.setText("TURNS: Each player will get a chance to take thier turn. Starting \n"
				+ "     with player one (the blue team). Once all of a players living units \n"
				+ "     have moved or the end turn button is clicked it will automatically \n"
				+ "     change to the other players turn \n");

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
		this.add(turns);
		this.add(endTurn);
	}

}
