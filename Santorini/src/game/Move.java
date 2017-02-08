package game;

import board.Coords;

public class Move {

	private Coords from;
	private Coords to;
	private Coords build;

	public Coords getFrom() {
		return from;
	}

	public void setFrom(Coords from) {
		this.from = from;
	}

	public Coords getTo() {
		return to;
	}

	public void setTo(Coords to) {
		this.to = to;
	}

	public Coords getBuild() {
		return build;
	}

	public void setBuild(Coords build) {
		this.build = build;
	}
}
