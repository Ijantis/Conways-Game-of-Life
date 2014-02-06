package Gui.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpeedChangeListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {

		switch (arg0.getActionCommand()) {
		case "Faster":
			Main.GameLoop.incrementSpeed(-10);
			break;

		case "Slower":
			Main.GameLoop.incrementSpeed(10);
			break;
		}

	}

}
