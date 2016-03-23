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
	//private OneDaysWorthOfData todaysData;
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
	public HistoricalFitnessData getHistoricalFitnessData(){
		return historicalData;
	}
    

	/**
	 * Helps enable this user's fitness data to persist between uses of application.
	 * Sets all of this user's historical fitness data by replacing it.
	 * 
	 * @param one day's worth of fitness data
	 */
	
	/**
	 * Gets this user's daily goals.
	 *
	 * @return dg
	 */
	public DailyGoals getDailyGoals() {
		return this.dg;
	}
	
	public void setHistoricalFitnessData( HistoricalFitnessData hfd ) {
		this.historicalData = hfd;
	}


	public void setDailyGoals( DailyGoals dg ) {
		this.dg = dg;
	}
	
}
