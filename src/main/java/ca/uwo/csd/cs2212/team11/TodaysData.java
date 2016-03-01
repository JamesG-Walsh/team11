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
 * Sets this user's calories burned so far today.
 * 
 * @param todaysCaloriesBurned
 */
public void setTodaysCaloriesBurned( float todaysCaloriesBurned ) {
	this.todaysCaloriesBurned = todaysCaloriesBurned;
}

/**
 * Sets this user's distance traveled so far today.
 *  
 * @param todaysDistance
 */
public void setTodaysDistance( float todaysDistance ) {
	this.todaysDistance = todaysDistance;
}

/** 
 * Sets this user's steps taken so far today.
 * 
 * @param todaysSteps
 */
public void setTodaysSteps( float todaysSteps ) {
	this.todaysSteps = todaysSteps;
}

/** 
 * Sets this user's floors climbed so far today.
 * 
 * @param todaysFloors
 */
public void setTodaysFloors( float todaysFloors ) {
	this.todaysFloors = todaysFloors;
}

/** 
 * Sets this user's active minutes so far today.
 * 
 * @param todaysActiveMins
 */
public void setTodaysActiveMins( float todaysActiveMins ) {
	this.todaysActiveMins = todaysActiveMins;
}

/** 
 * Sets this user's sedentary minutes so far today.
 * 
 * @param todaysSedentaryMins
 */
public void setTodaysSedentaryMins( float todaysSedentaryMins ) {
	this.todaysSedentaryMins = todaysSedentaryMins;
}

/** 
 * Sets time of last update of this user's data.
 * 
 * @param lastUpdated
 */
public void setWhenUpdated( float lastUpdated ) {
	this.lastUpdated = lastUpdated;
}

/** 
 * Gets this user's calories burned so far today.
 * 
 * @return todaysCaloriesBurned
 */
public float getTodaysCaloriesBurned() {
	return todaysCaloriesBurned;
}

/** 
 * Gets this user's distance traveled so far today.
 * 
 * @return todaysDistance
 */
public float getTodaysDistance() {
	return todaysDistance;
}

/** 
 * Gets this user's steps taken so far today.
 * 
 * @return todaysSteps
 */
public float setTodaysSteps() {
	return todaysSteps;
}

/** 
 * Gets this user's floors climbed so far today.
 *  
 * @return todaysFloors
 */
public float getTodaysFloors() {
	return todaysFloors;
}

/** 
 * Gets this user's active minutes so far today.
 * 
 * @return todaysActiveMins 
 */
public float getTodaysActiveMins() {
	return todaysActiveMins;
}

/** 
 * Gets this user's sedentary minutes so far today.
 * 
 * @return todaysSedentaryMins  
 */
public float getTodaysSedentaryMins() {
	return todaysSedentaryMins;
}

/** 
 * Gets time of last update of this user's data.
 * 
 * @return lastUpdated
 */
public float getWhenUpdated() {
	return lastUpdated;
}

}
