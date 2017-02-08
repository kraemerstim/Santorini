package board;

import exceptions.FieldAlreadyOccupiedException;
import exceptions.MaxLevelAlreadyReachedException;
import exceptions.TooManySameColorWorkersOnBoardException;

public class Board {

	public static final int BOARDSIZE = 5;
	private Field[][] board;

	public Board() {
		initBoard();
	}

	public Field getField(int x, int y) {
		return board[x][y];
	}

	public Field getField(Coords from) {
		return board[from.getX()][from.getY()];
	}

	private void initBoard() {
		board = new Field[BOARDSIZE][BOARDSIZE];
		for (int i = 0; i < BOARDSIZE; i++)
			for (int j = 0; j < BOARDSIZE; j++)
				board[i][j] = new Field();
	}

	public void setWorker(Coords fieldCoords, Color color) throws Exception {
		if (getField(fieldCoords).getWorkerColor() != Color.None)
			throw new FieldAlreadyOccupiedException();

		if (getWorkerCountByColor(color) >= 2)
			throw new TooManySameColorWorkersOnBoardException();

		getField(fieldCoords).setWorkerColor(color);
	}

	public void setBlock(int x, int y) throws MaxLevelAlreadyReachedException, FieldAlreadyOccupiedException {
		if (board[x][y].getLevel() >= 4)
			throw new MaxLevelAlreadyReachedException();

		if (board[x][y].getWorkerColor() != Color.None)
			throw new FieldAlreadyOccupiedException();

		board[x][y].setLevel(board[x][y].getLevel() + 1);
	}

	private int getWorkerCountByColor(Color color) {
		int count = 0;
		for (int i = 0; i < BOARDSIZE; i++)
			for (int j = 0; j < BOARDSIZE; j++)
				if (board[i][j].getWorkerColor() == color)
					count++;
		return count;
	}
}
