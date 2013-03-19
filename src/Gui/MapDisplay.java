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

	protected static Grid data;
	protected static int currentCellEdit = Cell.Alive;

	public MapDisplay() {
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	@Override
	public void paintComponent(Graphics g) {

		for (int x = 0; x < data.getWidth(); x++) {
			for (int y = 0; y < data.getHeight(); y++) {
				if (Grid.getCellValue(x, y) == Cell.Dead) {
					g.setColor(Color.white);
					g.fillRect(x * 4, y * 4, 4, 4);
				} else {
					g.setColor(Color.black);
					g.fillRect(x * 4, y * 4, 4, 4);
				}
			}
		}

	}

	public void updateMap(Grid data) {
		MapDisplay.data = data;
		this.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

		if (arg0.getButton() == 1) {
			currentCellEdit = Cell.Alive;
		} else {
			currentCellEdit = Cell.Dead;
		}

		Grid.setCellValue(arg0.getX() / 4, arg0.getY() / 4, currentCellEdit);
		this.repaint();
		System.out.println(arg0.getButton());
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

		if (arg0.getButton() == 3) {
			currentCellEdit = Cell.Dead;
		} else if (arg0.getButton() == 1) {
			currentCellEdit = Cell.Alive;
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		if (arg0.getButton() == 3) {
			currentCellEdit = Cell.Alive;
		}
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {

		Grid.setCellValue(arg0.getX() / 4, arg0.getY() / 4, currentCellEdit);
		this.repaint();

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
