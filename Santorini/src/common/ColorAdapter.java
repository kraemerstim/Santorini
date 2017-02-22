package common;

public class ColorAdapter {

	public String toString(Color color) {
		if (color.equals(Color.WHITE))
			return "w";
		else if (color.equals(Color.BLUE))
			return "b";
		return "";
	}

	public Color toColor(String color) {
		if (color.equals("w"))
			return Color.WHITE;
		else if (color.equals("b"))
			return Color.BLUE;
		return Color.NONE;
	}

}
