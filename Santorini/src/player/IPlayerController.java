package player;

import common.Color;

public interface IPlayerController {

	public void next();

	public IPlayer getCurrentPlayer();

	public IPlayer getFollowingPlayer();

	public IPlayer getPlayerByColor(Color color);
	
	public IPlayer getPlayerByIndex(int index);
}
