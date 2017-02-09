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
		return checkWorkerColor(player.getColor(), move.getFrom())
			&& areNeighbouringFields(move.getFrom(), move.getTo()) //
			&& isFieldEmpty(move.getTo()) //
			&& arePossibleMoveLevels(move.getFrom(), move.getTo());
	}

	private boolean checkWorkerColor(Color color, Coord from) {
		return color.equals(board.getField(from).getWorkerColor());
	}

	private boolean areNeighbouringFields(Coord from, Coord to) {
		return from.isNeighbour(to);
	}

	private boolean isFieldEmpty(Coord coord) {
		return board.getField(coord).isEmtpty();
	}

	private boolean arePossibleMoveLevels(Coord from, Coord to) {
		return (board.getField(from).getLevel() < board.getField(to).getLevel()) ||
			   (board.getField(from).getLevel() - board.getField(to).getLevel() <= 1);
	}

}
