package player;

import board.Board;
import game.Move;

public interface IPlayer {

	Move nextMove(Board board);

}
