package board;

import common.Color;

public class Field {
	private int level;
	private Color workerColor;

	public Field() {
		super();
		workerColor = Color.NONE;
		level = 0;
	}

	public Field(Field field) {
		setLevel(field.getLevel());
		setWorkerColor(field.getWorkerColor());
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

	public boolean isEmpty() {
		return this.workerColor.equals(Color.NONE);
	}
}