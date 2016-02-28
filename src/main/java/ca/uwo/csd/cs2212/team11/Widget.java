package ca.uwo.csd.cs2212.team11;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ca.uwo.csd.cs2212.team11.SharedData.IDs;

public class Widget extends JPanel{
	
	public Widget(IDs type){
		super();
		this.setSize(200, 200);
		this.setBackground(SharedData.COLOR_SET[type.ordinal()]);
		String typeName ="";
		switch(type){
			case CALORIES:
				typeName = "Calories Burned";
				break;
			case DISTANCE:
				typeName = "Distance Travelled";
				break;
			case CLIMB:
				typeName = "Floors Climbed";
				break;
			case STEPS:
				typeName = "Steps Taken";
				break;
			case ACTIVE:
				typeName = "Minutes of Activity";
				break;
			case SEDENTARY:
				typeName = "Minute of Inactivity";
				break;
			case HEART_RATE:
				typeName = "Heart Rate";
				break;
			default:
				typeName = "Undefined Widget";
		}
		this.add(new JLabel(typeName));
	}
}
