package game;

import java.util.ArrayList;
import java.util.Scanner;

import board.Board;
import board.Color;
import board.Coord;
import board.CoordValidator;
import board.ICoordValidator;
import exceptions.NoWorkerOnFieldException;
import exceptions.TooManySameColorWorkersOnBoardException;
import move.BuildMoveValidator;
import move.BuildMove;
import move.MoveValidator;
import move.WorkerMove;
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
		status = Status.preGame;
		viewer = new ConsoleViewer();
		// TODO scanner sollte nicht im Game sein sondern im ConsolePlayer
		scanner = new Scanner(System.in);
		playerManager = new PlayerManager(new ConsolePlayer(scanner, Color.Blue),
				new ConsolePlayer(scanner, Color.White));

		board = new Board();
	}

	public void start() {
		status = Status.WorkerPlacementPhase;
		while (status != Status.GameFinished && status != Status.GameAborted) {
			switch (status) {
			case WorkerPlacementPhase:
				waitForNextWorkerPlacement();
				break;
			case GamePhase:
				waitForNextMove();
				break;
			default:
				break;
			}
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

	private void waitForNextMove() {
		viewer.showBoard(board);

		WorkerMove workerMove = getNextWorkerMove();
		doMove(workerMove);
		BuildMove buildMove = getNextBuildMove();
		doMove(buildMove);

		if (isGameOver(workerMove))
			status = Status.GameFinished;
	}

	private void waitForNextWorkerPlacement() {
		viewer.showBoard(board);

		Coord coord;
		Color playerColor = playerManager.getCurrentPlayer().getColor();
		while (board.getCoordsWithWorkers(playerColor).length < 2) {
			try {
				coord = getPlayerCoord();
				setWorker(coord, playerColor);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		playerManager.next();

		if (areAllWorkersSet())
			status = Status.GamePhase;
	}

	private boolean areAllWorkersSet() {
		int countBlue = board.getCoordsWithWorkers(Color.Blue).length;
		int countWhite = board.getCoordsWithWorkers(Color.White).length;
		return countBlue == 2 && countWhite == 2;
	}

	private Coord getPlayerCoord() {
		Coord nextWorkerCoord;
		IPlayer currentPlayer = playerManager.getCurrentPlayer();

		ICoordValidator validator = new CoordValidator(board.getBoardSize());
		do {
			currentPlayer = playerManager.getCurrentPlayer();
			viewer.showNextPlayerWorkerPlacement(currentPlayer);
			nextWorkerCoord = playerManager.getCurrentPlayer().nextWorkerPlacement(board);
		} while (!validator.equals(nextWorkerCoord));
		return nextWorkerCoord;
	}

	private WorkerMove getNextWorkerMove() {
		WorkerMove move;
		IPlayer currentPlayer;

		WorkerMoveValidator moveValidator = new WorkerMoveValidator(board);
		playerManager.next();
		do {
			currentPlayer = playerManager.getCurrentPlayer();
			viewer.showNextPlayerMove(currentPlayer);
			move = currentPlayer.nextWorkerMove(board);
		} while (!moveValidator.validate(currentPlayer, move));
		return move;
	}
	
	private BuildMove getBuildeNextMove() {
		BuildMove move;
		IPlayer currentPlayer;

		BuildMoveValidator buildMoveValidator = new BuildMoveValidator(board);
		playerManager.next();
		do {
			currentPlayer = playerManager.getCurrentPlayer();
			viewer.showNextPlayerMove(currentPlayer);
			move = currentPlayer.nextBuildMove(board);
		} while (!buildMoveValidator.validate(move));
		return move;
	}

	private boolean isGameOver(WorkerMove move) {
		return (isWinningMove(move)) || (isLastMove());
	}

	private boolean isLastMove() {
		int count = 0;
		Coord[] coords = board.getCoordsWithWorkers(playerManager.getFollowingPlayer().getColor());
		for (Coord coord : coords) {
			try {
				count += getReachableCoords(coord).length;
			} catch (NoWorkerOnFieldException e) {

			}
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
				if (validator.validate(player, new WorkerMove(fromCoord, new Coord(newX, newY))))
					coords.add(reachableCoord);
			}
		}
		return coords.toArray(new Coord[coords.size()]);
	}

	private boolean isWinningMove(WorkerMove move) {
		return (board.getField(move.getFrom()).getLevel() == 2) && (board.getField(move.getTo()).getLevel() == 3);
	}

	private void doMove(WorkerMove move) {
		board.getField(move.getFrom()).setWorkerColor(Color.None);
		board.getField(move.getTo()).setWorkerColor(playerManager.getCurrentPlayer().getColor());
	}
	
	private void doMove(BuildMove move) {
		board.getField(move.getBuild()).setLevel(board.getField(move.getBuild()).getLevel() + 1);
	}

	public void loadBoard(String s) {
		Board board = new Board();
		board.setBoardFromString(s);
		if (board.isBoardValid())
			this.board = board;
	}
}
