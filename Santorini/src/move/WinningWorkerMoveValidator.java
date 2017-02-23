package move;

import board.Board;

public class WinningWorkerMoveValidator {

	private Board board;

	public WinningWorkerMoveValidator(Board board) {
		this.board = board;
	}

	public boolean validate(WorkerMove workerMove) {
		return (board.getField(workerMove.getFrom()).getLevel() == 2) && (board.getField(workerMove.getTo()).getLevel() == 3);
	}

}
