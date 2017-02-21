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
	}

	@Override
	public void showNextBuildMove(IPlayer player) {
		showMessage("Player " + player.getColor() + ", it's your turn!");
	}

	@Override
	public void showNextPlayerWorkerPlacement(IPlayer player) {
		showMessage("Player " + player.getColor() + ", it's your turn!");
	}

	@Override
	public void showWinner(IPlayer player) {
		showMessage("Player " + player.getColor() + " has won!");
	}

	public void showMessage(String message) {
		System.out.println(message);
	}

	private void printBoardHeader() {
		showMessage("    1   2   3   4   5");
		showMessage("----------------------");
	}

	private void printBoard(Board board) {
		for (int i = 0; i < board.getBoardSize(); i++) {
			printBoardRow(board, i);
		}
	}

	private void printBoardRow(Board board, int i) {
		StringBuilder line = new StringBuilder();
		line.append(Character.valueOf((char) ('a' + i)) + " ");

		for (int j = 0; j < board.getBoardSize(); j++) {
			line.append("| " + String.valueOf(board.getField(i, j).getLevel()));
			if (board.getField(i, j).getWorkerColor() == Color.BLUE)
				line.append("b");
			else if (board.getField(i, j).getWorkerColor() == Color.WHITE)
				line.append("w");
			else
				line.append(" ");
		}
		showMessage(line.toString());
		showMessage("----------------------");
	}
}
