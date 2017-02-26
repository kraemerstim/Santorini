package move;

import common.Color;

public interface IMoveValidator {

	public boolean validate(Color color, WorkerMove workerMove, BuildMove buildMove);

}
