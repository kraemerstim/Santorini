package view;

import board.Board;
import board.Color;
import player.IPlayer;

public class ConsoleViewer implements IViewer {

	@Override
	public void showBoard(Board board) {
		printBoardHeader();
		printBoard(board);
	}

	@Override
	public void showNextWorkerMove(IPlayer player) {
		showMessage("Player " + player.getColor() + ", it's your turn!");
		showMessage(
				"Enter two coords for the worker move. First coord for the current position, second for the new position. For example: 1112");
	}

	@Override
	public void showNextBuildMove(IPlayer player) {
		showMessage("Player " + player.getColor() + ", it's your turn!");
		showMessage(
				"Enter two coords for the build move. First coord for the worker position, second for the build position. For example: 1222");
	}

	@Override
	public void showNextPlayerWorkerPlacement(IPlayer player) {
		showMessage("Player " + player.getColor() + ", it's your turn!");
		showMessage(
				"Enter a coord for the new worker as a two diget number. First diget for x, second for y. For example: 03");
	}

	@Override
	public void showWinner(IPlayer player) {
		showMessage("Player " + player.getColor() + " has won!");
	}

	private void showMessage(String message) {
		System.out.println(message);
	}

	private void printBoardHeader() {
		System.out.println("   0   1   2   3   4");
	}

	private void printBoard(Board board) {
		for (int i = 0; i < board.getBoardSize(); i++) {
			printBoardRow(board, i);
		}
	}

	private void printBoardRow(Board board, int i) {
		StringBuilder line = new StringBuilder();
		line.append(String.valueOf(i));

		for (int j = 0; j < board.getBoardSize(); j++) {
			line.append("| " + String.valueOf(board.getField(j, i).getLevel()));
			if (board.getField(j, i).getWorkerColor() == Color.Blue)
				line.append("b");
			else if (board.getField(j, i).getWorkerColor() == Color.White)
				line.append("w");
			else
				line.append(" ");
		}
		showMessage(line.toString());
		showMessage("--------------------");
	}
}
