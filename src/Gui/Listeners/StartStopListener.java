package Gui.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Gui.ArrangeGui;

public class StartStopListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		switch (arg0.getActionCommand()) {
		case "Start":
			Main.GameLoop.isPaused = false;
			ArrangeGui.disableForwardButton();
			break;

		case "Pause":
			Main.GameLoop.isPaused = true;
			ArrangeGui.enableForwardButton();
			break;
		}

	}

}
