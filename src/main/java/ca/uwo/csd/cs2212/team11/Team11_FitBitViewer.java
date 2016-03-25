package ca.uwo.csd.cs2212.team11;
import java.io.Serializable;
//import org.json.JSONException;
//import org.json.JSONObject;
import ca.uwo.csd.cs2212.team11.DeskTop;
import ca.uwo.csd.cs2212.team11.OneDaysWorthOfData;
import ca.uwo.csd.cs2212.team11.User;

/**
 * COMP 2212 Group Project
 * FitBit Viewer
 * @author Team 11
 * 
 */
public class Team11_FitBitViewer implements Serializable
{
	public static DeskTop GUI;

	//public static OneDaysWorthOfData odwod;
	//public static HistoricalFitnessData hfd;

	/**
	 * Main method for the project	 
	 * @param args				"test" if in test mode (passed from command line)
	 * @throws JSONException 
	 */
	public static void main(String[] args) //throws JSONException 
	{
		boolean testFlag = false;
		if(args.length >= 1 && args[0].equals("test"))
		{
			testFlag = true;
		}
		else
		{
			
		}
			User usr = new User();
			
			GUI = new DeskTop(testFlag, usr);
			GUI.setVisible(true);
		
	}//end of main method
}//end of class
