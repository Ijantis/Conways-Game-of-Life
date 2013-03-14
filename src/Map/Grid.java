package Map;

public class Grid {

	private int width;
	private int height;
	private Cell[][] data;

	public Grid(int height, int width) {

		this.height = height;
		this.width = width;
		initialiseMapData();

	}

	private void initialiseMapData() {
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

	public int getValue(int x, int y) {
		return data[x][y].getCellValue();
	}

	public void setValue(int x, int y, int value) {
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
}
