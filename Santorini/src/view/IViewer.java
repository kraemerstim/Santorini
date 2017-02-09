package view;

import board.Board;
import player.IPlayer;

public interface IViewer {

	public void showBoard(Board board);
	public void showNextPlayer(IPlayer player);
	public void showWinner(IPlayer player);

}
