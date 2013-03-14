package Gui;

import java.awt.Dimension;

import javax.swing.JFrame;

public class GuiMain extends JFrame {

	MapDisplay mapDisplay;

	public GuiMain() {
		super("Life Simulation");

		initialiseMap();
		new ArrangeGui(this);
		initialiseFrame();
	}

	private void initialiseMap() {

		mapDisplay = new MapDisplay();
		mapDisplay.setPreferredSize(new Dimension(200, 200));

	}

	private void initialiseFrame() {

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		pack();

	}

}
