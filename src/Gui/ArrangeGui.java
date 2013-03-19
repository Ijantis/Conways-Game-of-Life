package Gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
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

	protected ArrangeGui(GuiMain guiMain) {

		mainPanel = new JPanel(new GridLayout(20, 1));
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		guiMain.add(splitPane);

		createMapArea();
		createStartStop();
		createSpeedButtons();
		createFrameButtons();
		createResetButton();
		createCellEditButton();

	}

	private void createCellEditButton() {

		final JButton createCells = new JButton("Create");
		final JButton destroyCells = new JButton("Destroy");

		createCells.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				createCells.setEnabled(false);
				destroyCells.setEnabled(true);
				MapDisplay.currentCellEdit = Cell.Alive;
			}
		});

		destroyCells.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				createCells.setEnabled(true);
				destroyCells.setEnabled(false);
				MapDisplay.currentCellEdit = Cell.Dead;
			}
		});
		destroyCells.setEnabled(false);

		mainPanel.add(createCells);
		mainPanel.add(destroyCells);

	}

	private void createResetButton() {

		JButton resetButton = new JButton("Reset");

		resetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				MapDisplay.data.reset();
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

				Main.Main.nextFrame();

			}
		});

		// backward = new JButton("<");
		//
		// backward.addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// Main.Main.previousFrame();
		// }
		// });
		//
		// JPanel temp = new JPanel(new FlowLayout());
		// temp.add(backward);
		// temp.add(forward);
		mainPanel.add(forward);

	}

	private void createSpeedButtons() {

		JButton faster = new JButton("Faster");
		faster.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Main.Main.setSpeed(-10);
			}
		});

		JButton slower = new JButton("Slower");
		slower.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Main.Main.setSpeed(10);
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

				Main.Main.isPaused = false;
				forward.setEnabled(false);
				// backward.setEnabled(false);

			}
		});
		mainPanel.add(startButton);

		JButton pauseButton = new JButton("Pause");
		pauseButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				Main.Main.isPaused = true;
				forward.setEnabled(true);
				// backward.setEnabled(true);

			}
		});
		mainPanel.add(pauseButton);

	}

	@SuppressWarnings({ "unused" })
	private ArrangeGui() {

	}

}
