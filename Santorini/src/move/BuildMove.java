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

	public void setWorker(Coord worker) {
		this.worker = worker;
	}

	public Coord getBuild() {
		return build;
	}

	public void setBuild(Coord build) {
		this.build = build;
	}
}
