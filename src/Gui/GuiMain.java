package Gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import Map.Grid;

@SuppressWarnings("serial")
public class GuiMain extends JFrame {

	public static MapDisplay mapDisplay; // JPanel

	public GuiMain(Grid myGrid) {
		super("Life Simulation");

		initialiseMap(myGrid);
		new CreateAndArrangeGui(this);
		initialiseFrame();
	}

	private void initialiseMap(Grid myGrid) {

		mapDisplay = MapDisplay.getInstance();
		mapDisplay.setPreferredSize(new Dimension(myGrid.getWidth()
				* MapDisplay.getScale(), myGrid.getHeight()
				* MapDisplay.getScale()));
		mapDisplay.updateMap(myGrid);

	}

	private void initialiseFrame() {

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		pack();

	}

	public static void redrawMap(Grid currentGrid) {

		mapDisplay.updateMap(currentGrid);

	}

}
