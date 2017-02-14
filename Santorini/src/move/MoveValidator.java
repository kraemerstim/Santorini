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
	public boolean validate(IPlayer player, WorkerMove workerMove, BuildMove buildMove) {
		return workerMoveValdidator.validate(player, workerMove) && buildMoveValidator.validate(buildMove);
	}
}
