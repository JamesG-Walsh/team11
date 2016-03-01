package ca.uwo.csd.cs2212.team11;

public class TodaysData {

private float todaysCaloriesBurned;
private float todaysDistance;
private float todaysSteps;
private float todaysFloors;
private float todaysActiveMins;
private float todaysSedentaryMins;
private float lastUpdated;


/**
 *  Class constructor.
 */
public TodaysData() {
}

/** 
 * @param todaysCaloriesBurned
 */
public void setTodaysCaloriesBurned( float todaysCaloriesBurned ) {
	this.todaysCaloriesBurned = todaysCaloriesBurned;
}

/** 
 * @param todaysDistance
 */
public void setTodaysDistance( float todaysDistance ) {
	this.todaysDistance = todaysDistance;
}

/** 
 * @param todaysSteps
 */
public void setTodaysSteps( float todaysSteps ) {
	this.todaysSteps = todaysSteps;
}

/** 
 * @param todaysFloors
 */
public void setTodaysFloors( float todaysFloors ) {
	this.todaysFloors = todaysFloors;
}

/** 
 * @param todaysActiveMins
 */
public void setTodaysActiveMins( float todaysActiveMins ) {
	this.todaysActiveMins = todaysActiveMins;
}

/** 
 * @param todaysSedentaryMins
 */
public void setTodaysSedentaryMins( float todaysSedentaryMins ) {
	this.todaysSedentaryMins = todaysSedentaryMins;
}

/** 
 * @param lastUpdated
 */
public void setWhenUpdated( float lastUpdated ) {
	this.lastUpdated = lastUpdated;
}

/** 
 * Gets today's calories burned.
 */
public float getTodaysCaloriesBurned() {
	return todaysCaloriesBurned;
}

/** 
 * Gets today's distance.
 */
public float getTodaysDistance() {
	return todaysDistance;
}

/** 
 * Gets today's floors.
 */
public float setTodaysSteps() {
	return todaysSteps;
}

/** 
 * Gets today's floors.
 */
public float getTodaysFloors() {
	return todaysFloors;
}

/** 
 * Gets today's active minutes.
 */
public float getTodaysActiveMins() {
	return todaysActiveMins;
}

/** 
 * Gets today's sedentary minutes.
 */
public float getTodaysSedentaryMins() {
	return todaysSedentaryMins;
}

/** 
 * Gets the time of last data update.
 */
public float getWhenUpdated() {
	return lastUpdated;
}

}
