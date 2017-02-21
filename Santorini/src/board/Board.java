package board;

import java.util.ArrayList;

import exceptions.InvalidBoardAlterationException;

public class Board {

	public static final int DEFAULT_BOARDSIZE = 5;
	private Field[][] fields;
	private int boardSize;

	public Board() {
		this(DEFAULT_BOARDSIZE);
	}

	public Board(int size) {
		boardSize = size;
		fields = new Field[boardSize][boardSize];
		initBoard();
	}

	public Board(Board board) {
		boardSize = board.getBoardSize();
		this.fields = new Field[boardSize][boardSize];
		for (int i = 0; i < boardSize; i++)
			for (int j = 0; j < boardSize; j++)
				this.fields[i][j] = new Field(board.getField(i, j));
	}

	public Field getField(int x, int y) {
		return fields[x][y];
	}

	public Field getField(Coord from) {
		return fields[from.getX()][from.getY()];
	}

	public Coord[] getCoordsWithWorkers(Color color) {
		ArrayList<Coord> workerCoords = new ArrayList<>();
		for (int i = 0; i < boardSize; i++)
			for (int j = 0; j < boardSize; j++)
				if (fields[i][j].getWorkerColor() == color)
					workerCoords.add(new Coord(i, j));
		return workerCoords.toArray(new Coord[workerCoords.size()]);
	}

	private void initBoard() {
		for (int i = 0; i < boardSize; i++)
			for (int j = 0; j < boardSize; j++)
				fields[i][j] = new Field();
	}

	public void setWorker(Coord fieldCoords, Color color) throws InvalidBoardAlterationException {
		if (getField(fieldCoords).getWorkerColor() != Color.NONE)
			throw new InvalidBoardAlterationException("Field already occupied by worker");

		getField(fieldCoords).setWorkerColor(color);
	}

	public void removeWorker(Coord fieldCoords) throws InvalidBoardAlterationException {
		if (getField(fieldCoords).getWorkerColor() == Color.NONE)
			throw new InvalidBoardAlterationException("No worker on field");

		getField(fieldCoords).setWorkerColor(Color.NONE);
	}

	public void setBlock(Coord buildCoord) throws InvalidBoardAlterationException {
		int x = buildCoord.getX();
		int y = buildCoord.getY();
		if (fields[x][y].getLevel() >= 4)
			throw new InvalidBoardAlterationException("Maximum level reached");

		if (fields[x][y].getWorkerColor() != Color.NONE)
			throw new InvalidBoardAlterationException("Field already occupied by worker");

		fields[x][y].setLevel(fields[x][y].getLevel() + 1);
	}

	public int getWorkerCountByColor(Color color) {
		return getCoordsWithWorkers(color).length;
	}

	public int getBoardSize() {
		return boardSize;
	}
}
