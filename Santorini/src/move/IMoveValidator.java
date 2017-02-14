package move;

import player.IPlayer;

public interface IMoveValidator {

	public boolean validate(IPlayer player, WorkerMove workerMove, BuildMove buildMove);

}
