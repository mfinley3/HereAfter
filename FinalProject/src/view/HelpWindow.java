package view;

import javax.swing.JFrame;

public class HelpWindow extends JFrame implements Runnable {

	@Override
	public void run() {
		
	}
	
	public HelpWindow() {

		this.setSize(350, 660);
		this.setVisible(true);
		this.setLocation(1105, 0);
		
		setTitle("HereAfterHelp");	

		
	}

}
