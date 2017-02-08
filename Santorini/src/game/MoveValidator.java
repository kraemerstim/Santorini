package game;

import board.Board;
import board.Color;
import board.Coords;

public class MoveValidator implements IMoveValidator {

	private Board board;

	public MoveValidator(Board board) {
		this.board = board;
	}

	@Override
	public boolean isValid(Move move) {
		return canMoveWorker(move) && canBuild(move);
	}

	private boolean canBuild(Move move) {
		return areNeighbours(move.getTo(), move.getBuild()) && isBuildPossibleField(move) && isPossibleBuildLevel(move.getBuild());
	}

	private boolean isBuildPossibleField(Move move) {
		return isFieldEmpty(move.getBuild()) && (move.getTo().toString() != move.getBuild().toString()) || (move.getFrom().toString() == move.getBuild().toString());
	}

	private boolean canMoveWorker(Move move) {
		return areNeighbours(move.getFrom(), move.getTo()) && isFieldEmpty(move.getTo()) && arePossibleMoveLevels(move.getFrom(), move.getTo());
	}

	private boolean arePossibleMoveLevels(Coords from, Coords to) {
		return (board.getField(from).getLevel() < board.getField(to).getLevel()) ||
			   (board.getField(from).getLevel() - board.getField(to).getLevel() <= 1);
	}

	private boolean areNeighbours(Coords from, Coords to) {
		return Math.abs(from.getX() - to.getX()) <= 1 && Math.abs(from.getY() - to.getY()) <= 1;
	}

	private boolean isFieldEmpty(Coords build) {
		return board.getField(build).getWorkerColor() == Color.None;
	}

	private boolean isPossibleBuildLevel(Coords build) {
		return board.getField(build).getLevel() < (Board.BOARDSIZE - 1);
	}

}
