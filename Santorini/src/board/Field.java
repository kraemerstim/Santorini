package board;

public class Field {
	private int level;
	private Color workerColor;

	public Field() {
		super();
		workerColor = Color.None;
		level = 0;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Color getWorkerColor() {
		return workerColor;
	}

	public void setWorkerColor(Color workerColor) {
		this.workerColor = workerColor;
	}

	public boolean isEmtpty() {
		return this.workerColor.equals(Color.None);
	}
}