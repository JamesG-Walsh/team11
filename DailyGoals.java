import java.io.Serializable;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JFrame;

/**
 * Class that contains user's daily goals
 * @author 
 */
public class DailyGoals extends JFrame implements Serializable
{
	private int caloriesDailyGoal;
	private int floorsDailyGoal;
	private double distanceDailyGoal;
	private int stepsDailyGoal;
	private int activeMinutesDailyGoal;

	
	
	public void getCalorieDailyGoal(int calorieDailyGoal){
		this.caloriesDailyGoal = calorieDailyGoal;
		
	}
	
	public void setCaloriesDailyGoal(){
		Scanner caloriesDailyGoal = new Scanner(System.in);
		System.out.println("Enter your today's goal for calories: ");
		this.caloriesDailyGoal = caloriesDailyGoal.nextInt();
		
	}
	
	public void getfloorsDailyGoal(int floorsDailyGoal){
		this.floorsDailyGoal = floorsDailyGoal;
		
	}
	
	public void setfloorsDailyGoal(){
		Scanner floorsDailyGoal = new Scanner(System.in);
		System.out.println("Enter your today's goal for calories: ");
		this.floorsDailyGoal = floorsDailyGoal.nextInt();
		
	}
}// end of class
