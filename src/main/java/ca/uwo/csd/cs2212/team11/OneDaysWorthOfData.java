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
	public void setFloorsByTheMin(JSONObject jo) throws JSONException 
	{
		JSONArray ja = jo.getJSONObject("activities-floors-intraday").getJSONArray("dataset");  
		//System.out.println("length: " + ja.length());
		//System.out.println(ja);
		/*for (int hour = 0, obj = 0; hour < 24 && obj < 1440; hour++)
		{
			for(int min=0 ; min < 60 ; min++ , obj++)
			{
				this.floorsByTheMin[hour][min] = ja.getJSONObject(obj).getInt("value");
				System.out.println(ja.getJSONObject(obj).getString("time") + "|||" + this.floorsByTheMin[hour][min]);
			}
		}*/
		for (int count = 0; count < ja.length(); count++)
		{
			System.out.println("obj: " + count);
			String time = ja.getJSONObject(count).getString("time");
			System.out.println(time);
			
			int hour = Integer.parseInt(time.substring(0, 2));
			int min = Integer.parseInt(time.substring(3, 5));
			System.out.println("hour:" + hour);
			System.out.println("min:" + min);
			
			this.floorsByTheMin[hour][min] = ja.getJSONObject(count).getInt("value");
			System.out.println(ja.getJSONObject(count).getString("time") + "|||" + this.floorsByTheMin[hour][min]);
			System.out.println("--------");
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
