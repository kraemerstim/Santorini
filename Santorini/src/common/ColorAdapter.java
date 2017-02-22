package common;

public class ColorAdapter {

	public String toString(Color color) {
		if (color == Color.WHITE)
			return "w";
		else if (color == Color.BLUE)
			return "b";
		return "";
	}

	public Color toColor(String color) {
		if (color == "w")
			return Color.WHITE;
		else if (color == "b")
			return Color.BLUE;
		return Color.NONE;
	}

}
