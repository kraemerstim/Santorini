package move;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import board.Board;
import board.Coord;

public class BuildMoveValidatorTest {

	private Board board;

	@Before
	public void setup() {
		board = new Board();
	}

	@Test
	public void validate_withEmptyBoard_shouldBeValid() {
		BuildMoveValidator validator = new BuildMoveValidator(board);
		BuildMove move = new BuildMove(new Coord(0, 0), new Coord(0, 0), new Coord(0, 1));
		assertTrue(validator.validate(move));
	}

}
