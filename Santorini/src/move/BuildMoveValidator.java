package move;

import board.Board;
import board.Coord;

public class BuildMoveValidator {

	private Board board;

	public BuildMoveValidator(Board board) {
		this.board = board;
	}

	public boolean validate(Move move) {
		return move.getTo().isNeighbour(move.getBuild()) && isPossibleBuildField(move)
				&& isPossibleBuildLevel(move.getBuild());
	}

	private boolean isPossibleBuildField(Move move) {
		return board.getField(move.getBuild()).isEmpty()
				&& (!move.getTo().equals(move.getBuild()) || (move.getFrom().equals(move.getBuild())));
	}

	private boolean isPossibleBuildLevel(Coord build) {
		return board.getField(build).getLevel() < 4;
	}

}
