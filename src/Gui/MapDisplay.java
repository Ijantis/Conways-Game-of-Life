package Gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import Map.Cell;
import Map.Grid;

@SuppressWarnings("serial")
public class MapDisplay extends JPanel implements MouseListener,
		MouseMotionListener {

	public static Grid currentGrid;
	protected static int currentCellEdit = Cell.Alive;
	private final int scale = 5;

	public MapDisplay() {
		addMouseListener(this);
		addMouseMotionListener(this);
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
		MapDisplay.currentGrid = data;
		this.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

		//left mouse button
		if (arg0.getButton() == 1) {
			currentCellEdit = Cell.Alive;
		} else {
			currentCellEdit = Cell.Dead;
		}

		currentGrid.setCellValue(arg0.getX() / scale, arg0.getY() / scale, currentCellEdit);
		this.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	
		//right click
		if (arg0.getButton() == 3) {
			currentCellEdit = Cell.Dead;
		} else if (arg0.getButton() == 1) {
			currentCellEdit = Cell.Alive;
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		//right click
		if (arg0.getButton() == 3) {
			currentCellEdit = Cell.Alive;
		}
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {

		currentGrid.setCellValue(arg0.getX() / scale, arg0.getY() / scale, currentCellEdit);
		this.repaint();

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
