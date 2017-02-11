package board;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import board.Board;
import exceptions.FieldAlreadyOccupiedException;

public class AlterBoardTest {

	private Board board;

	@Before
	public void setup() {
		board = new Board();
		try {
			board.setWorker(new Coord(1, 1), Color.Blue);
		} catch (FieldAlreadyOccupiedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void buildFirstBlockOnUnoccupiedSpaceShouldShowBlockOnField() throws Exception {
		assertEquals(0, board.getField(0, 0).getLevel());
		board.setBlock(0, 0);
		assertEquals(1, board.getField(0, 0).getLevel());
	}

	@Test
	public void setWorkerProvidingWorkerOnField() throws Exception {
		assertEquals(Color.None, board.getField(0, 0).getWorkerColor());
		board.setWorker(new Coord(0, 0), Color.Blue);
		assertEquals(Color.Blue, board.getField(0, 0).getWorkerColor());
	}

	@Test(expected = FieldAlreadyOccupiedException.class)
	public void setSecondWorkerOnSameFieldShouldFail() throws Exception {
		board.setWorker(new Coord(0, 0), Color.Blue);
		board.setWorker(new Coord(0, 0), Color.White);
	}

	@Test
	public void removeWorkerFromBoardTest() throws Exception {
		assertEquals(Color.Blue, board.getField(1, 1).getWorkerColor());
		board.removeWorker(new Coord(1, 1));
		assertEquals(Color.None, board.getField(1, 1).getWorkerColor());
	}

}
