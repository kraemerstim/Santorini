package board;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import exceptions.FieldAlreadyOccupiedException;

public class BoardSerializerTest {

	BoardSerializer boardSerializer;

	@Before
	public void setup() {
		boardSerializer = new BoardSerializer();
	}

	@Test
	public void deserialize_withStringRepresentation_shouldCreateBoardAsStringRepresentation() {
		Board board = boardSerializer.deserialize("01w02w03b04b00000000000000000");
		assertEquals(board.getField(new Coord(0, 0)).getLevel(), 0);
		assertEquals(board.getField(new Coord(0, 0)).getWorkerColor(), Color.None);
		assertEquals(board.getField(new Coord(0, 1)).getLevel(), 1);
		assertEquals(board.getField(new Coord(0, 1)).getWorkerColor(), Color.White);
		assertEquals(board.getField(new Coord(0, 2)).getLevel(), 0);
		assertEquals(board.getField(new Coord(0, 2)).getWorkerColor(), Color.None);
		assertEquals(board.getField(new Coord(0, 3)).getLevel(), 2);
		assertEquals(board.getField(new Coord(0, 3)).getWorkerColor(), Color.White);
		assertEquals(board.getField(new Coord(0, 4)).getLevel(), 0);
		assertEquals(board.getField(new Coord(0, 4)).getWorkerColor(), Color.None);
		assertEquals(board.getField(new Coord(1, 0)).getLevel(), 3);
		assertEquals(board.getField(new Coord(1, 0)).getWorkerColor(), Color.Blue);
		assertEquals(board.getField(new Coord(1, 1)).getLevel(), 0);
		assertEquals(board.getField(new Coord(1, 1)).getWorkerColor(), Color.None);
		assertEquals(board.getField(new Coord(1, 2)).getLevel(), 4);
		assertEquals(board.getField(new Coord(1, 2)).getWorkerColor(), Color.Blue);
		assertEquals(board.getField(new Coord(1, 3)).getLevel(), 0);
		assertEquals(board.getField(new Coord(1, 3)).getWorkerColor(), Color.None);
		assertEquals(board.getField(new Coord(1, 4)).getLevel(), 0);
		assertEquals(board.getField(new Coord(1, 4)).getWorkerColor(), Color.None);

		for (int i = 2; i < board.getBoardSize(); i++)
			for (int j = 0; j < board.getBoardSize(); j++) {
				assertEquals(board.getField(new Coord(2, 0)).getLevel(), 0);
				assertEquals(board.getField(new Coord(2, 0)).getWorkerColor(), Color.None);
			}
	}

	@Test
	public void serialize_withBoard_shouldReturnBoardAsStringRepresentation() throws FieldAlreadyOccupiedException {
		Board board = new Board();
		board.setWorker(new Coord(3, 0), Color.Blue);
		board.setWorker(new Coord(3, 3), Color.White);
		assertEquals("0000000000000000b000w000000", boardSerializer.serialize(board));
	}

}
