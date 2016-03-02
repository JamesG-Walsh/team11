package ca.uwo.csd.cs2212.team11;


/* When UserPreferences class exists: */
/* import ca.uwo.csd.cs2212.team11.UserPreferences.*;  */

/** 
 * This class contains all information and data unique to this user. 
 * 
 * @author Team 11
 */

public class User {
	
/* private UserPreferences preferences; */
private HistoricalFitnessData historicalData;
private OneDaysWorthOfData todaysData;
	
/**
 *  Class constructor.
 */
public User() {
}



/**
 * Gets this user's fitness data so far today.
 *
 * @return todaysData
 */
public OneDaysWorthOfData getTodaysData() {
	return todaysData;
}

/**
 * Gets this user's fitness data from all past days and today.
 * 
 * @return historicalData
 */
public HistoricalFitnessData getHistoricalFitnessData(){
	return historicalData;
}

/**
 * Gets this user's fitness goals and application configuration preferences.
 * 
 * @return preferences
 */
/*
public UserPreferences getPreferences() {
	return preferences;
}
*/

}
