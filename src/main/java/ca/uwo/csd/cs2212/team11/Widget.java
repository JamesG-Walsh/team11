package ca.uwo.csd.cs2212.team11;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import ca.uwo.csd.cs2212.team11.SharedData.IDs;

/**
 * Creates components that are added/removed on dashboard that contain the users activity data
 * @author Andrew Hall
 * 
 */
public class Widget extends JPanel{
	private static final String[] views = {"<html>D<br/>a<br/>i<br/>l<br/>y</html>",
											"<html>R<br/>e<br/>c<br/>o<br/>r<br/>d</html>", 
											"<html>L<br/>i<br/>f<br/>e<br/>t<br/>i<br/>m<br/>e</html>"};
	private int goals = 0;
	private int currentView = 0;
	private int maxView = 3;
	private String units, typeName, altUnit, stash;
	private JLabel hintLabel, viewLabel;
	private JTextField dataBox = new JTextField(10);
	private int[] data;
	
	/**
	 * Widget class constructor
	 * @param type	the type of the widget
	 */
	public Widget(IDs type){
		super();
		this.setLayout(new BoxLayout(this, 1));
		this.setPreferredSize(new Dimension(150, 150));
		JPanel display = new JPanel();
		display.setLayout(new BorderLayout(1,1));
		display.setBackground(SharedData.COLOR_SET[type.ordinal()]);
		
		//this.setBackground(new Color(0,0,0,0));
		display.setBorder(BorderFactory.createLineBorder(SharedData.COLOR_SET[type.ordinal()].darker()));
		this.add(display);
		
		switch(type){
			case CALORIES:
				this.typeName = "Calories Burned";
				this.units = "calories";
				this.maxView = 2;
				data = getData(type);
				this.altUnit = "Joules";
				break;
			case DISTANCE:
				this.typeName = "Distance Travelled";
				this.units = "km";
				data = getDistanceData(type);
				this.altUnit = "miles";
				break;
			case CLIMB:
				this.typeName = "Floors Climbed";
				this.units = "Floors";
				data = getFloorsData(type);
				break;
			case STEPS:
				this.typeName = "Steps Taken";
				this.units = "steps";
				data = getData(type);
				break;
			case ACTIVE:
				this.typeName = "Minutes of Activity";
				this.units = "minutes";
				this.maxView = 2;
				data = getActiveMinData(type);
				break;
			case SEDENTARY:
				this.typeName = "Minute of Inactivity";
				this.units = "minutes";
				this.maxView = 2;
				data = getSedData(type);
				break;
			case HEART_RATE:
				this.typeName = "Heart Rate";
				this.units = "bpm";
				this.maxView = 1;
				data = getData(type);
				break;
			default:
				typeName = "Undefined Widget";
		}
		
		display.add(new JLabel(typeName), BorderLayout.NORTH);
		hintLabel = new JLabel("Single view Widget");
		if (this.maxView > 1){		hintLabel.setText("Click Widget to Change View"); 	}
		viewLabel = new JLabel();
		dataBox.setEditable(false);
		dataBox.setOpaque(false);
//		dataBox.setBackground(new Color(0, 0, 0)); does someone want this changed to black???
		dataBox.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				Component source = (Component)e.getSource();
				if(e.getButton() == MouseEvent.BUTTON1){		source.getParent().dispatchEvent(e);	} 	
			}
		});

		display.add(hintLabel, BorderLayout.SOUTH);
		changeView(0);
/*		switch(type){
			case CALORIES:
				
				break;
			case DISTANCE:
				changeView(0);
				break;
			case CLIMB:
				changeView(0);
				break;
			case STEPS:
				changeView(0);
				break;
			case ACTIVE:
				changeView(0);
				break;
			case SEDENTARY:
				changeView(0);
				break;
			case HEART_RATE:
				changeView(0);
				break;
			default:
				typeName = "Undefined Widget";
		}*/
		display.add(viewLabel, BorderLayout.WEST);
		display.add(dataBox, BorderLayout.CENTER);
		display.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				currentView = (currentView + 1) % maxView;
				changeView(currentView);
			}
		});

	}
	
	/**
	 * change view to specific data
	 * @param i the index of a given data type
	 */     
	private void changeView(int i) {
		dataBox.setText(this.data[i] + " " + this.units);
		viewLabel.setText(Widget.views[i]);
	}

	/**
	 * Get canned data for specific type of data
	 * @param type
	 * @return specific data for specific data type
	 */
	private int[] getData(IDs type) {
		return SharedData.base_array;
	}
	
	/*private int[] getStepsData(IDs type) {
		return SharedData.steps_Data;
	}*/
	
	/**
	 * Get sedentary canned data
	 * @param type -- 
	 * @return sedentary data
	 */
	private int[] getSedData(IDs type) { // what is the purpose of the arg????
		return SharedData.sedentary_Data;
	}
	
	/**
	 * Get canned distance data
	 * @param type
	 * @return canned distance data
	 */
	private int[] getDistanceData(IDs type) {
		return SharedData.distance_Data;
	}
	
	/**
	 * Get canned floor data
	 * @param type
	 * @return Canned floor data
	 */
	private int[] getFloorsData(IDs type) {
		return SharedData.floors_Data;
	}
	
	/**
	 * Get canned active minutes data
	 * @param type
	 * @return Canned active minutes data
	 */
	private int[] getActiveMinData(IDs type) {
		return SharedData.activeMin_Data;
	}
}
