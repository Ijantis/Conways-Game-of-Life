package Gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import Gui.Listeners.MouseCellListener;
import Map.Cell;
import Map.Grid;

@SuppressWarnings("serial")
public class MapDisplay extends JPanel {

	public static Grid currentGrid;
	private static int currentCellDrawType = Cell.Alive;
	private static final int drawingScale = 4;
	private static MapDisplay instance;

	@Override
	public void paintComponent(Graphics g) {

		drawCells(g);
		drawGrid(g);

	}

	private void drawCells(Graphics g) {

		for (int x = 0; x < currentGrid.getWidth(); x++) {
			for (int y = 0; y < currentGrid.getHeight(); y++) {
				if (currentGrid.getCellValue(x, y) == Cell.Dead) {
					g.setColor(Color.white);
					g.fillRect(x * drawingScale, y * drawingScale, drawingScale, drawingScale);
				} else {
					g.setColor(Color.black);
					g.fillRect(x * drawingScale, y * drawingScale, drawingScale, drawingScale);
				}
			}
		}

	}

	private void drawGrid(Graphics g) {

		g.setColor(Color.gray);
		for (int x = 0; x < currentGrid.getWidth(); x++) {
			for (int y = 0; y < currentGrid.getHeight(); y++) {
				g.drawRect(x * drawingScale, y * drawingScale, drawingScale, drawingScale);
			}
		}

	}

	public void updateMap(Grid data) {
		currentGrid = Grid.getInstance();
		this.repaint();
	}

	public static void setCellDrawType(int edit) {
		currentCellDrawType = edit;
	}

	public static int getCellDrawType() {
		return currentCellDrawType;
	}

	public static MapDisplay getInstance() {

		if (instance == null) {
			instance = new MapDisplay();
		}

		return instance;
	}

	private MapDisplay() {
		addMouseListener(new MouseCellListener());
		addMouseMotionListener(new MouseCellListener());
		currentGrid = Grid.getInstance();
	}

	public static int getScale() {
		return drawingScale;
	}
}
