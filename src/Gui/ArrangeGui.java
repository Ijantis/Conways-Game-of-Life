package Gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

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
public class ArrangeGui {

	JPanel mainPanel;
	JSplitPane splitPane;
	// private static JButton backward;
	private static JButton forward;
	private static JLabel speedLabel;

	protected ArrangeGui(GuiMain guiMain) {

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

		resetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				MapDisplay.currentGrid.reset();
				GuiMain.mapDisplay.repaint();

			}
		});

		mainPanel.add(resetButton);

	}

	private void createFrameButtons() {

		forward = new JButton(">");

		forward.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				Main.GameLoop.nextFrame();

			}
		});

		mainPanel.add(forward);

	}

	private void createSpeedButtons() {

		JButton faster = new JButton("Faster");
		faster.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Main.GameLoop.changeSpeed(-10);
			}
		});

		JButton slower = new JButton("Slower");
		slower.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Main.GameLoop.changeSpeed(10);
			}
		});

		mainPanel.add(faster);
		mainPanel.add(slower);
	}

	private void createMapArea() {
		splitPane.add(GuiMain.mapDisplay);

	}

	private void createStartStop() {

		splitPane.add(mainPanel);

		JButton startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Main.GameLoop.isPaused = false;
				forward.setEnabled(false);

			}
		});
		mainPanel.add(startButton);

		JButton pauseButton = new JButton("Pause");
		pauseButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				Main.GameLoop.isPaused = true;
				forward.setEnabled(true);

			}
		});
		mainPanel.add(pauseButton);

	}

	@SuppressWarnings({ "unused" })
	private ArrangeGui() {

	}

	public static void setSpeed(int speed) {
		
		speedLabel.setText(speed + "ms");
		
	}

}
