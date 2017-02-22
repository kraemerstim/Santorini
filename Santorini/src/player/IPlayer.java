package player;

import board.Board;
import board.Coord;
import common.Color;
import move.BuildMove;
import move.WorkerMove;

public interface IPlayer {

	public BuildMove nextBuildMove(Board board);

	public WorkerMove nextWorkerMove(Board board);

	public Coord nextWorkerPlacement(Board board);

	public Color getColor();

}
