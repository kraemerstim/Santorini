package move;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import board.Board;
import board.BoardSerializer;
import board.Coord;

public class BuildMoveValidatorTest {

	private Board board;
	private BuildMoveValidator validator;
	private BoardSerializer boardSerializer;

	@Before
	public void setup() {
		board = new Board();
		boardSerializer = new BoardSerializer();
		validator = new BuildMoveValidator(board);
	}

	@Test
	public void validate_withValidMove_shouldBeValid() {
		board = boardSerializer.deserialize("0b00000000000000000000000");
		BuildMove move = new BuildMove(new Coord(0, 0), new Coord(0, 1));
		assertTrue(validator.validate(move));
	}

	@Test
	public void validate_withUnreachableField_shouldBeInvalid() {
		BuildMove move = new BuildMove(new Coord(0, 0), new Coord(0, 2));
		assertFalse(validator.validate(move));
	}

	@Test
	public void validate_withOccupiedField_shouldBeInvalid() {
		board = boardSerializer.deserialize("00b00000000000000000000000");
		BuildMove move = new BuildMove(new Coord(0, 0), new Coord(0, 1));
		validator = new BuildMoveValidator(board);
		assertFalse(validator.validate(move));
	}

	@Test
	public void validate_withMaxBuildLevel_shouldBeInvalid() {
		board = boardSerializer.deserialize("04000000000000000000000000");
		BuildMove move = new BuildMove(new Coord(0, 0), new Coord(0, 1));
		validator = new BuildMoveValidator(board);
		assertFalse(validator.validate(move));
	}

}
