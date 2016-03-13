package ca.uwo.csd.cs2212.team11;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.Serializable;

/**
 * COMP 2212 Group Project
 * FitBit Viewer
 * @author Team 11
 * 
 */
public class Team11_FitBitViewer implements Serializable 
{
	public static boolean testFlag = false;
	
	public static DeskTop GUI;
	
	/**
	 * Main method for the project	 
	 * @param args				"test" if in test mode (passed from command line)
	 * @throws JSONException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws JSONException, FileNotFoundException, IOException 
	{
		
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("./src/main/resources/Desktop.config"));
	  	
		try {
			GUI = (DeskTop) in.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		in.close();
		
		//GUI = new DeskTop();
		GUI.setVisible(true);
		
			

		if(args.length >= 1 && args[0].equals("test")){

			System.out.println("Running in test mode ......");
			testFlag = true;
		}
		else
		{

			System.out.println("Running in live mode.....");

			
			//OneDaysWorthOfData odwod = new OneDaysWorthOfData(2016, 3, 13);
			//String startTime = "23:00";
			//String endTime = "23:59";

			/*try
			{
				odwod.setTodaysTotalFloors(ResponseParser.parseDailyFloorsTotal(h.getSpecificDataDailyTotal("floors", "2016-03-01")));
				odwod.setTodaysTotalSteps(ResponseParser.parseDailyStepsTotal(h.getSpecificDataDailyTotal("steps", "2016-03-01")));
				odwod.setTodaysTotalCaloriesBurned(ResponseParser.parseDailyCaloriesTotal(h.getSpecificDataDailyTotal("calories", "2016-03-01")));
				odwod.setTodaysTotalDistance(ResponseParser.parseDailyDistanceTotal(h.getSpecificDataDailyTotal("distance", "2016-03-01")));
				odwod.setTodaysTotalSedentaryMins(ResponseParser.parseDailySedentaryMinsTotal(h.getSpecificDataDailyTotal("minutesSedentary", "2016-03-01")));

				JSONObject joLA = h.getSpecificDataDailyTotal("minutesLightlyActive", "2016-03-01");
				JSONObject joFA = h.getSpecificDataDailyTotal("minutesFairlyActive", "2016-03-01");
				JSONObject joVA = h.getSpecificDataDailyTotal("minutesVeryActive", "2016-03-01");		
				odwod.setTodaysTotalActiveMins(ResponseParser.parseDailyActiveMinsTotal(joLA, joFA, joVA));
			}
			catch (NullPointerException e)
			{
				System.out.println("Null Pointer Exception when parsing server response.  Most likely will work if run again.  Will Try to figure this out for stage 3.");
			}

			System.out.println("\nTOTALS");
			System.out.println("FLoors: " + odwod.getTodaysTotalFloors());
			System.out.println("Steps: " + odwod.getTodaysTotalSteps());
			System.out.println("Calories: " + odwod.getTodaysTotalCaloriesBurned());
			System.out.println("Distance: " + odwod.getTodaysTotalDistance());
			System.out.println("SedentaryMins: " + odwod.getTodaysTotalSedentaryMins());
			System.out.println("ActiveMins: " + odwod.getTodaysTotalActiveMins());
			 
			odwod.setStepsByTheMin(h.getSpecificDataByTheMin("steps", "2016-03-01", "1min", startTime, endTime));	
			odwod.setCaloriesByTheMin(h.getSpecificDataByTheMin("calories", "2016-03-01", "1min", startTime, endTime));
			odwod.setDistanceByTheMin(h.getSpecificDataByTheMin("distance", "2016-03-01", "1min", startTime, endTime));
			odwod.setFloorsByTheMin(h.getSpecificDataByTheMin("floors", "2016-03-01", "1min", startTime, endTime));
			odwod.setSedentaryMinsByTheMin(h.getSpecificDataByTheMin("minutesSedentary", "2016-03-01", "1min", startTime, endTime));
			*/
			
			/*odwod.populateTotals();
			
			odwod.populateAllMins();*/
		
		/*	for (int hour = 0; hour < 24; hour++)
			{
				for (int min = 0; min < 60; min++)
				{
					System.out.println();
					System.out.print("time: " + hour + ":" + min + "\t|||" + "steps:" + odwod.getStepsByTheMin()[hour][min]);
					System.out.print("\t|||" + "calories: " + odwod.getCaloriesByTheMin()[hour][min]);
					//System.out.print("\t|||" + "distance: " + odwod.getDistanceByTheMin()[hour][min]);
					System.out.print("\t|||" + "floors: " + odwod.getFloorsByTheMin()[hour][min]);
					System.out.print("\t|||" + "sedentary mins: " + odwod.getSedentaryMinsByTheMin()[hour][min]);
					System.out.print("\t|||" + "active mins: " + odwod.getActiveMinsByTheMin()[hour][min]);
				}
			}*/
		}
	}
}
