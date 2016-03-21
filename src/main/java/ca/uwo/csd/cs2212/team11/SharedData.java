package ca.uwo.csd.cs2212.team11;

import java.awt.Color;

/**
 * Canned data to be used on our GUI
 * @author Dara, Andrew
 *
 */
public final class SharedData {
	public static enum IDs{ CALORIES, DISTANCE, CLIMB, STEPS, ACTIVE, SEDENTARY, HEART_RATE};
	public static final Color[] COLOR_SET = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE, Color.PINK};
	public static final String PATH_TO_RESOURCES = "./src/main/resources/";
	public static final String PATH_TO_IMAGES = "./src/main/resources/imgs/";
	public static final String ALT_PATH_TO_IMAGES = "./src/main/resources/imgs/";
	public static Color SMOKE = new Color(0, 0, 0, 100);
	public static final double JOULES_PER_CALORIE = 4.184;
	public static final int YARDS_PER_MILE = 1760;
	public static final double YARDS_PER_KM = 1093.6;
	public static int[] base_array = {1492, 55555, 987654321}; //Static data being 
	public static int[] sedentary_Data = {100, 334, 7000};
	public static int[] distance_Data = {253, 678, 8900};
	public static int[] floors_Data = {40, 400, 4000};
	public static int[] activeMin_Data = {140, 600, 7000};
	public static int[] time_series = {168, 85, 149, 176, 172, 66, 59, 175, 172, 50, 89, 66, 50, 130, 112, 93, 173, 68, 155, 179, 179, 151, 81, 150, 130, 95, 109, 138, 154, 74, 149, 82, 160, 148, 112, 83, 144, 74, 107, 155, 91, 71, 90, 83, 112, 175, 67, 138, 140, 169, 138, 76, 52, 78, 75, 74, 150, 83, 147, 94, 165, 93, 176, 50, 176, 141, 126, 73, 60, 109, 150, 139, 78, 161, 60, 140, 93, 63, 141, 86, 95, 115, 58, 50, 65, 158, 131, 164, 81, 156, 159, 166, 83, 127, 163, 97} ;
	public static int[] dummyCalories = {101, 217, 134, 82, 96, 221, 248, 162, 177, 73, 197, 126, 87, 130, 234, 113, 196, 85, 90, 97, 91, 205, 195, 75, 238, 191, 123, 113, 134, 232, 227, 119, 230, 140, 99, 79, 151, 176, 156, 138, 96, 137, 142, 117, 228, 201, 93, 94, 149, 199, 160, 128, 204, 90, 109, 190, 239, 92, 72, 98, 225, 237, 147, 111, 140, 223, 155, 145, 141, 132, 181, 241, 87, 199, 96, 170, 206, 221, 210, 114, 128, 192, 78, 243, 87, 194, 74, 93, 139, 191, 179, 228, 145, 221, 231, 148} ;
	public static int[] dummySteps = {221, 1214, 163, 25, 1322, 907, 169, 602, 518, 147, 864, 204, 544, 62, 743, 1328, 1338, 679, 373, 608, 24, 492, 1293, 174, 1103, 304, 845, 1276, 592, 1446, 1336, 299, 863, 505, 250, 758, 18, 91, 892, 603, 938, 1088, 766, 642, 746, 687, 414, 76, 1315, 868, 662, 792, 377, 1100, 1466, 616, 484, 878, 547, 313, 1045, 1397, 560, 60, 716, 540, 872, 745, 1099, 1070, 512, 564, 270, 129, 3, 1172, 875, 420, 1124, 1295, 596, 174, 1372, 835, 988, 627, 1394, 207, 994, 1227, 192, 839, 614, 382, 1260, 362} ;
	public static int[] dummyDistance = {920, 617, 667, 689, 416, 108, 480, 911, 59, 656, 897, 243, 611, 665, 124, 68, 411, 308, 611, 171, 769, 784, 171, 152, 56, 848, 568, 723, 192, 932, 404, 26, 954, 400, 766, 823, 389, 382, 732, 825, 767, 324, 160, 342, 308, 394, 841, 702, 492, 651, 228, 191, 224, 787, 773, 663, 360, 791, 114, 385, 655, 372, 251, 168, 583, 366, 915, 6, 328, 612, 919, 23, 455, 264, 688, 595, 576, 848, 477, 936, 571, 235, 505, 635, 85, 891, 305, 769, 526, 473, 292, 497, 102, 53, 794, 492} ;

	public static double[] big_double = {103.362, 88.193, 136.861, 102.902, 138.651, 84.163, 120.625, 145.863, 82.892, 91.984, 133.591, 145.980, 119.288, 101.352, 126.094, 121.374, 104.109, 133.633, 54.010, 67.708, 61.273, 70.611, 72.961, 144.456, 78.013, 75.393, 108.239, 138.287, 119.450, 53.119, 116.793, 90.194, 97.832, 99.716, 125.182, 67.781, 54.982, 126.349, 147.050, 89.700, 95.499, 68.683, 53.844, 81.773, 58.267, 57.643, 63.367, 97.534, 137.749, 129.473, 74.014, 77.132, 131.273, 143.312, 137.192, 130.909, 142.791, 115.927, 108.172, 56.771, 99.218, 72.187, 79.430, 79.477, 83.124, 93.677, 130.702, 94.603, 133.579, 122.296, 58.874, 135.414, 93.281, 54.186, 147.642, 68.249, 72.057, 123.740, 69.441, 147.784, 123.749, 97.888, 105.347, 97.788, 135.221, 62.726, 93.274, 96.912, 82.826, 93.451, 112.483, 117.020, 113.096, 76.702, 97.573, 111.051, 64.073, 127.088, 55.377, 132.731, 130.575, 51.544, 68.229, 88.400, 64.594, 131.296, 83.051, 142.613, 144.631, 68.655, 97.183, 146.128, 131.457, 112.692, 115.437, 63.681, 112.353, 82.828, 81.560, 70.711, 51.113, 113.360, 98.584, 96.015, 75.935, 109.886, 115.099, 99.338, 141.421, 139.234, 98.220, 98.249, 89.364, 139.552, 71.280, 144.295, 113.729, 116.001, 91.245, 80.148, 92.376, 62.904, 126.917, 144.010, 65.805, 60.720, 121.729, 106.894, 77.586, 72.532, 115.017, 137.348, 93.801, 83.930, 62.895, 109.039, 75.163, 61.452, 93.931, 79.204, 75.394, 91.445, 98.817, 77.546, 128.033, 100.741, 115.679, 106.896, 68.499, 81.249, 72.098, 98.090, 143.744, 86.826, 84.984, 78.218, 133.420, 99.432, 69.953, 82.487, 113.258, 113.650, 135.887, 69.549, 132.751, 106.621, 69.804, 143.632, 52.352, 126.719, 63.130, 60.750, 91.693, 70.751, 85.813, 70.929, 148.855, 84.987, 94.485, 63.997, 104.044, 74.708, 71.024, 113.292, 92.788, 103.290, 85.852, 53.603, 74.884, 102.128, 137.330, 59.057, 146.319, 107.804, 129.486, 138.372, 64.156, 62.093, 111.955, 81.767, 94.995, 104.777, 91.371, 122.908, 140.881, 82.866, 72.172, 70.930, 75.228, 53.045, 117.175, 73.667, 71.856, 101.263, 123.577, 124.429, 148.279, 87.547, 143.380, 121.067, 86.711, 77.884, 57.240, 111.119, 64.101, 148.580, 124.363, 72.510, 135.191, 70.239, 107.336, 90.440, 80.311, 102.719, 68.675, 51.202, 85.716, 82.020, 106.347, 96.994, 108.787, 75.560, 141.704, 57.494, 72.066, 101.380, 124.131, 100.834, 73.069, 88.770, 69.157, 86.964, 109.482, 117.473, 65.048, 73.381, 101.070, 120.894, 104.297, 93.583, 134.818, 145.926, 73.103, 113.061, 61.969, 80.820, 62.470, 94.196, 139.992, 120.936, 86.580, 113.908, 99.488, 124.519, 50.956, 89.802, 102.024, 63.401, 125.841, 141.150, 120.922, 65.091, 127.410, 77.523, 120.160, 112.113, 81.748, 135.208, 91.216, 116.544, 137.485, 136.555, 62.627, 61.461, 138.092, 145.621, 57.891, 122.014, 50.199, 115.046, 69.922, 119.411, 131.116, 143.101, 86.824, 57.479, 102.825, 108.307, 78.948, 62.513, 113.735, 91.445, 78.051, 64.786, 91.456, 87.517, 143.391, 145.378, 89.841, 109.548, 82.606, 91.420, 50.267, 62.502, 113.724, 137.474, 97.476, 101.065, 82.867, 136.654, 91.674, 79.979, 130.678, 122.231, 144.248, 121.200, 50.221, 92.348, 146.689, 138.981, 55.227, 66.352, 147.464, 67.740, 104.011, 117.442, 56.722, 134.884, 99.689, 63.591, 59.604, 143.939, 130.157, 140.806, 52.630, 87.315, 107.057, 109.696, 107.993, 54.679, 131.300, 97.243, 110.676, 107.289, 79.928, 57.450, 138.266, 102.401, 116.117, 107.642, 111.640, 91.412, 111.568, 134.586, 89.754, 83.270, 142.711, 87.624, 106.253, 81.373, 119.779, 97.364, 67.583, 107.876, 97.744, 149.044, 108.259, 88.296, 111.560, 122.276, 72.496, 128.627, 53.819, 106.551, 87.369, 102.306, 146.667, 118.590, 102.199, 126.341, 141.508, 120.724, 91.923, 84.723, 55.369, 54.308, 104.512, 134.888, 83.721, 51.095, 63.604, 126.428, 101.489, 65.406, 101.975, 149.280, 62.427, 139.032, 89.061, 77.623, 84.899, 95.104, 99.662, 124.783, 85.914, 144.759, 119.708, 103.836, 56.916, 128.969, 93.748, 93.295, 103.228, 69.502, 89.653, 62.232, 80.584, 100.224, 142.526, 59.605, 126.407, 106.500, 52.940, 67.093, 76.182, 60.279, 91.041, 118.541, 138.706, 148.934, 74.240, 68.182, 93.703, 56.042, 89.493, 80.386, 52.199, 100.942, 134.034, 118.457, 149.303, 148.190, 92.467, 106.772, 81.955, 59.534, 89.347, 53.013, 85.650, 96.068, 62.232, 132.033, 59.764, 122.179, 59.097, 131.163, 72.472, 51.029, 85.695, 55.999, 139.481, 74.132, 101.145, 55.319, 93.560, 110.677, 137.386, 137.373, 73.638, 116.273, 64.654, 129.806, 133.048, 142.513, 92.506, 125.319, 80.861, 112.694, 87.840, 136.671, 51.019, 120.931, 72.658, 50.209, 81.557, 67.495, 129.438, 92.708, 89.465, 135.050, 81.495, 116.168, 142.581, 69.076, 127.956, 78.384, 125.082, 75.092, 102.354, 83.606, 50.393, 62.516, 62.485, 130.319, 115.706, 99.287, 111.307, 145.245, 77.356, 146.667, 88.721, 73.259, 56.502, 63.985, 93.200, 127.720, 82.220, 66.725, 108.383, 144.483, 128.926, 137.644, 94.796, 71.775, 108.458, 130.746, 128.020, 129.011, 71.633, 117.246, 110.159, 78.849, 129.414, 132.234, 109.162, 111.600, 140.611, 87.010, 132.204, 125.257, 74.902, 110.720, 54.042, 54.635, 112.953, 126.018, 102.870, 108.811, 64.287, 84.625, 148.321, 52.715, 109.930, 97.294, 80.193, 119.925, 70.746, 143.899, 67.102, 137.301, 114.880, 141.220, 102.375, 65.201, 133.168, 146.056, 58.322, 93.605, 114.197, 59.464, 148.512, 113.087, 118.718, 146.634, 53.246, 119.092, 111.734, 118.410, 139.080, 140.733, 81.297, 134.558, 120.488, 68.785, 73.251, 113.093, 82.012, 53.632, 61.643, 124.267, 126.742, 59.466, 99.180, 81.979, 136.700, 145.946, 105.820, 106.242, 51.536, 63.394, 88.309, 107.355, 143.402, 61.204, 64.392, 58.749, 118.433, 69.178, 74.066, 141.629, 122.378, 81.650, 61.287, 69.249, 65.751, 123.274, 66.347, 128.835, 63.552, 66.292, 59.200, 87.070, 110.526, 88.646, 50.393, 92.056, 67.796, 118.032, 102.316, 67.307, 108.585, 50.811, 85.222, 101.201, 134.800, 75.323, 75.801, 127.404, 131.433, 56.560, 87.345, 130.352, 92.352, 53.215, 113.284, 94.094, 114.546, 109.608, 136.327, 144.669, 52.144, 141.636, 87.677, 84.551, 80.125, 136.380, 81.514, 122.381, 130.321, 52.841, 64.102, 69.304, 76.032, 64.675, 61.294, 134.558, 74.293, 141.320, 108.628, 127.724, 117.207, 68.204, 70.262, 90.678, 56.910, 94.103, 73.100, 61.208, 97.663, 68.320, 121.755, 50.952, 78.322, 105.572, 88.870, 99.643, 106.933, 71.842, 120.037, 78.704, 66.009, 67.062, 119.502, 139.101, 119.322, 133.200, 109.551, 72.948, 94.716, 97.520, 76.029, 93.681, 50.833, 144.993, 110.438, 107.747, 50.840, 90.927, 95.651, 89.812, 71.455, 91.718, 101.482, 122.359, 132.681, 149.032, 62.848, 92.301, 139.018, 93.355, 107.660, 121.204, 138.479, 58.221, 119.782, 52.429, 53.050, 121.268, 106.574, 70.440, 144.459, 83.495, 63.417, 54.798, 121.755, 70.850, 81.797, 94.507, 140.450, 110.106, 102.926, 130.870, 139.576, 98.350, 89.498, 132.318, 64.025, 114.597, 96.014, 118.323, 63.553, 114.282, 103.492, 141.581, 75.734, 126.529, 69.076, 131.229, 118.533, 100.997, 78.191, 126.511, 54.085, 58.143, 58.090, 69.080, 146.200, 92.421, 132.567, 108.994, 90.320, 109.005, 64.016, 74.417, 103.598, 111.532, 55.981, 78.641, 64.389, 52.239, 60.469, 135.422, 104.669, 142.837, 126.896, 79.568, 149.460, 99.751, 122.683, 103.649, 146.719, 117.397, 142.165, 141.372, 55.086, 103.087, 133.654, 57.507, 102.285, 109.244, 71.264, 98.301, 139.654, 111.546, 96.205, 86.682, 124.612, 136.595, 96.264, 131.364, 95.347, 69.657, 149.752, 114.501, 113.027, 127.198, 52.886, 55.279, 98.348, 58.866, 110.047, 97.452, 117.451, 121.785, 87.941, 131.714, 83.786, 82.760, 92.673, 122.178, 145.981, 147.324, 54.115, 127.406, 93.577, 148.632, 98.122, 106.245, 117.503, 80.569, 89.518, 122.604, 81.062, 106.455, 100.346, 54.522, 129.175, 81.978, 98.104, 88.160, 139.170, 113.910, 95.301, 77.843, 75.149, 136.773, 133.379, 124.791, 68.189, 57.120, 149.645, 82.378, 149.773, 148.474, 90.597, 132.811, 136.603, 97.624, 103.946, 135.397, 136.066, 68.689, 72.645, 60.508, 97.346, 133.726, 69.698, 135.282, 83.900, 139.741, 92.059, 97.174, 121.881, 104.520, 146.128, 124.362, 120.715, 68.877, 118.547, 63.855, 149.035, 147.274, 108.704, 141.455, 58.656, 146.824, 132.170, 146.567, 83.073, 115.568, 53.329, 106.895, 59.986, 52.398, 120.239, 99.687, 58.283, 144.769, 132.689, 108.384, 106.967, 138.587, 124.483, 73.125, 121.527, 59.452, 54.660, 79.236, 76.198, 54.597, 92.857, 128.412, 89.464, 92.091, 126.909, 59.044, 97.755, 93.819, 93.590, 112.406, 78.688, 91.604, 82.371, 147.798, 121.715, 116.069, 92.593, 104.931, 147.909, 120.276, 114.509, 77.049, 142.027, 134.792, 139.572, 99.521, 74.175, 68.228, 68.582, 120.342, 78.532, 72.353, 53.720, 60.180, 94.984, 86.662, 57.801, 50.334, 66.466, 117.543, 112.141, 101.196, 73.594, 93.111, 95.717, 67.760, 135.208, 89.675, 69.647, 136.803, 51.348, 84.652, 113.049, 126.705, 149.789, 133.272, 128.369, 60.030, 118.837, 79.062, 74.264, 58.451, 93.193, 84.117, 149.622, 74.921, 125.823, 146.336, 98.791, 76.209, 51.512, 68.895, 84.722, 89.565, 66.708, 80.109, 96.186, 60.751, 148.213, 145.714, 103.917, 112.284, 114.652, 73.276, 55.801, 50.076, 70.192, 75.807, 66.143, 110.832, 112.349, 123.366, 104.576, 88.016, 91.512, 112.084, 132.821, 79.985, 86.881, 89.084, 98.683, 93.136, 122.753, 63.737, 62.986, 105.150, 114.035, 146.091, 108.102, 63.943, 129.119, 51.151, 144.910, 74.720, 85.857, 119.833, 95.346, 84.685, 136.360, 116.273, 78.722, 76.044, 137.659, 133.831, 100.610, 115.073, 147.926, 77.748, 107.289, 73.951, 108.178, 105.070, 54.569, 126.640, 86.274, 132.008, 57.145, 145.697, 115.769, 78.052, 127.341, 66.509, 110.058, 85.366, 95.095, 135.045, 117.447, 89.174, 92.167, 146.744, 84.043, 51.745, 141.246, 138.853, 99.539, 72.749, 149.024, 87.738, 100.870, 143.303, 130.056, 72.103, 121.142, 81.035, 52.501, 90.052, 51.039, 144.575, 116.066, 121.404, 130.924, 126.605, 116.020, 71.797, 117.401, 65.947, 57.484, 73.245, 86.514, 83.080, 81.340, 62.995, 145.531, 140.613, 71.287, 123.022, 97.353, 121.197, 120.480, 120.174, 117.261, 97.218, 54.948, 63.069, 123.505, 128.230, 79.300, 74.018, 118.235, 112.468, 116.043, 118.274, 68.846, 144.345, 87.422, 109.427, 89.328, 89.553, 131.121, 145.694, 107.143, 71.426, 64.918, 78.800, 53.532, 127.503, 101.552, 86.462, 136.193, 127.514, 120.166, 102.802, 69.678, 94.970, 82.578, 58.582, 58.279, 130.580, 112.729, 115.868, 118.778, 118.553, 91.254, 133.767, 128.389, 85.244, 137.455, 55.482, 113.492, 62.382, 112.649, 134.491, 60.488, 114.488, 53.592, 115.140, 147.763, 71.572, 99.790, 72.454, 80.479, 100.297, 86.580, 140.569, 133.486, 107.457, 99.894, 88.869, 114.044, 130.984, 115.513, 107.046, 59.041, 66.883, 74.792, 68.064, 63.028, 137.707, 149.996, 147.903, 139.177, 63.941, 64.026, 104.552, 118.970, 127.007, 113.926, 87.394, 93.379, 81.608, 70.165, 122.011, 96.871, 93.389, 53.360, 59.224, 149.489, 108.236, 109.620, 60.349, 90.261, 137.847, 58.662, 113.109, 78.260, 148.134, 128.549, 138.559, 91.742, 69.593, 148.760, 60.917, 103.890, 84.073, 69.958, 149.882, 135.329, 133.386, 68.644, 136.543, 114.705, 136.256, 128.872, 78.482, 121.405, 137.274, 93.949, 72.355, 119.098, 104.911, 53.040, 140.724, 132.948, 118.803, 146.758, 64.477, 130.518, 59.190, 87.440, 131.738, 86.793, 74.202, 104.823, 109.265, 138.228, 58.704, 110.546, 149.475, 127.681, 148.301, 51.543, 56.751, 126.337, 147.534, 50.541, 53.277, 68.773, 125.989, 145.663, 118.318, 117.869, 140.158, 117.005, 73.661, 133.502, 81.971, 90.781, 94.354, 131.244, 102.343, 72.367, 143.683, 60.088, 59.043, 76.205, 144.167, 52.466, 111.532, 90.945, 97.945, 147.679, 130.205, 131.809, 120.901, 132.373, 123.942, 70.630, 81.970, 112.996, 54.209, 63.771, 90.636, 108.847, 85.964, 54.416, 55.469, 52.600, 128.040, 66.541, 63.751, 77.337, 107.821, 135.748, 129.040, 55.374, 122.984, 50.271, 95.435, 114.001, 130.930, 102.585, 72.815, 140.893, 147.662, 76.617, 131.446, 133.716, 76.862, 89.432, 59.748, 112.124, 73.379, 75.092, 87.346, 137.796, 76.687, 56.221, 70.294, 87.108, 104.360, 135.132, 99.089, 129.922, 50.396, 110.388, 51.188, 57.265, 72.412, 54.672, 88.976, 61.236, 50.902, 141.404, 133.145, 108.545, 80.211, 113.936, 72.982, 72.672, 93.833, 84.903, 116.295, 137.042, 148.958, 94.318, 55.699, 130.127, 100.730, 116.759, 92.973, 62.087, 100.935, 69.101, 118.787, 121.674, 140.264, 70.786, 75.459, 140.676, 142.131, 103.813, 110.442, 71.840, 81.708, 124.943, 118.347, 63.341, 133.952, 122.940, 88.248, 56.702, 138.779, 59.798, 139.215, 109.706, 105.894, 54.317, 102.980, 69.716, 147.392} ;
	public static double[] newBigD = {0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 47.910, 57.414, 42.930, 30.904, 92.501, 32.814, 72.430, 135.300, 124.217, 141.242, 70.301, 54.852, 156.561, 60.951, 88.585, 40.754, 122.784, 51.805, 81.364, 136.334, 48.592, 30.867, 93.614, 89.222, 70.923, 99.376, 172.643, 47.250, 147.279, 65.693, 120.000, 52.331, 60.813, 145.383, 50.722, 151.140, 62.015, 55.216, 143.516, 85.312, 144.468, 124.853, 37.915, 48.227, 126.873, 119.377, 170.015, 163.597, 34.613, 164.824, 111.112, 41.393, 133.009, 106.113, 146.618, 130.715, 70.330, 128.051, 158.559, 63.632, 53.124, 157.056, 152.384, 101.832, 79.805, 66.598, 152.991, 102.046, 160.644, 105.898, 69.228, 114.552, 114.636, 63.848, 67.098, 92.489, 46.768, 44.221, 163.286, 47.148, 154.933, 31.861, 148.403, 76.236, 83.974, 160.009, 88.652, 92.446, 146.184, 117.808, 112.871, 66.552, 124.980, 31.606, 140.394, 167.147, 124.674, 106.658, 150.678, 48.435, 65.032, 104.993, 90.876, 57.175, 57.302, 78.256, 87.056, 149.172, 120.764, 97.814, 168.649, 149.324, 31.339, 99.952, 55.537, 112.355, 145.165, 157.375, 65.079, 126.363, 112.011, 164.508, 99.023, 107.557, 165.441, 57.323, 62.227, 122.360, 98.367, 152.397, 101.753, 87.590, 104.055, 65.543, 151.488, 82.585, 67.229, 72.328, 68.223, 64.614, 79.040, 46.632, 43.028, 154.911, 135.452, 61.540, 146.230, 72.716, 57.570, 141.422, 50.712, 174.172, 139.325, 48.994, 144.739, 102.733, 92.632, 160.227, 123.750, 146.771, 149.089, 123.611, 86.364, 160.797, 166.165, 153.761, 115.195, 75.643, 141.415, 76.517, 145.884, 107.691, 101.816, 62.023, 164.860, 39.196, 58.508, 110.758, 76.614, 75.361, 110.183, 100.676, 157.858, 105.479, 108.122, 143.054, 120.950, 81.586, 163.806, 147.010, 115.368, 83.641, 148.556, 165.297, 140.309, 91.370, 61.126, 154.700, 130.689, 114.944, 109.461, 162.874, 164.268, 79.505, 75.809, 51.807, 139.596, 136.740, 165.910, 55.193, 134.813, 82.680, 134.657, 78.444, 43.002, 71.943, 164.015, 120.790, 137.118, 127.049, 59.766, 92.820, 53.316, 49.597, 144.179, 112.515, 138.119, 112.906, 47.967, 79.346, 75.703, 132.092, 149.758, 134.196, 32.665, 157.794, 45.125, 93.628, 87.441, 89.581, 57.568, 33.898, 75.230, 33.683, 129.781, 111.514, 85.419, 149.008, 33.840, 155.451, 143.950, 124.358, 122.628, 70.765, 120.302, 45.995, 108.483, 98.476, 43.942, 63.625, 163.266, 63.361, 62.040, 32.156, 100.980, 94.015, 92.440, 113.852, 66.129, 81.067, 161.750, 89.097, 163.539, 162.772, 118.303, 170.870, 145.317, 131.810, 48.609, 172.839, 73.449, 144.396, 96.569, 47.804, 50.884, 65.905, 62.467, 41.480, 147.319, 67.552, 67.981, 52.283, 150.661, 169.531, 65.689, 67.002, 115.620, 106.966, 97.820, 85.513, 83.914, 70.150, 106.960, 150.535, 78.174, 123.892, 98.202, 166.999, 169.455, 54.568, 93.665, 76.863, 136.677, 47.210, 140.620, 167.721, 94.690, 103.388, 37.446, 81.903, 106.261, 74.212, 98.026, 103.419, 75.683, 131.590, 148.836, 118.876, 43.970, 139.190, 105.411, 147.237, 82.739, 71.738, 35.290, 163.904, 174.555, 90.325, 82.229, 142.376, 78.318, 98.656, 132.622, 58.244, 142.849, 170.604, 87.468, 44.927, 73.739, 146.891, 139.292, 136.036, 49.974, 110.932, 61.943, 101.342, 78.984, 146.492, 174.672, 174.150, 126.481, 93.698, 42.360, 152.086, 37.800, 142.955, 51.752, 118.890, 79.198, 113.668, 54.634, 57.470, 32.071, 107.750, 96.581, 128.018, 138.160, 168.764, 137.203, 65.819, 105.481, 45.384, 33.424, 124.051, 36.409, 87.571, 67.561, 41.688, 125.968, 126.835, 59.623, 119.074, 69.694, 45.646, 147.431, 100.374, 81.653, 160.029, 90.919, 47.080, 129.077, 52.349, 134.838, 159.693, 34.284, 77.022, 71.512, 148.862, 160.191, 131.830, 134.526, 125.864, 80.402, 174.672, 137.214, 150.441, 75.032, 142.430, 90.812, 146.717, 109.272, 35.382, 163.253, 135.302, 134.724, 116.759, 158.027, 44.818, 161.151, 89.645, 132.122, 114.958, 64.578, 140.074, 170.893, 105.905, 90.882, 149.333, 165.598, 160.171, 31.298, 66.191, 49.193, 61.444, 57.769, 164.551, 121.955, 72.945, 115.288, 76.936, 109.372, 128.185, 170.354, 173.784, 154.374, 49.701, 50.136, 148.305, 159.993, 30.470, 33.216, 120.349, 103.301, 123.409, 36.681, 153.162, 48.411, 156.517, 68.294, 51.269, 78.433, 30.773, 91.893, 46.538, 115.688, 130.097, 131.010, 60.738, 130.867, 32.143, 118.165, 173.085, 121.045, 87.599, 136.236, 166.119, 166.575, 52.996, 139.262, 72.279, 128.902, 140.734, 64.118, 98.826, 93.565, 126.500, 167.904, 54.359, 164.612, 71.950, 164.011, 165.707, 152.173, 157.601, 68.139, 31.100, 167.462, 72.356, 76.809, 108.363, 144.432, 96.776, 157.978, 96.275, 126.163, 173.108, 68.585, 138.910, 93.484, 126.758, 59.609, 135.606, 131.948, 153.581, 117.300, 43.113, 132.638, 145.963, 37.737, 162.437, 31.076, 171.224, 136.637, 125.571, 37.662, 130.106, 60.353, 136.337, 137.289, 127.801, 159.363, 114.295, 153.536, 98.246, 151.452, 115.755, 129.322, 167.420, 93.765, 116.912, 62.351, 93.407, 124.423, 118.617, 32.024, 62.496, 68.230, 149.732, 35.498, 67.658, 92.928, 108.153, 63.375, 116.143, 132.120, 157.634, 116.273, 149.412, 80.002, 85.348, 62.996, 119.996, 87.282, 118.731, 131.815, 64.504, 150.534, 146.148, 172.430, 152.355, 107.532, 30.795, 47.119, 135.379, 80.921, 123.309, 43.878, 153.155, 93.344, 80.888, 49.192, 161.438, 79.835, 107.047, 72.648, 101.135, 101.232, 149.402, 78.490, 43.084, 89.171, 63.450, 132.962, 117.536, 75.788, 55.201, 124.456, 141.343, 107.101, 45.096, 73.850, 123.076, 88.931, 149.226, 170.333, 41.894, 157.454, 97.102, 80.282, 47.002, 43.302, 156.114, 49.953, 154.346, 173.092, 135.778, 63.653, 37.867, 155.530, 83.344, 66.724, 42.868, 105.920, 87.334, 148.888, 44.386, 95.196, 106.207, 56.087, 118.905, 123.693, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000} ;

}
