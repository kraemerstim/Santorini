package move;

import board.Board;
import board.Color;
import board.Coord;
import player.IPlayer;

public class WorkerMoveValidator {

	private Board board;

	public WorkerMoveValidator(Board board) {
		this.board = board;
	}

	public boolean validate(IPlayer player, Move move) {
		return isWorkerColorMatching(player.getColor(), move.getFrom())
			&& isFieldNeighbouring(move.getFrom(), move.getTo())
			&& isFieldEmpty(move.getTo())
			&& isPossibleMoveLevel(move.getFrom(), move.getTo());
	}

	private boolean isWorkerColorMatching(Color color, Coord from) {
		return color.equals(board.getField(from).getWorkerColor());
	}

	private boolean isFieldNeighbouring(Coord from, Coord to) {
		return from.isNeighbour(to);
	}

	private boolean isFieldEmpty(Coord coord) {
		return board.getField(coord).isEmpty();
	}

	private boolean isPossibleMoveLevel(Coord from, Coord to) {
		return board.getField(to).getLevel() < 4
		  && ((board.getField(to).getLevel() < board.getField(from).getLevel()) ||
		      (board.getField(to).getLevel() - board.getField(from).getLevel() <= 1));
	}

}
