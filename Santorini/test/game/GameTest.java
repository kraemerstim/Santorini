package game;

import org.junit.Before;
import org.junit.Test;

import board.Color;
import board.Coord;
import exceptions.TooManySameColorWorkersOnBoardException;

public class GameTest {

	private Game game;

	@Before
	public void setup() {
		game = new Game();
	}

	@Test(expected = TooManySameColorWorkersOnBoardException.class)
	public void setThirdWorkerOfSameColorShouldFail() throws Exception {
		for (int i = 0; i < 3; i++)
			game.setWorker(new Coord(0, i), Color.BLUE);
	}
}
