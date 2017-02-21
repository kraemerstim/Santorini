package exceptions;

public class InvalidBoardAlterationException extends RuntimeException {

	private static final long serialVersionUID = 857030592826984384L;
	
	public InvalidBoardAlterationException(String message) {
		super(message);
	}
}
