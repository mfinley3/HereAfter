package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TRPGGUI extends JFrame {
	//Random serial number
	private static final long serialVersionUID = -703102129384036053L;
	
	private JButton newGame;
	private JButton loadGame;
	private JButton quit;
	private JPanel selections;

	// private Map map;

	public static void main(String[] args) {
		new TRPGGUI().setVisible(true);
	}

	public TRPGGUI() {

		// map = new Map();

		setTitle("______________");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		this.setSize(d.width - 250, d.height - 150);
		this.setVisible(true);
		this.setLocation(100, 0);

		this.setLayout(new BorderLayout());

		startUp();

	}

	public void startUp() {
		// add the picture to the startUp

		selections = new JPanel();
		selections.setLayout(new FlowLayout());

		
		newGame = new JButton("New Game");
		
		loadGame = new JButton("Continue Game");
		quit = new JButton("Quit");
		
		//Icon image = new ImageIcon("pc5ezzEyi.png");
		//newGame.setIcon(image);
		
		//newGame.setContentAreaFilled(false);
		//newGame.setFocusPainted(false);
		//newGame.setBorderPainted(false);

		selections.add(newGame);
		selections.add(loadGame);
		selections.add(quit);

		this.add(selections, BorderLayout.WEST);

		registerListeners();

	}

	private void registerListeners() {

		newGame.addActionListener(new NewGameListener());
		loadGame.addActionListener(new LoadGameListener());
		quit.addActionListener(new QuitListener());

	}

	private class NewGameListener implements ActionListener {
		public void actionPerformed(ActionEvent anActionEvent) {
			if (newGame.getText().equals("New Game")) {

				newGame.setText("Easy");
				loadGame.setText("Medium");
				quit.setText("Hard");

			} else if (newGame.getText().equals("easy")) {

				// setLevel = "easy";

			} else {
				// Continue last game, load the most recent game

			}

		}
	}

	private class LoadGameListener implements ActionListener {
		public void actionPerformed(ActionEvent anActionEvent) {
			// See if we can load a game from the previous time, so instead of
			// it asking at the beginning,
			// have a pop up message happen now so that they can reload the past
			// information
			if (loadGame.getText().equals("Continue Game")) {
				newGame.setText("Continue last game");
				loadGame.setText("Load game");
			} else if (loadGame.getText().equals("medium")) {
				// setLevel = "medium";
			} else {
				//Go to a list of saved games so they can select one
				
			}
		}
	}

	private class QuitListener implements ActionListener {
		public void actionPerformed(ActionEvent anActionEvent) {
			// Exit the JFrame
			if (quit.getText().equals("Quit")) {
				System.exit(0);
			}
			// getText().equals("hard")
			else {
				// setLevel = "hard";
			}
		}
	}

}