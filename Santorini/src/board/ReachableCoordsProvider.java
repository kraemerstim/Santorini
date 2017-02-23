package board;

import java.util.ArrayList;

import exceptions.InvalidBoardAlterationException;
import move.WorkerMove;
import move.WorkerMoveValidator;

public class ReachableCoordsProvider {

	public Coord[] provide(Board board, Coord fromCoord) {
		ArrayList<Coord> coords = new ArrayList<>();
		WorkerMoveValidator workerMovevalidator = new WorkerMoveValidator(board);
		CoordValidator coordValidator = new CoordValidator(board.getBoardSize());
		if (board.getField(fromCoord).isEmpty())
			throw new InvalidBoardAlterationException("No worker on field");
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				Coord nextCoord = new Coord(fromCoord.getX() + i, fromCoord.getY() + j);
				if (!coordValidator.validate(nextCoord))
					continue;
				if (workerMovevalidator.validate(new WorkerMove(fromCoord, nextCoord)))
					coords.add(nextCoord);
			}
		}
		return coords.toArray(new Coord[coords.size()]);
	}

}
