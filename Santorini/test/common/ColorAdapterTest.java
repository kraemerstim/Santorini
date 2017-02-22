package common;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ColorAdapterTest {

	private ColorAdapter colorAdapter;

	@Before
	public void setup() {
		colorAdapter = new ColorAdapter();
	}

	@Test
	public void toString_WithEmptyString_ShouldReturnColorNone() {
		assertEquals(Color.NONE, colorAdapter.toColor(""));
	}

	@Test
	public void toString_WithB_ShouldReturnColorBlue() {
		assertEquals(Color.BLUE, colorAdapter.toColor("b"));
	}

	@Test
	public void toString_WithW_ShouldReturnColorBlue() {
		assertEquals(Color.WHITE, colorAdapter.toColor("w"));
	}

	@Test
	public void toColor_WithColorNone_ShouldReturnEmptyString() {
		assertEquals("", colorAdapter.toString(Color.NONE));
	}

	@Test
	public void toColor_WithColorBlue_ShouldReturnB() {
		assertEquals("b", colorAdapter.toString(Color.BLUE));
	}

	@Test
	public void toColor_WithColorWhite_ShouldReturnW() {
		assertEquals("w", colorAdapter.toString(Color.WHITE));
	}

}
