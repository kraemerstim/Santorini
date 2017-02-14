package player;

import java.util.Scanner;

import board.Board;
import board.Color;
import board.Coord;
import exceptions.InvalidInputException;
import move.BuildMove;
import move.WorkerMove;

public class ConsolePlayer implements IPlayer {

	private Color color;
	private Scanner scanner;

	public ConsolePlayer(Scanner scanner, Color color) {
		super();
		this.scanner = scanner;
		this.color = color;
	}

	@Override
	public WorkerMove nextWorkerMove(Board board) {
		String input = scanner.nextLine();
		Coord[] coords = parseInputIntoCoords(input);
		return new WorkerMove(coords[0], coords[1]);
	}

	@Override
	public BuildMove nextBuildMove(Board board) {
		String input = scanner.nextLine();
		Coord[] coords = parseInputIntoCoords(input);
		return new BuildMove(coords[0], coords[1]);
	}

	@Override
	public Color getColor() {
		return this.color;
	}

	private Coord[] parseInputIntoCoords(String input) {
		Coord[] result = new Coord[2];
		result[0] = new Coord(Integer.valueOf(input.substring(0,1)), Integer.valueOf(input.substring(1,2)));
		result[1] = new Coord(Integer.valueOf(input.substring(2,3)), Integer.valueOf(input.substring(3,4)));
		return result;
	}

	@Override
	public Coord nextWorkerPlacement(Board board) {
		String input = scanner.nextLine();
		Coord returnCoord = null;
		while (returnCoord == null) {
			try {
				returnCoord = parseInputIntoCoord(input);
			} catch (InvalidInputException e) {
				returnCoord = null;
			}
		}
		return returnCoord;
	}

	private Coord parseInputIntoCoord(String input) throws InvalidInputException {
		if (input.length() != 2)
			throw new InvalidInputException();

		char[] characters = input.toCharArray();
		return new Coord(Character.getNumericValue(characters[0]), Character.getNumericValue(characters[1]));
	}

}
