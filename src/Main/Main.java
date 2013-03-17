package Main;

import java.io.ObjectInputStream.GetField;
import java.util.EmptyStackException;
import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

import Gui.GuiMain;
import Map.Cell;
import Map.Grid;

public class Main implements Runnable {

	public static boolean isPaused = true;
	private static Grid myGrid;
	private static GuiMain gui;
	private static int speed = 10;
	private static Stack<Coordinate> toBeRevived = new Stack<Coordinate>();
	private static Stack<Coordinate> toBeKilled = new Stack<Coordinate>();
	private static Stack<Grid> frames = new Stack<Grid>();

	public static void main(String[] args) {

		myGrid = new Grid(200, 200);

		myGrid.setCellValue(100, 100, Cell.Alive);
		myGrid.setCellValue(101, 101, Cell.Alive);
		myGrid.setCellValue(101, 102, Cell.Alive);
		myGrid.setCellValue(100, 101, Cell.Alive);
		myGrid.setCellValue(99, 101, Cell.Alive);

		addFrame();

		gui = new GuiMain(myGrid);

		new Main();

	}

	public Main() {

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
				addFrame();
				updateGraphics();
				pauseProgram(Main.speed);
			}
		}

	}

	private static void addFrame() {

		frames.add(myGrid);
		System.out.println("Added");
		System.out.println(myGrid.getCellTypeCount(Cell.Alive));

	}

	public static void nextFrame() {

		calculateCellsToUpdate();
		updateCells();
		addFrame();
		updateGraphics();
		System.out.println("next");

	}

	public static void previousFrame() {

		try {
			myGrid = frames.pop();
			// System.out.println(myGrid.getCellTypeCount(Cell.Alive));
			updateGraphics();
			System.out.println("prev");

		} catch (EmptyStackException e) {
		}

	}

	private static void updateCells() {
		Coordinate temp;

		while (!toBeRevived.isEmpty()) {
			temp = toBeRevived.pop();
			myGrid.setCellValue(temp.x, temp.y, Cell.Alive);
		}

		while (!toBeKilled.isEmpty()) {
			temp = toBeKilled.pop();
			myGrid.setCellValue(temp.x, temp.y, Cell.Dead);
		}

	}

	private static void updateGraphics() {

		// System.out.println(myGrid.getCellTypeCount(Cell.Alive));
		GuiMain.mapDisplay.updateMap(myGrid);

	}

	/*
	 * For a space that is 'populated': Each cell with one or no neighbors dies,
	 * as if by loneliness. Each cell with four or more neighbors dies, as if by
	 * overpopulation. Each cell with two or three neighbors survives. For a
	 * space that is 'empty' or 'unpopulated' Each cell with three neighbors
	 * becomes populated.
	 */
	/**
	 * 
	 */
	private static void calculateCellsToUpdate() {

		int value;
		int neighbours;
		for (int x = 0; x < myGrid.getWidth(); x++) {
			for (int y = 0; y < myGrid.getHeight(); y++) {
				value = myGrid.getCellValue(x, y);
				neighbours = checkCellNeighbours(x, y);
				if (value == Cell.Alive && (neighbours < 2 || neighbours > 3)) {
					toBeKilled.push(new Coordinate(x, y));
				} else if (value == Cell.Dead && neighbours == 3) {
					toBeRevived.push(new Coordinate(x, y));
				}

			}
		}

	}

	/**
	 * 
	 * Checks to see how many direct neighbours a certain cell has.
	 * 
	 * @param xCoord
	 *            - The x coordinate of the cell to be checked
	 * @param yCoord
	 *            - The y coordinate of the cell to be checked
	 * @return neighbours - The number of live neighbours a cell has
	 */
	// TODO This method counts the cell being checked as a neighbour. OPTIMISE
	// IT
	private static int checkCellNeighbours(int xCoord, int yCoord) {
		int neighbours = 0;

		for (int x = (xCoord - 1); x < (xCoord + 2); x++) {
			for (int y = (yCoord - 1); y < (yCoord + 2); y++) {
				try {
					if (myGrid.getCellValue(x, y) == Cell.Alive) {
						neighbours++;
					}
				} catch (ArrayIndexOutOfBoundsException e) {
				}
			}
		}

		if (myGrid.getCellValue(xCoord, yCoord) == Cell.Alive) {
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

	/**
	 * @param speed
	 *            the speed to set
	 */
	public static void setSpeed(int speed) {

		if (!(Main.speed + speed < 10 || Main.speed + speed > 200)) {

			Main.speed += speed;
		}

	}

}
