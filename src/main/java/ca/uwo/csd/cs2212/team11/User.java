package ca.uwo.csd.cs2212.team11;

import ca.uwo.csd.cs2212.team11.UserLoginInfo;
import ca.uwo.csd.cs2212.team11.HistoricalFitnessData;
import ca.uwo.csd.cs2212.team11.TodaysData;
/* When UserPreferences class exists: */
/* import ca.uwo.csd.cs2212.team11.UserPreferences.*;  */

/** 
 * This class contains all information and data unique to this user. 
 * 
 * @author Team 11
 */

public class User {
	
private UserLoginInfo loginInfo;
/* private UserPreferences preferences; */
private HistoricalFitnessData historicalData;
private TodaysData todaysData;
	
/**
 *  Class constructor.
 */
public User() {
}

/**
 * Gets this user's login information.
 * 
 * @return loginInfo
 */
public UserLoginInfo getLoginInfo() {
	return loginInfo;
}

/**
 * Gets this user's fitness data so far today.
 *
 * @return todaysData
 */
public TodaysData getTodaysData() {
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
