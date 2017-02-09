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
	public void showNextPlayer(IPlayer player) {
		showMessage("Player " + player.getColor() + ", it's your turn!");
		showMessage("Enter three coords, 1. current field, . field to move, 3. field to build. For example: (1,1),(1,2),(2,2)");
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
		for (int i = 0; i < Board.BOARDSIZE; i++) {
			printBoardColumn(board, i);
	    }
	}

	private void printBoardColumn(Board board, int i) {
		StringBuilder line = new StringBuilder();
		line.append(String.valueOf(i));

		for (int j = 0; j < Board.BOARDSIZE; j++) {
			line.append("| " + String.valueOf(board.getField(i, j).getLevel()));
			if (board.getField(i, j).getWorkerColor() == Color.Blue)
				line.append("b");
			else if (board.getField(i, j).getWorkerColor() == Color.White)
				line.append("w");
			else
				line.append(" ");
		}
		showMessage(line.toString());
		showMessage("--------------------");
	}

}
