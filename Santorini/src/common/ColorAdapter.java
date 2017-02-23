package common;

public class ColorAdapter {

	public String toString(Color color) {
		if (Color.WHITE.equals(color))
			return "w";
		else if (Color.BLUE.equals(color))
			return "b";
		return "";
	}

	public Color toColor(String color) {
		if ("w".equals(color))
			return Color.WHITE;
		else if ("b".equals(color))
			return Color.BLUE;
		return Color.NONE;
	}

}
