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
 * @author James Walsh, Dara Amin, Abdi Ibrahim
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

	public static int[][] parseActiveMinsByTheMin (JSONObject joLightlyActive, JSONObject joFairlyActive, JSONObject joVeryActive)
	{
		int[][] lightlyActiveMins = new int[24][60];
		int[][] fairlyActiveMins = new int[24][60];
		int[][] veryActiveMins = new int[24][60]; //create temporary containers
		int[][] totalActiveMins = new int[24][60]; //create object to be returned

		JSONArray ja = null;
		int hour;
		int min; 

		try 
		{
			ja = joLightlyActive.getJSONObject("activities-minutesLightlyActive-intraday").getJSONArray("dataset"); //get the JSONArray of values
		} 
		catch (JSONException e) 
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		for (int i = 0; i < ja.length(); i++)
		{
			try 
			{
				String time = ja.getJSONObject(i).getString("time"); //get time as string
				hour = Integer.parseInt(time.substring(0, 2));
				min = Integer.parseInt(time.substring(3, 5)); //convert time to ints
				lightlyActiveMins[hour][min] = Integer.parseInt(ja.getJSONObject(i).getString("value"));	//store in temp container	
			} 
			catch (NumberFormatException e) 
			{
				System.out.println(e.getMessage());
				e.printStackTrace();
			} 
			catch (JSONException e) 
			{
				System.out.println(e.getMessage());
				e.printStackTrace();
			}	
		}

		try 
		{
			ja = joFairlyActive.getJSONObject("activities-minutesFairlyActive-intraday").getJSONArray("dataset");
		} 
		catch (JSONException e) 
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		for (int i = 0; i < ja.length(); i++)
		{
			try 
			{
				String time = ja.getJSONObject(i).getString("time"); //get time as string
				hour = Integer.parseInt(time.substring(0, 2));
				min = Integer.parseInt(time.substring(3, 5)); //convert time to ints
				fairlyActiveMins[hour][min] = Integer.parseInt(ja.getJSONObject(i).getString("value"));	//store in temp container	
			} 
			catch (NumberFormatException e) 
			{
				System.out.println(e.getMessage());
				e.printStackTrace();
			} 
			catch (JSONException e) 
			{
				System.out.println(e.getMessage());
				e.printStackTrace();
			}	
		}
		
		try 
		{
			ja = joVeryActive.getJSONObject("activities-minutesVeryActive-intraday").getJSONArray("dataset");
		} 
		catch (JSONException e) 
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		for (int i = 0; i < ja.length(); i++)
		{
			try 
			{
				String time = ja.getJSONObject(i).getString("time"); //get time as string
				hour = Integer.parseInt(time.substring(0, 2));
				min = Integer.parseInt(time.substring(3, 5)); //convert time to ints
				veryActiveMins[hour][min] = Integer.parseInt(ja.getJSONObject(i).getString("value"));	//store in temp container	
			} 
			catch (NumberFormatException e) 
			{
				System.out.println(e.getMessage());
				e.printStackTrace();
			} 
			catch (JSONException e) 
			{
				System.out.println(e.getMessage());
				e.printStackTrace();
			}	
		}
		
		
		
		for(hour = 0; hour < 24; hour++)
		{
			for(min = 0 ; min < 60 ; min++)
			{
				totalActiveMins[hour][min] = (lightlyActiveMins[hour][min] + fairlyActiveMins[hour][min] + veryActiveMins[hour][min]); //sum temp containers
			}
		}

		return totalActiveMins;
	}// end of parseActiveMinsByTheMin() method
	
//	/**
//	 * 
//	 * @param jo
//	 * @return returns an integer array containing 9 integers which are indexed 0: resting heart rate, 1: Out of Range min, 2: Out of Range max,
//	 * 3: Fat Burn min,4: Fat Burn max, 5: Cardio min,6: Cardio max,7: Peak min,8: Peak max
//	 */
//	public static int[] parseHeartRateZoneBoundaries(JSONObject jo)
//	{
//		int[] heartRateZoneBoundaries = new int[24][60]; //create object to be returned
//
//		JSONArray ja = null;
//		int hour;
//		int min; 
//		
//		jo.
//		return null;
//	}
	
	

} //end of class
