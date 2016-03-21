package ca.uwo.csd.cs2212.team11;

import javax.swing.JPanel;

import ca.uwo.csd.cs2212.team11.SharedData.IDs;

public class CGraph extends JPanel{
	
	private IDs type;
	private double[] data;
	private int[] hourlyData;
	private int[] qtrHourlyData;
	private int legend = 575;
	private static int graphWidth = 650;
	private static int graphHeight = 200;
	private int endOfGraph = 534;

	public CGraph(IDs type){
		this.type = type;
		switch(type){
		case CALORIES:
			//normalizeData(getCaloriesData());
			break;
		case STEPS:
			//normalizeData(getStepsData());
			break;
		case DISTANCE:
			//normalizeData(getDistanceData());
			break;
		default:
			System.err.println("Error in graph creation " + type.name() + " is not recognized");
		}

	}

	private double[] getCaloriesData(){
		return SharedData.newBigD;
	}

	private double[] getStepsData(){
		return SharedData.newBigD;
	}
	
	private double[] getDistanceData(){
		return SharedData.newBigD;
	}

}
