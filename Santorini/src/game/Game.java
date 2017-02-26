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
import move.MoveValidator;
import move.WinningWorkerMoveValidator;
import move.BuildMove;
import move.WorkerMove;
import move.WorkerMoveValidator;
import player.IPlayer;
import player.IPlayerController;
import player.PlayerController;
import view.ConsoleViewer;
import view.IViewer;
import player.ConsolePlayer;

public class Game {
	private Board board;

	private IPlayerController playerController;
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
		playerController = new PlayerController(new ConsolePlayer(Color.BLUE), new ConsolePlayer(Color.WHITE));

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
			viewer.showWinner(playerController.getCurrentPlayer());
	}

	public void setWorker(Coord coord, Color color) throws TooManySameColorWorkersOnBoardException {
		if (board.getWorkerCountByColor(color) >= 2)
			throw new TooManySameColorWorkersOnBoardException();

		board.setWorker(coord, color);
	}

	private void waitForNextMove() throws InvalidBoardAlterationException {
		viewer.showBoard(board);
		MoveValidator validator = new MoveValidator(new WorkerMoveValidator(board), new BuildMoveValidator(board), board);
		WorkerMove workerMove = null;
		BuildMove buildMove = null;
		boolean validated = false;
		
		while (!validated) {
			workerMove = getNextWorkerMove();
			buildMove = getNextBuildMove();
			validated = validator.validate(playerController.getCurrentPlayer().getColor(), workerMove, buildMove);
			if (!validated)
				viewer.showMessage("Your moves were not valid, try again!");
		}
		
		doWorkerMove(workerMove);
		if (isWinningMove(workerMove)) {
			status = Status.GAME_FINISHED;
			return;
		}
		doBuildMove(buildMove);
		if (isGameOver()) {
			status = Status.GAME_FINISHED;
			return;
		}
		
		viewer.showBoard(board);
		playerController.next();
	}

	private void waitForNextWorkerPlacement() {
		viewer.showBoard(board);

		Coord coord;
		Color playerColor = playerController.getCurrentPlayer().getColor();
		while (board.getCoordsWithWorkers(playerColor).length < 2) {
			try {
				coord = getPlayerCoord();
				setWorker(coord, playerColor);
			} catch (Exception e) {
				logger.log(Level.INFO, "Exception in waitForNextWorkerPlacement", e);
				viewer.showMessage(e.toString());
			}
		}
		playerController.next();

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
			viewer.showNextPlayerWorkerPlacement(playerController.getCurrentPlayer());
			nextWorkerCoord = playerController.getCurrentPlayer().nextWorkerPlacement(board);
		} while (!validator.validate(nextWorkerCoord));
		return nextWorkerCoord;
	}

	private WorkerMove getNextWorkerMove() {
		IPlayer currentPlayer = playerController.getCurrentPlayer();

		return currentPlayer.nextWorkerMove(board);
	}

	private BuildMove getNextBuildMove() {
		IPlayer currentPlayer = playerController.getCurrentPlayer();

		return currentPlayer.nextBuildMove(board);
	}

	private boolean isWinningMove(WorkerMove move) {
		WinningWorkerMoveValidator isWinningWorkerMoveValidator = new WinningWorkerMoveValidator(board);
		return isWinningWorkerMoveValidator.validate(move);
	}

	private boolean isGameOver() {
		GameOverValidator gameOverValidator = new GameOverValidator(board);
		return gameOverValidator.validate(playerController.getFollowingPlayer());
	}

	private void doWorkerMove(WorkerMove move) {
		board.applyWorkerMove(move);
	}

	private void doBuildMove(BuildMove move) throws InvalidBoardAlterationException {
		board.applyBuildMove(move);
	}

	public void loadBoard(String s) {
		BoardValidator boardValidator = new BoardValidator();
		BoardSerializer boardSerializer = new BoardSerializer();
		Board serializedBoard = boardSerializer.deserialize(s);
		if (boardValidator.validate(serializedBoard))
			board = serializedBoard;
	}
}
