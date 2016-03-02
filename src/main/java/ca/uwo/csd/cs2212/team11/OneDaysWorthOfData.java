package ca.uwo.csd.cs2212.team11;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;


public class OneDaysWorthOfData 
{

	private int todaysCaloriesBurned;
	private double todaysDistance;
	private int todaysSteps;
	private int todaysFloors;
	private int[][] floorsByTheMin = new int [24][60];
	private int todaysActiveMins;
	private int todaysSedentaryMins;
	private String lastUpdated;


	/**
	 *  Class constructor.
	 */
	public OneDaysWorthOfData() 
	{

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
	 * @return the floorsByTheMin
	 */
	public int[][] getFloorsByTheMin() {
		return floorsByTheMin;
	}

	/**
	 * @param floorsByTheMin the floorsByTheMin to set
	 * @throws JSONException 
	 */
	public void setFloorsByTheMin(JSONArray ja) throws JSONException 
	{
		System.out.println("length: " + ja.length());
		for (int i = 0; i < ja.length(); i++)
		{
			
			System.out.println(ja.getJSONObject(i));
		}
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

}
