package board;

public class BoardValidator implements IBoardValidator {

	@Override
	public boolean validate(Board board) {
		Field field;
		int boardSize = board.getBoardSize();
		for (int i = 0; i < boardSize; i++)
			for (int j = 0; j < boardSize; j++) {
				field = board.getField(i, j);
				if (field.getLevel() < 0 || field.getLevel() > 4)
					return false;
			}
		return true;
	}

}
