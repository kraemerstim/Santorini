package view;

import board.Board;
import player.IPlayer;

public interface IViewer {

	public void showBoard(Board board);

	public void showNextPlayerMove(IPlayer player);

	public void showNextPlayerWorkerPlacement(IPlayer player);

	public void showWinner(IPlayer player);

}
