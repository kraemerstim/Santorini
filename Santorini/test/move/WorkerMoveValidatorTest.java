package move;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import board.Board;
import board.Coord;

public class WorkerMoveValidatorTest {

	private Board board;

	@Before
	public void setup() {
		board = new Board();
	}

	@Test
	public void validate_withInvalidCoords_shouldBeInvalid() {
		BuildMoveValidator validator = new BuildMoveValidator(board);
		Move move = new Move(new Coord(0,0), new Coord(0,2), new Coord(0,0));
		assertFalse(validator.validate(move));
	}

}
