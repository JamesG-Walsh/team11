package ca.uwo.csd.cs2212.team11;

/**
 * This class contains this user's fitness data from all past days and today, 
 * accolades earned, accumulated fitness item values (for example, total distance 
 * traveled) over the lifetime of program use, and best days for particular fitness 
 * data items.
 * 
 * For stage 3 Will eventually include an attribute (probably a LinkedList) that contains all populated OneDaysWOrthOfData objects so that this class can be serialized and loaded upon the 
 * next run of the program.  The goal being that the program will not have to re-request data that it has already requested.
 * 
 * @author Team 11
 */
public class HistoricalFitnessData {

	/*
	 * commented out attributes for old design of class
	 * 
	private int[] historicalCalorieData;
	private double[] historicalDistanceData;
	private int[] historicalStepData;
	private int[] historicalFloorData; 
	private int[] historicalActiveMins;
	private int[] historicalSedentaryMins;
	private double[][] historicalTimeSeriesCalorieData;
	private int[][] historicalTimeSeriesStepsData;
	private double[][] historicalTimeSeriesDistanceData;
	private int[][] historicalTimeSeriesHeartRateData;
	 */

	private double bestDistance;
	private int bestSteps;
	private int bestFloors;
	private double lifetimeDistance;
	private int lifetimeSteps;
	private int lifetimeFloors;
	private String[] accoladesEarned;

	/**
	 *  Class constructor.
	 */
	public HistoricalFitnessData() {
	}

	/*
	 * Commented out methods for old design of class
	 * 	
public void loadUserData() {

}

public void storeUserData() {

}

public void loadCalories() {

}

public void storeCalories() {

}

public void loadDistance() {

}

public void storeDistance() {

}

public void loadSteps() {

}

public void storeSteps() {

}

public void loadFloors() {

}

public void storeFloors() {

}

public void loadActiveMins() {

}

public void storeActiveMins() {

}

public void loadSedentaryMins() {

}

public void storeSedentaryMins() {

}

public void loadHeartRate() {

}

public void storeHeartRate() {

}

public void loadAccolades(){

}

public void storeAccolades() {

}

public void loadTimeSeriesCalories() {

}

public void storeTimeSeriesCalories() {

}

public void loadTimeSeriesSteps() {

}

public void storeTimeSeriesSteps() {

}

public void loadTimeSeriesDistance() {

}

public void storeTimeSeriesDistance() {

}

public void loadTimeSeriesHeartRate() {

}

public void storeTimeSeriesHeartRate() {

}
	 */

	/**
	 * Gets this user's longest distance traveled in one day.
	 * 
	 * @return bestDistance
	 */
	public double getBestDistance() {
		return bestDistance;
	}

	/**
	 * Gets this user's greatest steps taken in one day.
	 * 
	 * @return bestSteps
	 */
	public int getBestSteps() {
		return bestSteps;
	}

	/**
	 * Gets this user's greatest floors ascended in one day.
	 * 
	 * @return bestFloors
	 */
	public int getBestFloors() {
		return bestFloors;
	}

	/**
	 * Gets this user's accumulated distance traveled since first started measuring.
	 * @return lifetimeDistance
	 */
	public double getLifetimeDistance(){
		return lifetimeDistance;
	}

	/**
	 * Gets this user's accumulated steps taken since first started measuring.
	 * @return lifetimeSteps
	 */
	public double getLifetimeSteps(){
		return lifetimeSteps;
	}

	/**
	 * Gets this user's accumulated floors ascended since first started measuring.
	 * @return lifetimeFloors
	 */
	public double getLifetimeFloors(){
		return lifetimeFloors;
	}

}
