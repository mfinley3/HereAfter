package view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MenuBar extends JPanel {

	JMenuBar menuBar;
	JMenu zoom, exit;
	JMenuItem zoomIn, ZoomOut;
	
	public MenuBar()  {

		menuBar = new JMenuBar();
		zoom = new JMenu("Zoom");
		
		
	}

}
