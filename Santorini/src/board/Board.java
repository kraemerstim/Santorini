package board;

import exceptions.FieldAlreadyOccupiedException;
import exceptions.TooManySameColorWorkersOnBoardException;

public class Board {
	private static final int BOARDSIZE = 5;
	private Field[][] board;

	public Field getField(int x, int y) {
		return board[x][y];
	}

	public Board() {
		super();
		initBoard();
	}

	private void initBoard() {
		board = new Field[BOARDSIZE][BOARDSIZE];
		for (int i = 0; i < BOARDSIZE; i++)
			for (int j = 0; j < BOARDSIZE; j++)
				board[i][j] = new Field();
	}

	public void setWorker(int i, int j, Color color) throws Exception {
		if (getField(i,j).getWorkerColor() != Color.None)
			throw new FieldAlreadyOccupiedException();
		
		if (getWorkerCountByColor(color) >= 2)
			throw new TooManySameColorWorkersOnBoardException();
		getField(i, j).setWorkerColor(color);		
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
