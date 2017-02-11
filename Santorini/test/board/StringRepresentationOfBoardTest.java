package board;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StringRepresentationOfBoardTest {

	private Board board;

	@Before
	public void setup() throws Exception {
		board = new Board();
		board.setWorker(new Coord(0, 0), Color.Blue);
		board.setWorker(new Coord(0, 3), Color.White);
	}

	@Test
	public void BoardReturnsExpectedStringRepresentations() throws Exception {
		assertEquals("0b000w000000000000000000000", board.getBoardString());
		board.setWorker(new Coord(3, 0), Color.Blue);
		board.setWorker(new Coord(3, 3), Color.White);
		assertEquals("0b000w000000000000b000w000000", board.getBoardString());
	}

	@Test
	public void BoardIsCorrectSetByStringRepresentations() throws Exception {
		board.setBoardFromString("1b2w34w000000000000000000003b");
		assertEquals(board.getField(new Coord(0, 0)).getLevel(), 1);
		assertEquals(board.getField(new Coord(0, 0)).getWorkerColor(), Color.Blue);
		assertEquals(board.getField(new Coord(0, 1)).getLevel(), 2);
		assertEquals(board.getField(new Coord(0, 1)).getWorkerColor(), Color.White);
	}
}
