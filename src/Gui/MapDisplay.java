package Gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import Gui.Listeners.MouseCellListener;
import Map.Cell;
import Map.Grid;

@SuppressWarnings("serial")
public class MapDisplay extends JPanel {

	public static Grid currentGrid;
	private static int currentCellEdit = Cell.Alive;
	private static final int scale = 5;
	private static MapDisplay instance;

	private MapDisplay() {
		addMouseListener(new MouseCellListener());
		addMouseMotionListener(new MouseCellListener());
		currentGrid = Grid.getInstance();
	}

	@Override
	public void paintComponent(Graphics g) {

		

		for (int x = 0; x < currentGrid.getWidth(); x++) {
			for (int y = 0; y < currentGrid.getHeight(); y++) {
				if (currentGrid.getCellValue(x, y) == Cell.Dead) {
					g.setColor(Color.white);
					g.fillRect(x * scale, y * scale, scale, scale);
				} else {
					g.setColor(Color.black);
					g.fillRect(x * scale, y * scale, scale, scale);
				}
			}
		}

		g.setColor(Color.gray);
		for (int x = 0; x < currentGrid.getWidth(); x++) {
			for (int y = 0; y < currentGrid.getHeight(); y++) {
				g.drawRect(x * scale, y * scale, scale, scale);
			}
		}

	}

	public void updateMap(Grid data) {
		currentGrid = Grid.getInstance();
		this.repaint();
	}
	
	public static void setCellEdit(int edit) {
		currentCellEdit = edit;
	}
	
	public static int getCellEdit() {
		return currentCellEdit;
	}

	public static MapDisplay getInstance() {
		
		if (instance == null) {
			instance = new MapDisplay();
		}
		
		return instance;
	}
	
	public static int getScale() {
		return scale;
	}
}
