package Gui.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Gui.GuiMain;
import Gui.MapDisplay;
import Map.Grid;

public class NextFrameResetListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {

		switch (arg0.getActionCommand()) {

		case "Reset":
			Grid.getInstance().reset();
			GuiMain.mapDisplay.repaint();
			break;

		case ">":
			Main.GameLoop.nextFrame();
			break;
		}

		Main.GameLoop.nextFrame();
	}

}
