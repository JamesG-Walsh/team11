package ca.uwo.csd.cs2212.team11;

import org.json.JSONException;

public class Team11_FitBitViewer {

	/**
	 * COMP 2212 Group Project
	 * FitBit Viewer
	 * @author Team 11 
	 * @param args
	 * @throws JSONException 
	 */
	public static void main(String[] args) throws JSONException 
	{
		/*DeskTop GUI = new DeskTop();
		GUI.setVisible(true);*/
		
		HttpClient h = new HttpClient();
		OneDaysWorthOfData odwod = new OneDaysWorthOfData(2016, 3, 1);
		
		
		
		String startTime = "23:00";
		String endTime = "23:59";
		
		h.getSpecificDataDailyTotal("floors", "2016-03-01");
		/*
		odwod.setStepsByTheMin(h.getSpecificDataByTheMin("steps", "2016-03-01", "1min", startTime, endTime));	
		odwod.setCaloriesByTheMin(h.getSpecificDataByTheMin("calories", "2016-03-01", "1min", startTime, endTime));
		odwod.setDistanceByTheMin(h.getSpecificDataByTheMin("distance", "2016-03-01", "1min", startTime, endTime));
		odwod.setFloorsByTheMin(h.getSpecificDataByTheMin("floors", "2016-03-01", "1min", startTime, endTime));
		odwod.setSedentaryMinsByTheMin(h.getSpecificDataByTheMin("minutesSedentary", "2016-03-01", "1min", startTime, endTime));
		
		for (int hour = 23; hour < 24; hour++)
		{
			for (int min = 0; min < 60; min++)
			{
				System.out.println();
				System.out.print("time: " + hour + ":" + min + "\t ||| " + "steps:" + odwod.getStepsByTheMin()[hour][min]);
				System.out.print("\t ||| " + "calories: " + odwod.getCaloriesByTheMin()[hour][min]);
				System.out.print("\t ||| " + "distance: " + odwod.getDistanceByTheMin()[hour][min]);
				System.out.print("\t ||| " + "floors: " + odwod.getFloorsByTheMin()[hour][min]);
				System.out.print("\t ||| " + "sedentary mins: " + odwod.getSedentaryMinsByTheMin()[hour][min]);
			}
		}*/
	}
}
