package game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import board.Board;
import board.BoardSerializer;
import common.Color;
import game.GameOverValidator;
import player.ConsolePlayer;
import player.IPlayer;

public class GameOverValidatorTest {

	private GameOverValidator isGameOverValidator;
	private BoardSerializer boardSerializer;
	private IPlayer bluePlayer;
	private IPlayer whitePlayer;

	@Before
	public void setup() {
		boardSerializer = new BoardSerializer();
		bluePlayer = new ConsolePlayer(0, Color.BLUE);
		whitePlayer = new ConsolePlayer(1, Color.WHITE);
	}

	@Test
	public void validate_withReachableFields_shouldBeInvalid() {
		Board board = boardSerializer.deserialize("0b0b0w0w000000000000000000000");
		isGameOverValidator = new GameOverValidator(board);
		assertFalse(isGameOverValidator.validate(bluePlayer));
	}

	@Test
	public void validate_withNoReachableFields_shouldBeValid() {
		Board board = boardSerializer.deserialize("4b4b4w4w444444444444444444444");
		isGameOverValidator = new GameOverValidator(board);
		assertTrue(isGameOverValidator.validate(bluePlayer));
	}

	@Test
	public void validate_withOnlyOnePlayerCanMove_shouldBeValidForThisPlayer() {
		Board board = boardSerializer.deserialize("000w0w00044444444444444444b4b");
		isGameOverValidator = new GameOverValidator(board);
		assertTrue(isGameOverValidator.validate(bluePlayer));
		assertFalse(isGameOverValidator.validate(whitePlayer));
	}

}
