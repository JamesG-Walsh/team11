/**
 * Container class that stores all data pertaining to heart rate info for a given day
 */
package ca.uwo.csd.cs2212.team11;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author James Walsh
 *
 */
public class HeartRateDayOfData
{
	private String date; //date in the form of a string formatted yyyy-mm-dd
	
	private int restingHeartRate;
	
	private int[][] heartRateByTheMin;
	
	private int outOfRangeZoneMaximum;
	private int outOfRangeZoneMinimum;
	private int outOfRangeZoneMinutes;
	
	private int fatBurnZoneMaximum;
	private int fatBurnZoneMinimum;
	private int fatBurnZoneMinutes;
	
	private int cardioZoneMaximum;
	private int cardioZoneMinimum;
	private int cardioZoneMinutes;
	
	private int peakZoneMaximum;
	private int peakZoneMinimum;
	private int peakZoneMinutes;
	
	public HeartRateDayOfData(String date)
	{
		this.date = date;
		heartRateByTheMin = new int [24][60];
	}
	
	/**
	 * @param restingHeartRate the restingHeartRate to set
	 */
	public void populate() 
	{
		
		try 
		{
			JSONObject jo = HttpClient.getHeartRateZones(this.date); // make server request and get the resulting JSONObject
			JSONObject activitiesHeart;
			activitiesHeart = jo.getJSONArray("activities-heart").getJSONObject(0).getJSONObject("value");
			
			this.restingHeartRate = activitiesHeart.getInt("restingHeartRate"); //set the resting heart rate //TODO  why is this not in the JSON?
			//this.restingHeartRate = 75;
			
			JSONArray ja = activitiesHeart.getJSONArray("heartRateZones"); //get array of heart rate zones
			
			this.outOfRangeZoneMaximum = ja.getJSONObject(0).getInt("max");
			this.outOfRangeZoneMinimum = ja.getJSONObject(0).getInt("min");
			this.outOfRangeZoneMinutes = ja.getJSONObject(0).getInt("minutes");
			
			this.fatBurnZoneMaximum = ja.getJSONObject(1).getInt("max");
			this.fatBurnZoneMinimum = ja.getJSONObject(1).getInt("min");
			this.fatBurnZoneMinutes = ja.getJSONObject(1).getInt("minutes");
			
			this.cardioZoneMaximum = ja.getJSONObject(2).getInt("max");
			this.cardioZoneMinimum = ja.getJSONObject(2).getInt("min");
			this.cardioZoneMinutes = ja.getJSONObject(2).getInt("minutes");
			
			this.peakZoneMaximum = ja.getJSONObject(3).getInt("max");
			this.peakZoneMinimum = ja.getJSONObject(3).getInt("min");
			this.peakZoneMinutes = ja.getJSONObject(3).getInt("minutes"); //set the zone boundaries and record the total mins in each zone
			
			ja = jo.getJSONObject("activities-heart-intraday").getJSONArray("dataset"); //now get the by the minute data
			int hour;
			int min;
			JSONObject dataPoint;
			for(int i = 0; i < ja.length(); i++) //loop through all minutes
			{
				dataPoint = ja.getJSONObject(i);
				hour = Integer.parseInt(dataPoint.getString("time").substring(0, 2));
				min = Integer.parseInt(dataPoint.getString("time").substring(3, 5));
				this.heartRateByTheMin[hour][min] = dataPoint.getInt("value");			//store the value for the current min in the 2D array
			}
		}
		catch (JSONException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public String toString()
	{
		String str = ("\n\nHeart Rate Zone Data for " + this.date);
		
		str = str.concat("\nResting Heart Rate:\t" + this.getRestingHeartRate());
		
		str = str.concat("\n\nOut Of Range\tMinimum:" + this.getOutOfRangeZoneMinimum() + "\tMaximum:" + this.getOutOfRangeZoneMaximum() + "\tMinutes:" + this.getOutOfRangeZoneMinutes());
		str = str.concat("\nFat Burn\tMinimum:" + this.getFatBurnZoneMinimum() + "\tMaximum:" + this.getFatBurnZoneMaximum() + "\tMinutes:" + this.getFatBurnZoneMinutes());
		str = str.concat("\nCardio\t\tMinimum:" + this.getCardioZoneMinimum() + "\tMaximum:" + this.getCardioZoneMaximum() + "\tMinutes:" + this.getCardioZoneMinutes());
		str = str.concat("\nPeak\t\tMinimum:" + this.getPeakZoneMinimum() + "\tMaximum:" + this.getPeakZoneMaximum() + "\tMinutes:" + this.getPeakZoneMinutes());
		
		str = str.concat("\n");
		
		for (int hour = 0 ; hour < 24; hour++)
		{
			for(int min = 0; min < 60; min++)
			{
				str = str.concat("\n" + hour + ":" + min + "\t heart rate: " + this.heartRateByTheMin[hour][min]);
			}
		}
		str = str.concat("\nEnd of Heart Rate Data\n");
		
		return str;
	}

	/**
	 * @return the restingHeartRate
	 */
	public int getRestingHeartRate() {
		return restingHeartRate;
	}

	/**
	 * @param restingHeartRate the restingHeartRate to set
	 */
	public void setRestingHeartRate(int restingHeartRate) {
		this.restingHeartRate = restingHeartRate;
	}

	/**
	 * @return the outOfRangeZoneMaximum
	 */
	public int getOutOfRangeZoneMaximum() {
		return outOfRangeZoneMaximum;
	}

	/**
	 * @param outOfRangeZoneMaximum the outOfRangeZoneMaximum to set
	 */
	public void setOutOfRangeZoneMaximum(int outOfRangeZoneMaximum) {
		this.outOfRangeZoneMaximum = outOfRangeZoneMaximum;
	}

	/**
	 * @return the outOfRangeZoneMinimum
	 */
	public int getOutOfRangeZoneMinimum() {
		return outOfRangeZoneMinimum;
	}

	/**
	 * @param outOfRangeZoneMinimum the outOfRangeZoneMinimum to set
	 */
	public void setOutOfRangeZoneMinimum(int outOfRangeZoneMinimum) {
		this.outOfRangeZoneMinimum = outOfRangeZoneMinimum;
	}

	/**
	 * @return the outOfRangeZoneMinutes
	 */
	public int getOutOfRangeZoneMinutes() {
		return outOfRangeZoneMinutes;
	}

	/**
	 * @param outOfRangeZoneMinutes the outOfRangeZoneMinutes to set
	 */
	public void setOutOfRangeZoneMinutes(int outOfRangeZoneMinutes) {
		this.outOfRangeZoneMinutes = outOfRangeZoneMinutes;
	}

	/**
	 * @return the fatBurnZoneMaximum
	 */
	public int getFatBurnZoneMaximum() {
		return fatBurnZoneMaximum;
	}

	/**
	 * @param fatBurnZoneMaximum the fatBurnZoneMaximum to set
	 */
	public void setFatBurnZoneMaximum(int fatBurnZoneMaximum) {
		this.fatBurnZoneMaximum = fatBurnZoneMaximum;
	}

	/**
	 * @return the fatBurnZoneMinimum
	 */
	public int getFatBurnZoneMinimum() {
		return fatBurnZoneMinimum;
	}

	/**
	 * @param fatBurnZoneMinimum the fatBurnZoneMinimum to set
	 */
	public void setFatBurnZoneMinimum(int fatBurnZoneMinimum) {
		this.fatBurnZoneMinimum = fatBurnZoneMinimum;
	}

	/**
	 * @return the fatBurnZoneMinutes
	 */
	public int getFatBurnZoneMinutes() {
		return fatBurnZoneMinutes;
	}

	/**
	 * @param fatBurnZoneMinutes the fatBurnZoneMinutes to set
	 */
	public void setFatBurnZoneMinutes(int fatBurnZoneMinutes) {
		this.fatBurnZoneMinutes = fatBurnZoneMinutes;
	}

	/**
	 * @return the cardioZoneMaximum
	 */
	public int getCardioZoneMaximum() {
		return cardioZoneMaximum;
	}

	/**
	 * @param cardioZoneMaximum the cardioZoneMaximum to set
	 */
	public void setCardioZoneMaximum(int cardioZoneMaximum) {
		this.cardioZoneMaximum = cardioZoneMaximum;
	}

	/**
	 * @return the cardioZoneMinimum
	 */
	public int getCardioZoneMinimum() {
		return cardioZoneMinimum;
	}

	/**
	 * @param cardioZoneMinimum the cardioZoneMinimum to set
	 */
	public void setCardioZoneMinimum(int cardioZoneMinimum) {
		this.cardioZoneMinimum = cardioZoneMinimum;
	}

	/**
	 * @return the cardioZoneMinutes
	 */
	public int getCardioZoneMinutes() {
		return cardioZoneMinutes;
	}

	/**
	 * @param cardioZoneMinutes the cardioZoneMinutes to set
	 */
	public void setCardioZoneMinutes(int cardioZoneMinutes) {
		this.cardioZoneMinutes = cardioZoneMinutes;
	}

	/**
	 * @return the peakZoneMaximum
	 */
	public int getPeakZoneMaximum() {
		return peakZoneMaximum;
	}

	/**
	 * @param peakZoneMaximum the peakZoneMaximum to set
	 */
	public void setPeakZoneMaximum(int peakZoneMaximum) {
		this.peakZoneMaximum = peakZoneMaximum;
	}

	/**
	 * @return the peakZoneMinimum
	 */
	public int getPeakZoneMinimum() {
		return peakZoneMinimum;
	}

	/**
	 * @param peakZoneMinimum the peakZoneMinimum to set
	 */
	public void setPeakZoneMinimum(int peakZoneMinimum) {
		this.peakZoneMinimum = peakZoneMinimum;
	}

	/**
	 * @return the peakZoneMinutes
	 */
	public int getPeakZoneMinutes() {
		return peakZoneMinutes;
	}

	/**
	 * @param peakZoneMinutes the peakZoneMinutes to set
	 */
	public void setPeakZoneMinutes(int peakZoneMinutes) {
		this.peakZoneMinutes = peakZoneMinutes;
	}

	/**
	 * @return the heartRateByTheMin
	 */
	public int[][] getHeartRateByTheMin() {
		return heartRateByTheMin;
	}
	
}//end of class
