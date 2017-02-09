package move;

import board.Coord;

public class Move {

	private Coord from;
	private Coord to;
	private Coord build;

	public Move(Coord from, Coord to, Coord build) {
		super();
		this.from = from;
		this.to = to;
		this.build = build;
	}

	public Coord getFrom() {
		return from;
	}

	public void setFrom(Coord from) {
		this.from = from;
	}

	public Coord getTo() {
		return to;
	}

	public void setTo(Coord to) {
		this.to = to;
	}

	public Coord getBuild() {
		return build;
	}

	public void setBuild(Coord build) {
		this.build = build;
	}
}
