package move;

import board.Board;
import board.Coord;
import common.Color;
import player.IPlayer;

public class MoveValidator implements IMoveValidator {

	private WorkerMoveValidator workerMoveValdidator;
	private BuildMoveValidator buildMoveValidator;
	private Board board;

	public MoveValidator(WorkerMoveValidator workerMoveValdidator, BuildMoveValidator buildMoveValidator, Board board) {
		this.workerMoveValdidator = workerMoveValdidator;
		this.buildMoveValidator = buildMoveValidator;
		this.board = board;
	}

	@Override
	public boolean validate(IPlayer player, WorkerMove workerMove, BuildMove buildMove) {
		return isWorkerColorMatching(player.getColor(), workerMove.getFrom())
				&& workerMoveValdidator.validate(workerMove) && buildMoveValidator.validate(buildMove);
	}

	private boolean isWorkerColorMatching(Color color, Coord from) {
		return color.equals(board.getField(from).getWorkerColor());
	}
}
