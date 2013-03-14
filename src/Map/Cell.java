package Map;

public class Cell {

	public static final int Dead = 0;
	public static final int Alive = 1;
	private int value;

	protected Cell(int state) {
		this.value = state;
	}

	protected void setValue(int value) {
		this.value = value;
	}

	protected int getCellValue() {
		return this.value;
	}

}
