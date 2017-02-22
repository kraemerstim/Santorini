package game;

import java.util.logging.Level;
import java.util.logging.Logger;

import board.Board;
import board.BoardSerializer;
import board.BoardValidator;
import board.Coord;
import board.CoordValidator;
import board.ICoordValidator;
import common.Color;
import exceptions.InvalidBoardAlterationException;
import exceptions.TooManySameColorWorkersOnBoardException;
import move.BuildMoveValidator;
import move.BuildMove;
import move.WorkerMove;
import move.WorkerMoveValidator;
import player.IPlayer;
import player.IPlayerManager;
import player.PlayerManager;
import view.ConsoleViewer;
import view.IViewer;
import player.ConsolePlayer;

public class Game {
	private Board board;

	private IPlayerManager playerManager;
	private IViewer viewer;
	private Logger logger;

	private Status status;

	public Game() {
		logger = Logger.getLogger(Game.class.getName());
		initGame();
	}

	private void initGame() {
		status = Status.PRE_GAME;
		viewer = new ConsoleViewer();
		playerManager = new PlayerManager(new ConsolePlayer(Color.BLUE), new ConsolePlayer(Color.WHITE));

		board = new Board();
	}

	public void start() throws InvalidBoardAlterationException {
		status = Status.WORKER_PLACEMENT_PHASE;
		while (status != Status.GAME_FINISHED && status != Status.GAME_ABORTED) {
			switch (status) {
			case WORKER_PLACEMENT_PHASE:
				waitForNextWorkerPlacement();
				break;
			case GAME_PHASE:
				waitForNextMove();
				break;
			default:
				break;
			}
		}
		if (status == Status.GAME_FINISHED)
			viewer.showWinner(playerManager.getCurrentPlayer());
	}

	public void setWorker(Coord coord, Color color) throws TooManySameColorWorkersOnBoardException {
		if (board.getWorkerCountByColor(color) >= 2)
			throw new TooManySameColorWorkersOnBoardException();

		board.setWorker(coord, color);
	}

	private void waitForNextMove() throws InvalidBoardAlterationException {
		viewer.showBoard(board);
		WorkerMove workerMove = getNextWorkerMove();
		doWorkerMove(workerMove);

		if (isGameOver(workerMove)) {
			status = Status.GAME_FINISHED;
			return;
		}

		viewer.showBoard(board);
		BuildMove buildMove = getNextBuildMove();
		doBuildMove(buildMove);

		playerManager.next();
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
				logger.log(Level.INFO, "Exception in waitForNextWorkerPlacement", e);
				viewer.showMessage(e.toString());
			}
		}
		playerManager.next();

		if (areAllWorkersSet())
			status = Status.GAME_PHASE;
	}

	private boolean areAllWorkersSet() {
		int countBlue = board.getCoordsWithWorkers(Color.BLUE).length;
		int countWhite = board.getCoordsWithWorkers(Color.WHITE).length;
		return countBlue == 2 && countWhite == 2;
	}

	private Coord getPlayerCoord() {
		Coord nextWorkerCoord;

		ICoordValidator validator = new CoordValidator(board.getBoardSize());
		do {
			viewer.showNextPlayerWorkerPlacement(playerManager.getCurrentPlayer());
			nextWorkerCoord = playerManager.getCurrentPlayer().nextWorkerPlacement(board);
		} while (!validator.validate(nextWorkerCoord));
		return nextWorkerCoord;
	}

	private WorkerMove getNextWorkerMove() {
		WorkerMove move = null;
		boolean validMove = false;
		IPlayer currentPlayer = playerManager.getCurrentPlayer();

		WorkerMoveValidator moveValidator = new WorkerMoveValidator(board);

		while (!validMove) {
			viewer.showNextWorkerMove(currentPlayer);
			move = currentPlayer.nextWorkerMove(board);
			validMove = moveValidator.validate(move);
			if (!validMove)
				viewer.showMessage("This was not a valid worker move");
		}
		return move;
	}

	private BuildMove getNextBuildMove() {
		BuildMove move = null;
		boolean validMove = false;
		IPlayer currentPlayer = playerManager.getCurrentPlayer();

		BuildMoveValidator buildMoveValidator = new BuildMoveValidator(board);

		while (!validMove) {
			viewer.showNextWorkerMove(currentPlayer);
			move = currentPlayer.nextBuildMove(board);
			validMove = buildMoveValidator.validate(move);
			if (!validMove)
				viewer.showMessage("This was not a valid build move");
		}
		return move;
	}

	private boolean isGameOver(WorkerMove move) {
		IsGameOverValidator isGameOverValidator = new IsGameOverValidator(playerManager);
		return isGameOverValidator.validate(board, move);
	}

	private void doWorkerMove(WorkerMove move) {
		board.getField(move.getFrom()).setWorkerColor(Color.NONE);
		board.getField(move.getTo()).setWorkerColor(playerManager.getCurrentPlayer().getColor());
	}

	private void doBuildMove(BuildMove move) throws InvalidBoardAlterationException {
		board.setBlock(move.getBuild());
	}

	public void loadBoard(String s) {
		BoardValidator boardValidator = new BoardValidator();
		BoardSerializer boardSerializer = new BoardSerializer();
		Board serializedBoard = boardSerializer.deserialize(s);
		if (boardValidator.validate(serializedBoard))
			board = serializedBoard;
	}
}
