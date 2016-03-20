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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
	private IDs typeLive;
	private User user;
	private Calendar calen;

	Color backColor;
	
	public Widget (User usr, Calendar cal)
	{
		user = usr;
		calen = cal;
	}
	
	/**
	 * Widget class constructor
	 * @param type	the type of the widget
	 */
	public Widget(User usr, Calendar cal, IDs type){
		super();

		user = usr;
		calen = cal;
		typeLive = type;

		System.out.println(typeLive);
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
				data = getData(type);
				changeView(0);
				break;
			case DISTANCE:
				if(Team11_FitBitViewer.testFlag){
					data = getDistanceData(type);
					changeView(0);
				}else{
					changeViewLive(user.getHistoricalFitnessData(), calen, 0, type);

				}
				break;
			case CLIMB:
				if(Team11_FitBitViewer.testFlag){
					data = getFloorsData(type);
					changeView(0);
				}else{
					changeViewLive(user.getHistoricalFitnessData(), calen, 0, type);
				}
				break;
			case STEPS:
				if(Team11_FitBitViewer.testFlag){
					data = getData(type);
					changeView(0);	
				}else{
					changeViewLive(user.getHistoricalFitnessData(), calen, 0, type);

				}
					
				break;
			case ACTIVE:
				if(Team11_FitBitViewer.testFlag){
					data = getData(type);
					changeView(0);
				}else{
					changeViewLive(user.getHistoricalFitnessData(), calen, 0, type);

				}
				break;
			case SEDENTARY:
				if(Team11_FitBitViewer.testFlag){
					data = getSedData(type);
					changeView(0);
				}else{
					changeViewLive(user.getHistoricalFitnessData(), calen, 0, type);

				}
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
				if(Team11_FitBitViewer.testFlag){

					currentView = (currentView + 1) % maxView;
					changeView(currentView);
				}else{

					currentView = (currentView + 1) % maxView;
					System.out.println(currentView);
					changeViewLive(user.getHistoricalFitnessData(), calen, currentView, typeLive);
				}

				revalidate();
				repaint();
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

	public void changeViewLive(HistoricalFitnessData hfd, Calendar cal, int i, IDs type){
		System.out.println(cal.DAY_OF_MONTH + " " + cal.MONTH + " "+ cal.YEAR);
		OneDaysWorthOfData odwod = hfd.retrieve2(cal.DAY_OF_MONTH, cal.MONTH, cal.YEAR);
		System.out.println("Inside CVL...\n" + odwod.toString(false));
		System.out.println("CVL hfd...\n" + hfd.lifetimeAndBestDaysToString());
		System.out.println("i = " + i);
				switch(type){
						case STEPS: 
									System.out.print("Getting steps...");
									if(i==0){ //Get day calorie
										String convert = String.valueOf(odwod.getTodaysTotalSteps());
										System.out.println(convert);
										dataBox.setText(convert);
										viewLabel.setText(Widget.views[i]);
	
									}else if(i==1){ //Get Best Day calorie
										String convert = String.valueOf(hfd.getBestStepsValue());
										//System.out.println(Team11_FitBitViewer.odwod.getTodaysTotalSteps());
	
										dataBox.setText(convert);
										viewLabel.setText(Widget.views[i]);
	
									}else if(i==2){
										String convert = String.valueOf(hfd.getLifetimeSteps());
										dataBox.setText(convert);
										viewLabel.setText(Widget.views[i]);
									}
									break;
						case DISTANCE: 
									if(i==0){ //Get day calorie
										String convert = String.valueOf(odwod.getTodaysTotalDistance());
										dataBox.setText(convert);
										viewLabel.setText(Widget.views[i]);
	
									}else if(i==1){ //Get Best Day calorie
										String convert = String.valueOf(hfd.getBestDistanceValue());
										dataBox.setText(convert);
										viewLabel.setText(Widget.views[i]);
	
									}else if(i==2){
										String convert = String.valueOf(hfd.getLifetimeDistance());
										dataBox.setText(convert);
										viewLabel.setText(Widget.views[i]);
									}
									break;
						case CLIMB: 
									if(i==0){ //Get day calorie
										String convert = String.valueOf(odwod.getTodaysTotalFloors());
										System.out.println("CONVERT:- " + convert);
										System.out.println("ODWOD TOTAL FLOORS - " + odwod.getTodaysTotalFloors());
										dataBox.setText(convert);
										viewLabel.setText(Widget.views[i]);
		
									}else if(i==1){ //Get Best Day calorie
										String convert = String.format("%.2f",hfd.getBestFloorsValue());
										dataBox.setText(convert);
										viewLabel.setText(Widget.views[i]);
		
									}else if(i==2){
										String convert = String.valueOf(Math.round(hfd.getLifetimeFloors()));
										dataBox.setText(convert);
										viewLabel.setText(Widget.views[i]);
									}
									break;
						case ACTIVE: 
							if(i==0){ //Get day calorie
								String convert = String.valueOf(odwod.getTodaysTotalActiveMins());
								dataBox.setText(convert);
								viewLabel.setText(Widget.views[i]);

							}
							break;
						case SEDENTARY: 
							if(i==0){ //Get day calorie
								String convert = String.valueOf(odwod.getTodaysTotalSedentaryMins());
								dataBox.setText(convert);
								viewLabel.setText(Widget.views[i]);
							}
							break;
						default:
							break;
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
