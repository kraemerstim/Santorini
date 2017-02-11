package game;

import java.util.ArrayList;
import java.util.Scanner;

import board.Board;
import board.Color;
import board.Coord;
import board.Field;
import exceptions.NoWorkerOnFieldException;
import exceptions.TooManySameColorWorkersOnBoardException;
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

	private IPlayerManager playerManager;
	private IViewer viewer;

	private Status status;

	public Game() {
		initGame();
	}

	private void initGame() {
		viewer = new ConsoleViewer();
		// TODO scanner sollte nicht im Game sein sondern im ConsolePlayer
		scanner = new Scanner(System.in);
		playerManager = new PlayerManager(new ConsolePlayer(scanner, Color.Blue),
				new ConsolePlayer(scanner, Color.White));

		board = new Board();

		status = Status.preGame;

		// TODO bzw. die ganze Klasse ist eigentlich ein Testcase
		board.getField(1, 1).setWorkerColor(Color.Blue);
		board.getField(2, 3).setWorkerColor(Color.Blue);
		board.getField(1, 2).setWorkerColor(Color.White);
		board.getField(3, 3).setWorkerColor(Color.White);
		status = Status.GamePhase;
	}

	public void start() {
		while (status == Status.GamePhase)
			try {
				waitForNextMove();
			} catch (NoWorkerOnFieldException e) {

			}
		if (status == Status.GameFinished)
			viewer.showWinner(playerManager.getCurrentPlayer());
		scanner.close();
	}

	public void setWorker(Coord coord, Color color) throws Exception {
		if (board.getWorkerCountByColor(color) >= 2)
			throw new TooManySameColorWorkersOnBoardException();

		board.setWorker(coord, color);
	}

	private void waitForNextMove() throws NoWorkerOnFieldException {
		viewer.showBoard(board);

		Move move = getNextMove();
		doMove(move);

		if (isGameOver(move))
			status = Status.GameFinished;
	}

	private Move getNextMove() {
		Move move;
		IPlayer currentPlayer;

		IMoveValidator moveValidator = new MoveValidator(new WorkerMoveValidator(board), new BuildMoveValidator(board));
		playerManager.next();
		do {
			currentPlayer = playerManager.getCurrentPlayer();
			viewer.showNextPlayer(currentPlayer);
			move = currentPlayer.nextMove(board);
		} while (!moveValidator.validate(currentPlayer, move));
		return move;
	}

	private boolean isGameOver(Move move) throws NoWorkerOnFieldException {
		return (isWinningMove(move)) || (isLastMove(move));
	}

	private boolean isLastMove(Move move) throws NoWorkerOnFieldException {
		int count = 0;
		Coord[] coords = board.getCoordsWithWorkers(playerManager.getFollowingPlayer().getColor());
		for (Coord coord : coords) {
			count += getReachableCoords(coord).length;
		}
		return count == 0;
	}

	private Coord[] getReachableCoords(Coord fromCoord) throws NoWorkerOnFieldException {
		ArrayList<Coord> coords = new ArrayList<>();
		WorkerMoveValidator validator = new WorkerMoveValidator(board);
		IPlayer player = playerManager.getPlayerByColor(board.getField(fromCoord).getWorkerColor());
		if (player == null)
			throw new NoWorkerOnFieldException();
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				int newX = fromCoord.getX() + i;
				int newY = fromCoord.getY() + j;
				if ((i == 0 && j == 0) || newX >= board.getBoardSize() || newX < 0 || newY >= board.getBoardSize()
						|| newY < 0)
					continue;
				Coord reachableCoord = new Coord(fromCoord.getX() + i, fromCoord.getY() + j);
				if (validator.validate(player, new Move(fromCoord, new Coord(newX, newY), fromCoord)))
					coords.add(reachableCoord);
			}
		}
		return coords.toArray(new Coord[coords.size()]);
	}

	private boolean isWinningMove(Move move) {
		return (board.getField(move.getFrom()).getLevel() == 2) && (board.getField(move.getTo()).getLevel() == 3);
	}

	private void doMove(Move move) {
		board.getField(move.getFrom()).setWorkerColor(Color.None);
		board.getField(move.getTo()).setWorkerColor(playerManager.getCurrentPlayer().getColor());
		board.getField(move.getBuild()).setLevel(board.getField(move.getBuild()).getLevel() + 1);
	}

	public void loadBoard(String s) {
		Board board = new Board();
		board.setBoardFromString(s);
		if (board.isBoardValid())
			this.board = board;
	}
}
