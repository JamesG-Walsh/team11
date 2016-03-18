package ca.uwo.csd.cs2212.team11;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
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
public class Widget extends JPanel implements Serializable{
	/*private static final String[] views = {"<html>D<br/>a<br/>i<br/>l<br/>y</html>",
		"<html>R<br/>e<br/>c<br/>o<br/>r<br/>d</html>", 
		"<html>L<br/>i<br/>f<br/>e<br/>t<br/>i<br/>m<br/>e</html>"};*/
	private static final String[] views = {"<html>Daily</html>",
		"<html>Record</html>", 
		"<html>Lifetime</html>"};
	private int goals = 0;
	private int currentView = 0;
	private int maxView = 3;
	private String units, typeName;
	private JLabel hintLabel, viewLabel;
	private JTextField dataBox = new JTextField(10);
	private int[] data;
	Color backColor;
	
	/**
	 * Widget class constructor
	 * @param type	the type of the widget
	 */
	public Widget(IDs type){
		super();

		JPanel content = new JPanel();
		this.setLayout(new BoxLayout(this, 1));
		this.setPreferredSize(new Dimension(150, 150));
		content.setLayout(new BorderLayout(1,1));
		content.setBackground(SharedData.COLOR_SET[type.ordinal()]);
		content.setBorder(BorderFactory.createLineBorder(SharedData.COLOR_SET[type.ordinal()].darker()));
		this.add(content);

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
				break;
			case SEDENTARY:
				this.typeName = "Minute of Inactivity";
				this.units = "minutes";
				break;
			case HEART_RATE:
				this.typeName = "Heart Rate";
				this.units = "bpm";
				break;
			default:
				typeName = "Undefined Widget";
		}
		
		content.add(new JLabel(typeName), BorderLayout.NORTH);
		hintLabel = new JLabel("Click to Change View");
		viewLabel = new JLabel();
		dataBox.setEditable(false);

		dataBox.setOpaque(false);
		dataBox.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				Component source = (Component)e.getSource();
				source.getParent().dispatchEvent(e);
			}
		});

		content.add(hintLabel, BorderLayout.SOUTH);
		//changeView(0);
		switch(type){
			case CALORIES:
				if(Team11_FitBitViewer.testFlag){
					data = getData(type);
					changeView(0);

				}else{

					//changeViewLive(0);
				}
				
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
		content.add(viewLabel, BorderLayout.WEST);
		content.add(dataBox, BorderLayout.CENTER);
		content.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				
				//source.getParent().repaint();
				// source.getParent().revalidate();
				/*dataBox.revalidate();
				dataBox.repaint();*/
				System.out.println("HEre");
				currentView = (currentView + 1) % maxView;
				changeView(currentView);

				/*revalidate();
				repaint();*/
			}
		});

	}
	

	/**
	 * change view to specific data
	 * @param i the index of a given data type
	 */     
	private void changeView(int i) {
		
		System.out.println("currentView :" + i);
		dataBox.setText(this.data[i] + " " + this.units);
		viewLabel.setText(Widget.views[i]);
	}

	private void changeViewLive(int i, IDs type){
		String convert;
		switch(type){
						case CALORIES:
								if(i==0){ //Get day calorie
									convert = String.valueOf(Team11_FitBitViewer.odwod.getTodaysTotalCaloriesBurned());
									dataBox.setText(convert);
									viewLabel.setText(Widget.views[i]);

								}else if(i==1){ //Get Best Day calorie
									convert = String.valueOf(Team11_FitBitViewer.hfd.getBestCalories());
									dataBox.setText(convert);
									viewLabel.setText(Widget.views[i]);

								}else if(i==2){
									convert = String.valueOf(Team11_FitBitViewer.hfd.getLifetimeCalories());
									dataBox.setText(convert);
									viewLabel.setText(Widget.views[i]);
								}
						case STEPS: 
								if(i==0){ //Get day calorie
									convert = String.valueOf(Team11_FitBitViewer.odwod.getTodaysTotalSteps());
									dataBox.setText(convert);
									viewLabel.setText(Widget.views[i]);

								}else if(i==1){ //Get Best Day calorie
									convert = String.valueOf(Team11_FitBitViewer.hfd.getBest());
									dataBox.setText(convert);
									viewLabel.setText(Widget.views[i]);

								}else if(i==2){
									convert = String.valueOf(Team11_FitBitViewer.hfd.getLifetimeCalories());
									dataBox.setText(convert);
									viewLabel.setText(Widget.views[i]);
								}
					}
	}

	/**
	 * Get canned data for specific type of data
	 * @param type
	 * @return specific data for specific data type
	 */
	private int[] getData(IDs type) {
		return SharedData.base_array;
	}
	
	private int[] getStepsData(IDs type) {
		return SharedData.steps_Data;
	}
	
	/**
	 * Get sedentary canned data
	 * @param type -- 
	 * @return sedentary data
	 */
	private int[] getSedData(IDs type) {
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
