package ca.uwo.csd.cs2212.team11;

import java.util.*;

import org.json.JSONException;
import org.json.JSONObject;
/**
 * This class contains this user's fitness data from all past days and from today, 
 * accolades earned, accumulated fitness item values over the lifetime of program use
 * (for example, total distance traveled), and best days for particular fitness 
 * data items.
 * 
 * @author Team 11
 */
public class HistoricalFitnessData 
{
	private double bestDistance;
	private int bestSteps;
	private double bestFloors;
	private double lifetimeDistance;
	private int lifetimeSteps;
	private double lifetimeFloors;
	private String[] accoladesEarned;
	private ArrayList<OneDaysWorthOfData> allOneDays; /* Sorted list of recorded days; lower indices are earlier days */ 
	/**
	 * 		To do: Figure out if ArrayList initialized to size>0 such that empty elements counted in size
	 * 		 	   and subsequent element additions leave empty spots as are- if so, affects methods to add and retrieve elements. 
	 */


	/**
	 *  Class constructor.
	 */
	public HistoricalFitnessData() 
	{
		allOneDays = new ArrayList<OneDaysWorthOfData>();
	}

	/** 
	 * Gets total number of days of data stored for this user. 
	 * 
	 * @return    Number of days that have fitness day recorded for
	 */
	public int getNumDays() {
		return allOneDays.size();
	}
	/** Adds given day to this user's list of days for which fitness data is recorded
	 * 
	 * @param odwod 	fitness data collected on one specific day
	 * @return boolean value	true if day added, and false if day already in historical set of days  
	 */
	public boolean addDay( OneDaysWorthOfData odwod ){
		/* Add day to ordered list of days */
		int i = allOneDays.size() - 1;
		boolean foundPos = false;
		int compare;
		while (!foundPos) {
			compare = odwod.compareTo(allOneDays.get(i)); 
			/* If odwod date is earlier than day at index i */
			if (compare == -1){ 
				/* If odwod date is earlier than earliest stored day */
				if (i==0) {
					foundPos = true;					
				}
				/* If odwod date is earlier than day at index i */
				else
					i--;
			}
			else {
				/* If odwod date is later than latest stored day */
				if (compare == 1) {
					/* Add latest day to end of list */
					foundPos = true;
					allOneDays.add(odwod); 
				}
				/* If we already have this day */
				if (compare == 0 ){
					foundPos = true;
					/* Error: Adding a day we already have */
					return false;
				}
			}
		}
		allOneDays.add(i, odwod);
		return true;
	}
	/** 
	 * Gets this user's fitness data for requested day. 
	 * 
	 * @param dayOfMonth	day of the month of date that want daily fitness data for  
	 * @param month		month of date that want daily fitness data for
	 * @param year	year of date that want daily fitness data for
	 * @return 	One day's worth of fitness data for requested date
	 * @exception Throw exception if no recorded fitness data for requested day
	 */
	/* Change method:THROW exception if day not present */
	public OneDaysWorthOfData retrieveDay( int dayOfMonth, int month, int year ) {		

		/* To find day of interest, binary search sorted array of days. */
		int size = allOneDays.size();
		int max = size - 1;
		int min = 0;
		int mid; 
		/* Bias first search point to a more recent day on assumption that user more likely  
		 * to review more recent days. This reduces average time of method; worst case time unaltered.*/
		if ( size > 30 )
			mid = 15;
		else
			mid = max/2;
		int compare;
		boolean found = false;
		while (!found) {
			/* If date is not among recorded historical days */
			if ((mid < 0) || (mid > (size - 1))) {
				/* throw error, instead? */
				return null;
			}			
			compare = compareDate(dayOfMonth, month, year, allOneDays.get(mid));
			/* If desired date is earlier than date at index i */
			if ( compare == -1 ) {
				max = mid - 1;
				mid = (max + min)/2; /* Average location */
			}
			else { 
				/* If desired date is later than day at index i */
				if (compare == 1) {
					min = mid + 1; 
					mid = (max + min)/2; /* Average location */
				}
				/* Found date */
				else 
					found = true;
			}
		}
		return allOneDays.get(mid);					
	}	

	/**
	 * Helps enable historical fitness data to persist between uses of application.
	 * Sets all of this user's recorded fitness data by replacing it.
	 * 
	 * @param List of single days worth of fitness data. 
	 */
	public void setAllOneDays(ArrayList<OneDaysWorthOfData> allOneDays){
		this.allOneDays = allOneDays;
	}

	/**
	 * Helps enable historical fitness data to persist between uses of application.
	 * Gets all of this user's recorded fitness data.
	 * 
	 * @return allOneDays
	 */
	public ArrayList<OneDaysWorthOfData> getAllOneDays(){
		return allOneDays;
	}

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
	public double getBestFloors() {
		return bestFloors;
	}

	/**
	 * Gets this user's accumulated distance traveled since first started measuring.
	 * 
	 * @return lifetimeDistance
	 */
	public double getLifetimeDistance(){
		return lifetimeDistance;
	}

	/**
	 * Gets this user's accumulated steps taken since first started measuring.
	 * 
	 * @return lifetimeSteps
	 */
	public double getLifetimeSteps(){
		return lifetimeSteps;
	}

	/**
	 * Gets this user's accumulated floors ascended since first started measuring.
	 * 
	 * @return lifetimeFloors
	 */
	public double getLifetimeFloors(){
		return lifetimeFloors;
	}

	/**
	 * Compares a given date to the date of a given set of daily fitness data.
	 * 
	 * @param dayOfMonth
	 * @param month
	 * @param year
	 * @param oswod
	 * @return      integer value indicating result of comparison: -1, 0, or 1 if given date is earlier than, 
	 * 				same as, or later than date of OneDaysWorthOfData object
	 */
	private int compareDate( int dayOfMonth, int month, int year, OneDaysWorthOfData odwod ) {

		/* Compare years of the dates */
		if ( year < odwod.getYear() )
			return -1;
		if ( year > odwod.getYear() )
			return 1;
		/* Years are the same, so compare months of the dates */
		if ( month < odwod.getMonth() )
			return -1;
		if ( month > odwod.getMonth() )
			return 1;
		/* Months are the same, so compare days-of-month of the dates */
		if ( dayOfMonth < odwod.getDayOfMonth() )
			return -1;
		if ( dayOfMonth > odwod.getDayOfMonth() )
			return 1;
		/* Dates are the same */
		return 0;
	}

	public void populateLifetimeAndBestDays()
	{
		try 
		{
			JSONObject jo = HttpClient.getLifetimeAndBestDays();
			JSONObject lifetimeTotalsJSON = jo.getJSONObject("lifetime").getJSONObject("total");
			
			this.lifetimeDistance = lifetimeTotalsJSON.getDouble("distance");
			this.lifetimeFloors = lifetimeTotalsJSON.getDouble("floors");
			this.lifetimeSteps = lifetimeTotalsJSON.getInt("steps");
			
			JSONObject bestDaysJSON = jo.getJSONObject("best").getJSONObject("total");
			
			System.out.println(bestDaysJSON.toString(1));
			
			this.bestDistance = bestDaysJSON.getJSONObject("distance").getDouble("value");
//	TODO	this.bestFloors = 
//			this.bestSteps = 
		} 
		catch (JSONException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getLifetimeAndBestDays()
	{
		String str = "Lifetime totals\n";
		
		str = str.concat("Distance: " + this.getLifetimeDistance() + "\tFloors: " + this.getLifetimeFloors() + "\tSteps: " + this.getLifetimeSteps());
		
		return str;
	}

}//end of class
