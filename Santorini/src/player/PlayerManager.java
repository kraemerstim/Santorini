package player;

public class PlayerManager implements IPlayerManager {

	private IPlayer player1;
	private IPlayer player2;
	private IPlayer currentPlayer;

	public PlayerManager(IPlayer player1, IPlayer player2) {
		this.player1 = player1;
		this.player2 = player2;
		currentPlayer = player1;
	}

	@Override
	public void next() {
		if (currentPlayer == player1)
			currentPlayer = player2;
		else
			currentPlayer = player1;
	}

	@Override
	public IPlayer getCurrentPlayer() {
		return currentPlayer;
	}

}
