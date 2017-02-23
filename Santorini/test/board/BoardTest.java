package board;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import board.Board;
import common.Color;
import exceptions.InvalidBoardAlterationException;

public class BoardTest {

	private Board board;
	private BoardSerializer boardSerializer;

	@Before
	public void setup() {
		board = new Board();
		boardSerializer = new BoardSerializer();
	}

	@Test
	public void createBoard_withoutCloning_shouldCreateEmptyBoard() {
		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 5; j++) {
				assertEquals(0, board.getField(i, j).getLevel());
				assertEquals(Color.NONE, board.getField(i, j).getWorkerColor());
			}
	}

	@Test
	public void createBoard_withCloning_shouldReturnBoardWithSameStringRepresentation() {
		String s = "1b2w34w000000000000000000000";
		board = boardSerializer.deserialize(s);
		Board clonedBoard = new Board(board);
		assertEquals(s, boardSerializer.serialize(clonedBoard));
	}

	@Test
	public void setBlock_withFirstBlockOnUnoccupiedField_shouldSetBlockOnField() throws Exception {
		assertEquals(0, board.getField(0, 0).getLevel());
		board.setBlock(new Coord(0, 0));
		assertEquals(1, board.getField(0, 0).getLevel());
	}

	@Test
	public void setWorker_withFirstWorkerOnUnoccupiedField_shouldSetWorkerOnField() {
		assertEquals(Color.NONE, board.getField(0, 0).getWorkerColor());
		board.setWorker(new Coord(0, 0), Color.BLUE);
		assertEquals(Color.BLUE, board.getField(0, 0).getWorkerColor());
	}

	@Test
	public void setWorker_withTwoWorkers_shouldReturnExpectedStringRepresentation() {
		assertEquals("0000000000000000000000000", boardSerializer.serialize(board));
		board.setWorker(new Coord(3, 0), Color.BLUE);
		board.setWorker(new Coord(3, 3), Color.WHITE);
		assertEquals("0000000000000000b000w000000", boardSerializer.serialize(board));
	}

	@Test(expected = InvalidBoardAlterationException.class)
	public void setWorker_withSecondWorkerOnSameField_shouldFail() throws Exception {
		board.setWorker(new Coord(0, 0), Color.BLUE);
		board.setWorker(new Coord(0, 0), Color.WHITE);
	}

	@Test
	public void removeWorker_withWorkerOnField_shouldRemoveWorkerFromBoard() {
		board.setWorker(new Coord(1, 1), Color.BLUE);
		assertEquals(Color.BLUE, board.getField(1, 1).getWorkerColor());
		board.removeWorker(new Coord(1, 1));
		assertEquals(Color.NONE, board.getField(1, 1).getWorkerColor());
	}

}
