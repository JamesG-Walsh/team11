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
 *
 */
public class ResponseParser 
{
	
	public static int parseDailyFloorsTotal(JSONObject jo) throws JSONException
	{
		String value = jo.getJSONArray("activities-tracker-floors").getJSONObject(0).getString("value");
		int ret = Integer.parseInt(value);
		return ret;
	}//end of method
	
	public static int parseDailyStepsTotal(JSONObject jo) throws JSONException
	{
		String value = jo.getJSONArray("activities-tracker-steps").getJSONObject(0).getString("value");
		int ret = Integer.parseInt(value);
		return ret;
	}//end of method
	
	//TODO 4 methods below
	public static double parseDailyCaloriesTotal(JSONObject jo) throws JSONException
	{
		String value = jo.getJSONArray("activities-tracker-calories").getJSONObject(0).getString("value");
		double ret = Double.parseDouble(value);
		return ret;
	}//end of method
	
	public static double parseDailyDistanceTotal(JSONObject jo) throws JSONException
	{
		String value = jo.getJSONArray("activities-tracker-distance").getJSONObject(0).getString("value");
		double ret = Double.parseDouble(value);
		return ret;
	}//end of method
	
	public static int parseDailySedentaryMinsTotal(JSONObject jo) throws JSONException
	{
		String value = jo.getJSONArray("activities-tracker-minutesSedentary").getJSONObject(0).getString("value");
		int ret = Integer.parseInt(value);
		return ret;
	}//end of method
	
	//public static int parseDailyActiveMinsTotal(JSONObject joLightlyActive, JSONObject joFairlyActive, JSONObject joVeryActive) throws JSONException
	{
		// TODO
		//String value = jo.getJSONArray("activities-tracker-steps").getJSONObject(0).getString("value");
		//int ret = Integer.parseInt(value);
		//return ret;
	}//end of method

} //end of class
