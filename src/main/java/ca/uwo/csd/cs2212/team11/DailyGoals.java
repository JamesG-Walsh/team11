package ca.uwo.csd.cs2212.team11;
import java.io.Serializable;

/**
 * This class is used in for creating an object for daily goals
 * 
 * 
 * @author Team 11
 */


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

/**
	 * 
	 * @param stpGoal, calGoal, distGoal, floorsGoal -- All are passed to construct our daily goals object
	 * 
	 */
	public void setGoals(int stpGoal, int calGoal, int distGoal, int floorsGoal) {
		this.stpGoal = stpGoal;
		this.calGoal = calGoal;
		this.distGoal = distGoal;
		this.floorsGoal = floorsGoal;
	}

/**
*
*@param stpGoal, calGoal, distGoal, floorsGoal are strings that will contruct the string array
*/


	
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


/** 
* This function is used in for setting an array for daily goals
*@param goalsArray will contain all values for goals
*/
	
	public void setGoalsArray( String[] goalsArray) {
		this.goalsArray = goalsArray;
	}

/** 
 * This function take two ints to determine if the goal was met
 * 
 * @param daily and goal are two params that will be use to determine if the goal has been met
 *@return boolean value used as a check
 */

	public boolean isGoal(int daily, int goal){

		if(daily > goal){

			return true;
		}

		return false;

	}

	/**
	*@return stpGoal -- Users set step goal
	*/
	
	public int getStepGoal() {
		return stpGoal;
	}

	/**
	*@return calGoal -- user set calories goal
	*/

	public int getCalGoal() {
		return calGoal;
	}
	/**
	*@return distGoal -- user set distance goal
	*/
	
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
