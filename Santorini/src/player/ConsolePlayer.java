package player;

import java.util.Scanner;

import board.Board;
import board.Color;
import board.Coord;
import move.Move;

public class ConsolePlayer implements IPlayer {

	private Color color;
	private Scanner scanner;

	public ConsolePlayer(Scanner scanner, Color color) {
		super();
		this.scanner = scanner;
		this.color = color;
	}

	@Override
	public Move nextMove(Board board) {
		String input = scanner.nextLine();
		return parseInput(input);
	}

	@Override
	public Color getColor() {
		return this.color;
	}

	private Move parseInput(String input) {
		String inputString = input;
		inputString = inputString.replace("(", "");
		inputString = inputString.replace(")", "");
		String[] choords = inputString.split(",");

		Coord from = new Coord(Integer.valueOf(choords[0]), Integer.valueOf(choords[1]));
		Coord to = new Coord(Integer.valueOf(choords[2]), Integer.valueOf(choords[3]));
		Coord build = new Coord(Integer.valueOf(choords[4]), Integer.valueOf(choords[5]));

		return new Move(from, to, build);
	}

}
