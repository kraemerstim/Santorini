package game;

import exceptions.InvalidBoardAlterationException;

public class Santorini {

	private Santorini() {

	}

	public static void main(String[] args) throws InvalidBoardAlterationException {
		Game game = new Game();
		game.start();
	}

}
