package player;

import java.util.Scanner;

import board.Board;
import board.Coords;
import game.Move;

public class ConsolePlayer implements IPlayer {

	@Override
	public Move nextMove(Board board) {
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		return parseInput(input);
	}

	private Move parseInput(String input) {
		String inputString = input;
		inputString = inputString.replace("(", "");
		inputString = inputString.replace(")", "");
		String[] choords = inputString.split(",");

		Move result = new Move();
		result.setFrom(new Coords(Integer.valueOf(choords[0]), Integer.valueOf(choords[1])));
		result.setTo(new Coords(Integer.valueOf(choords[2]), Integer.valueOf(choords[3])));
		result.setBuild(new Coords(Integer.valueOf(choords[4]), Integer.valueOf(choords[5])));

		return result;
	}



}
