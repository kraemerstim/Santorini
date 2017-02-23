package player;

import common.Color;

public class PlayerController implements IPlayerController {

	private IPlayer player1;
	private IPlayer player2;
	private IPlayer currentPlayer;

	public PlayerController(IPlayer player1, IPlayer player2) {
		this.player1 = player1;
		this.player2 = player2;
		currentPlayer = player1;
	}

	@Override
	public void next() {
		currentPlayer = getFollowingPlayer();
	}

	@Override
	public IPlayer getCurrentPlayer() {
		return currentPlayer;
	}

	@Override
	public IPlayer getFollowingPlayer() {
		if (currentPlayer == player1)
			return player2;
		return player1;
	}

	@Override
	public IPlayer getPlayerByColor(Color color) {
		if (color == Color.NONE)
			return null;
		return player1.getColor() == color ? player1 : player2;
	}

}
