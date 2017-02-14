package board;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class InitTest {

	private Board board;

	@Before
	public void setup() {
		board = new Board();
	}

	@Test
	public void newBoardReturningEmptyBoard() {
		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 5; j++) {
				assertEquals(0, board.getField(i, j).getLevel());
				assertEquals(Color.None, board.getField(i, j).getWorkerColor());
			}
	}

	@Test
	public void boardReturnsExpectedStringRepresentations() throws Exception {
		assertEquals("0000000000000000000000000", board.getBoardString());
		board.setWorker(new Coord(3, 0), Color.Blue);
		board.setWorker(new Coord(3, 3), Color.White);
		assertEquals("0000000000000000b000w000000", board.getBoardString());
	}

	@Test
	public void boardIsCorrectSetByStringRepresentations() throws Exception {
		board.setBoardFromString("1b2w34w000000000000000000003b");
		assertEquals(board.getField(new Coord(0, 0)).getLevel(), 1);
		assertEquals(board.getField(new Coord(0, 0)).getWorkerColor(), Color.Blue);
		assertEquals(board.getField(new Coord(0, 1)).getLevel(), 2);
		assertEquals(board.getField(new Coord(0, 1)).getWorkerColor(), Color.White);
	}

	@Test
	public void cloningBoardReturningSameStringRepresentation() {
		String s = "1b2w34w000000000000000000003b";
		board.setBoardFromString(s);
		Board clonedBoard = new Board(board);
		assertEquals(s, clonedBoard.getBoardString());
	}
}
