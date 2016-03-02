/**
 * 
 */
package ca.uwo.csd.cs2212.team11;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

//import com.github.scribejava.core.*;
import com.github.scribejava.core.model.Response;

//import org.apache.http.util.EntityUtils;

/**
 * @author James Walsh
 *	Class with static methods that take JSON Objects as parameters and return the numerical values within
 */

//TODO for stage 3 add try/catch within each method for JSONException and don't throw
public class ResponseParser 
{
	/**
	 * static method that parses a provided JSONObject for a day of floors and returns the total floors as an int
	 * @param jo the JSONObject for the activity tracker for floors
	 * @return the total floors for the day
	 * @throws JSONException
	 */
	public static int parseDailyFloorsTotal(JSONObject jo) throws JSONException
	{
		String value = jo.getJSONArray("activities-tracker-floors").getJSONObject(0).getString("value");
		int ret = Integer.parseInt(value);
		return ret;
	}//end of method
	
	/**
	 * static method that parses a provided JSONObject for a day of steps and returns the total floors as an int
	 * @param jo the JSONObject for the activity tracker for steps
	 * @return the total steps for the day
	 * @throws JSONException
	 */
	public static int parseDailyStepsTotal(JSONObject jo) throws JSONException
	{
		String value = jo.getJSONArray("activities-tracker-steps").getJSONObject(0).getString("value");
		int ret = Integer.parseInt(value);
		return ret;
	}//end of method
	
	/**
	 * static method that parses a provided JSONObject for a day of floors and returns the total Calories burned as a double
	 * @param jo the JSONObject for the activity tracker for Calories burned
	 * @return the total Calories burned for the day
	 * @throws JSONException
	 */
	public static double parseDailyCaloriesTotal(JSONObject jo) throws JSONException
	{
		//TODO for stage 3, is this the right calories value? (caloriesBMR, calories or activityCalories?)
		String value = jo.getJSONArray("activities-tracker-calories").getJSONObject(0).getString("value");
		double ret = Double.parseDouble(value);
		return ret;
	}//end of method
	
	/**
	 * static method that parses a provided JSONObject for a day of floors and returns the total distance as a double
	 * @param jo the JSONObject for the activity tracker for distance
	 * @return the total distance for the day
	 * @throws JSONException
	 */
	public static double parseDailyDistanceTotal(JSONObject jo) throws JSONException
	{
		String value = jo.getJSONArray("activities-tracker-distance").getJSONObject(0).getString("value");
		double ret = Double.parseDouble(value);
		return ret;
	}//end of method
	
	/**
	 * static method that parses a provided JSONObject for a day of floors and returns the total sedentaryMins as an int
	 * @param jo the JSONObject for the activity tracker for distance
	 * @return the total distance for the day
	 * @throws JSONException
	 */
	public static int parseDailySedentaryMinsTotal(JSONObject jo) throws JSONException
	{
		String value = jo.getJSONArray("activities-tracker-minutesSedentary").getJSONObject(0).getString("value");
		int ret = Integer.parseInt(value);
		return ret;
	}//end of method
	
	/**
	 * static method that parses  3 provided JSONObjects (for the 3 types of active minutes) for a day of active minutes and returns the total active minutes as an int
	 * @param joLightlyActive 	the JSONObject containing the lightly active minutes
	 * @param joFairlyActive	the JSONObject containing the fairly active minutes
	 * @param joVeryActive		the JSONObject containing the very active minutes
	 * @return					the total active minutes for the day as an int
	 * @throws JSONException
	 */
	public static int parseDailyActiveMinsTotal(JSONObject joLightlyActive, JSONObject joFairlyActive, JSONObject joVeryActive) throws JSONException
	{
		String LAvalue = joLightlyActive.getJSONArray("activities-tracker-minutesLightlyActive").getJSONObject(0).getString("value");
		int LAMins = Integer.parseInt(LAvalue);
		
		String FAvalue = joFairlyActive.getJSONArray("activities-tracker-minutesFairlyActive").getJSONObject(0).getString("value");
		int FAMins = Integer.parseInt(FAvalue);
		
		String VAvalue = joVeryActive.getJSONArray("activities-tracker-minutesVeryActive").getJSONObject(0).getString("value");
		int VAMins = Integer.parseInt(VAvalue);
		
		return (LAMins+FAMins+VAMins);
	}//end of method

} //end of class
