package player;

import java.util.Scanner;

import board.Board;
import board.Coord;
import common.Color;
import exceptions.InvalidInputException;
import move.BuildMove;
import move.WorkerMove;

public class ConsolePlayer implements IPlayer {

	private Color color;
	private static Scanner scanner;

	public ConsolePlayer(Color color) {
		super();
		this.color = color;
	}

	private static Scanner getScanner() {
		if (scanner == null)
			scanner = new Scanner(System.in);
		return scanner;
	}

	@Override
	public WorkerMove nextWorkerMove(Board board) {
		showMessage("Enter two coords for the worker move seperated by a '-'. First coord for the current position,"
				+ " second for the new position. For example: a1-b2");
		Coord[] coords = null;
		while (coords == null) {
			coords = getNextMove();
		}
		return new WorkerMove(coords[0], coords[1]);
	}

	@Override
	public BuildMove nextBuildMove(Board board) {
		Coord coord = null;
		while (coord == null) {
			showMessage("Enter the coord for the build move. For example: b3");
			coord = getNextCoord();
		}
		return new BuildMove(coord);
	}

	private Coord getNextCoord() {
		Coord coord;
		String input = getScanner().nextLine();
		try {
			coord = parseInputIntoCoord(input);
		} catch (InvalidInputException e) {
			showMessage(input + " is not a valid coord!");
			coord = null;
		}
		return coord;
	}

	private Coord[] getNextMove() {
		Coord[] coords;
		String input = getScanner().nextLine();
		try {
			coords = parseInputIntoCoords(input);
		} catch (InvalidInputException e) {
			showMessage(input + " is not a valid move!");
			coords = null;
		}
		return coords;
	}

	@Override
	public Color getColor() {
		return this.color;
	}

	private Coord[] parseInputIntoCoords(String input) throws InvalidInputException {
		if (input.length() != 5)
			throw new InvalidInputException();

		Coord[] result = new Coord[2];
		result[0] = parseInputIntoCoord(input.substring(0, 2));
		result[1] = parseInputIntoCoord(input.substring(3, 5));
		return result;
	}

	@Override
	public Coord nextWorkerPlacement(Board board) {
		Coord returnCoord = null;
		while (returnCoord == null) {
			showMessage("Enter a coord for the new worker as two characters for the row and the column. "
					+ "First character for row, second for column. For example: c5");
			String input = getScanner().nextLine();
			try {
				returnCoord = parseInputIntoCoord(input);
			} catch (InvalidInputException e) {
				showMessage(input + " is not a valid coord!");
				returnCoord = null;
			}
		}
		return returnCoord;
	}

	private Coord parseInputIntoCoord(String input) throws InvalidInputException {
		if (input.length() != 2)
			throw new InvalidInputException();

		char[] characters = input.toLowerCase().toCharArray();
		if (characters[0] < 'a' || characters[0] > 'e' || Character.getNumericValue(characters[1]) < 1
				|| Character.getNumericValue(characters[1]) > 5)
			throw new InvalidInputException();
		return new Coord(characters[0] - 'a', Character.getNumericValue(characters[1]) - 1);
	}

	private void showMessage(String message) {
		System.out.println(message);
	}

}
