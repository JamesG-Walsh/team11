package ca.uwo.csd.cs2212.team11;
import java.io.Serializable;

public class DailyGoals {
    
	private int stpGoal, calGoal, distGoal;
	private String[] goalsArray;
	
	public DailyGoals() {
		goalsArray = new String[3];
		for (int i = 0; i < 3; i++){ 
			goalsArray[i] = new String();
			goalsArray[i] = null;
		}
	}
	/* Methods to get and set attributes */
	void setGoals(int stpGoal, int calGoal, int distGoal) {
		this.stpGoal = stpGoal;
		this.calGoal = calGoal;
		this.distGoal = distGoal;
	}
	
	void setGoalsStr(String stpGoal, String calGoal, String distGoal) {
		goalsArray[0] = stpGoal;
		goalsArray[1] = calGoal;
		goalsArray[2] = distGoal;
	}
	
	void setGoalsArray( String[] goalsArray) {
		this.goalsArray = goalsArray;
	}
	
	int getStepGoal() {
		return stpGoal;
	}

	int getCalGoal() {
		return calGoal;
	}
	
	int getDistGoal() {
		return distGoal;
	}
	
	String getStepGoalStr() {
		return goalsArray[0];
	}

	String getCalGoalStr() {
		return goalsArray[1];
	}
	
	String getDistGoalStr() {
		return goalsArray[2];
	}
	
	String[] getGoalsArray() {
		return goalsArray;
	}
	
	/* Update integers to match strings */
	/*   QUESTION: If strings are not integers, then is there an error, or are there junk numbers put in?  */
	void goalsStingToInt() {
		stpGoal = Integer.parseInt(goalsArray[0]);
		calGoal = Integer.parseInt(goalsArray[1]);
		distGoal = Integer.parseInt(goalsArray[2]);		
	}
}
