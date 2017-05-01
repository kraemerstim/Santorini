package ws;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import board.Board;
import board.Coord;
import game.Game;
 
@Path("game")
public class GameREST {
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.TEXT_PLAIN)
	@Path("create")
	public String createNewGame(String message) {
		int i = Game.CreateGame();
		if (!message.isEmpty())
			Game.GetGame(i).loadBoard(message);
	
		JsonObject jsonObject = Json.createObjectBuilder().add("id", i).build();
		return jsonObject.toString();
	}
 
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public String showGame(@PathParam("id") int gameID)
	{
		Game game = Game.GetGame(gameID);
	    JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
	    objectBuilder.add("gamePhase", game.getStatus().ordinal());
	    JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
	    Board board = game.getBoardCopy();
	    for (int i = 0; i < board.getBoardSize(); i++)
			for (int j = 0; j < board.getBoardSize(); j++)
				arrayBuilder.add(board.getField(i, j).getLevel());
	    
	    objectBuilder.add("levels", arrayBuilder);
	    
	    arrayBuilder = Json.createArrayBuilder();
	    for (Coord coord : board.getCoordsWithWorkers(game.getPlayerByIndex(0).getColor()))
	    {
	    	arrayBuilder.add(coord.toString());
	    }
	    JsonObjectBuilder workerBuilder = Json.createObjectBuilder();
	    
	    workerBuilder.add("player1", arrayBuilder);
	    arrayBuilder = Json.createArrayBuilder();
	    for (Coord coord : board.getCoordsWithWorkers(game.getPlayerByIndex(1).getColor()))
	    {
	    	arrayBuilder.add(coord.toString());
	    }
	    workerBuilder.add("player2", arrayBuilder);
	    objectBuilder.add("workers", workerBuilder);
	    
	    objectBuilder.add("currentPlayer", game.getCurrentPlayer().getPlayerNumber());
	    
	    //winner
	    objectBuilder.add("winner", game.getWinningPlayer() == null ? -1 : game.getWinningPlayer().getPlayerNumber());
	    
		return objectBuilder.build().toString();
	}
}
