package game;

import static org.junit.Assert.*;

import org.junit.Test;

import board.Board;
import board.BoardSerializer;
import board.Coord;
import exceptions.InvalidBoardAlterationException;

public class ReachableCoordsProviderTest {

	@Test
	public void provide_withUnreachableCoords_shouldProvideEmptyArray() {
		ReachableCoordsProvider provider = new ReachableCoordsProvider();
		BoardSerializer boardSerializer = new BoardSerializer();
		Board board = boardSerializer.deserialize("0b444444444444444444444444");
		Coord fromCoord = new Coord(0, 0);
		assertEquals(0, provider.provide(board, fromCoord).length);
	}

	@Test
	public void provide_withReachableCoords_shouldProvideArrayWithReachableCoords() {
		ReachableCoordsProvider provider = new ReachableCoordsProvider();
		BoardSerializer boardSerializer = new BoardSerializer();
		Board board = boardSerializer.deserialize("0b144404444444444444444444");
		Coord fromCoord = new Coord(0, 0);
		Coord[] resultCoords = provider.provide(board, fromCoord);
		assertEquals(2, resultCoords.length);
		assertEquals(new Coord(0, 1), resultCoords[0]);
		assertEquals(new Coord(1, 0), resultCoords[1]);
	}

	@Test(expected = InvalidBoardAlterationException.class)
	public void provide_withEmptyField_shouldRaiseInvalidBoardAlterationException() {
		ReachableCoordsProvider provider = new ReachableCoordsProvider();
		BoardSerializer boardSerializer = new BoardSerializer();
		Board board = boardSerializer.deserialize("0144404444444444444444444");
		Coord fromCoord = new Coord(0, 0);
		provider.provide(board, fromCoord);
	}
}
