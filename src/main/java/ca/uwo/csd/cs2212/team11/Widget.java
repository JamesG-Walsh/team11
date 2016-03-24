package ca.uwo.csd.cs2212.team11;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar; 
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import ca.uwo.csd.cs2212.team11.SharedData.IDs;
import java.awt.Color;

/**
 * Creates components that are added/removed on dashboard that contain the users activity data
 * @author Andrew Hall
 * 
 */
public class Widget extends JPanel{
	private static final String[] views = {"<html>Daily</html>",
											"<html>Record</html>", 
											"<html>Lifetime</html>"};
	private int goals = 0;
	private int currentView = 0;
	private int maxView = 3;
	private String units, typeName, altUnit, stash;
	private JLabel hintLabel, viewLabel;
	private JTextField dataBox = new JTextField(10);
	private int[] data;

	private IDs typeLive;
	private User user;
	private Calendar calen;
	private	Serialize r;
	
	private boolean testF;

	Color backColor;
	
	/**
	 * Widget class constructor
	 * @param type	the type of the widget
	 */
	public Widget(boolean testFlag, User usr, Calendar cal, IDs type)
	{
		super();
		this.testF = testFlag;
		user = usr;
		calen = cal;
		typeLive = type;
		r = new Serialize();
		System.out.println(typeLive);
		JPanel content = new JPanel();
		this.setLayout(new BoxLayout(this, 1));
		this.setPreferredSize(new Dimension(130, 130));
		content.setLayout(new BorderLayout(1,1));
		content.setBackground(SharedData.COLOR_SET[type.ordinal()]);

		//this.setBackground(new Color(0,0,0,0));
		content.setBorder(BorderFactory.createLineBorder(SharedData.COLOR_SET[type.ordinal()].darker()));
		this.add(content);
		
		switch(type){
			case CALORIES:
				this.typeName = "Calories Burned";
				this.units = "calories";
				//this.maxView = 2;
				//data = getData(type);
				this.altUnit = "Joules";
				break;
			case DISTANCE:
				this.currentView = (Integer) r.readObject("./src/main/resources/desktop/currentView_"+typeLive.toString()+".xml").readObject();
				this.typeName = "Distance Travelled";
				this.units = "km";
				//data = getDistanceData(type);
				this.altUnit = "miles";

				hintLabel = new JLabel("Click to Change View");
				hintLabel.setFont(new Font("Tahoma", Font.PLAIN, 9));
				content.add(hintLabel, BorderLayout.SOUTH);

				break;
			case CLIMB:
				this.currentView = (Integer) r.readObject("./src/main/resources/desktop/currentView_"+typeLive.toString()+".xml").readObject();
				this.typeName = "Floors Climbed";
				this.units = "Floors";
				//data = getFloorsData(type);

				hintLabel = new JLabel("Click to Change View");
				hintLabel.setFont(new Font("Tahoma", Font.PLAIN, 9));
				content.add(hintLabel, BorderLayout.SOUTH);
				break;
			case STEPS:
				this.currentView = (Integer) r.readObject("./src/main/resources/desktop/currentView_"+typeLive.toString()+".xml").readObject();
				this.typeName = "Steps Taken";
				this.units = "steps";
				//data = getData(type);
				hintLabel = new JLabel("Click to Change View");
				hintLabel.setFont(new Font("Tahoma", Font.PLAIN, 9));
				content.add(hintLabel, BorderLayout.SOUTH);
				break;
			case ACTIVE:
				this.typeName = "Minutes of Activity";
				this.units = "minutes";
				//this.maxView = 2;
				//data = getActiveMinData(type);
				break;
			case SEDENTARY:
				this.typeName = "Minute of Inactivity";
				this.units = "minutes";
				//this.maxView = 2;
				//data = getSedData(type);
				break;
			case HEART_RATE:
				this.typeName = "Heart Rate";
				this.units = "bpm";
				//this.maxView = 1;
				//data = getData(type);
				break;
			default:
				typeName = "Undefined Widget";
		}
		
/*<<<<<<< HEAD
		display.add(new JLabel(typeName), BorderLayout.NORTH);
		hintLabel = new JLabel("Single view Widget");
		if (this.maxView > 1){		hintLabel.setText("Click Widget to Change View"); 	}
=======*/
		content.add(new JLabel(typeName), BorderLayout.NORTH);
		viewLabel = new JLabel();
		dataBox.setEditable(false);
		dataBox.setOpaque(false);
//		dataBox.setBackground(new Color(0, 0, 0)); does someone want this changed to black???
		dataBox.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				Component source = (Component)e.getSource();
				if(e.getButton() == MouseEvent.BUTTON1){		
					source.getParent().dispatchEvent(e);	
					} 	
			}
		});

		//display.add(hintLabel, BorderLayout.SOUTH);
		//changeView(0);

		//changeView(0);
		switch(type){
			case CALORIES:
				if(this.testF){
					data = getDistanceData(type);
					changeView(currentView);
				}else{
					changeViewLive(user.getHistoricalFitnessData(), calen, currentView, type);

				}
			case DISTANCE:
				if(this.testF){
					data = getDistanceData(type);
					changeView(currentView);
				}else{
					changeViewLive(user.getHistoricalFitnessData(), calen, currentView, type);

				}
				break;
			case CLIMB:
				if(this.testF){
					data = getFloorsData(type);
					changeView(currentView);
				}else{
					changeViewLive(user.getHistoricalFitnessData(), calen, currentView, type);
				}
				break;
			case STEPS:
				if(this.testF)
				{
					data = getData(type);
					changeView(currentView);	
				}
				else
				{
					changeViewLive(user.getHistoricalFitnessData(), calen, currentView, type);

				}
					
				break;
			case ACTIVE:
				if(this.testF){
					data = getData(type);
					changeView(currentView);
				}else{
					changeViewLive(user.getHistoricalFitnessData(), calen, currentView, type);

				}
				break;
			case SEDENTARY:
				if(this.testF){
					data = getSedData(type);
					changeView(currentView);
				}else{
					changeViewLive(user.getHistoricalFitnessData(), calen, currentView, type);

				}
				break;
			case HEART_RATE:
				data = getData(type);
				changeView(currentView);
				break;
			default:
				typeName = "Undefined Widget";
		}
		content.add(viewLabel, BorderLayout.WEST);
		content.add(dataBox, BorderLayout.CENTER);
		content.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{

				//source.getParent().repaint();
				// source.getParent().revalidate();
				/*dataBox.revalidate();
				dataBox.repaint();*/
				if(testF)
				{
					currentView = (currentView + 1) % maxView;
					Serialize writeTo = new Serialize();
					writeTo.writeObject(currentView, "./src/main/resources/desktop/currentView_"+typeLive.toString()+".xml");
				
					changeView(currentView);
				}
				else
				{
					currentView = (currentView + 1) % maxView;
					Serialize writeTo = new Serialize();
					writeTo.writeObject(currentView, "./src/main/resources/desktop/currentView_"+typeLive.toString()+".xml");
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
		dataBox.setText(this.data[i] + " " + this.units);
		viewLabel.setText(Widget.views[i]);
	}

	
	public void changeViewLive(HistoricalFitnessData hfd, Calendar cal, int i, IDs type){
		
		calen = cal;
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);


		if(!this.testF)
		{
			OneDaysWorthOfData odwod = hfd.retrieve2(day, month+1, year);

			System.out.println("Inside CVL...\n" + odwod.toString(false));
			System.out.println("CVL hfd...\n" + hfd.lifetimeAndBestDaysToString());
			System.out.println("i = " + i);

		

				switch(type){
						case CALORIES:
									if(i==0){ //Get day calorie
										String convert = String.valueOf(odwod.getTodaysTotalCaloriesBurned());
										dataBox.setText(convert);
										viewLabel.setText(Widget.views[i]);
									}
									break;
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
