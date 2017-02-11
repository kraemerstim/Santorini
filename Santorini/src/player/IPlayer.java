package player;

import board.Board;
import board.Color;
import move.Move;

public interface IPlayer {

	public Move nextMove(Board board);

	public Color getColor();

}
