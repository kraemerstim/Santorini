package board;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FieldTest {

	private Field field;

	@Before
	public void setup() {
		field = new Field();
	}

	@Test
	public void isEmtpy_withEmptyField_ShouldReturnTrue() {
		field.setWorkerColor(Color.NONE);
		assertTrue(field.isEmpty());
	}

	@Test
	public void isEmtpy_withNotEmptyField_ShouldReturnFalse() {
		field.setWorkerColor(Color.BLUE);
		assertFalse(field.isEmpty());
	}

}
