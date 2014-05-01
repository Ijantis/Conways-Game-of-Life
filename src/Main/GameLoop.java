package Main;

import java.awt.Dimension;
import java.util.Stack;

import Gui.CreateAndArrangeGui;
import Gui.GuiMain;
import Map.Cell;
import Map.Grid;

public class GameLoop implements Runnable {

	public static boolean isPaused = true;
	private static Grid currentGrid;
	private static int updateSpeedMilis = 100;
	private static Stack<Coordinate> cellsToBeRevived = new Stack<Coordinate>();
	private static Stack<Coordinate> cellsToBeKilled = new Stack<Coordinate>();

	public GameLoop() {

		currentGrid = Grid.getInstance();
		currentGrid.initialise(new Dimension(200, 200));

		// currentGrid.setCellValue(100, 100, Cell.Alive);
		// currentGrid.setCellValue(101, 101, Cell.Alive);
		// currentGrid.setCellValue(101, 102, Cell.Alive);
		// currentGrid.setCellValue(100, 101, Cell.Alive);
		// currentGrid.setCellValue(99, 101, Cell.Alive);

		for (int x = 50; x < 150; x++) {
			currentGrid.setCellValue(x, 100, Cell.Alive);
			currentGrid.setCellValue(x, 120, Cell.Alive);
			currentGrid.setCellValue(x, 110, Cell.Alive);
			currentGrid.setCellValue(x, 80, Cell.Alive);
			currentGrid.setCellValue(x, 90, Cell.Alive);
		}

		new GuiMain(currentGrid);

		Thread t1 = new Thread(this);
		t1.start();

	}

	@Override
	public void run() {

		while (true) {
			if (isPaused) {
				pauseProgram(10);
			} else {
				calculateCellsToUpdate();
				updateCells();
				redrawMap();
				pauseProgram(updateSpeedMilis);
			}
		}

	}

	public static void nextFrame() {

		calculateCellsToUpdate();
		updateCells();
		redrawMap();

	}

	private static void calculateCellsToUpdate() {

		int value;
		int neighbours;
		for (int x = 0; x < currentGrid.getWidth(); x++) {
			for (int y = 0; y < currentGrid.getHeight(); y++) {
				value = currentGrid.getCellValue(x, y);
				neighbours = calculateLiveNeighbours(x, y);
				if (value == Cell.Alive && (neighbours < 2 || neighbours > 3)) {
					cellsToBeKilled.push(new Coordinate(x, y));
				} else if (value == Cell.Dead && neighbours == 3) {
					cellsToBeRevived.push(new Coordinate(x, y));
				}

			}
		}

	}

	private static void updateCells() {
		Coordinate temp;

		while (!cellsToBeRevived.isEmpty()) {
			temp = cellsToBeRevived.pop();
			currentGrid.setCellValue(temp.x, temp.y, Cell.Alive);
		}

		while (!cellsToBeKilled.isEmpty()) {
			temp = cellsToBeKilled.pop();
			currentGrid.setCellValue(temp.x, temp.y, Cell.Dead);
		}

	}

	private static void redrawMap() {

		GuiMain.redrawMap(currentGrid);

	}

	// TODO: Optimise it at some point
	private static int calculateLiveNeighbours(int xCoord, int yCoord) {
		int neighbours = 0;

		for (int x = (xCoord - 1); x < (xCoord + 2); x++) {
			for (int y = (yCoord - 1); y < (yCoord + 2); y++) {
				try {
					if (currentGrid.getCellValue(x, y) == Cell.Alive) {
						neighbours++;
					}
				} catch (ArrayIndexOutOfBoundsException e) {
				}
			}
		}

		if (currentGrid.getCellValue(xCoord, yCoord) == Cell.Alive) {
			neighbours--;
		}

		return neighbours;

	}

	private void pauseProgram(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			System.exit(1);
		}

	}

	public static void incrementSimulationSpeed(int speedIncrement) {

		if (!(updateSpeedMilis + speedIncrement < 10 || updateSpeedMilis
				+ speedIncrement > 200)) {

			updateSpeedMilis += speedIncrement;
			CreateAndArrangeGui.setSpeed(updateSpeedMilis);
		}

	}

}
