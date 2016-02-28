package ca.uwo.csd.cs2212.team11;

import java.awt.Color;

public final class SharedData {
	public static enum IDs{ CALORIES, DISTANCE, CLIMB, STEPS, ACTIVE, SEDENTARY, HEART_RATE};
	public static final Color[] COLOR_SET = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE, Color.PINK};
	public static final String PATH_TO_RESOURCES = "../src/main/resources/";
	public static Color SMOKE = new Color(120, 120, 120, 50);
}
