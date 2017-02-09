package player;

import board.Board;
import board.Color;
import move.Move;

public interface IPlayer {

	Move nextMove(Board board);

	Color getColor();

}
