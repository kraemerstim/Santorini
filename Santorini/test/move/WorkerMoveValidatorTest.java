package move;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import board.Board;
import board.BoardSerializer;
import board.Coord;

public class WorkerMoveValidatorTest {

	private Board board;
	private WorkerMoveValidator validator;
	private BoardSerializer boardSerializer;

	@Before
	public void setup() {
		boardSerializer = new BoardSerializer();
	}

	@Test
	public void validate_withValidMove_shouldBeValid() {
		board = boardSerializer.deserialize("3b00000000000000000000000");
		WorkerMove move = new WorkerMove(new Coord(0, 0), new Coord(0, 1));
		validator = new WorkerMoveValidator(board);
		assertTrue(validator.validate(move));
	}

	@Test
	public void validate_withUnreachableField_shouldBeInvalid() {
		board = boardSerializer.deserialize("0b00000000000000000000000");
		WorkerMove move = new WorkerMove(new Coord(0, 0), new Coord(0, 2));
		validator = new WorkerMoveValidator(board);
		assertFalse(validator.validate(move));
	}

	@Test
	public void validate_withOccupiedField_shouldBeInvalid() {
		board = boardSerializer.deserialize("0b0b00000000000000000000000");
		WorkerMove move = new WorkerMove(new Coord(0, 0), new Coord(0, 1));
		validator = new WorkerMoveValidator(board);
		assertFalse(validator.validate(move));
	}

	@Test
	public void validate_withMaxMoveLevel_shouldBeInvalid() {
		board = boardSerializer.deserialize("3b4000000000000000000000000");
		WorkerMove move = new WorkerMove(new Coord(0, 0), new Coord(0, 1));
		validator = new WorkerMoveValidator(board);
		assertFalse(validator.validate(move));
	}

}
