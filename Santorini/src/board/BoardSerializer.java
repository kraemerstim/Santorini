package board;

import common.ColorAdapter;

public class BoardSerializer {

	private int currentIndex;
	private ColorAdapter colorAdapter;

	public BoardSerializer() {
		this.colorAdapter = new ColorAdapter();
	}

	public Board deserialize(String input) {
		Board board = new Board();
		int boardSize = board.getBoardSize();
		currentIndex = 0;
		for (int i = 0; i < boardSize; i++)
			for (int j = 0; j < boardSize; j++) {
				Field field = board.getField(i, j);
				setLevel(field, input);
				setWorkerColor(field, input);
			}
		return board;
	}

	private void setLevel(Field field, String input) {
		char c;
		if (currentIndex < input.length()) {
			c = input.charAt(currentIndex);
			if (Character.isDigit(c)) {
				field.setLevel(c - '0');
				currentIndex++;
			}
		}
	}

	private void setWorkerColor(Field field, String input) {
		char c;
		if (currentIndex < input.length()) {
			c = input.charAt(currentIndex);
			if (!Character.isDigit(c)) {
				field.setWorkerColor(colorAdapter.toColor(Character.toString(c)));
				currentIndex++;
			}
		}
	}

	public String serialize(Board board) {
		int boardSize = board.getBoardSize();
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < boardSize; i++)
			for (int j = 0; j < boardSize; j++) {
				Field field = board.getField(i, j);
				result.append(field.getLevel());
				result.append(colorAdapter.toString(field.getWorkerColor()));
			}
		return result.toString();
	}

}
