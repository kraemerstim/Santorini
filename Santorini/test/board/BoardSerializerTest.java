package board;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import exceptions.InvalidBoardAlterationException;

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
		assertEquals(board.getField(new Coord(0, 0)).getWorkerColor(), Color.NONE);
		assertEquals(board.getField(new Coord(0, 1)).getLevel(), 1);
		assertEquals(board.getField(new Coord(0, 1)).getWorkerColor(), Color.WHITE);
		assertEquals(board.getField(new Coord(0, 2)).getLevel(), 0);
		assertEquals(board.getField(new Coord(0, 2)).getWorkerColor(), Color.NONE);
		assertEquals(board.getField(new Coord(0, 3)).getLevel(), 2);
		assertEquals(board.getField(new Coord(0, 3)).getWorkerColor(), Color.WHITE);
		assertEquals(board.getField(new Coord(0, 4)).getLevel(), 0);
		assertEquals(board.getField(new Coord(0, 4)).getWorkerColor(), Color.NONE);
		assertEquals(board.getField(new Coord(1, 0)).getLevel(), 3);
		assertEquals(board.getField(new Coord(1, 0)).getWorkerColor(), Color.BLUE);
		assertEquals(board.getField(new Coord(1, 1)).getLevel(), 0);
		assertEquals(board.getField(new Coord(1, 1)).getWorkerColor(), Color.NONE);
		assertEquals(board.getField(new Coord(1, 2)).getLevel(), 4);
		assertEquals(board.getField(new Coord(1, 2)).getWorkerColor(), Color.BLUE);
		assertEquals(board.getField(new Coord(1, 3)).getLevel(), 0);
		assertEquals(board.getField(new Coord(1, 3)).getWorkerColor(), Color.NONE);
		assertEquals(board.getField(new Coord(1, 4)).getLevel(), 0);
		assertEquals(board.getField(new Coord(1, 4)).getWorkerColor(), Color.NONE);

		for (int i = 2; i < board.getBoardSize(); i++)
			for (int j = 0; j < board.getBoardSize(); j++) {
				assertEquals(board.getField(new Coord(2, 0)).getLevel(), 0);
				assertEquals(board.getField(new Coord(2, 0)).getWorkerColor(), Color.NONE);
			}
	}

	@Test
	public void serialize_withBoard_shouldReturnBoardAsStringRepresentation() throws InvalidBoardAlterationException {
		Board board = new Board();
		board.setWorker(new Coord(3, 0), Color.BLUE);
		board.setWorker(new Coord(3, 3), Color.WHITE);
		assertEquals("0000000000000000b000w000000", boardSerializer.serialize(board));
	}

}
