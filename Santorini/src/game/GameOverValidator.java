package game;

import java.util.logging.Level;
import java.util.logging.Logger;

import board.Board;
import board.Coord;
import board.ReachableCoordsProvider;
import exceptions.InvalidBoardAlterationException;
import player.IPlayer;

public class GameOverValidator {

	private Board board;
	private Logger logger;

	public GameOverValidator(Board board) {
		logger = Logger.getLogger(this.getClass().getName());
		this.board = board;
	}

	public boolean validate(IPlayer player) {
		int count = 0;
		Coord[] coords = board.getCoordsWithWorkers(player.getColor());
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

}
