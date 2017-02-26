package move;

import board.Board;
import board.Coord;
import common.Color;

public class MoveValidator implements IMoveValidator {

	private WorkerMoveValidator workerMoveValdidator;
	private BuildMoveValidator buildMoveValidator;
	private Board board;

	public MoveValidator(WorkerMoveValidator workerMoveValdidator, BuildMoveValidator buildMoveValidator, Board board) {
		this.workerMoveValdidator = workerMoveValdidator;
		this.buildMoveValidator = buildMoveValidator;
		this.board = new Board(board);
	}

	@Override
	public boolean validate(Color color, WorkerMove workerMove, BuildMove buildMove) {
		boolean result = isWorkerColorMatching(color, workerMove.getFrom());
		result = result && canMovedWorkerBuildOnBuildField(workerMove, buildMove);
		result = result && validateAllMoves(workerMove, buildMove);
		return result;
	}

	private boolean canMovedWorkerBuildOnBuildField(WorkerMove workerMove, BuildMove buildMove) {
		return workerMove.getTo().isNeighbour(buildMove.getBuild());
	}

	private boolean validateAllMoves(WorkerMove workerMove, BuildMove buildMove) {
		boolean result = workerMoveValdidator.validate(workerMove);
		if (result)
			board.applyWorkerMove(workerMove);
		else
			return result;
		
		if (buildMoveValidator.validate(buildMove))
			board.applyBuildMove(buildMove);
		else
			return result;
		
		return result;
	}

	private boolean isWorkerColorMatching(Color color, Coord from) {
		return color.equals(board.getField(from).getWorkerColor());
	}
}
