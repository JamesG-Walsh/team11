package ca.uwo.csd.cs2212.team11;

import java.awt.Color;

/**
 * Canned data to be used on our GUI
 * @author Dara, Andrew
 *
 */
public final class SharedData {
	public static enum IDs{ CALORIES, CALORIES_CUM, DISTANCE, DISTANCE_CUM, CLIMB, STEPS, STEPS_CUM, ACTIVE, SEDENTARY, HEART_RATE};
	public static final Color[] COLOR_SET = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE, Color.PINK};
	public static final String PATH_TO_RESOURCES = "./src/main/resources/";
	public static final String PATH_TO_IMAGES = "./src/main/resources/imgs/";
	public static final String ALT_PATH_TO_IMAGES = "./src/main/resources/imgs/";
	public static Color SMOKE = new Color(0, 0, 0, 100);
	public static int[] base_array = {1492, 55555, 987654321}; //Static data being 
	public static int[] sedentary_Data = {100, 334, 7000};
	public static int[] distance_Data = {253, 678, 8900};
	public static int[] floors_Data = {40, 400, 4000};
	public static int[] steps_Data = {40, 400, 4000};
	public static int[] activeMin_Data = {140, 600, 7000};
	public static int[] time_series = {168, 85, 149, 176, 172, 66, 59, 175, 172, 50, 89, 66, 50, 130, 112, 93, 173, 68, 155, 179, 179, 151, 81, 150, 130, 95, 109, 138, 154, 74, 149, 82, 160, 148, 112, 83, 144, 74, 107, 155, 91, 71, 90, 83, 112, 175, 67, 138, 140, 169, 138, 76, 52, 78, 75, 74, 150, 83, 147, 94, 165, 93, 176, 50, 176, 141, 126, 73, 60, 109, 150, 139, 78, 161, 60, 140, 93, 63, 141, 86, 95, 115, 58, 50, 65, 158, 131, 164, 81, 156, 159, 166, 83, 127, 163, 97} ;
	public static int[] dummyCalories = {101, 217, 134, 82, 96, 221, 248, 162, 177, 73, 197, 126, 87, 130, 234, 113, 196, 85, 90, 97, 91, 205, 195, 75, 238, 191, 123, 113, 134, 232, 227, 119, 230, 140, 99, 79, 151, 176, 156, 138, 96, 137, 142, 117, 228, 201, 93, 94, 149, 199, 160, 128, 204, 90, 109, 190, 239, 92, 72, 98, 225, 237, 147, 111, 140, 223, 155, 145, 141, 132, 181, 241, 87, 199, 96, 170, 206, 221, 210, 114, 128, 192, 78, 243, 87, 194, 74, 93, 139, 191, 179, 228, 145, 221, 231, 148} ;
	public static int[] dummySteps = {221, 1214, 163, 25, 1322, 907, 169, 602, 518, 147, 864, 204, 544, 62, 743, 1328, 1338, 679, 373, 608, 24, 492, 1293, 174, 1103, 304, 845, 1276, 592, 1446, 1336, 299, 863, 505, 250, 758, 18, 91, 892, 603, 938, 1088, 766, 642, 746, 687, 414, 76, 1315, 868, 662, 792, 377, 1100, 1466, 616, 484, 878, 547, 313, 1045, 1397, 560, 60, 716, 540, 872, 745, 1099, 1070, 512, 564, 270, 129, 3, 1172, 875, 420, 1124, 1295, 596, 174, 1372, 835, 988, 627, 1394, 207, 994, 1227, 192, 839, 614, 382, 1260, 362} ;
	public static int[] dummyDistance = {920, 617, 667, 689, 416, 108, 480, 911, 59, 656, 897, 243, 611, 665, 124, 68, 411, 308, 611, 171, 769, 784, 171, 152, 56, 848, 568, 723, 192, 932, 404, 26, 954, 400, 766, 823, 389, 382, 732, 825, 767, 324, 160, 342, 308, 394, 841, 702, 492, 651, 228, 191, 224, 787, 773, 663, 360, 791, 114, 385, 655, 372, 251, 168, 583, 366, 915, 6, 328, 612, 919, 23, 455, 264, 688, 595, 576, 848, 477, 936, 571, 235, 505, 635, 85, 891, 305, 769, 526, 473, 292, 497, 102, 53, 794, 492} ;
}
