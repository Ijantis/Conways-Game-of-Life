package Gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

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

	GuiMain guiMain;
	JPanel mainPanel;
	JSplitPane splitPane;

	protected ArrangeGui(GuiMain guiMain) {

		this.guiMain = guiMain;
		mainPanel = new JPanel(new GridLayout(20, 1));
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		guiMain.add(splitPane);

		createMapArea();
		createStartStop();
		createSpeedButtons();

	}

	private void createSpeedButtons() {

		JButton faster = new JButton("Faster");
		faster.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Main.Main.getSpeed(-10);
			}
		});

		JButton slower = new JButton("Slower");
		slower.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Main.Main.getSpeed(10);
			}
		});

		mainPanel.add(faster);
		mainPanel.add(slower);
	}

	private void createMapArea() {
		splitPane.add(guiMain.mapDisplay);

	}

	private void createStartStop() {

		splitPane.add(mainPanel);

		JButton startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Main.Main.isPaused = false;

			}
		});
		mainPanel.add(startButton);

		JButton pauseButton = new JButton("Pause");
		pauseButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				Main.Main.isPaused = true;

			}
		});
		mainPanel.add(pauseButton);

	}

	@SuppressWarnings({ "unused" })
	private ArrangeGui() {

	}

}
