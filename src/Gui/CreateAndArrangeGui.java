package Gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import Gui.Listeners.NextFrameResetListener;
import Gui.Listeners.SpeedChangeListener;
import Gui.Listeners.StartStopListener;
import Map.Cell;

/**
 * 
 * @author philip
 * 
 *         Button Area at the bottom with start stop of simulation. Label/input
 *         data area on the right for changing enviroment variables and
 *         outputting data.
 * 
 */
public class CreateAndArrangeGui {

	private JPanel mainPanel;
	private JSplitPane splitPane;
	private static JButton forward;
	private static JLabel speedLabel;

	protected CreateAndArrangeGui(GuiMain guiMain) {

		mainPanel = new JPanel(new GridLayout(20, 1));
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		speedLabel = new JLabel("100ms");
		guiMain.add(splitPane);

		createMapArea();
		createStartStop();
		createSpeedButtons();
		createFrameButtons();
		createResetButton();
		createLabel();

	}

	private void createLabel() {

		mainPanel.add(speedLabel);

	}

	private void createResetButton() {

		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new NextFrameResetListener());

		mainPanel.add(resetButton);

	}

	private void createFrameButtons() {

		forward = new JButton(">");
		forward.addActionListener(new NextFrameResetListener());

		mainPanel.add(forward);

	}

	private void createSpeedButtons() {

		JButton faster = new JButton("Faster");
		faster.addActionListener(new SpeedChangeListener());

		JButton slower = new JButton("Slower");
		slower.addActionListener(new SpeedChangeListener());

		mainPanel.add(faster);
		mainPanel.add(slower);
	}

	private void createMapArea() {
		splitPane.add(GuiMain.mapDisplay);

	}

	private void createStartStop() {

		splitPane.add(mainPanel);

		JButton startButton = new JButton("Start");
		startButton.addActionListener(new StartStopListener());
		mainPanel.add(startButton);

		JButton pauseButton = new JButton("Pause");
		pauseButton.addActionListener(new StartStopListener());
		mainPanel.add(pauseButton);

	}

	@SuppressWarnings({ "unused" })
	private CreateAndArrangeGui() {

	}

	public static void setSpeed(int speed) {

		speedLabel.setText(speed + "ms");

	}

	public static void enableForwardButton() {
		forward.setEnabled(true);
	}

	public static void disableForwardButton() {
		forward.setEnabled(false);
	}

}
