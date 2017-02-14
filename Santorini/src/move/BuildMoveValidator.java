package move;

import board.Board;
import board.Coord;

public class BuildMoveValidator {

	private Board board;

	public BuildMoveValidator(Board board) {
		this.board = board;
	}

	public boolean validate(BuildMove move) {
		return isPossibleBuildField(move) && isPossibleBuildLevel(move.getBuild());
	}

	private boolean isPossibleBuildField(BuildMove move) {
		return board.getField(move.getBuild()).isEmpty() && (move.getWorker().isNeighbour(move.getBuild()));
	}

	private boolean isPossibleBuildLevel(Coord build) {
		return board.getField(build).getLevel() < 4;
	}

}
