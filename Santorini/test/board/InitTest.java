package board;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class InitTest {

	private Board board;
	private BoardSerializer boardSerializer;

	@Before
	public void setup() {
		board = new Board();
		boardSerializer = new BoardSerializer();
	}

	@Test
	public void newBoardReturningEmptyBoard() {
		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 5; j++) {
				assertEquals(0, board.getField(i, j).getLevel());
				assertEquals(Color.NONE, board.getField(i, j).getWorkerColor());
			}
	}

	@Test
	public void boardReturnsExpectedStringRepresentations() throws Exception {
		assertEquals("0000000000000000000000000", boardSerializer.serialize(board));
		board.setWorker(new Coord(3, 0), Color.BLUE);
		board.setWorker(new Coord(3, 3), Color.WHITE);
		assertEquals("0000000000000000b000w000000", boardSerializer.serialize(board));
	}

	@Test
	public void cloningBoardReturningSameStringRepresentation() {
		String s = "1b2w34w000000000000000000003b";
		board = boardSerializer.deserialize(s);
		Board clonedBoard = new Board(board);
		assertEquals(s, boardSerializer.serialize(clonedBoard));
	}
}
