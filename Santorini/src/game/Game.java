package game;

import board.Board;
import board.Color;
import player.IPlayer;
import player.ConsolePlayer;

public class Game {

	private Board board;

	private int currentPlayer = 0;
	private static final int PLAYER_COUNT = 2;

	private IPlayer player1;
	private IPlayer player2;


	public Game() {
		board = new Board();
		initGame();

		player1 = new ConsolePlayer();
		player2 = new ConsolePlayer();
	}

	private void initGame() {
		board.getField(1,1).setWorkerColor(Color.Blue);
		board.getField(2,3).setWorkerColor(Color.Blue);
		board.getField(1,2).setWorkerColor(Color.White);
		board.getField(3,3).setWorkerColor(Color.White);
	}

	public void start() {
		while (notGameOver());
		System.out.println("Player " + (currentPlayer+1) + " has won!");
	}

	private boolean notGameOver() {
		printBoard();

		Move move = getNextMove();
		doMove(move);

		return !isGameOver(move);
	}

	private Move getNextMove() {
		Move move;
		MoveValidator moveValidator = new MoveValidator(board);
		toggleCurrentPlayer();
		do {
			System.out.println("Player " + (currentPlayer+1) + " (" + getColorByPlayer().toString() + "), it's your turn!");
			System.out.println("Enter three coords, 1. current field, 2. field to move, 3. field to build. For example: (1,1),(1,2),(2,2)");
			if (currentPlayer == 0)
				move = player1.nextMove(board);
			else
				move = player2.nextMove(board);
		} while (!moveValidator.isValid(move));
		return move;
	}

	private void toggleCurrentPlayer() {
		currentPlayer = (currentPlayer++)%PLAYER_COUNT;
	}

	private boolean isGameOver(Move move) {
		return (isWinningMove(move)) || (isLastMove(move));
	}

	private boolean isLastMove(Move move) {
		// TODO should check if next player can move
		return false;
	}

	private boolean isWinningMove(Move move) {
		return (board.getField(move.getFrom()).getLevel() == 2) && (board.getField(move.getTo()).getLevel() == 3);
	}

	private void doMove(Move move) {
		board.getField(move.getFrom()).setWorkerColor(Color.None);
		board.getField(move.getTo()).setWorkerColor(getColorByPlayer());
		board.getField(move.getBuild()).setLevel(board.getField(move.getBuild()).getLevel() + 1);
	}

	private Color getColorByPlayer() {
		if (currentPlayer == 1)
			return Color.Blue;
		else
			return Color.White;
	}

	private void printBoard() {
		printFirstLine();
		for (int i = 0; i < Board.BOARDSIZE; i++) {
			printLine(i);
	    }
	}

	private void printLine(int i) {
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
		System.out.println(line.toString());
		System.out.println("--------------------");
	}

	private void printFirstLine() {
        System.out.println("   0   1   2   3   4");
	}




}
