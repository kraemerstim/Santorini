package move;

import player.IPlayer;

public class MoveValidator implements IMoveValidator {

	private WorkerMoveValidator workerMoveValdidator;
	private BuildMoveValidator buildMoveValidator;

	public MoveValidator(WorkerMoveValidator workerMoveValdidator, BuildMoveValidator buildMoveValidator) {
		this.workerMoveValdidator = workerMoveValdidator;
		this.buildMoveValidator = buildMoveValidator;
	}

	@Override
	public boolean validate(IPlayer player, BuildMove move) {
		return workerMoveValdidator.validate(player, move) && buildMoveValidator.validate(move);
	}
}
