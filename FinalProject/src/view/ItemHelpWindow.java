package view;

import java.awt.GridLayout;

import javax.swing.JFrame;

public class ItemHelpWindow extends JFrame implements Runnable {

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
		
	}
}
