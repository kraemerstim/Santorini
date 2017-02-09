package board;

public class CoordValidator implements ICoordValidator {

	private int boardsize;

	public CoordValidator(int boardsize) {
		super();
		this.boardsize = boardsize;
	}

	@Override
	public boolean validate(Coord coord) {
		return coord.getX() >= 0 && coord.getX() < boardsize && coord.getY() >= 0 && coord.getY() < boardsize;
	}

}
