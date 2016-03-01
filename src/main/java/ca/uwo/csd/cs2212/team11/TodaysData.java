package ca.uwo.csd.cs2212.team11;

/**
 * This class contains this user's steps taken, calories burned, and other 
 * fitness data items, throughout and in total so far today, and the time 
 * this data was last updated. 
 *
 * @author Team 11
 */
public class TodaysData {

private int todaysCaloriesBurned;
private double todaysDistance;
private int todaysSteps;
private int todaysFloors;
private int todaysActiveMins;
private int todaysSedentaryMins;
private double[] timeSeriesCaloriesBurned;
private int[] timeSeriesSteps;
private double[] timeSeriesDistance;
private int[] timeSeriesHeartRate;
private String lastUpdated;


/**
 *  Class constructor.
 */
public TodaysData() {
}

/** 
 * Sets this user's calories burned so far today.
 * 
 * @param todaysCaloriesBurned
 */
public void setTodaysCaloriesBurned( int todaysCaloriesBurned ) {
	this.todaysCaloriesBurned = todaysCaloriesBurned;
}

/**
 * Sets this user's distance traveled so far today.
 *  
 * @param todaysDistance
 */
public void setTodaysDistance( double todaysDistance ) {
	this.todaysDistance = todaysDistance;
}

/** 
 * Sets this user's steps taken so far today.
 * 
 * @param todaysSteps
 */
public void setTodaysSteps( int todaysSteps ) {
	this.todaysSteps = todaysSteps;
}

/** 
 * Sets this user's floors climbed so far today.
 * 
 * @param todaysFloors
 */
public void setTodaysFloors( int todaysFloors ) {
	this.todaysFloors = todaysFloors;
}

/** 
 * Sets this user's active minutes so far today.
 * 
 * @param todaysActiveMins
 */
public void setTodaysActiveMins( int todaysActiveMins ) {
	this.todaysActiveMins = todaysActiveMins;
}

/** 
 * Sets this user's sedentary minutes so far today.
 * 
 * @param todaysSedentaryMins
 */
public void setTodaysSedentaryMins( int todaysSedentaryMins ) {
	this.todaysSedentaryMins = todaysSedentaryMins;
}

/** 
 * Sets time of last update of this user's data.
 * 
 * @param lastUpdated
 */
public void setWhenUpdated( String lastUpdated ) {
	this.lastUpdated = lastUpdated;
}

public void storeTimeSeriesCaloriesBurned() {
	
}

public void storeTimeSeriesSteps() {
	
}

public void storeTimeSeriesDistance() {
	
}

public void storeTimeSeriesHeartRate(){
	
}


/** 
 * Gets this user's calories burned so far today.
 * 
 * @return todaysCaloriesBurned
 */
public int getTodaysCaloriesBurned() {
	return todaysCaloriesBurned;
}

/** 
 * Gets this user's distance traveled so far today.
 * 
 * @return todaysDistance
 */
public double getTodaysDistance() {
	return todaysDistance;
}

/** 
 * Gets this user's steps taken so far today.
 * 
 * @return todaysSteps
 */
public int setTodaysSteps() {
	return todaysSteps;
}

/** 
 * Gets this user's floors climbed so far today.
 *  
 * @return todaysFloors
 */
public int getTodaysFloors() {
	return todaysFloors;
}

/** 
 * Gets this user's active minutes so far today.
 * 
 * @return todaysActiveMins 
 */
public int getTodaysActiveMins() {
	return todaysActiveMins;
}

/** 
 * Gets this user's sedentary minutes so far today.
 * 
 * @return todaysSedentaryMins  
 */
public int getTodaysSedentaryMins() {
	return todaysSedentaryMins;
}

/** 
 * Gets time of last update of this user's data.
 * 
 * @return lastUpdated
 */
public String getWhenUpdated() {
	return lastUpdated;
}

public void loadTimeSeriesCaloriesBurned() {
	
}

public void loadTimeSeriesSteps() {
	
}

public void loadTimeSeriesDistance() {
	
}

public void loadTimeSeriesHeartRate(){
	
}

}
