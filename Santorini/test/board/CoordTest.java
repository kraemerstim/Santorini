package board;

import static org.junit.Assert.*;

import org.junit.Test;

public class CoordTest {

	@Test
	public void isNeighbour_withSameCoords_shouldNotBeNeighbouring() {
		Coord coord = new Coord(0,0);
		assertFalse(coord.isNeighbour(coord));
	}

	@Test
	public void isNeighbour_withNeighbouringXCoords_shouldBeNeighbouring() {
		Coord first = new Coord(0,0);
		Coord second = new Coord(1,0);
		assertTrue(first.isNeighbour(second));
	}

	@Test
	public void isNeighbour_withNotNeighbouringXCoords_shouldNotBeNeighbouring() {
		Coord first = new Coord(0,0);
		Coord second = new Coord(2,0);
		assertFalse(first.isNeighbour(second));
	}

	@Test
	public void isNeighbour_withNeighbouringYCoords_shouldBeNeighbouring() {
		Coord first = new Coord(0,0);
		Coord second = new Coord(0,1);
		assertTrue(first.isNeighbour(second));
	}

	@Test
	public void isNeighbour_withNotNeighbouringYCoords_shouldNotBeNeighbouring() {
		Coord first = new Coord(0,0);
		Coord second = new Coord(0,2);
		assertFalse(first.isNeighbour(second));
	}

	@Test
	public void isNeighbour_withNeighbouringXAndYCoords_shouldBeNeighbouring() {
		Coord first = new Coord(0,0);
		Coord second = new Coord(1,1);
		assertTrue(first.isNeighbour(second));
	}

	@Test
	public void isNeighbour_withNotNeighbouringXAndYCoords_shouldNotBeNeighbouring() {
		Coord first = new Coord(0,0);
		Coord second = new Coord(2,2);
		assertFalse(first.isNeighbour(second));
	}

}
