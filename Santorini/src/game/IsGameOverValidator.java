package game;

import java.util.logging.Level;
import java.util.logging.Logger;

import board.Board;
import board.Coord;
import exceptions.InvalidBoardAlterationException;
import move.WorkerMove;
import player.IPlayerManager;

public class IsGameOverValidator {

	private IPlayerManager playerManager;
	private Logger logger;

	public IsGameOverValidator(IPlayerManager playerManager) {
		logger = Logger.getLogger(this.getClass().getName());
		this.playerManager = playerManager;
	}

	public boolean validate(Board board, WorkerMove move) {
		return isWinningMove(board, move) || isLastMove(board);
	}

	private boolean isLastMove(Board board) {
		int count = 0;
		Coord[] coords = board.getCoordsWithWorkers(playerManager.getFollowingPlayer().getColor());
		ReachableCoordsProvider reachableCoordsProvider = new ReachableCoordsProvider();
		for (Coord coord : coords) {
			try {
				count += reachableCoordsProvider.provide(board, coord).length;
			} catch (InvalidBoardAlterationException e) {
				logger.log(Level.INFO, "Exception in isLastMove", e);
			}
		}
		return count == 0;
	}

	private boolean isWinningMove(Board board, WorkerMove move) {
		return (board.getField(move.getFrom()).getLevel() == 2) && (board.getField(move.getTo()).getLevel() == 3);
	}

}
