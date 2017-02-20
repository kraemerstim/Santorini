package move;

import board.Coord;

public class BuildMove {

	private Coord worker;
	private Coord build;

	public BuildMove(Coord worker, Coord build) {
		super();
		this.worker = worker;
		this.build = build;
	}

	public Coord getWorker() {
		return worker;
	}

	public Coord getBuild() {
		return build;
	}

}
