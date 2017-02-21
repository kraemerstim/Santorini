package board;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BoardValidatorTest {

	private Board board;
	private BoardValidator boardValidator;

	@Before
	public void setup() {
		board = new Board();
		boardValidator = new BoardValidator();
	}

	@Test
	public void validate_withEmptyBoard_ShouldBeValid() {
		assertTrue(boardValidator.validate(board));
	}

	@Test
	public void validate_withValidLevels_ShouldBeValid() {
		board.getField(0, 0).setLevel(1);
		board.getField(1, 1).setLevel(2);
		board.getField(2, 2).setLevel(3);
		board.getField(3, 3).setLevel(4);
		assertTrue(boardValidator.validate(board));
	}

	@Test
	public void validate_withInvalidMinLevel_ShouldBeInvalid() {
		board.getField(0, 0).setLevel(-1);
		assertFalse(boardValidator.validate(board));
	}

	@Test
	public void validate_withInvalidMaxLevel_ShouldBeInvalid() {
		board.getField(0, 0).setLevel(5);
		assertFalse(boardValidator.validate(board));
	}

}
