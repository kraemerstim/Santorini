package move;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import board.Board;
import board.BoardSerializer;
import board.Coord;
import common.Color;

public class MoveValidatorTest {

	private Board board;
	private MoveValidator mValidator;
	private BoardSerializer boardSerializer;
	
	@Before
	public void setup() {
		boardSerializer = new BoardSerializer();
		board = boardSerializer.deserialize("0b00000000000000000000000");
		mValidator = new MoveValidator(new WorkerMoveValidator(board), new BuildMoveValidator(board), board);
	}
	
	@Test
	public void validate_withValidMove_shouldReturnTrue() {
		WorkerMove wMove = new WorkerMove(new Coord(0,0), new Coord(0,1));
		BuildMove bMove = new BuildMove(new Coord(0,0));
		assertTrue(mValidator.validate(Color.BLUE, wMove, bMove));
	}
	
	@Test
	public void validate_withUnreachableField_shouldBeInvalid() {
		WorkerMove wMove = new WorkerMove(new Coord(0,0), new Coord(0,1));
		BuildMove bMove = new BuildMove(new Coord(0, 3));
		assertFalse(mValidator.validate(Color.BLUE, wMove, bMove));
	}

}
