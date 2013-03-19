package Map;

import java.util.Random;

public class Grid {

	private int width;
	private int height;
	private static Cell[][] data;

	/**
	 * Creates a new Grid instance and fills it up with dead cells.
	 * 
	 * @param height
	 *            - Height of the map
	 * @param width
	 *            - Width of the map
	 */
	public Grid(int height, int width) {

		this.height = height;
		this.width = width;
		initialiseCells();

	}

	/*
	 * Copy constructor
	 */
	// public Grid(Grid myGrid) {
	// this.width = myGrid.width;
	// this.height = myGrid.height;
	// this.data = new Cell[myGrid.width][myGrid.height];
	// initialiseCells();
	//
	// for (int i = 0; i < data.length; i++) {
	// for (int j = 0; j < data[0].length; j++) {
	// this.data[i][j].setValue(myGrid.data[i][j].getCellValue());
	// }
	// }
	//
	// }

	/**
	 * Returns the current width of the map.
	 * 
	 * @return width - The width of the map along the x-axis
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Returns the current height of the map.
	 * 
	 * @return height - The height of the map along the y-axis
	 */
	public int getHeight() {
		return height;
	}

	private void initialiseCells() {
		data = new Cell[this.width][this.height];

		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				data[i][j] = new Cell(Cell.Dead);
			}
		}

	}

	@SuppressWarnings("unused")
	private Grid() {

	}

	public static int getCellValue(int x, int y) {
		return data[x][y].getCellValue();
	}

	public static void setCellValue(int x, int y, int value) {
		data[x][y].setValue(value);
	}

	public int getCellTypeCount(int value) {
		int counter = 0;
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				if (data[i][j].getCellValue() == value) {
					counter++;
				}
			}
		}

		return counter;
	}

	/**
	 * 
	 * Goes through every cell and based on a given percentage turns that cell
	 * into a live cell. If the cell is already alive then no change is made.
	 * 
	 * @param percentage
	 *            - Chance that a cell is made into a live cell.
	 */
	public void addRandomLiveCells(double percentage) {

		percentage = (int) percentage;
		Random myRandom = new Random();
		for (int x = 0; x < getWidth(); x++) {
			for (int y = 0; y < getWidth(); y++) {
				if (myRandom.nextInt(100) + 1 < percentage) {
					setCellValue(x, y, Cell.Alive);
				}
			}
		}
	}

	public static void swapCellValue(int x, int y) {

		if (data[x][y].getCellValue() == Cell.Alive) {
			data[x][y].setValue(Cell.Dead);
		} else {
			data[x][y].setValue(Cell.Alive);
		}

	}

}
