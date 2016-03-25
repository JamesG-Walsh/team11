package ca.uwo.csd.cs2212.team11;
import java.io.Serializable;

public class DailyGoals {
    
	private int stpGoal, calGoal, distGoal, floorsGoal;
	private String[] goalsArray;
	
	public DailyGoals() {
		goalsArray = new String[4];
		for (int i = 0; i < 4; i++){ 
			goalsArray[i] = new String();
			goalsArray[i] = null;
		}
	}
	/* Methods to get and set attributes */
	public void setGoals(int stpGoal, int calGoal, int distGoal, int floorsGoal) {
		this.stpGoal = stpGoal;
		this.calGoal = calGoal;
		this.distGoal = distGoal;
		this.floorsGoal = floorsGoal;
	}
	
	public void setGoalsStr(String stpGoal, String calGoal, String distGoal, String floorsGoal) {
		goalsArray[0] = stpGoal;
		goalsArray[1] = calGoal;
		goalsArray[2] = distGoal;
		goalsArray[3] = floorsGoal;

		int tmp1 = Integer.parseInt(stpGoal);
		int tmp2 = Integer.parseInt(calGoal);
		int tmp3 = Integer.parseInt(distGoal);
		int tmp4 = Integer.parseInt(floorsGoal);

		this.setGoals(tmp1, tmp2, tmp3, tmp4);
	}
	
	public void setGoalsArray( String[] goalsArray) {
		this.goalsArray = goalsArray;
	}

	public boolean isGoal(int daily, int goal){

		if(daily > goal){

			return true;
		}

		return false;

	}
	
	public int getStepGoal() {
		return stpGoal;
	}

	public int getCalGoal() {
		return calGoal;
	}
	
	public int getDistGoal() {
		return distGoal;
	}

	public int getFloorsGoal() {
		return floorsGoal;
	}

	
	public String getStepGoalStr() {
		return goalsArray[0];
	}

	public String getCalGoalStr() {
		return goalsArray[1];
	}
	
	public String getDistGoalStr() {
		return goalsArray[2];
	}
	
	public String[] getGoalsArray() {
		return goalsArray;
	}
	
	/* Update integers to match strings */
	/*   QUESTION: If strings are not integers, then is there an error, or are there junk numbers put in?  */
	public void goalsStingToInt() {
		stpGoal = Integer.parseInt(goalsArray[0]);
		calGoal = Integer.parseInt(goalsArray[1]);
		distGoal = Integer.parseInt(goalsArray[2]);		
	}
}
