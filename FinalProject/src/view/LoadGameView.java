package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class LoadGameView extends JPanel{

	public LoadGameView() {

		Dimension maxSize = new Dimension(4800, 4800);
		this.setPreferredSize(maxSize);
		this.setVisible(true);
		this.setBackground(Color.RED);
	}

}
