package ca.uwo.csd.cs2212.team11;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.util.Date;

/**
 * Class that contains all numerical data for the dashboard & time series displays (except lifetime an best days which is stored in HistoricalFitnessData)
 * 
 * @author Alecia DeBoeck, James Walsh, Dara Amin, Abdi Ibrahim
 */
public class OneDaysWorthOfData 
{
	private int year;
	private int month;
	private int dayOfMonth; //the date that this object encapsulates

	private double todaysTotalCaloriesBurned; //the days total calories burned
	private double[][] caloriesByTheMin; //a 2D array that will hold minute by minute values for calories

	private double todaysTotalDistance;
	private double[][] distanceByTheMin;

	private int todaysTotalSteps;
	private int[][] stepsByTheMin;

	private int todaysTotalFloors;
	private int[][] floorsByTheMin;

	private int todaysTotalActiveMins;
	private int[][] activeMinsByTheMin;

	private int todaysTotalSedentaryMins;
	private int[][] sedentaryMinsByTheMin;

	private HeartRateDayOfData hrdod; //contains all data pertaining to heart rate

	private Date lastUpdated; //not used, desktop to keep track itself


	/**
	 *  Class constructor.
	 */
	public OneDaysWorthOfData() 
	{
	}

	/**
	 *  Class constructor.
	 *  @param year the current year
	 *  @param month the current month of the year (1 = jan, 12 = Dec)
	 *  @param dayOfMonth the current day of the month
	 */
	public OneDaysWorthOfData(int year, int month, int dayOfMonth) 
	{
		this.year = year;
		this.month = month;
		this.dayOfMonth = dayOfMonth;	
		
		this.todaysTotalCaloriesBurned = -1;			//initialize all fitness data attributes to -1 to indicate when they have not been populated without having to check for null
		this.todaysTotalDistance = -1;
		this.todaysTotalSteps = -1;
		this.todaysTotalFloors = -1;
		this.todaysTotalActiveMins = -1;
		this.todaysTotalSedentaryMins = -1;
		
		this.caloriesByTheMin = new double [24][60]; //initialize the arrays
		this.distanceByTheMin = new double [24][60];
		this.stepsByTheMin = new int [24][60];
		this.floorsByTheMin = new int [24][60];
		this.activeMinsByTheMin = new int [24][60];
		this.sedentaryMinsByTheMin = new int [24][60];
		
		for(int hour = 0; hour < 24; hour++)
		{
			for (int min = 0; min < 60; min++)
			{
				this.caloriesByTheMin[hour][min] = -1; //do the same for all minutes of time series data
				this.distanceByTheMin[hour][min] = -1;
				this.stepsByTheMin[hour][min] = -1;
				this.floorsByTheMin[hour][min] = -1;
				this.activeMinsByTheMin[hour][min] = -1;
				this.sedentaryMinsByTheMin[hour][min] = -1;
			}
		}
		
		this.hrdod = new HeartRateDayOfData(this.buildDateAsString()); // create separate modularized sub-container for heart rate data
	}

	/** 
	 * a method that Sets this user's calories burned so far today.
	 * 
	 * @param todaysCaloriesBurned the calories burned today by the user
	 */
	public void setTodaysTotalCaloriesBurned( double todaysCaloriesBurned )
	{
		this.todaysTotalCaloriesBurned = todaysCaloriesBurned;
	}

	/**
	 * Sets this user's distance traveled so far today.
	 *  
	 * @param todaysDistance distance covered today by the user
	 */
	public void setTodaysTotalDistance( double todaysDistance ) {
		this.todaysTotalDistance = todaysDistance;
	}

	/** 
	 * Sets this user's steps taken so far today.
	 * 
	 * @param todaysSteps steps taken today by the user
	 */
	public void setTodaysTotalSteps( int todaysSteps ) {
		this.todaysTotalSteps = todaysSteps;
	}

	/**
	 * Getter for total steps
	 * 
	 * @return the today's total Steps taken
	 */
	public int getTodaysTotalSteps() {
		int ret = this.todaysTotalSteps;
		return ret;
	}

	/** 
	 * Sets this user's floors climbed so far today.
	 * 
	 * @param todaysFloors the floors climbed today by the user
	 */
	public void setTodaysTotalFloors( int todaysFloors ) 
	{
		this.todaysTotalFloors = todaysFloors;
	}

	/** 
	 * Sets this user's active minutes so far today.
	 * 
	 * @param todaysActiveMins the minutes the user was active today
	 */
	public void setTodaysTotalActiveMins( int todaysActiveMins ) {
		this.todaysTotalActiveMins = todaysActiveMins;
	}

	/** 
	 * Sets this user's sedentary minutes so far today.
	 * 
	 * @param todaysSedentaryMins the minutes the user was inactive today
	 */
	public void setTodaysTotalSedentaryMins( int todaysSedentaryMins ) {
		this.todaysTotalSedentaryMins = todaysSedentaryMins;
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
	 * A method that sets the floors the user has climbed in every minute
	 * @param floorsByTheMin 
	 * @param jo the JSON object that contains the data for floors
	 * 
	 * @throws JSONException if the JSON object cannot be found          
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
		}
	}


	/**
	 * sets the steps taken by the user in every minute
	 * @param jo			the JSON object that contains the data for steps by the minute
	 * @throws Exception 	if the object cannot be found
	 */
	public void setStepsByTheMin(JSONObject jo) throws JSONException 
	{
		JSONArray ja = jo.getJSONObject("activities-steps-intraday").getJSONArray("dataset");  
		for (int count = 0; count < ja.length(); count++)
		{
			String time = ja.getJSONObject(count).getString("time");

			int hour = Integer.parseInt(time.substring(0, 2));
			int min = Integer.parseInt(time.substring(3, 5));

			this.stepsByTheMin[hour][min] = ja.getJSONObject(count).getInt("value");
			//System.out.println(ja.getJSONObject(count).getString("time") + "|||" + this.stepsByTheMin[hour][min]);
			//System.out.println("--------");
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
	public Date getWhenUpdated() {
		Date ret = this.lastUpdated;
		return ret;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year 
	 * sets the current year
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * @return the current month
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * @param month
	 * sets the current month of the year
	 */
	public void setMonth(int month) {
		this.month = month;
	}

	/**
	 * @return the dayOfMonth
	 */
	public int getDayOfMonth() {
		return dayOfMonth;
	}

	/**
	 * @param dayOfMonth the current day of the month
	 * sets the current day of the month
	 */
	public void setDayOfMonth(int dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}

	/**
	 * @return the caloriesByTheMin
	 * gets the calories burned in every minute
	 */
	public double[][] getCaloriesByTheMin() {
		return caloriesByTheMin;
	}

	/**
	 * sets the calories that the user has burned in every minute
	 * @param JSONObject jo	the JSON object that contains the data calories by the minute
	 * @throws JSONException if the JSON object cannot be found
	 */
	public void setCaloriesByTheMin(JSONObject jo) throws JSONException 
	{
		JSONArray ja = jo.getJSONObject("activities-calories-intraday").getJSONArray("dataset");  
		for (int count = 0; count < ja.length(); count++)
		{
			String time = ja.getJSONObject(count).getString("time");

			int hour = Integer.parseInt(time.substring(0, 2));
			int min = Integer.parseInt(time.substring(3, 5));

			this.caloriesByTheMin[hour][min] = ja.getJSONObject(count).getDouble("value");
		}
	}

	/**
	 * @return the distanceByTheMin
	 * gets the distance that the user has walked in every minute
	 */
	public double[][] getDistanceByTheMin() {
		return distanceByTheMin;
	}

	/**
	 * sets the distance the user has walked in every minute
	 * @param distanceByTheMin the distanceByTheMin to set
	 * @param jo the JSON object that contains the data for Distance
	 * @throws Exception if the object cannot be found
	 */
	public void setDistanceByTheMin(JSONObject jo) throws JSONException 
	{
		JSONArray ja = jo.getJSONObject("activities-distance-intraday").getJSONArray("dataset");  
		for (int count = 0; count < ja.length(); count++)
		{
			String time = ja.getJSONObject(count).getString("time");

			int hour = Integer.parseInt(time.substring(0, 2));
			int min = Integer.parseInt(time.substring(3, 5));

			this.distanceByTheMin[hour][min] = ja.getJSONObject(count).getDouble("value");
			this.distanceByTheMin[hour][min] = (this.distanceByTheMin[hour][min] * 1000); //convert from km to m
		}
	}

	/**
	 * Getter for stepsByTheMin
	 * @return the steps taken in every minute as 2d array
	 */
	public int[][] getStepsByTheMin() {
		return stepsByTheMin;
	}


	/**
	 * @return the activeMinsByTheMin
	 */
	public int[][] getActiveMinsByTheMin() {
		return activeMinsByTheMin;
	}

	/**
	 * @param activeMinsByTheMin the activeMinsByTheMin to set
	 */
	public void setActiveMinsByTheMin(int[][] activeMinsByTheMin) {
		this.activeMinsByTheMin = activeMinsByTheMin;
	}

	/**
	 * @return the sedentaryMinsByTheMin
	 */
	public int[][] getSedentaryMinsByTheMin() {
		return sedentaryMinsByTheMin;
	}

	/**
	 * sets the minutes that the user is inactive
	 * @param sedentaryMinsByTheMin 
	 * @param jo the JSON object that contains the data for sedentary minutes
	 * @throws JSONException if the the JSON object cannot be found
	 */
	public void setSedentaryMinsByTheMin(JSONObject jo) throws JSONException 
	{
		JSONArray ja = jo.getJSONObject("activities-minutesSedentary-intraday").getJSONArray("dataset");  
		for (int count = 0; count < ja.length(); count++)
		{
			String time = ja.getJSONObject(count).getString("time");

			int hour = Integer.parseInt(time.substring(0, 2));
			int min = Integer.parseInt(time.substring(3, 5));

			this.sedentaryMinsByTheMin[hour][min] = ja.getJSONObject(count).getInt("value");
		}
	}

	/**
	 * @return the hrdod
	 */
	public HeartRateDayOfData getHeartRateDayOfData() {
		return hrdod;
	}

	/**
	 * @param hrdod the hrdod to set
	 */
	public void setHrdod(HeartRateDayOfData hrdod) {
		this.hrdod = hrdod;
	}

	/**
	 * sets the time when the user's data was last updated
	 * @param lastUpdated 
	 */
	public void setLastUpdated(Date lastUpdated) {
		//TODO???
		this.lastUpdated = lastUpdated;
	}

	/**
	 * sets the floors the user has climbed in every minute
	 * Method may be used later in the development
	 * @param floorsByTheMin 
	 */
	public void setFloorsByTheMin(int[][] floorsByTheMin) {
		this.floorsByTheMin = floorsByTheMin;
	}




	/**
	 * @throws RateLimitExceededException 
	 * 
	 */
	public void populateTotals() throws RateLimitExceededException
	{
		String date = this.buildDateAsString();
		//System.out.println("date string for populateTotals() : "+ date);

		try
		{
			this.setTodaysTotalFloors(ResponseParser.parseDailyFloorsTotal(HttpClient.getSpecificDataDailyTotal("floors", date)));
			this.setTodaysTotalSteps(ResponseParser.parseDailyStepsTotal(HttpClient.getSpecificDataDailyTotal("steps", date)));
			this.setTodaysTotalCaloriesBurned(ResponseParser.parseDailyCaloriesTotal(HttpClient.getSpecificDataDailyTotal("calories",date)));
			this.setTodaysTotalDistance(ResponseParser.parseDailyDistanceTotal(HttpClient.getSpecificDataDailyTotal("distance", date)));
			this.setTodaysTotalSedentaryMins(ResponseParser.parseDailySedentaryMinsTotal(HttpClient.getSpecificDataDailyTotal("minutesSedentary", date)));

			JSONObject joLA = HttpClient.getSpecificDataDailyTotal("minutesLightlyActive",date);
			JSONObject joFA = HttpClient.getSpecificDataDailyTotal("minutesFairlyActive", date);
			JSONObject joVA = HttpClient.getSpecificDataDailyTotal("minutesVeryActive", date);		
			this.setTodaysTotalActiveMins(ResponseParser.parseDailyActiveMinsTotal(joLA, joFA, joVA));

		}
		catch (JSONException je)
		{
		}
		catch (NullPointerException npe)
		{
		}
	}

	/**
	 * @throws RateLimitExceededException 
	 * 
	 */
	public void populateAllMins() throws RateLimitExceededException
	{
		String date = this.buildDateAsString();

		try 
		{
			this.setStepsByTheMin(HttpClient.getSpecificDataByTheMin("steps",date, "1min", "00:00", "23:59"));
			this.setCaloriesByTheMin(HttpClient.getSpecificDataByTheMin("calories",date, "1min", "00:00", "23:59"));
			this.setDistanceByTheMin(HttpClient.getSpecificDataByTheMin("distance",date, "1min", "00:00", "23:59"));
			this.setFloorsByTheMin(HttpClient.getSpecificDataByTheMin("floors",date, "1min", "00:00", "23:59"));
			this.setSedentaryMinsByTheMin(HttpClient.getSpecificDataByTheMin("minutesSedentary", date, "1min", "00:00", "23:59"));

			JSONObject joLA = HttpClient.getSpecificDataByTheMin("minutesLightlyActive", date, "1min", "00:00", "23:59");
			JSONObject joFA = HttpClient.getSpecificDataByTheMin("minutesFairlyActive", date, "1min", "00:00", "23:59");
			JSONObject joVA = HttpClient.getSpecificDataByTheMin("minutesVeryActive", date, "1min", "00:00", "23:59");		
			this.setActiveMinsByTheMin(ResponseParser.parseActiveMinsByTheMin(joLA, joFA, joVA));

			//System.out.println(HttpClient.getHeartRateZones(date).toString(2));

			//TODO ResponseParser methods for minute setters(excluding activeMins which is done already) (optional but nice)
		}
		catch (JSONException je)
		{
		}
		catch (NullPointerException npe)
		{
		}

	}



	public String buildDateAsString()
	{
		//System.out.println("Entered build date as string");
		Integer yearObj = new Integer(year);
		Integer monthObj = new Integer(month);
		Integer dayObj = new Integer(dayOfMonth); //get the day of this object
		String date;

		if(dayOfMonth<10 && month <10)
		{
			//System.out.println("both < 10");
			date = (yearObj.toString() + "-0" + monthObj.toString() + "-0" + dayObj.toString());
		}
		else if(dayOfMonth<10 && month >=10)
		{
			//System.out.println("day < 10");
			date = (yearObj.toString() + "-" + monthObj.toString() + "-0" + dayObj.toString());
		}
		else if(dayOfMonth>=10 && month <10)
		{
			//System.out.println("month < 10");
			date = (yearObj.toString() + "-0" + monthObj.toString() + "-" + dayObj.toString());
		}
		else
		{
			//System.out.println("neither < 10");
			date = (yearObj.toString() + "-" + monthObj.toString() + "-" + dayObj.toString()); //format date string properly for URL
		}

		return date;
	}

	/**
	 * Compares this date to another date for the purpose of ordering and retrieving historical fitness data.
	 * 
	 * @param odwod OneDaysWorthOfData object being compared by date to this object
	 * @return relDate -1, 0, or 1 if this day is earlier than, the same as, or later than the input object's date
	 */
	public int compareTo( OneDaysWorthOfData odwod ) {
		int relDate;
		if (this.year < odwod.getYear())
			relDate = -1;
		else { 
			if (this.year > odwod.getYear())
				relDate = 1;
			else { /* Days in same year */ 
				if (this.month < odwod.getMonth())
					relDate = -1;
				else {
					if (this.month > odwod.getMonth())
						relDate = 1;
					else { /* Days in same year and same month */
						if (this.dayOfMonth < odwod.getDayOfMonth())
							relDate = -1;
						else {
							if (this.dayOfMonth > odwod.getDayOfMonth())
								relDate = 1;
							else
								relDate = 0;
						}
					}
				}
			}
		}
		return relDate;
	}

	public String toString(boolean includeMins)
	{
		String str = "\nTOTALS for "+ this.buildDateAsString() +"\n\n";

		str = str.concat("Floors:\t\t\t" + this.getTodaysTotalFloors());
		str = str.concat("\nSteps:\t\t\t" + this.getTodaysTotalSteps());
		str = str.concat("\nCalories:\t\t" + this.getTodaysTotalCaloriesBurned());
		str = str.concat("\nDistance:\t\t" + this.getTodaysTotalDistance());
		str = str.concat("\nSedentaryMins:\t\t" + this.getTodaysTotalSedentaryMins());
		str = str.concat("\nActiveMins:\t\t" + this.getTodaysTotalActiveMins());

		if(includeMins)
		{
			//concat mins
		}

		return str;
	}

}// end of class
