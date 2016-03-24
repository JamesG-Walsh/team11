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
	//private OneDaysWorthOfData todaysData; //get from hfd instead
	private DailyGoals dg;

	/**
	 *  Class constructor.
	 */
	public User() 
	{
		historicalData = new HistoricalFitnessData();

		dg = new DailyGoals();
	}


	/**
	 * Gets this user's fitness data from all past days and today.
	 * 
	 * @return historicalData
	 */
	public HistoricalFitnessData getHistoricalFitnessData()
	{
		return historicalData;
	}
    
	
	/**
	 * Gets this user's daily goals.
	 *
	 * @return dg
	 */
	public DailyGoals getDailyGoals() {
		return this.dg;
	}
	

	/**
	 * sets daily goals
	 * @param dg
	 */
	public void setDailyGoals( DailyGoals dg ) {
		this.dg = dg;
	}
	
}
