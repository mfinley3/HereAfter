package view;

import java.awt.GridLayout;

import javax.swing.JFrame;

public class SpaceHelpWindow extends JFrame implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
	
	public SpaceHelpWindow(){
		this.setSize(400, 750);
		this.setVisible(true);
		this.setLocation(900, 0);
		this.setTitle("HereAfter: Space Help Screen");	
		this.setLayout(new GridLayout(7,1));
	}

}
