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

		OneDaysWorthOfData odwod = new OneDaysWorthOfData();
		
		odwod.setStepsByTheMin(h.getSpecificData("steps", "2016-03-01", "1min", "12:00", "23:59"));
		
		System.out.println("mark1");
		
		odwod.setFloorsByTheMin(h.getSpecificData("floors", "2016-03-01", "1min", "23:00", "23:59"));

		odwod.setSedentaryMinsByTheMin(h.getSpecificData("minutesSedentary", "2016-03-01", "1min", "23:00", "23:59"));
		
		for (int hour = 0; hour < 24; hour++)
		{
			for (int min = 0; min < 60; min++)
			{
				System.out.println();
				System.out.print("time: " + hour + ":" + min + " ||| " + "steps:" + odwod.getStepsByTheMin()[hour][min]);
				System.out.print(" ||| " + "floors:" + odwod.getFloorsByTheMin()[hour][min]);
				System.out.print(" ||| " + "sedentary mins:" + odwod.getSedentaryMinsByTheMin()[hour][min]);
			}
		}
	}
}
