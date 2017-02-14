package player;

import java.util.Scanner;

import board.Board;
import board.Color;
import board.Coord;
import board.CoordValidator;
import board.ICoordValidator;
import exceptions.InvalidInputException;
import move.BuildMove;

public class ConsolePlayer implements IPlayer {

	private Color color;
	private Scanner scanner;

	public ConsolePlayer(Scanner scanner, Color color) {
		super();
		this.scanner = scanner;
		this.color = color;
	}

	@Override
	public BuildMove nextMove(Board board) {
		String input = scanner.nextLine();
		return parseInputIntoMove(input);
	}

	@Override
	public Color getColor() {
		return this.color;
	}

	private BuildMove parseInputIntoMove(String input) {
		String inputString = input;
		inputString = inputString.replace("(", "");
		inputString = inputString.replace(")", "");
		String[] choords = inputString.split(",");

		Coord from = new Coord(Integer.valueOf(choords[0]), Integer.valueOf(choords[1]));
		Coord build = new Coord(Integer.valueOf(choords[2]), Integer.valueOf(choords[3]));

		return new BuildMove(from, build);
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
