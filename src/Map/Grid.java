package Map;

import java.awt.Dimension;
import java.util.Random;

public class Grid {

	private int width;
	private int height;
	private static Cell[][] data;
	private static Grid instance;

	public void initialise(Dimension size) {
		this.height = (int) size.getHeight();
		this.width = (int) size.getWidth();
		initialiseCells();
	}

	private Grid(int height, int width) {

		this.height = height;
		this.width = width;
		initialiseCells();
	}
	
	private void initialiseCells() {
		data = new Cell[this.width][this.height];

		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				data[i][j] = new Cell(Cell.Dead);
			}
		}

	}

	public synchronized static Grid getInstance() {

		if (instance == null) {
			instance = new Grid();
		}
		return instance;
	}
	
	private Grid() {
		
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getCellValue(int x, int y) {
		return data[x][y].getCellValue();
	}

	public void setCellValue(int x, int y, int value) {
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

	public void reset() {

		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				data[i][j] = new Cell(Cell.Dead);
			}
		}
	}

}
