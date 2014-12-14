package view;

import item.ItemType;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import songplayer.Songs;
import units.Doctor;
import units.Engineer;
import units.Ranger;
import units.Sniper;
import units.Soldier;
import units.Unit;
import model.Difficulty;
import model.Player;
import controller.GameController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

// TODO: Auto-generated Javadoc
/**
 * The Class SetupPanel.
 */
public class SetupPanel extends JPanel implements Observer {

	/** The help icon. */
	private BufferedImage unitHelpIcon, toggleSoundIcon, zombieHelpIcon, itemHelpIcon, spaceHelpIcon, background, loadPage, setUp1, setUpLevel, setUpType, soldier, doctor, engineer, ranger, sniper, zoomInImg, zoomOutImg, floppy, helpIcon;

	/** The current user name. */
	private JLabel title, currentUserName;

	/** The snip num. */
	private JTextArea userName, docNum, soldNum, engNum, rangNum, snipNum;

	/** The save. */
	private JButton select, wait, item, attack, move, help, endTurn, save;

	/** The data was loaded. */
	private boolean selectLevel, startUp1, selectUnits, selected, gameIsRunning, selectType, loadGame, dataWasLoaded;

	/** The controller. */
	private GameController controller;

	/** The difficulty. */
	private Difficulty difficulty;

	/** The type. */
	private String type;

	/** The testing mode. */
	private boolean testingMode;

	/** The scroll panel. */
	private JScrollPane scrollPanel;

	/** The views. */
	private JTabbedPane views;

	/** The text. */
	private JPanel text = new TextView();

	/** The graphical. */
	private JPanel graphical = new GraphicalView();

	/** The text map. */
	private JPanel textMap = new MapView();

	/** The Unit locations. */
	private JPanel UnitLocations = new UnitLocations();

	/** The player name. */
	public String playerName;

	/** The player. */
	private Player player;

	/** The scale factor. */
	private double scaleFactor;

	/** The menu bar. */
	JMenuBar menuBar;

	/** The sound. */
	JMenu zoom, saveMenu, helpMenu, gamePlayHelp, sound;

	/** The toggle sound. */
	JMenuItem zoomIn, zoomOut, saveAndContinue, saveAndQuit, helpWindow, unitHelp, zombieHelp, itemHelp, spaceHelp, toggleSound;

	/**
	 * Instantiates a new setup panel. This loads all of the images that are
	 * going to be needed.
	 */
	public SetupPanel() {

		this.setLayout(null);

		try {

			background = ImageIO.read(new File("unitSelect.jpg"));
			loadPage = ImageIO.read(new File("LoadGameScreen.png"));
			setUp1 = ImageIO.read(new File("FinalStartScreenBackground.png"));
			setUpLevel = ImageIO.read(new File("FinalStartScreenBackgroundDifficulty.png"));
			doctor = ImageIO.read(new File("Doctor1.png"));
			engineer = ImageIO.read(new File("Engineer1.png"));
			ranger = ImageIO.read(new File("Ranger1.png"));
			sniper = ImageIO.read(new File("sniper1.PNG"));
			soldier = ImageIO.read(new File("soldier1.png"));
			setUpType = ImageIO.read(new File("GameTypeSelection.png"));
			zoomInImg = ImageIO.read(new File("zoomIn.png"));
			zoomOutImg = ImageIO.read(new File("zoomOut.png"));
			floppy = ImageIO.read(new File("floppydisk.png"));
			helpIcon = ImageIO.read(new File("helpIcon.png"));
			itemHelpIcon = ImageIO.read(new File("ItemHelpIcon.png"));
			spaceHelpIcon = ImageIO.read(new File("MountainSpaceHelpIcon.jpg"));
			unitHelpIcon = ImageIO.read(new File("UnitHelpIcon.png"));
			zombieHelpIcon = ImageIO.read(new File("ZombieHelpIcon.png"));
			toggleSoundIcon = ImageIO.read(new File("ToggleSoundIcon.png"));

		} catch (IOException e) {
			System.out.println("Could not find picture file");
		}

		startUp1 = true;
		selectLevel = false;
		selectUnits = false;
		selectType = false;
		selected = true;

		registerListeners();

	}

	/**
	 * Select unit. This instantiates the splash page for the user to select
	 * their units and user name. It adds a label, picture, and text area for
	 * each unit.
	 */

	private void selectUnit() {

		title = new JLabel("HereAfter");
		title.setFont(new Font(Font.SERIF, Font.BOLD, 50));
		title.setForeground(Color.WHITE);
		title.setSize(250, 70);
		title.setLocation(395, 15);
		this.add(title);

		JLabel userNameLabel = new JLabel("Enter user name: ");
		userNameLabel.setFont(new Font(Font.SERIF, Font.BOLD, 25));
		userNameLabel.setForeground(Color.WHITE);
		userNameLabel.setSize(200, 25);
		userNameLabel.setLocation(15, 100);
		this.add(userNameLabel);

		JLabel selectLabel = new JLabel("Select your units.  Enter a number between 0 and 5 for ");
		selectLabel.setFont(new Font(Font.SERIF, Font.BOLD, 25));
		selectLabel.setForeground(Color.WHITE);
		selectLabel.setSize(900, 25);
		selectLabel.setLocation(15, 170);
		this.add(selectLabel);

		JLabel selectLabel2 = new JLabel("each type of unit.  Can have up to 5 units total.");
		selectLabel2.setFont(new Font(Font.SERIF, Font.BOLD, 25));
		selectLabel2.setForeground(Color.WHITE);
		selectLabel2.setSize(900, 25);
		selectLabel2.setLocation(15, 195);
		this.add(selectLabel2);

		userName = new JTextArea();
		userName.setFont(new Font(Font.SERIF, Font.BOLD, 25));
		userName.setSize(250, 25);
		userName.setLocation(220, 100);
		this.add(userName);

		select = new JButton("Make team!");
		select.setFont(new Font(Font.SERIF, Font.BOLD, 25));
		select.setSize(200, 35);
		select.setLocation(180, 580);

		select.addActionListener(new selectButtonListener());
		this.add(select);

		JLabel doc = new JLabel("Doctor");
		doc.setFont(new Font(Font.SERIF, Font.BOLD, 25));
		doc.setForeground(Color.WHITE);
		doc.setSize(100, 25);
		doc.setLocation(50, 250);
		this.add(doc);

		docNum = new JTextArea();
		docNum.setFont(new Font(Font.SERIF, Font.BOLD, 25));
		docNum.setSize(25, 25);
		docNum.setLocation(70, 375);
		this.add(docNum);

		JLabel sold = new JLabel("Soldier");
		sold.setFont(new Font(Font.SERIF, Font.BOLD, 25));
		sold.setForeground(Color.WHITE);
		sold.setSize(100, 25);
		sold.setLocation(250, 250);
		this.add(sold);

		soldNum = new JTextArea();
		soldNum.setFont(new Font(Font.SERIF, Font.BOLD, 25));
		soldNum.setSize(25, 25);
		soldNum.setLocation(270, 375);
		this.add(soldNum);

		JLabel rang = new JLabel("Ranger");
		rang.setFont(new Font(Font.SERIF, Font.BOLD, 25));
		rang.setForeground(Color.WHITE);
		rang.setSize(100, 25);
		rang.setLocation(450, 250);
		this.add(rang);

		rangNum = new JTextArea();
		rangNum.setFont(new Font(Font.SERIF, Font.BOLD, 25));
		rangNum.setSize(25, 25);
		rangNum.setLocation(470, 375);
		this.add(rangNum);

		JLabel snip = new JLabel("Sniper");
		snip.setFont(new Font(Font.SERIF, Font.BOLD, 25));
		snip.setForeground(Color.WHITE);
		snip.setSize(100, 25);
		snip.setLocation(140, 420);
		this.add(snip);

		snipNum = new JTextArea();
		snipNum.setFont(new Font(Font.SERIF, Font.BOLD, 25));
		snipNum.setSize(25, 25);
		snipNum.setLocation(160, 545);
		this.add(snipNum);

		JLabel eng = new JLabel("Engineer");
		eng.setFont(new Font(Font.SERIF, Font.BOLD, 25));
		eng.setForeground(Color.WHITE);
		eng.setSize(100, 25);
		eng.setLocation(350, 420);
		this.add(eng);

		engNum = new JTextArea();
		engNum.setFont(new Font(Font.SERIF, Font.BOLD, 25));
		engNum.setSize(25, 25);
		engNum.setLocation(380, 545);
		this.add(engNum);

	}

	/**
	 * The listener interface for receiving selectButton events. This occurs
	 * when the "Select" button on the setUp page is pushed. It will check that
	 * the number of units adds up to 5, and that actual numbers are entered for
	 * each unit. This class creates a new unit for each unit the user selects,
	 * a new player with those units, a GameController, and sets the graphical
	 * and text view to observe the map.
	 *
	 * @see selectButtonEvent
	 */
	private class selectButtonListener implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		public void actionPerformed(ActionEvent ae) {
			// Check that the number of all units adds to 5. if they dont, show
			// an error message
			// Once this is confirmed, then we want to create the actual map

			try {
				String temp = docNum.getText();
				int docs;
				try {
					docs = Integer.parseInt(temp);
				} catch (Exception e) {
					docs = 0;
				}

				temp = soldNum.getText();
				int solds;
				try {
					solds = Integer.parseInt(temp);
				} catch (Exception e) {
					solds = 0;
				}

				temp = engNum.getText();
				int engs;
				try {
					engs = Integer.parseInt(temp);
				} catch (Exception e) {
					engs = 0;
				}

				temp = rangNum.getText();
				int rangs;
				try {
					rangs = Integer.parseInt(temp);
				} catch (Exception e) {
					rangs = 0;
				}
				temp = snipNum.getText();
				int snips;
				try {
					snips = Integer.parseInt(temp);
				} catch (Exception e) {
					snips = 0;
				}

				if (docs + solds + engs + rangs + snips == 5) {
					playerName = userName.getText();
					player = new Player(playerName);

					while (docs != 0) {
						player.addUnits((Unit) new Doctor(difficulty.getValue()));
						docs--;
					}
					while (solds != 0) {
						player.addUnits((Unit) new Soldier(difficulty.getValue()));
						solds--;
					}
					while (engs != 0) {
						player.addUnits((Unit) new Engineer(difficulty.getValue()));
						engs--;
					}
					while (rangs != 0) {
						player.addUnits((Unit) new Ranger(difficulty.getValue()));
						rangs--;
					}
					while (snips != 0) {
						player.addUnits((Unit) new Sniper(difficulty.getValue()));
						snips--;
					}
					gameIsRunning = true;
					controller = new GameController(player, difficulty, type, testingMode);
					controller.getMap().addObserver((Observer) graphical);
					((GraphicalView) graphical).setController(controller);
					controller.getMap().addObserver((Observer) text);
					((TextView) text).setController(controller);
					controller.getMap().addObserver((Observer) UnitLocations);
					((GraphicalView) graphical).setGameType(type);
					controller.setGraphicalView((GraphicalView) graphical);

					selected = false;
					actualMap();
					TRPGGUI.canResize();

				} else {
					JOptionPane.showMessageDialog(null, "The number of units must add up to 5");
				}
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, userName.getText() + " did not enter a valid input");
			}

		}
	}

	/**
	 * If the move button is selected, the move method of controller is called
	 * so that the map and units change location.
	 *
	 * @see moveButtonEvent
	 */
	private class moveButtonListener implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			controller.move();
		}

	}

	/**
	 * If the attack button is selected, the attack method of controller is
	 * called so that the map and units change, and so that one unit can attack
	 * and kill the other if possible.
	 *
	 * @see attackButtonEvent
	 */
	private class attackButtonListener implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			controller.attack();
		}

	}

	/**
	 * If the item button is selected, the useItem method will be called from
	 * the controller. This is not implemented yet.
	 *
	 * @see useItemButtonEvent
	 */
	private class useItemButtonListener implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		@Override
		public void actionPerformed(ActionEvent e) {

			controller.useItem();

		}
	}

	/**
	 * If the wait button is selected, the wait method of controller is called
	 * so that the map and units change, and so that that unit's ability to move
	 * is changed to false.
	 *
	 * @see waitButtonEvent
	 */
	private class waitButtonListener implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			controller.unitDoNothing();
		}

	}

	/**
	 * If the help button is selected, a help window is open that tell you the
	 * rules.
	 *
	 * @see helpButtonEvent
	 */
	private class helpButtonListener implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		@Override
		public void actionPerformed(ActionEvent e) {

			new Thread(new HelpWindow()).start();

		}

	}

	/**
	 * The listener interface for receiving unitHelpButton events. The class
	 * that is interested in processing a unitHelpButton event implements this
	 * interface, and the object created with that class is registered with a
	 * component using the component's
	 * <code>addunitHelpButtonListener<code> method. When
	 * the unitHelpButton event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see unitHelpButtonEvent
	 */
	private class unitHelpButtonListener implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		@Override
		public void actionPerformed(ActionEvent e) {

			new Thread(new UnitHelpWindow()).start();

		}

	}

	/**
	 * The listener interface for receiving itemHelpButton events. The class
	 * that is interested in processing a itemHelpButton event implements this
	 * interface, and the object created with that class is registered with a
	 * component using the component's
	 * <code>additemHelpButtonListener<code> method. When
	 * the itemHelpButton event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see itemHelpButtonEvent
	 */
	private class itemHelpButtonListener implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		@Override
		public void actionPerformed(ActionEvent e) {

			new Thread(new ItemHelpWindow()).start();

		}

	}

	/**
	 * The listener interface for receiving spaceHelpButton events. The class
	 * that is interested in processing a spaceHelpButton event implements this
	 * interface, and the object created with that class is registered with a
	 * component using the component's
	 * <code>addspaceHelpButtonListener<code> method. When
	 * the spaceHelpButton event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see spaceHelpButtonEvent
	 */
	private class spaceHelpButtonListener implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		@Override
		public void actionPerformed(ActionEvent e) {

			new Thread(new SpaceHelpWindow()).start();

		}

	}

	/**
	 * The listener interface for receiving zombieHelpButton events. The class
	 * that is interested in processing a zombieHelpButton event implements this
	 * interface, and the object created with that class is registered with a
	 * component using the component's
	 * <code>addZombieHelpButtonListener<code> method. When
	 * the zombieHelpButton event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see ZombieHelpButtonEvent
	 */
	private class ZombieHelpButtonListener implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		@Override
		public void actionPerformed(ActionEvent e) {

			new Thread(new ZombieHelpWindow()).start();

		}

	}

	/**
	 * If the end turn button is selected, the end turn method of controller is
	 * called and the team that is allowed to be used is the other team.
	 * 
	 * @see endTurnButtonEvent
	 */
	private class endTurnButtonListener implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			controller.endTurn();
		}

	}

	/**
	 * The Class thisMenuListner.
	 */
	private class thisMenuListner implements MenuListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * javax.swing.event.MenuListener#menuCanceled(javax.swing.event.MenuEvent
		 * )
		 */
		@Override
		public void menuCanceled(MenuEvent arg0) {
			// TODO Auto-generated method stub

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * javax.swing.event.MenuListener#menuDeselected(javax.swing.event.MenuEvent
		 * )
		 */
		@Override
		public void menuDeselected(MenuEvent arg0) {
			// TODO Auto-generated method stub

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * javax.swing.event.MenuListener#menuSelected(javax.swing.event.MenuEvent
		 * )
		 */
		@Override
		public void menuSelected(MenuEvent arg0) {
			// TODO Auto-generated method stub

		}

	}

	/**
	 * The listener interface for receiving zoomIn events. The class that is
	 * interested in processing a zoomIn event implements this interface, and
	 * the object created with that class is registered with a component using
	 * the component's <code>addZoomInListener<code> method. When
	 * the zoomIn event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see ZoomInEvent
	 */
	private class ZoomInListener implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		@Override
		public void actionPerformed(ActionEvent e) {

			if (((GraphicalView) graphical).getScaleFactor() == 4) {
				JOptionPane.showMessageDialog(null, "Cannot zoom in any more. It is already hard to see the map! It would make it way too hard to see the game!", "Can't Zoom In", JOptionPane.OK_OPTION);
			} else {


				((GraphicalView) graphical).setZoomInScale();
				graphical.repaint();

			}
		}

	}

	/**
	 * The listener interface for receiving zoomOut events. The class that is
	 * interested in processing a zoomOut event implements this interface, and
	 * the object created with that class is registered with a component using
	 * the component's <code>addZoomOutListener<code> method. When
	 * the zoomOut event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see ZoomOutEvent
	 */
	private class ZoomOutListener implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if (((GraphicalView) graphical).getScaleFactor() == .25) {
				JOptionPane.showMessageDialog(null, "Cannot zoom out any more. It would make it too hard to see the game!", "Can't Zoom Out", JOptionPane.OK_OPTION);
			} else {
				
				((GraphicalView) graphical).setZoomOutScale();
				
				graphical.repaint();

			}
		}
	}

	/**
	 * The listener interface for receiving toggleSoundButton events. The class
	 * that is interested in processing a toggleSoundButton event implements
	 * this interface, and the object created with that class is registered with
	 * a component using the component's
	 * <code>addtoggleSoundButtonListener<code> method. When
	 * the toggleSoundButton event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see toggleSoundButtonEvent
	 */
	private class toggleSoundButtonListener implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			Songs.toogleSound();
		}

	}

	/**
	 * Actual map. This sets up the actual map. It adds the graphical and text
	 * views, as well as keeps all of the buttons available to work.
	 * 
	 */
	private void actualMap() {

		this.removeAll();
		this.setLayout(new BorderLayout());
		revalidate();
		repaint();

		JPanel northLayout = new JPanel();
		northLayout.setLayout(new BorderLayout());
		northLayout.setOpaque(false);

		menuBar = new JMenuBar();
		menuBar.setOpaque(false);

		setUpMenuBar();

		title = new JLabel("HereAfter");
		title.setFont(new Font(Font.SERIF, Font.BOLD, 50));
		title.setForeground(Color.WHITE);
		title.setSize(250, 70);

		northLayout.add(menuBar, BorderLayout.NORTH);
		northLayout.add(title, BorderLayout.CENTER);

		this.add(northLayout, BorderLayout.NORTH);
		views = new JTabbedPane();

		scrollPanel = new JScrollPane(graphical);
		scrollPanel.setSize(862, 542);
		views.add(scrollPanel, "Graphical");

		JScrollPane scrollPanel1 = new JScrollPane(text);
		scrollPanel1.setSize(862, 542);
		views.add(scrollPanel1, "Unit Information");

		views.add(textMap, "Map");
		views.add(UnitLocations, "Unit Locations");

		this.add(views, BorderLayout.CENTER);

		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(9, 1));
		buttons.setOpaque(false);

		move = new JButton("Move");
		move.addActionListener(new moveButtonListener());
		attack = new JButton("Attack");
		attack.addActionListener(new attackButtonListener());
		item = new JButton("Use an Item");
		item.addActionListener(new useItemButtonListener());
		wait = new JButton("Wait");
		wait.addActionListener(new waitButtonListener());
		// help = new JButton("How to play");
		// help.addActionListener(new helpButtonListener());
		endTurn = new JButton("End Turn");
		endTurn.addActionListener(new endTurnButtonListener());
		// save = new JButton("Save And Quit");
		// save.addActionListener(new saveButtonListener());

		JPanel temp = new JPanel();
		temp.setOpaque(false);

		JLabel currentUser = new JLabel("Current User:");
		currentUser.setFont(new Font(Font.SERIF, Font.BOLD, 25));
		currentUser.setForeground(Color.WHITE);

		if (!dataWasLoaded) {
			currentUserName = new JLabel(userName.getText());

			currentUserName.setFont(new Font(Font.SERIF, Font.BOLD, 25));
			currentUserName.setForeground(Color.WHITE);

		}
		buttons.add(currentUser);
		if (!dataWasLoaded) {
			buttons.add(currentUserName);
		}
		// buttons.add(help);
		buttons.add(move);
		buttons.add(attack);
		buttons.add(item);
		buttons.add(wait);
		buttons.add(endTurn);
		// buttons.add(save);

		this.add(buttons, BorderLayout.WEST);
		if (dataWasLoaded) {
			revalidate();
			repaint();
		}
	}

	/**
	 * Sets the up menu bar.
	 */
	private void setUpMenuBar() {

		saveMenu = new JMenu("Save");
		saveMenu.addMenuListener(new thisMenuListner());
		saveMenu.setForeground(Color.WHITE);
		saveMenu.setFont(new Font(Font.SERIF, Font.PLAIN, 15));
		menuBar.add(saveMenu);

		saveAndContinue = new JMenuItem("Save and Continue", new ImageIcon(floppy));
		saveAndContinue.addActionListener(new saveContinueButtonListener());
		saveMenu.add(saveAndContinue);

		saveAndQuit = new JMenuItem("Save and Quit", new ImageIcon(floppy));
		saveAndQuit.addActionListener(new saveQuitButtonListener());
		saveMenu.add(saveAndQuit);

		zoom = new JMenu("Zoom");
		zoom.addMenuListener(new thisMenuListner());
		zoom.setForeground(Color.WHITE);
		zoom.setFont(new Font(Font.SERIF, Font.PLAIN, 15));

		menuBar.add(zoom);

		zoomIn = new JMenuItem("Zoom In", new ImageIcon(zoomInImg));
		zoomIn.addActionListener(new ZoomInListener());
		zoom.add(zoomIn);

		zoomOut = new JMenuItem("Zoom Out", new ImageIcon(zoomOutImg));
		zoomOut.addActionListener(new ZoomOutListener());
		zoom.add(zoomOut);

		sound = new JMenu("Sound");
		sound.addMenuListener(new thisMenuListner());
		sound.setForeground(Color.WHITE);
		sound.setFont(new Font(Font.SERIF, Font.PLAIN, 15));
		menuBar.add(sound);

		toggleSound = new JMenuItem("Toggle Sound", new ImageIcon(toggleSoundIcon));
		toggleSound.addActionListener(new toggleSoundButtonListener());
		sound.add(toggleSound);

		helpMenu = new JMenu("Help");
		helpMenu.addMenuListener(new thisMenuListner());
		helpMenu.setForeground(Color.WHITE);
		helpMenu.setFont(new Font(Font.SERIF, Font.PLAIN, 15));
		menuBar.add(helpMenu);

		helpWindow = new JMenuItem("General Help", new ImageIcon(helpIcon));
		helpWindow.addActionListener(new helpButtonListener());
		helpMenu.add(helpWindow);

		gamePlayHelp = new JMenu("Game Play Help");
		gamePlayHelp.addMenuListener(new thisMenuListner());
		helpMenu.add(gamePlayHelp);

		unitHelp = new JMenuItem("Unit Help", new ImageIcon(unitHelpIcon));// new
																			// ImageIcon(helpIcon));
		unitHelp.addActionListener(new unitHelpButtonListener());
		gamePlayHelp.add(unitHelp);

		zombieHelp = new JMenuItem("Zombie Help", new ImageIcon(zombieHelpIcon));
		zombieHelp.addActionListener(new ZombieHelpButtonListener());
		gamePlayHelp.add(zombieHelp);

		itemHelp = new JMenuItem("Item Help", new ImageIcon(itemHelpIcon));// new
																			// ImageIcon(helpIcon));
		itemHelp.addActionListener(new itemHelpButtonListener());
		gamePlayHelp.add(itemHelp);

		spaceHelp = new JMenuItem("Space Help", new ImageIcon(spaceHelpIcon));// new
																				// ImageIcon(helpIcon));
		spaceHelp.addActionListener(new spaceHelpButtonListener());
		gamePlayHelp.add(spaceHelp);
	}

	/**
	 * Register listeners. This registers the mouse listeners.
	 */
	private void registerListeners() {

		MouseListener listener = new ListenToMouse();
		MouseMotionListener motionListener = new ListenToMouse();

		this.addMouseMotionListener(motionListener);
		this.addMouseListener(listener);

	}

	/**
	 * This paintComponent changes depending on what the splash screen is. If it
	 * is the screen for the user to select their units, then one of each of the
	 * units are drawn. This does not happen however if the splash screen is one
	 * of the other 2 set up screens to select to have a new game or select the
	 * level.
	 *
	 * @param g
	 *            the g
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D gr = (Graphics2D) g;
		if (gameIsRunning) {
			Toolkit tk = Toolkit.getDefaultToolkit();
			Dimension d = tk.getScreenSize();

			validate();
			gr.drawImage(background, 0, 0, d.width, d.height, null);
			selectUnits = false;
			startUp1 = false;
			selectLevel = false;
			selectType = false;
			loadGame = false;

		}

		if (selectUnits) {
			gr.drawImage(background, 0, 0, null);
			if (selected) {
				gr.drawImage(doctor, 35, 260, null);
				gr.drawImage(engineer, 340, 435, null);
				gr.drawImage(ranger, 430, 260, null);
				gr.drawImage(sniper, 125, 435, null);
				gr.drawImage(soldier, 235, 260, null);
			}

		} else if (loadGame) {
			gr.drawImage(loadPage, 0, 0, null);
		} else if (startUp1) {
			gr.drawImage(setUp1, 0, 0, null);
		} else if (selectLevel) {
			gr.drawImage(setUpLevel, 0, 0, null);
		} else if (selectType) {
			gr.drawImage(setUpType, 0, 0, null);
		}

	}

	/**
	 * Adds a listener to the mouse.
	 */
	private class ListenToMouse implements MouseMotionListener, MouseListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
		 */
		public void mouseClicked(MouseEvent evt) {

		}

		/**
		 * If the mouse is pressed, it keeps track of what step in the set up
		 * the gui is at. Depending on that, it will change the image that is in
		 * the background, and what options are able to be selected.
		 *
		 * @param e
		 *            the e
		 */
		@Override
		public void mousePressed(MouseEvent e) {
			int clickX = e.getX();
			int clickY = e.getY();

			if (startUp1) {

				if (clickX > 685 && clickX < 870 && clickY > 190 && clickY < 230) {

					// This means there is a new game
					// go to the second page with the level options
					selectType = true;
					startUp1 = false;

					repaint();
				} else if (clickX > 650 && clickX < 940 && clickY > 250 && clickY < 290) {
					// This means they want to continue their game

					loadGame = true;
					startUp1 = false;
					selectLevel = false;
					selectType = false;
					gameIsRunning = false;
					repaint();

				} else if (clickX > 750 && clickX < 850 && clickY > 310 && clickY < 350) {
					// This means they want to quit the game

					System.exit(0);
				}
			} else if (selectType) {
				if (clickX > 630 && clickX < 960 && clickY > 230 && clickY < 270) {
					// This means they select "Capture the tower"
					// go to the page with the level options
					selectType = false;
					selectLevel = true;
					type = "tower";
					repaint();

				} else if (clickX > 635 && clickX < 960 && clickY > 280 && clickY < 320) {
					// This means they select "Seize the corners"
					selectType = false;
					selectLevel = true;
					type = "corner";
					repaint();

				} else if (clickX > 710 && clickX < 880 && clickY > 330 && clickY < 370) {
					// This means they select "Survival"
					selectType = false;
					selectLevel = true;
					type = "survive";
					repaint();

				}

				((MapView) textMap).setGameType(type);

			}

			else if (selectLevel) {
				if (clickX > 750 && clickX < 850 && clickY > 235 && clickY < 275) {
					// This means the difficulty is easy
					difficulty = Difficulty.EASY;
					selectUnit();
					selectLevel = false;
					selectUnits = true;
					repaint();
				} else if (clickX > 725 && clickX < 875 && clickY > 290 && clickY < 320) {
					// This means the difficulty is medium
					difficulty = Difficulty.EASY;
					selectUnit();
					selectLevel = false;
					selectUnits = true;
					repaint();
				} else if (clickX > 740 && clickX < 840 && clickY > 340 && clickY < 380) {
					// This means the difficulty is hard
					difficulty = Difficulty.EASY;
					selectUnit();
					selectLevel = false;
					selectUnits = true;
					repaint();
				}
			} else if (loadGame) {
				if (clickX > 58 && clickX < 432 && clickY > 210 && clickY < 255) {
					LoadData("SaveStateOne");
				} else if (clickX > 58 && clickX < 440 && clickY > 315 && clickY < 358) {
					LoadData("SaveStateTwo");
				} else if (clickX > 58 && clickX < 492 && clickY > 415 && clickY < 466) {
					LoadData("SaveStateThree");
				} else if (clickX > 61 && clickX < 503 && clickY > 522 && clickY < 557) {
					gameIsRunning = false;
					startUp1 = true;
					TRPGGUI.setdontAskAgain(true);
					Songs.setPlaying(true);
					Songs.toogleSound();
					TRPGGUI.dispose();
				}

			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
		 */
		@Override
		public void mouseReleased(MouseEvent e) {

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
		 */
		@Override
		public void mouseEntered(MouseEvent e) {

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
		 */
		@Override
		public void mouseExited(MouseEvent e) {

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent
		 * )
		 */
		@Override
		public void mouseDragged(MouseEvent e) {

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent
		 * )
		 */
		@Override
		public void mouseMoved(MouseEvent e) {

		}

	}

	/**
	 * This update is called through the Map. Every time something happens, the
	 * map calls update, and the username that is in the upper hand corner
	 * changes so that it is the current player.
	 *
	 * @param o
	 *            the o
	 * @param arg
	 *            the arg
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (!dataWasLoaded) {
			currentUserName.setText(controller.getCurrPlayerName());

		}

		repaint();
	}

	/**
	 * This just returns if the game is running or not.
	 * 
	 * @return gameIsRunning
	 */
	public boolean getGameIsRunning() {
		// TODO Auto-generated method stub
		return gameIsRunning;
	}

	/**
	 * The listener interface for receiving saveQuitButton events. The class
	 * that is interested in processing a saveQuitButton event implements this
	 * interface, and the object created with that class is registered with a
	 * component using the component's
	 * <code>addsaveQuitButtonListener<code> method. When
	 * the saveQuitButton event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see saveQuitButtonEvent
	 */
	private class saveQuitButtonListener implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		@Override
		public void actionPerformed(ActionEvent e) {

			saveTheGame(true);
		}

	}

	/**
	 * The listener interface for receiving saveContinueButton events. The class
	 * that is interested in processing a saveContinueButton event implements
	 * this interface, and the object created with that class is registered with
	 * a component using the component's
	 * <code>addsaveContinueButtonListener<code> method. When
	 * the saveContinueButton event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see saveContinueButtonEvent
	 */
	private class saveContinueButtonListener implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		@Override
		public void actionPerformed(ActionEvent e) {

			saveTheGame(false);
		}

	}

	/**
	 * Save the game.
	 *
	 * @param quit
	 *            the quit
	 */
	public void saveTheGame(boolean quit) {

		Object[] options = { "Save 1", "Save 2", "Save 3", "Cancel" };
		int answer = JOptionPane.showOptionDialog(null, "Where would you like to save?", "Save Game?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
		int slotNumber = 0;

		if (answer == JOptionPane.YES_OPTION) {
			saveData("SaveStateOne");
			slotNumber = 1;

		} else if (answer == JOptionPane.NO_OPTION) {
			saveData("SaveStateTwo");
			slotNumber = 2;
		} else if (answer == JOptionPane.CANCEL_OPTION) {
			saveData("SaveStateThree");
			slotNumber = 3;
		} else {
			quit = false;
		}

		if (quit) {

			this.removeAll();
			gameIsRunning = false;
			startUp1 = true;
			if (slotNumber == 1 || slotNumber == 2 || slotNumber == 3)
				JOptionPane.showMessageDialog(null, "Game Saved Successfully! Saved in save state " + slotNumber, "Game Saved", JOptionPane.INFORMATION_MESSAGE);
			slotNumber = 0;
			TRPGGUI.setdontAskAgain(true);
			Songs.setPlaying(true);
			Songs.toogleSound();
			TRPGGUI.dispose();

		}
		if (slotNumber == 1 || slotNumber == 2 || slotNumber == 3)
			JOptionPane.showMessageDialog(null, "Game Saved Successfully! Saved in save state " + slotNumber, "Game Saved", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Save data.
	 *
	 * @param saveName
	 *            the save name
	 */
	private void saveData(String saveName) {
		try {

			scaleFactor = ((GraphicalView) graphical).getScaleFactor();
			if (saveName.equals("SaveStateOne")) {
				FileOutputStream outStream = new FileOutputStream("SaveStateOne.data");
				ObjectOutputStream outObject = new ObjectOutputStream(outStream);
				outObject.writeObject(controller);
				outObject.writeObject(type);
				outObject.writeObject(player);
				outObject.writeObject(scaleFactor);
				outObject.close();
			}
			if (saveName.equals("SaveStateTwo")) {
				FileOutputStream outStream = new FileOutputStream("SaveStateTwo.data");
				ObjectOutputStream outObject = new ObjectOutputStream(outStream);
				outObject.writeObject(controller);
				outObject.writeObject(type);
				outObject.writeObject(player);
				outObject.writeObject(scaleFactor);
				outObject.close();
			}
			if (saveName.equals("SaveStateThree")) {
				FileOutputStream outStream = new FileOutputStream("SaveStateThree.data");
				ObjectOutputStream outObject = new ObjectOutputStream(outStream);
				outObject.writeObject(controller);
				outObject.writeObject(type);
				outObject.writeObject(player);
				outObject.writeObject(scaleFactor);
				outObject.close();
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	/**
	 * Load data.
	 *
	 * @param saveStateName
	 *            the save state name
	 */
	public void LoadData(String saveStateName) {
		try {

			if (saveStateName.equals("SaveStateOne")) {
				FileInputStream inStream = new FileInputStream("SaveStateOne.data");
				ObjectInputStream inObject = new ObjectInputStream(inStream);
				controller = (GameController) inObject.readObject();
				type = (String) inObject.readObject();
				player = (Player) inObject.readObject();
				scaleFactor = (double) inObject.readObject();
			}

			if (saveStateName.equals("SaveStateTwo")) {
				FileInputStream inStream = new FileInputStream("SaveStateTwo.data");
				ObjectInputStream inObject = new ObjectInputStream(inStream);
				controller = (GameController) inObject.readObject();
				type = (String) inObject.readObject();
				player = (Player) inObject.readObject();
				scaleFactor = (double) inObject.readObject();
			}

			if (saveStateName.equals("SaveStateThree")) {
				FileInputStream inStream = new FileInputStream("SaveStateThree.data");
				ObjectInputStream inObject = new ObjectInputStream(inStream);
				controller = (GameController) inObject.readObject();
				type = (String) inObject.readObject();
				player = (Player) inObject.readObject();
				scaleFactor = (double) inObject.readObject();
			}

			controller.getMap().addObserver((Observer) graphical);
			((GraphicalView) graphical).setController(controller);
			controller.getMap().addObserver((Observer) text);
			((TextView) text).setController(controller);
			controller.getMap().addObserver((Observer) UnitLocations);
			controller.setCurrentPlayer(player);
			controller.setPlayerTurn(true);
			((MapView) textMap).setGameType(type);
			((GraphicalView) graphical).setScaleFactor(scaleFactor);
			controller.setGraphicalView((GraphicalView) graphical);
			((GraphicalView) graphical).setGameType(type);

			gameIsRunning = true;
			dataWasLoaded = true;
			selected = false;
			actualMap();
			TRPGGUI.canResize();

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Unable to load the selected data. There is either no save in this slot or the data is currupt.", "Unable to load Data", JOptionPane.WARNING_MESSAGE);

		}

	}

	/**
	 * Sets the testing mode.
	 *
	 * @param testingMode
	 *            the testingMode to set
	 */
	public void setTestingMode(boolean testingMode) {
		this.testingMode = testingMode;
	}

}