package boardtests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import board.*;
import exceptions.FieldAlreadyOccupiedException;
import exceptions.TooManySameColorWorkersOnBoardException;

public class InitTest {

	private Board board;

	@Before
	public void setup() {
		board = new Board();		
	}
	
	@Test
	public void newBoardReturningEmptyBoard() {
		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 5; j++)
			{
				assertEquals(0, board.getField(i, j).getLevel());
				assertEquals(Color.None, board.getField(i, j).getWorkerColor());
			}
	}
	
	@Test
	public void setFirstWorkerProvidingWorkerOnField() throws Exception {
		board.setWorker(0, 0, Color.Blue);
		assertEquals(Color.Blue, board.getField(0, 0).getWorkerColor());
	}
	
	@Test(expected=FieldAlreadyOccupiedException.class)
	public void setSecondWorkerOnSameFieldShouldFail() throws Exception
	{
		board.setWorker(0, 0, Color.Blue);
		board.setWorker(0, 0, Color.White);
	}
	
	
	@Test(expected=TooManySameColorWorkersOnBoardException.class)
	public void setThirdWorkerOfSameColorShouldFail() throws Exception {
		for (int i = 0; i < 3; i++)
			board.setWorker(0, i, Color.Blue);
	}
	
	@Test
	public void buildFirstBlockOnUnoccupiedSpaceShouldShowBlockOnField() throws Exception {
				
	}

}
