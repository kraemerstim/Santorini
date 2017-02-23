package game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import board.Board;
import board.BoardSerializer;
import board.Coord;
import move.WorkerMove;
import move.WorkerMoveValidator;

public class WorkerMoveValidatorTest {
	private BoardSerializer boardSerializer;

	@Before
	public void setup() {
		boardSerializer = new BoardSerializer();
	}

	@Test
	public void validate_withWinningMoveCoords_shouldBeValid() {
		Board board = boardSerializer.deserialize("2b30000000000000000000000");
		WorkerMoveValidator workerMoveValidator = new WorkerMoveValidator(board);
		WorkerMove workerMove = new WorkerMove(new Coord(0, 0), new Coord(0, 1));
		assertTrue(workerMoveValidator.validate(workerMove));
	}

	@Test
	public void validate_withNotWinningMoveCoords_shouldBeInvalid() {
		Board board = boardSerializer.deserialize("1b0w0b0w00000000000000000000");
		WorkerMoveValidator workerMoveValidator = new WorkerMoveValidator(board);
		WorkerMove workerMove = new WorkerMove(new Coord(0, 0), new Coord(0, 1));
		assertFalse(workerMoveValidator.validate(workerMove));
	}
}
