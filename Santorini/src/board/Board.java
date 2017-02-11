package board;

import java.util.ArrayList;

import exceptions.FieldAlreadyOccupiedException;
import exceptions.MaxLevelAlreadyReachedException;
import exceptions.NoWorkerOnFieldException;

public class Board {

	public static final int DEFAULT_BOARDSIZE = 5;
	private Field[][] board;
	private int boardSize;

	public Board() {
		this(DEFAULT_BOARDSIZE);
	}

	public Board(int size) {
		boardSize = size;
		board = new Field[boardSize][boardSize];
		initBoard();
	}

	public Board(Board board) {
		boardSize = board.getBoardSize();
		this.board = new Field[boardSize][boardSize];
		for (int i = 0; i < boardSize; i++)
			for (int j = 0; j < boardSize; j++)
				this.board[i][j] = new Field(board.getField(i, j));

	}

	public Field getField(int x, int y) {
		return board[x][y];
	}

	public Field getField(Coord from) {
		return board[from.getX()][from.getY()];
	}

	public Coord[] getCoordsWithWorkers(Color color) {
		ArrayList<Coord> fields = new ArrayList<Coord>();
		for (int i = 0; i < boardSize; i++)
			for (int j = 0; j < boardSize; j++)
				if (board[i][j].getWorkerColor() == color)
					fields.add(new Coord(i, j));
		return fields.toArray(new Coord[fields.size()]);
	}

	private void initBoard() {
		for (int i = 0; i < boardSize; i++)
			for (int j = 0; j < boardSize; j++)
				board[i][j] = new Field();
	}

	public void setWorker(Coord fieldCoords, Color color) throws FieldAlreadyOccupiedException {
		if (getField(fieldCoords).getWorkerColor() != Color.None)
			throw new FieldAlreadyOccupiedException();

		getField(fieldCoords).setWorkerColor(color);
	}

	public void removeWorker(Coord fieldCoords) throws NoWorkerOnFieldException {
		if (getField(fieldCoords).getWorkerColor() == Color.None)
			throw new NoWorkerOnFieldException();

		getField(fieldCoords).setWorkerColor(Color.None);
	}

	public void setBlock(int x, int y) throws MaxLevelAlreadyReachedException, FieldAlreadyOccupiedException {
		if (board[x][y].getLevel() >= 4)
			throw new MaxLevelAlreadyReachedException();

		if (board[x][y].getWorkerColor() != Color.None)
			throw new FieldAlreadyOccupiedException();

		board[x][y].setLevel(board[x][y].getLevel() + 1);
	}

	public int getWorkerCountByColor(Color color) {
		return getCoordsWithWorkers(color).length;
	}

	public void setBoardFromString(String position) {
		initBoard();
		int index = 0;
		char c;
		for (int i = 0; i < boardSize; i++)
			for (int j = 0; j < boardSize; j++) {
				c = position.charAt(index);
				if (Character.isDigit(c)) {
					board[i][j].setLevel(c - '0');
					index++;
				}
				c = position.charAt(index);
				if (c == 'w')
					board[i][j].setWorkerColor(Color.White);
				else if (c == 'b')
					board[i][j].setWorkerColor(Color.Blue);
				if (!Character.isDigit(c))
					index++;
			}
	}

	public String getBoardString() {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < boardSize; i++)
			for (int j = 0; j < boardSize; j++) {
				result.append(board[i][j].getLevel());
				if (board[i][j].getWorkerColor() == Color.White)
					result.append("w");
				else if (board[i][j].getWorkerColor() == Color.Blue)
					result.append("b");
			}
		return result.toString();
	}

	public boolean isBoardValid() {
		Field field;
		for (int i = 0; i < boardSize; i++)
			for (int j = 0; j < boardSize; j++) {
				field = getField(i, j);
				if (field.getLevel() < 0 || field.getLevel() > 4)
					return false;
			}
		return true;
	}

	public int getBoardSize() {
		return boardSize;
	}
}
