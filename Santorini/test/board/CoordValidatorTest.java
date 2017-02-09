package board;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CoordValidatorTest {

	ICoordValidator validator;

	@Before
	public void setup() {
		validator = new CoordValidator(5);
	}

	@Test
	public void validate_withNegativeCoord_ShouldBeInvalid() {
		Coord coord = new Coord(-1,0);
		assertFalse(validator.validate(coord));
	}

	@Test
	public void validate_withZeroCoord_ShouldBeValid() {
		Coord coord = new Coord(0,0);
		assertTrue(validator.validate(coord));
	}

	@Test
	public void validate_withBoardsizeCoord_ShouldBeInvalid() {
		Coord coord = new Coord(5,5);
		assertFalse(validator.validate(coord));
	}

}
