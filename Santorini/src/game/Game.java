package game;

import java.util.ArrayList;

import board.Board;
import board.BoardSerializer;
import board.Color;
import board.Coord;
import board.CoordValidator;
import board.ICoordValidator;
import exceptions.NoWorkerOnFieldException;
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

	private Status status;

	public Game() {
		initGame();
	}

	private void initGame() {
		status = Status.preGame;
		viewer = new ConsoleViewer();
		playerManager = new PlayerManager(new ConsolePlayer(Color.Blue), new ConsolePlayer(Color.White));

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

		if (isGameOver(workerMove)) {
			status = Status.GameFinished;
			return;
		}

		viewer.showBoard(board);
		BuildMove buildMove = getNextBuildMove();
		doMove(buildMove);

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
				viewer.showMessage(e.toString());
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
			validMove = moveValidator.validate(currentPlayer, move);
			if (!validMove)
				viewer.showMessage("This was not a valid worker move");
		}
		return move;
	}

	private BuildMove getNextBuildMove() {
		BuildMove move = null;
		boolean validMove = false;
		;
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
		BoardSerializer boardSerializer = new BoardSerializer();
		Board board = boardSerializer.deserialize(s);
		if (board.isBoardValid())
			this.board = board;
	}
}
