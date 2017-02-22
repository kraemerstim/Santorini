package game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import board.Board;
import board.BoardSerializer;
import board.Color;
import board.Coord;
import game.IsGameOverValidator;
import move.WorkerMove;
import player.ConsolePlayer;
import player.IPlayer;
import player.PlayerManager;

public class IsGameOverValidatorTest {

	private IsGameOverValidator isGameOverValidator;
	private BoardSerializer boardSerializer;

	@Before
	public void setup() {
		IPlayer player1 = new ConsolePlayer(Color.BLUE);
		IPlayer player2 = new ConsolePlayer(Color.WHITE);
		isGameOverValidator = new IsGameOverValidator(new PlayerManager(player1, player2));
		boardSerializer = new BoardSerializer();
	}

	@Test
	public void validate_withWinningMoveCoords_shouldBeValid() {
		Board board = boardSerializer.deserialize("2b30000000000000000000000");
		WorkerMove workerMove = new WorkerMove(new Coord(0, 0), new Coord(0, 1));
		assertTrue(isGameOverValidator.validate(board, workerMove));
	}

	@Test
	public void validate_withNotWinningMoveCoords_shouldBeInvalid() {
		Board board = boardSerializer.deserialize("1b0w0b0w00000000000000000000");
		WorkerMove workerMove = new WorkerMove(new Coord(0, 0), new Coord(0, 1));
		assertFalse(isGameOverValidator.validate(board, workerMove));
	}

	@Test
	public void validate_withLastMove_shouldBeValid() {
		Board board = boardSerializer.deserialize("3b33b3w3w44444444444444444444");
		WorkerMove workerMove = new WorkerMove(new Coord(0, 0), new Coord(0, 1));
		assertTrue(isGameOverValidator.validate(board, workerMove));
	}
}
