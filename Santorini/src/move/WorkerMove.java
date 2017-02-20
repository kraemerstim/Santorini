package move;

import board.Coord;

public class WorkerMove {
	private Coord from;
	private Coord to;

	public WorkerMove(Coord from, Coord to) {
		super();
		this.from = from;
		this.to = to;
	}

	public Coord getFrom() {
		return from;
	}

	public Coord getTo() {
		return to;
	}

}
