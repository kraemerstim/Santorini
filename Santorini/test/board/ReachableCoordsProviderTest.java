package board;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import board.Board;
import board.BoardSerializer;
import board.Coord;
import board.ReachableCoordsProvider;
import exceptions.InvalidBoardAlterationException;

public class ReachableCoordsProviderTest {

	private ReachableCoordsProvider reachableCoordsProvider;
	private BoardSerializer boardSerializer;

	@Before
	public void setup() {
		reachableCoordsProvider = new ReachableCoordsProvider();
		boardSerializer = new BoardSerializer();
	}

	@Test
	public void provide_withUnreachableCoords_shouldProvideEmptyArray() {
		Board board = boardSerializer.deserialize("0b444444444444444444444444");
		Coord fromCoord = new Coord(0, 0);
		assertEquals(0, reachableCoordsProvider.provide(board, fromCoord).length);
	}

	@Test
	public void provide_withReachableCoords_shouldProvideArrayWithReachableCoords() {
		Board board = boardSerializer.deserialize("0b144404444444444444444444");
		Coord fromCoord = new Coord(0, 0);
		Coord[] resultCoords = reachableCoordsProvider.provide(board, fromCoord);
		assertEquals(2, resultCoords.length);
		assertEquals(new Coord(0, 1), resultCoords[0]);
		assertEquals(new Coord(1, 0), resultCoords[1]);
	}

	@Test(expected = InvalidBoardAlterationException.class)
	public void provide_withEmptyField_shouldRaiseInvalidBoardAlterationException() {
		Board board = boardSerializer.deserialize("0144404444444444444444444");
		Coord fromCoord = new Coord(0, 0);
		reachableCoordsProvider.provide(board, fromCoord);
	}
}
