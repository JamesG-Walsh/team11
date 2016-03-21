package ca.uwo.csd.cs2212.team11;
import java.io.Serializable;

/** 
 * This class contains all information and data unique to this user. 
 * 
 * @author Team 11
 */

public class User implements Serializable
{
	private HistoricalFitnessData historicalData;
	private OneDaysWorthOfData todaysData;

	/**
	 *  Class constructor.
	 */
	public User() 
	{
		historicalData = new HistoricalFitnessData();
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
	 * Helps enable this user's fitness data to persist between uses of application.
	 * Sets all of this user's historical fitness data by replacing it.
	 * 
	 * @param one day's worth of fitness data
	 */
	public void setHistoricalFitnessData( HistoricalFitnessData hfd ) {
		this.historicalData = hfd;
	}

}
