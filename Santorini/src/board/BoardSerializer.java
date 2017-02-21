package board;

public class BoardSerializer {

	public Board deserialize(String input) {
		Board board = new Board();
		int boardSize = board.getBoardSize();
		int index = 0;
		char c;
		for (int i = 0; i < boardSize; i++)
			for (int j = 0; j < boardSize; j++) {
				if (index < input.length()) {
					c = input.charAt(index);
					if (Character.isDigit(c)) {
						board.getField(i, j).setLevel(c - '0');
						index++;
					}
				}
				if (index < input.length()) {
					c = input.charAt(index);
					if (c == 'w')
						board.getField(i, j).setWorkerColor(Color.WHITE);
					else if (c == 'b')
						board.getField(i, j).setWorkerColor(Color.BLUE);
					if (!Character.isDigit(c))
						index++;
				}
			}
		return board;
	}

	public String serialize(Board board) {
		int boardSize = board.getBoardSize();
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < boardSize; i++)
			for (int j = 0; j < boardSize; j++) {
				result.append(board.getField(i, j).getLevel());
				if (board.getField(i, j).getWorkerColor() == Color.WHITE)
					result.append("w");
				else if (board.getField(i, j).getWorkerColor() == Color.BLUE)
					result.append("b");
			}
		return result.toString();
	}

}
