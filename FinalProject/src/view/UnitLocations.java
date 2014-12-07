package view;

import java.awt.Color;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class UnitLocations extends JPanel implements Observer {

	private JTextArea textMap;
	private String stringOfMap;
	private JScrollPane scrollPanel;

	public UnitLocations() {
		textMap = new JTextArea();
		textMap.setText(stringOfMap);
		textMap.setBackground(Color.WHITE);
		textMap.setFont(new Font("Courier", Font.BOLD, 10));
		textMap.setLocation(0, 0);
		textMap.setEditable(false);
		scrollPanel = new JScrollPane(textMap);
		add(scrollPanel);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		stringOfMap = arg0.toString();

		textMap.setText(stringOfMap.substring(0, 250));
		System.getProperty("line.separator");
		textMap.append(stringOfMap.substring(250, 251));
		System.getProperty("line.separator");
		textMap.append(stringOfMap.substring(251, 502));
		System.getProperty("line.separator");
		textMap.append(stringOfMap.substring(502, 753));
		System.getProperty("line.separator");
		textMap.append(stringOfMap.substring(753, 1004));
		System.getProperty("line.separator");
		textMap.append(stringOfMap.substring(1004, 1255));
		System.getProperty("line.separator");
		textMap.append(stringOfMap.substring(1255, 1506));
		System.getProperty("line.separator");
		textMap.append(stringOfMap.substring(1506, 1757));
		System.getProperty("line.separator");
		textMap.append(stringOfMap.substring(1757, 2008));
		System.getProperty("line.separator");
		textMap.append(stringOfMap.substring(2008, 2259));

		System.getProperty("line.separator");
		textMap.append(stringOfMap.substring(2259, 2510));
		System.getProperty("line.separator");
		textMap.append(stringOfMap.substring(2510, 2761));
		System.getProperty("line.separator");
		textMap.append(stringOfMap.substring(2761, 3012));
		System.getProperty("line.separator");
		textMap.append(stringOfMap.substring(3012, 3263));
		System.getProperty("line.separator");
		textMap.append(stringOfMap.substring(3263, 3514));
		System.getProperty("line.separator");
		textMap.append(stringOfMap.substring(3514, 3765));
		System.getProperty("line.separator");
		textMap.append(stringOfMap.substring(3765, 4016));
		System.getProperty("line.separator");
		textMap.append(stringOfMap.substring(4016, 4267));
		System.getProperty("line.separator");
		textMap.append(stringOfMap.substring(4267, 4518));

		System.getProperty("line.separator");
		textMap.append(stringOfMap.substring(4518, 4769));
		System.getProperty("line.separator");
		textMap.append(stringOfMap.substring(4769, 5020));
		System.getProperty("line.separator");
		textMap.append(stringOfMap.substring(5271, 5522));
		System.getProperty("line.separator");
		textMap.append(stringOfMap.substring(5522, 5773));
		System.getProperty("line.separator");
		textMap.append(stringOfMap.substring(5773, 6024));
		System.getProperty("line.separator");
		textMap.append(stringOfMap.substring(6024, 6275));
		System.getProperty("line.separator");
		textMap.append(stringOfMap.substring(6275, 6526));
		System.getProperty("line.separator");
		textMap.append(stringOfMap.substring(6526, 6777));
		System.getProperty("line.separator");
		textMap.append(stringOfMap.substring(6777, 7028));

		System.getProperty("line.separator");
		textMap.append(stringOfMap.substring(7028, 7279));
		System.getProperty("line.separator");
		textMap.append(stringOfMap.substring(7279, 7530));
		System.getProperty("line.separator");
		textMap.append(stringOfMap.substring(7530, 7781));
		System.getProperty("line.separator");
		textMap.append(stringOfMap.substring(7781, 8032));
		System.getProperty("line.separator");
		textMap.append(stringOfMap.substring(8032, 8283));
		System.getProperty("line.separator");
		textMap.append(stringOfMap.substring(8283, 8534));
		System.getProperty("line.separator");
		textMap.append(stringOfMap.substring(8534, 8785));
		System.getProperty("line.separator");
		textMap.append(stringOfMap.substring(8785, 9036));
		System.getProperty("line.separator");
		textMap.append(stringOfMap.substring(9036, 9287));

		System.getProperty("line.separator");
		textMap.append(stringOfMap.substring(9287, 9538));
		System.getProperty("line.separator");
		textMap.append(stringOfMap.substring(9538, 9789));
		System.getProperty("line.separator");
		textMap.append(stringOfMap.substring(9789, 10040));
		System.getProperty("line.separator");
		textMap.append(stringOfMap.substring(10040, 10291));
		System.getProperty("line.separator");
		textMap.append(stringOfMap.substring(10291, 10542));
		System.getProperty("line.separator");
		textMap.append(stringOfMap.substring(10542, 10793));
		System.getProperty("line.separator");
		textMap.append(stringOfMap.substring(10793, 11044));
		System.getProperty("line.separator");
		textMap.append(stringOfMap.substring(11044, 11295));
		System.getProperty("line.separator");
		textMap.append(stringOfMap.substring(11295, 11546));

		System.getProperty("line.separator");
		textMap.append(stringOfMap.substring(11546, 11797));
		System.getProperty("line.separator");
		textMap.append(stringOfMap.substring(11797, 12048));
		System.getProperty("line.separator");
		textMap.append(stringOfMap.substring(12048, 12299));
		// System.getProperty("line.separator");
		textMap.append(stringOfMap.substring(12299, 12550));

	}

}
