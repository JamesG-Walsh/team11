package ca.uwo.csd.cs2212.team11;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ca.uwo.csd.cs2212.team11.SharedData.IDs;

public class Widget extends JPanel{
	private static final String[] views = {"<html>D<br/>a<br/>i<br/>l<br/>y</html>",
		"<html>R<br/>e<br/>c<br/>o<br/>r<br/>d</html>", 
		"<html>L<br/>i<br/>f<br/>e<br/>t<br/>i<br/>m<br/>e</html>"};
	private int goals = 0;
	private int currentView = 0;
	private int maxView = 3;
	private String units, typeName;
	private JLabel hintLabel, viewLabel;
	private JTextField dataBox = new JTextField(10);
	private int[] data;
	private int[] stepData;
	

	public Widget(IDs type){
		super();
		this.setSize(200, 200);
		System.out.println(this.getWidth());
		this.setLayout(new BorderLayout(1,1));
		this.setBackground(SharedData.COLOR_SET[type.ordinal()]);
		this.setBorder(BorderFactory.createLineBorder(SharedData.COLOR_SET[type.ordinal()].darker()));

		switch(type){
			case CALORIES:
				this.typeName = "Calories Burned";
				this.units = "calories";
				break;
			case DISTANCE:
				this.typeName = "Distance Travelled";
				this.units = "yards";
				break;
			case CLIMB:
				this.typeName = "Floors Climbed";
				this.units = "Floors";
				break;
			case STEPS:
				this.typeName = "Steps Taken";
				this.units = "steps";
				break;
			case ACTIVE:
				this.typeName = "Minutes of Activity";
				this.units = "minutes";
				this.maxView = 1;
				break;
			case SEDENTARY:
				this.typeName = "Minute of Inactivity";
				this.units = "minutes";
				this.maxView = 1;
				break;
			case HEART_RATE:
				this.typeName = "Heart Rate";
				this.units = "bpm";
				this.maxView = 1;
				break;
			default:
				typeName = "Undefined Widget";
		}
		
		this.add(new JLabel(typeName), BorderLayout.NORTH);
		hintLabel = new JLabel("Click Widget to Change View");
		viewLabel = new JLabel();
		dataBox.setEditable(false);
		dataBox.setBackground(new Color(255, 255, 255, 100));
		dataBox.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				Component source = (Component)e.getSource();
				source.getParent().dispatchEvent(e);
			}
		});

		this.add(hintLabel, BorderLayout.SOUTH);
		//changeView(0);
		switch(type){
			case CALORIES:
				data = getData(type);
				changeView(0);
				break;
			case DISTANCE:
				data = getDistanceData(type);
				changeView(0);
				break;
			case CLIMB:
				data = getFloorsData(type);
				changeView(0);
				break;
			case STEPS:
				data = getData(type);
				changeView(0);
				break;
			case ACTIVE:
				data = getActiveMinData(type);
				changeView(0);
				break;
			case SEDENTARY:
				data = getSedData(type);
				changeView(0);
				break;
			case HEART_RATE:
				data = getData(type);
				changeView(0);
				break;
			default:
				typeName = "Undefined Widget";
		}
		this.add(viewLabel, BorderLayout.WEST);
		this.add(dataBox, BorderLayout.CENTER);
		this.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				currentView = (currentView + 1) % maxView;
				changeView(currentView);
			}
		});

	}

	private void changeView(int i) {
		
		dataBox.setText(this.data[i] + " " + this.units);
		viewLabel.setText(Widget.views[i]);
	}

	private int[] getData(IDs type) {
		return SharedData.base_array;
	}
	/*private int[] getStepsData(IDs type) {
		return SharedData.steps_Data;
	}*/
	private int[] getSedData(IDs type) {
		return SharedData.sedentary_Data;
	}
	private int[] getDistanceData(IDs type) {
		return SharedData.distance_Data;
	}
	private int[] getFloorsData(IDs type) {
		return SharedData.floors_Data;
	}
	private int[] getActiveMinData(IDs type) {
		return SharedData.activeMin_Data;
	}
}
