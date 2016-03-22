package ca.uwo.csd.cs2212.team11;

import java.util.*;

import org.json.JSONException;
import org.json.JSONObject;
import ca.uwo.csd.cs2212.team11.Team11_FitBitViewer.*;

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
	private double bestDistanceValue;
	private String bestDistanceDate;

	private int bestStepsValue;
	private String bestStepsDate;

	private double bestFloorsValue;
	private String bestFloorsDate;

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

		if ( i < 0 ) {
			allOneDays.add(odwod);
			return true;
		}

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
	public OneDaysWorthOfData retrieveDay(int dayOfMonth, int month, int year ) 
	{		
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
				OneDaysWorthOfData odwod = new OneDaysWorthOfData(year, month, dayOfMonth);
				this.addDay(odwod);
				return odwod;
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

	public OneDaysWorthOfData retrieve2(int dayOfMonth, int month, int year)
	{
		if(Team11_FitBitViewer.testFlag)
		{

			return null;
		}
		else
		{
				System.out.println("entering retrieve2.  Size of container is " + this.allOneDays.size() + "...\n");
				System.out.println("looking for: " + dayOfMonth + " " + month + " " + year);
				Iterator<OneDaysWorthOfData> itr = this.getAllOneDays().iterator();
				OneDaysWorthOfData odwod = null;

				while (itr.hasNext())
				{
					odwod = itr.next();
					if(year == odwod.getYear() && month == odwod.getMonth() && dayOfMonth == odwod.getDayOfMonth())
					{
						System.out.println("Found odwod : " + odwod.buildDateAsString() + " in ArrayList container.\n Returning from retreive2.");
						return odwod;
					}
				}				
	
				odwod = new OneDaysWorthOfData(year, month, dayOfMonth);
				System.out.println("Odwod for " + odwod.buildDateAsString() + "not in ArrayList container.  Creating new odwod and adding to container.");
				odwod.populateTotals();
				this.addDay(odwod);
				System.out.print("New Size is " + this.getAllOneDays().size() + "\n");
				System.out.println(odwod.toString(false));
				System.out.println("Returning from retrieve2");
				return odwod;
		}
	}//end of retreive2 method

	/**
	 * Helps enable historical fitness data to persist between uses of application.
	 * Sets all of this user's recorded fitness data by replacing it.
	 * 
	 * @param List of single days worth of fitness data. 
	 */
	public void setAllOneDays(ArrayList<OneDaysWorthOfData> allOneDays){ //TODO why is this public method needed? for serialization?
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

	public double getBestDistanceValue() {
		return bestDistanceValue;
	}

	/**
	 * Gets this user's greatest steps taken in one day.
	 * 
	 * @return bestSteps
	 */
	public int getBestStepsValue() {
		return bestStepsValue;
	}

	/**
	 * Gets this user's greatest floors ascended in one day.
	 * 
	 * @return bestFloors
	 */
	public double getBestFloorsValue() {
		return bestFloorsValue;
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
	 * @return the bestDistanceDate
	 */
	public String getBestDistanceDate() {
		return bestDistanceDate;
	}

	/**
	 * @return the bestStepsDate
	 */
	public String getBestStepsDate() {
		return bestStepsDate;
	}

	/**
	 * @return the bestFloorsDate
	 */
	public String getBestFloorsDate() {
		return bestFloorsDate;
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

			//System.out.println(bestDaysJSON.toString(1));

			this.bestDistanceValue = bestDaysJSON.getJSONObject("distance").getDouble("value");
			this.bestDistanceDate = bestDaysJSON.getJSONObject("distance").getString("date");

			this.bestFloorsValue = bestDaysJSON.getJSONObject("floors").getDouble("value");
			this.bestFloorsDate = bestDaysJSON.getJSONObject("floors").getString("date");

			this.bestStepsValue = bestDaysJSON.getJSONObject("steps").getInt("value");
			this.bestStepsDate = bestDaysJSON.getJSONObject("steps").getString("date");
		} 
		catch (JSONException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String lifetimeAndBestDaysToString()
	{
		String str = "\nLifetime totals\n";
		str = str.concat("Distance: " + this.getLifetimeDistance() + "\t\tFloors: " + this.getLifetimeFloors() + "\t\t\tSteps: " + this.getLifetimeSteps());

		str = str.concat("\nBest Days\n");
		str = str.concat("Distance: " + this.getBestDistanceValue() + ", " + this.getBestDistanceDate() + "\tFloors: " + this.getBestFloorsValue() + ", " + this.getBestFloorsDate() + "\tSteps: " + this.getBestStepsValue() + ", " + this.getBestStepsDate());

		return str;
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
	
	

	
}//end of class
