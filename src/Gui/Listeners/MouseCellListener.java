package Gui.Listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import Gui.MapDisplay;
import Map.Cell;
import Map.Grid;

public class MouseCellListener implements MouseMotionListener, MouseListener {

	@Override
	public void mouseClicked(MouseEvent arg0) {

		// left mouse button
		if (arg0.getButton() == 1) {
			MapDisplay.setCellDrawType(Cell.Alive);
		} else {
			MapDisplay.setCellDrawType(Cell.Dead);
		}

		Grid.getInstance().setCellValue(arg0.getX() / MapDisplay.getScale(),
				arg0.getY() / MapDisplay.getScale(), MapDisplay.getCellDrawType());
		MapDisplay.getInstance().repaint();
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

		// right click
		if (arg0.getButton() == 3) {
			MapDisplay.setCellDrawType(Cell.Dead);
		} else if (arg0.getButton() == 1) {
			MapDisplay.setCellDrawType(Cell.Alive);
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// right click
		if (arg0.getButton() == 3) {
			MapDisplay.setCellDrawType(Cell.Alive);
		}
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		Grid.getInstance().setCellValue(arg0.getX() / MapDisplay.getScale(),
				arg0.getY() / MapDisplay.getScale(), MapDisplay.getCellDrawType());
		MapDisplay.getInstance().repaint();

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
