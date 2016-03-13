package ca.uwo.csd.cs2212.team11;

import java.util.*;
/**
 * This class contains this user's fitness data from all past days and today, 
 * accolades earned, accumulated fitness item values (for example, total distance 
 * traveled) over the lifetime of program use, and best days for particular fitness 
 * data items.
 * 
 * For stage 3 Will eventually include an attribute (probably a LinkedList) that contains all populated OneDaysWOrthOfData objects so that this class can be serialized and loaded upon the 
 * next run of the program.  The goal being that the program will not have to re-request data that it has already requested.
 * 
 * @author Team 11
 */
public class HistoricalFitnessData {
	
	private double bestDistance;
	private int bestSteps;
	private int bestFloors;
	private double lifetimeDistance;
	private int lifetimeSteps;
	private int lifetimeFloors;
	private String[] accoladesEarned;
	private ArrayList<OneDaysWorthOfData> allOneDays; /* Sorted list of recorded days; lower indices are earlier days */ 
	
	/**
	 *  Class constructor.
	 */
	public HistoricalFitnessData() {
	}
	
	/**
	 * Class constructor with one day of historical data
	 */
	public HistoricalFitnessData( int numdays ) {
	/* numdays, logically, must be one */
		allOneDays = new ArrayList(numdays);
	}
	
	/** Gets total number of days of data stored for this user. 
	 * 
	 */
	public int getNumDays() {
		return allOneDays.size();
	}
	/** Adds new day's worth of data to set of historical data
	 * 
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
	
	/* Change method:THROW exception if day not present */
	public OneDaysWorthOfData retrieveDay( int dayOfMonth, int month, int year ) {		
		
		/* To find day of interest, binary search sorted array of days */
		int size = allOneDays.size();
		int max = size - 1;
		int min = 0;
		int mid = max/2;
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
	public int getBestFloors() {
		return bestFloors;
	}

	/**
	 * Gets this user's accumulated distance traveled since first started measuring.
	 * @return lifetimeDistance
	 */
	public double getLifetimeDistance(){
		return lifetimeDistance;
	}

	/**
	 * Gets this user's accumulated steps taken since first started measuring.
	 * @return lifetimeSteps
	 */
	public double getLifetimeSteps(){
		return lifetimeSteps;
	}

	/**
	 * Gets this user's accumulated floors ascended since first started measuring.
	 * @return lifetimeFloors
	 */
	public double getLifetimeFloors(){
		return lifetimeFloors;
	}
	
	/**
	 * Compares a given date to the date of a given OneDaysWorthOfData object
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
}
