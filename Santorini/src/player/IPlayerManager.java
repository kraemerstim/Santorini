package player;

import board.Color;

public interface IPlayerManager {

	public void next();

	public IPlayer getCurrentPlayer();

	public IPlayer getFollowingPlayer();

	public IPlayer getPlayerByColor(Color color);
}
