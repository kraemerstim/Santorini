package move;

import board.Board;
import board.Coord;

public class WorkerMoveValidator {

	private Board board;

	public WorkerMoveValidator(Board board) {
		this.board = board;
	}

	public boolean validate(WorkerMove move) {
		return isFieldNeighbouring(move.getFrom(), move.getTo()) && isFieldEmpty(move.getTo())
				&& isPossibleMoveLevel(move.getFrom(), move.getTo());
	}

	private boolean isFieldNeighbouring(Coord from, Coord to) {
		return from.isNeighbour(to);
	}

	private boolean isFieldEmpty(Coord coord) {
		return board.getField(coord).isEmpty();
	}

	private boolean isPossibleMoveLevel(Coord from, Coord to) {
		return board.getField(to).getLevel() < 4 && ((board.getField(to).getLevel() < board.getField(from).getLevel())
				|| (board.getField(to).getLevel() - board.getField(from).getLevel() <= 1));
	}

}
