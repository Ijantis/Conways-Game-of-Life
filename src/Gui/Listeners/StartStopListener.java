package Gui.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Gui.CreateAndArrangeGui;

public class StartStopListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		switch (arg0.getActionCommand()) {
		case "Start":
			Main.GameLoop.isPaused = false;
			CreateAndArrangeGui.disableForwardButton();
			break;

		case "Pause":
			Main.GameLoop.isPaused = true;
			CreateAndArrangeGui.enableForwardButton();
			break;
		}

	}

}
