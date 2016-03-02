package ca.uwo.csd.cs2212.team11;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;


public class OneDaysWorthOfData 
{
	private double todaysTotalCaloriesBurned;
	private double[][] caloriesByTheMin = new double [24][60];
	
	private double todaysTotalDistance;
	private double[][] distanceByTheMin = new double [24][60];
	
	private int todaysTotalSteps;
	private int[][] stepsByTheMin = new int [24][60];
	
	private int todaysTotalFloors;
	private int[][] floorsByTheMin = new int [24][60];
	
	private int todaysTotalActiveMins;
	private int[][] activeMinsByTheMin = new int [24][60];
	
	private int todaysTotalSedentaryMins;
	private int[][] sedentaryMinsByTheMin = new int [24][60];
	
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
	public void setTodaysTotalCaloriesBurned( double todaysCaloriesBurned ) {
		this.todaysTotalCaloriesBurned = todaysCaloriesBurned;
	}

	/**
	 * Sets this user's distance traveled so far today.
	 *  
	 * @param todaysDistance
	 */
	public void setTodaysTotalDistance( double todaysDistance ) {
		this.todaysTotalDistance = todaysDistance;
	}

	/** 
	 * Sets this user's steps taken so far today.
	 * 
	 * @param todaysSteps
	 */
	public void setTodaysTotalSteps( int todaysSteps ) {
		this.todaysTotalSteps = todaysSteps;
	}

	/**
	 * @return the todaysTotalSteps
	 */
	public int getTodaysTotalSteps() {
		int ret = this.todaysTotalSteps;
		return ret;
	}

	/** 
	 * Sets this user's floors climbed so far today.
	 * 
	 * @param todaysFloors
	 */
	public void setTodaysTotalFloors( int todaysFloors ) {
		this.todaysTotalFloors = todaysFloors;
	}

	/** 
	 * Sets this user's active minutes so far today.
	 * 
	 * @param todaysActiveMins
	 */
	public void setTodaysTotalActiveMins( int todaysActiveMins ) {
		this.todaysTotalActiveMins = todaysActiveMins;
	}

	/** 
	 * Sets this user's sedentary minutes so far today.
	 * 
	 * @param todaysSedentaryMins
	 */
	public void setTodaysTotalSedentaryMins( int todaysSedentaryMins ) {
		this.todaysTotalSedentaryMins = todaysSedentaryMins;
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
	public double getTodaysTotalCaloriesBurned() {
		double ret = this.todaysTotalCaloriesBurned;
		return ret;
	}

	/** 
	 * Gets this user's distance traveled so far today.
	 * 
	 * @return todaysDistance
	 */
	public double getTodaysTotalDistance() {
		double ret = this.todaysTotalDistance;
		return ret;
	}

	/** 
	 * Gets this user's floors climbed so far today.
	 *  
	 * @return todaysFloors
	 */
	public int getTodaysTotalFloors() {
		int ret = this.todaysTotalFloors;
		return ret;
	}

	/**
	 * @return the floorsByTheMin
	 */
	public int[][] getFloorsByTheMin() {
		return this.floorsByTheMin;
	}

	/**
	 * @param floorsByTheMin the floorsByTheMin to set
	 * @throws JSONException 
	 */
	public void setFloorsByTheMin(JSONObject jo) throws JSONException 
	{
		JSONArray ja = jo.getJSONObject("activities-floors-intraday").getJSONArray("dataset");  
		for (int count = 0; count < ja.length(); count++)
		{
			String time = ja.getJSONObject(count).getString("time");
			
			int hour = Integer.parseInt(time.substring(0, 2));
			int min = Integer.parseInt(time.substring(3, 5));
			
			this.floorsByTheMin[hour][min] = ja.getJSONObject(count).getInt("value");
			System.out.println(ja.getJSONObject(count).getString("time") + "|||" + this.floorsByTheMin[hour][min]);
			System.out.println("--------");
		}
	}
	
	public void setStepsByTheMin(JSONObject jo) throws JSONException 
	{
		JSONArray ja = jo.getJSONObject("activities-steps-intraday").getJSONArray("dataset");  
		for (int count = 0; count < ja.length(); count++)
		{
			String time = ja.getJSONObject(count).getString("time");
			
			int hour = Integer.parseInt(time.substring(0, 2));
			int min = Integer.parseInt(time.substring(3, 5));
			
			this.stepsByTheMin[hour][min] = ja.getJSONObject(count).getInt("value");
			System.out.println(ja.getJSONObject(count).getString("time") + "|||" + this.stepsByTheMin[hour][min]);
			System.out.println("--------");
		}
	}

	/** 
	 * Gets this user's active minutes so far today.
	 * 
	 * @return todaysActiveMins 
	 */
	public int getTodaysTotalActiveMins() {
		int ret = this.todaysTotalActiveMins;
		return ret;
	}

	/** 
	 * Gets this user's sedentary minutes so far today.
	 * 
	 * @return todaysSedentaryMins  
	 */
	public int getTodaysTotalSedentaryMins() {
		int ret = this.todaysTotalSedentaryMins;
		return ret;
	}

	/** 
	 * Gets time of last update of this user's data.
	 * 
	 * @return lastUpdated
	 */
	public String getWhenUpdated() {
		String ret = this.lastUpdated;
		return ret;
	}
	

}
