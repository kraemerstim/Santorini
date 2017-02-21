package board;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import board.Board;
import exceptions.InvalidBoardAlterationException;

public class AlterBoardTest {

	private Board board;

	@Before
	public void setup() {
		board = new Board();
		try {
			board.setWorker(new Coord(1, 1), Color.BLUE);
		} catch (InvalidBoardAlterationException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void buildFirstBlockOnUnoccupiedSpaceShouldShowBlockOnField() throws Exception {
		assertEquals(0, board.getField(0, 0).getLevel());
		board.setBlock(new Coord(0, 0));
		assertEquals(1, board.getField(0, 0).getLevel());
	}

	@Test
	public void setWorkerProvidingWorkerOnField() throws Exception {
		assertEquals(Color.NONE, board.getField(0, 0).getWorkerColor());
		board.setWorker(new Coord(0, 0), Color.BLUE);
		assertEquals(Color.BLUE, board.getField(0, 0).getWorkerColor());
	}

	@Test(expected = InvalidBoardAlterationException.class)
	public void setSecondWorkerOnSameFieldShouldFail() throws Exception {
		board.setWorker(new Coord(0, 0), Color.BLUE);
		board.setWorker(new Coord(0, 0), Color.WHITE);
	}

	@Test
	public void removeWorkerFromBoardTest() throws Exception {
		assertEquals(Color.BLUE, board.getField(1, 1).getWorkerColor());
		board.removeWorker(new Coord(1, 1));
		assertEquals(Color.NONE, board.getField(1, 1).getWorkerColor());
	}

}
