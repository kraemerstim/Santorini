package player;

import board.Board;
import board.Color;
import board.Coord;
import move.Move;

public interface IPlayer {

	public Move nextMove(Board board);

	public Coord nextWorkerPlacement(Board board);

	public Color getColor();

}
