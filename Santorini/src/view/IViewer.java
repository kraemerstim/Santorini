package view;

import board.Board;
import player.IPlayer;

public interface IViewer {

	public void showBoard(Board board);

	public void showNextWorkerMove(IPlayer player);
	public void showNextBuildMove(IPlayer player);

	public void showNextPlayerWorkerPlacement(IPlayer player);

	public void showWinner(IPlayer player);

}
