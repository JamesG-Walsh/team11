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
		
		odwod.setFloorsByTheMin(h.getSpecificData("floors", "2016-02-29", "1min", "00:00", "23:59"));
		
		
	}

}
