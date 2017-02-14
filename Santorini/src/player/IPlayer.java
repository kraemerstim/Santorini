package player;

import board.Board;
import board.Color;
import board.Coord;
import move.BuildMove;

public interface IPlayer {

	public BuildMove nextMove(Board board);

	public Coord nextWorkerPlacement(Board board);

	public Color getColor();

}
