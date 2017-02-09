package game;

import java.util.Scanner;

import board.Board;
import board.Color;
import move.BuildMoveValidator;
import move.Move;
import move.MoveValidator;
import move.IMoveValidator;
import move.WorkerMoveValidator;
import player.IPlayer;
import player.IPlayerManager;
import player.PlayerManager;
import view.ConsoleViewer;
import view.IViewer;
import player.ConsolePlayer;

public class Game {

	private Scanner scanner;

	private Board board;

	private IPlayerManager playerMangager;
	private IViewer viewer;

	public Game() {
		initGame();
	}

	private void initGame() {
		viewer = new ConsoleViewer();
		//TODO scanner sollte nicht im Game sein sondern im ConsolePlayer
		scanner = new Scanner(System.in);
		playerMangager = new PlayerManager(new ConsolePlayer(scanner, Color.Blue), new ConsolePlayer(scanner, Color.White));

		board = new Board();
		//TODO bzw. die ganze Klasse ist eigentlich ein Testcase
		board.getField(1,1).setWorkerColor(Color.Blue);
		board.getField(2,3).setWorkerColor(Color.Blue);
		board.getField(1,2).setWorkerColor(Color.White);
		board.getField(3,3).setWorkerColor(Color.White);
	}

	public void start() {
		while (notGameOver());
		viewer.showWinner(playerMangager.getCurrentPlayer());
		scanner.close();
	}

	//TODO Methodennamen überdenken
	private boolean notGameOver() {
		viewer.showBoard(board);

		Move move = getNextMove();
		doMove(move);

		return !isGameOver(move);
	}

	private Move getNextMove() {
		Move move;
		IPlayer currentPlayer;

		IMoveValidator moveValidator = new MoveValidator(new WorkerMoveValidator(board), new BuildMoveValidator(board));
		playerMangager.next();
		do {
			currentPlayer = playerMangager.getCurrentPlayer();
			viewer.showNextPlayer(currentPlayer);
			move = currentPlayer.nextMove(board);
		} while (!moveValidator.validate(currentPlayer, move));
		return move;
	}

	private boolean isGameOver(Move move) {
		return (isWinningMove(move)) || (isLastMove(move));
	}

	private boolean isLastMove(Move move) {
		// FIXME should check if next player can move
		return false;
	}

	private boolean isWinningMove(Move move) {
		return (board.getField(move.getFrom()).getLevel() == 2) && (board.getField(move.getTo()).getLevel() == 3);
	}

	private void doMove(Move move) {
		board.getField(move.getFrom()).setWorkerColor(Color.None);
		board.getField(move.getTo()).setWorkerColor(playerMangager.getCurrentPlayer().getColor());
		board.getField(move.getBuild()).setLevel(board.getField(move.getBuild()).getLevel() + 1);
	}

}
