package ca.uwo.csd.cs2212.team11;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.Dimension;
import java.io.Serializable;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;

import ca.uwo.csd.cs2212.team11.Team11_FitBitViewer.*;
import ca.uwo.csd.cs2212.team11.SharedData.*;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jdatepicker.impl.JDatePickerImpl;
import java.util.Properties;
import javax.swing.JFormattedTextField.AbstractFormatter;



/**
 * Class that will display components on a JFRAME dashboard 
 * @author Andrew Hall
 *
 */

public class DeskTop extends JFrame implements Serializable  
{
	public static Widget[] all_widgets = new Widget[7];	
	public static boolean[] widgetVisible; /*= {false, false,false, false, false, false, false};*/
	private JPanel goalsPanel, widgetPanel, datePanel, northPanel, westPanel, awardsPanel, eastPanel, southPanel, centerPanel, graphPanel;
	private JLabel goalsListLabel, dateLabel, awardsListLabel;
	private PieChart activeChart;
	private boolean activeChartVisible = false;
	private Graph[] allGraphs = new Graph[7];
	private boolean[] graphVisible = {false, false, false, false, false, false, false};

	private Calendar workingDate;

	private User usr;
	/**
	 * Constructor to create Desktop with all widgets hidden (for now)
	 */

	public DeskTop()
	{
		super("Team 11 FitBit Viewer - Click on left Panel Colors to add Components");
		//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Serialize r = new Serialize();
		this.widgetVisible = (boolean[]) r.readObject("./src/main/resources/desktop/widgetVisible.xml").readObject();
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent e) {
				Serialize writeTo = new Serialize();
				writeTo.writeObject(Team11_FitBitViewer.GUI.widgetVisible, "./src/main/resources/desktop/widgetVisible.xml");
				System.exit(0);
			}
		});

		/*all_widgets[IDs.CALORIES.ordinal()] = new Widget(IDs.CALORIES);
		all_widgets[IDs.DISTANCE.ordinal()] = new Widget(IDs.DISTANCE);
		all_widgets[IDs.CLIMB.ordinal()] = new Widget(IDs.CLIMB);
		all_widgets[IDs.STEPS.ordinal()] = new Widget(IDs.STEPS);
		all_widgets[IDs.ACTIVE.ordinal()] = new Widget(IDs.ACTIVE);
		all_widgets[IDs.SEDENTARY.ordinal()] = new Widget(IDs.SEDENTARY);
		all_widgets[IDs.HEART_RATE.ordinal()] = new Widget(IDs.HEART_RATE);
		 */
		System.out.println("Here");

		activeChart = new PieChart();
		this.setWorkingDate();

		usr = new User();
		/*allGraphs[IDs.CALORIES.ordinal()] = new Graph(IDs.CALORIES, usr, this.getWorkingDate());
		allGraphs[IDs.DISTANCE.ordinal()] = new Graph(IDs.DISTANCE, usr, this.getWorkingDate());
		allGraphs[IDs.STEPS.ordinal()] = new Graph(IDs.STEPS, usr, this.getWorkingDate());
		allGraphs[IDs.HEART_RATE.ordinal()] = new Graph(IDs.HEART_RATE, usr, this.getWorkingDate());*/

		System.out.println("Here");

		if(Team11_FitBitViewer.testFlag){

			System.out.println("Other things");
			

		}else{
			usr.getHistoricalFitnessData().populateLifetimeAndBestDays();		
			//usr.getHistoricalFitnessData().retrieveDay( time.DAY_OF_MONTH,(time.MONTH + 1) ,time.YEAR ).populateTotals();

			OneDaysWorthOfData odwod = usr.getHistoricalFitnessData().retrieve2(1, 3, 2016 );
		}

		//odwod.populateTotals();

		all_widgets[IDs.CALORIES.ordinal()] = new Widget(usr, getWorkingDate(), IDs.CALORIES);
		all_widgets[IDs.DISTANCE.ordinal()] = new Widget(usr, getWorkingDate(),IDs.DISTANCE);
		all_widgets[IDs.CLIMB.ordinal()] = new Widget(usr, getWorkingDate(),IDs.CLIMB);
		all_widgets[IDs.STEPS.ordinal()] = new Widget(usr, getWorkingDate(),IDs.STEPS);
		all_widgets[IDs.ACTIVE.ordinal()] = new Widget(usr, getWorkingDate(),IDs.ACTIVE);
		all_widgets[IDs.SEDENTARY.ordinal()] = new Widget(usr, getWorkingDate(),IDs.SEDENTARY);
		all_widgets[IDs.HEART_RATE.ordinal()] = new Widget(usr, getWorkingDate(),IDs.HEART_RATE);

		//		System.out.println(System.getProperty("user.dir"));

		ImagePanel backPanel = new ImagePanel("jogger.jpg"); // replace with no copyright
		this.setSize(backPanel.getWidth(), backPanel.getHeight());

		JPanel mainDisplay = new JPanel();
		mainDisplay.setLayout(new BorderLayout(1,1));
		mainDisplay.setPreferredSize(new Dimension(1000, 600));
		mainDisplay.setOpaque(false);
		mainDisplay.setVisible(true);


		northPanel = new JPanel();
		populateNorthPanel(northPanel);

		eastPanel = new JPanel();
		populateEastPanel(eastPanel);

		southPanel = new JPanel();
		populateSouthPanel(southPanel);

		westPanel = new JPanel();
		populateWestPanel(westPanel);

		widgetPanel = new JPanel();
		widgetPanel.setBackground(SharedData.SMOKE);


		centerPanel = new JPanel();
		populateCenterPanel(centerPanel);


		/*Visiblity of Widget save by user from last save*/
		configWidgetVisibilityToUsersPref(IDs.CALORIES);
		configWidgetVisibilityToUsersPref(IDs.CLIMB);
		configWidgetVisibilityToUsersPref(IDs.ACTIVE);
		configWidgetVisibilityToUsersPref(IDs.HEART_RATE);
		configWidgetVisibilityToUsersPref(IDs.STEPS);
		configWidgetVisibilityToUsersPref(IDs.SEDENTARY);
		configWidgetVisibilityToUsersPref(IDs.DISTANCE);

		mainDisplay.add(northPanel, BorderLayout.NORTH);
		mainDisplay.add(eastPanel, BorderLayout.EAST);
		mainDisplay.add(southPanel, BorderLayout.SOUTH);
		mainDisplay.add(westPanel, BorderLayout.WEST);
		mainDisplay.add(centerPanel, BorderLayout.CENTER);

		//refreshData();

		backPanel.add(mainDisplay);
		this.add(backPanel);


	}

	/**
	 * Will add a graphing window with data plugged in as per user event
	 * @param steps -- Data that will be plugged into the graph for steps
	 */
	protected void addRemoveGraph(IDs type) {
		System.err.println("DeskTop.refreshData() called");
		System.err.println("\t***Does nothing yet");
		if (type == IDs.ACTIVE){
			if(activeChartVisible == true){
				activeChartVisible = false;
				graphPanel.remove(activeChart);
			}else{
				activeChartVisible = true;
				graphPanel.add(activeChart);
			}
		}else{
			if(graphVisible[type.ordinal()] == true){
				graphVisible[type.ordinal()] = false;
				graphPanel.remove(allGraphs[type.ordinal()]);
			}else{
				graphVisible[type.ordinal()] = true;
				graphPanel.add(allGraphs[type.ordinal()]);
			}
		}
		graphPanel.revalidate();
		repaint();
	}

	/**
	 * Button that will refresh the data -- Make request to api and store new values in all containers
	 */
	private void refreshData()
	{

		if(Team11_FitBitViewer.testFlag)
		{
			System.err.println("DeskTop.refreshData() called");
			System.err.println("\t***Does nothing yet");
		}
		else
		{
			System.out.println("Starting live call of refreshData();");
			this.setWorkingDate();
			Calendar time =  Calendar.getInstance();
			Date date = new Date(116, 02, 3);
   			time.setTime(date);

			System.out.println( time.DAY_OF_MONTH + (time.MONTH + 1) + time.YEAR );

			usr.getHistoricalFitnessData().populateLifetimeAndBestDays();		
			//usr.getHistoricalFitnessData().retrieveDay( time.DAY_OF_MONTH,(time.MONTH + 1) ,time.YEAR ).populateTotals();

			int year = time.get(Calendar.YEAR);
			int month = time.get(Calendar.MONTH);
			int day = time.get(Calendar.DAY_OF_MONTH);

			OneDaysWorthOfData odwod = usr.getHistoricalFitnessData().retrieve2(day, month +1, year );
			odwod.populateTotals();
			System.out.println("Inside refreshData()...\n" + odwod.toString(false));

			HistoricalFitnessData hfd = usr.getHistoricalFitnessData();
			System.out.println("refreshData() hfd...\n" + hfd.lifetimeAndBestDaysToString());
			hfd.lifetimeAndBestDaysToString();

			this.all_widgets[IDs.CALORIES.ordinal()].changeViewLive(hfd, time, 0, IDs.CALORIES);
			this.all_widgets[IDs.CLIMB.ordinal()].changeViewLive(hfd, time, 0, IDs.CLIMB);
			this.all_widgets[IDs.ACTIVE.ordinal()].changeViewLive(hfd, time, 0, IDs.ACTIVE);
			this.all_widgets[IDs.HEART_RATE.ordinal()].changeViewLive(hfd, time, 0, IDs.HEART_RATE);
			this.all_widgets[IDs.STEPS.ordinal()].changeViewLive(hfd, time, 0, IDs.STEPS);
			this.all_widgets[IDs.SEDENTARY.ordinal()].changeViewLive(hfd, time, 0, IDs.SEDENTARY);
			this.all_widgets[IDs.DISTANCE.ordinal()].changeViewLive(hfd, time, 0, IDs.DISTANCE);
		}
	}

	/**
	 * This button will remove the widget specified by parameter
	 * @param type -- Type is the component (Calories, Distance ..etc)
	 */
	private void addRemoveWidget(IDs type){

		if(widgetVisible[type.ordinal()] == true){
			widgetVisible[type.ordinal()] = false;
			widgetPanel.remove(all_widgets[type.ordinal()]);
		}else{
			widgetVisible[type.ordinal()] = true;
			all_widgets[type.ordinal()].setVisible(true);
			widgetPanel.add(all_widgets[type.ordinal()]);
		}
		revalidate();
		repaint();
	}
	/**
	 * This button will remove the widget specified by parameter
	 * @param type -- Type is the component (Calories, Distance ..etc)
	 */
	private void configWidgetVisibilityToUsersPref(IDs type){

		if(widgetVisible[type.ordinal()] == false){
			widgetPanel.remove(all_widgets[type.ordinal()]);
		}else{
			all_widgets[type.ordinal()].setVisible(true);
			widgetPanel.add(all_widgets[type.ordinal()]);
		}
		revalidate();
		repaint();
	}

	/**
	 * This will set our last refresh label and will be called whenever the refresh button is hit
	 * @return
	 */
	public Calendar getWorkingDate()
	{		
		return this.workingDate;
	}

	public void setWorkingDate()
	{
		//Calendar cal = Calendar.getInstance();
		//	cal.setTime(javaSqlDate);
		this.workingDate = Calendar.getInstance();
		Date date = new Date(116, 02, 1);
   		this.workingDate.setTime(date);
	}

	/**
	 * OpenSettingsPanel will be attached to a button that will display the users preferences and they can interact with settings to their liking
	 */
	private void openSettingsPanel(){
		System.err.println("DeskTop.openSettingsPanel() called");
		System.err.println("\t***Does nothing yet");
	}

	private void populateNorthPanel(JPanel a){

		a.setLayout(new BoxLayout(northPanel, BoxLayout.LINE_AXIS));
		a.setOpaque(false);


		//add FitBit logo

		ImagePanel fitBitPic = new ImagePanel("Fitbit.png");
		fitBitPic.setSize(fitBitPic.getWidth(), fitBitPic.getHeight());
		fitBitPic.setAlignmentX(fitBitPic.getAlignmentX() + 100);
		a.add(fitBitPic);
		a.add(Box.createHorizontalGlue());


		// create the welcome to user
		JPanel hello = new JPanel();
		hello.setBackground(SharedData.SMOKE);
		JLabel helloLabel = new JLabel("Welcome, USER!");
		helloLabel.setFont(new Font("Tahoma", Font.PLAIN, 36));
		helloLabel.setForeground(new Color(255,255,255));
		hello.add(helloLabel);
		a.add(hello);
		a.add(Box.createHorizontalGlue());

		//add controller buttons
		//add settings button

		ImagePanel settingsButton = new ImagePanel("Settings.png");
		settingsButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				openSettingsPanel();
			}
		});
		settingsButton.setToolTipText("Click to open settings panel");
		a.add(settingsButton);

		//add exit button
		ImagePanel exitButton = new ImagePanel("Exit_Button.png");
		exitButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				System.exit(0);
			}
		});
		exitButton.setToolTipText("Click to exit FitBit Viewer");
		a.add(exitButton);

	}

	private void populateSouthPanel(JPanel a){
		a.setOpaque(false);
		a.setLayout(new BoxLayout(southPanel, BoxLayout.LINE_AXIS));

		//Create panel to display last update date
		datePanel = new JPanel();
		datePanel.setBackground(SharedData.SMOKE);
		String dateString = new SimpleDateFormat("yyyy-MM-dd").format(getWorkingDate().getTime());
		dateLabel = new JLabel(dateString);
		dateLabel.setFont(new Font("Tahoma", Font.PLAIN, 36));
		dateLabel.setForeground(new Color(255,255,255));
		datePanel.add(dateLabel);

		 JDatePickerImpl datePicker;

		 Properties p = new Properties();
			p.put("text.today", "Today");
			p.put("text.month", "Month");
			p.put("text.year", "Year");

		 UtilDateModel model=new UtilDateModel();
      	 JDatePanelImpl datePanel = new JDatePanelImpl(model,p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        datePanel.add(datePicker);

		JButton refreshButton = new JButton("Refresh Data");
		refreshButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				refreshData();
			}
		});
		refreshButton.setToolTipText("Click to refresh data");
		datePanel.add(refreshButton);  


		a.add(datePanel);
		a.add(Box.createHorizontalGlue());

		// add widget button *** now redundant?????
		ImagePanel addWidgetButton = new ImagePanel("Add_Widgit.png");
		addWidgetButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				//addWidgetPanel();
			}
		});
		addWidgetButton.setToolTipText("Click to add widget");
		a.add(addWidgetButton);
	}

	private void populateEastPanel(JPanel a){
		a.setOpaque(false);
		a.setLayout(new BoxLayout(a,1));

		goalsPanel = new JPanel();
		goalsPanel.setBackground(SharedData.SMOKE);
		goalsPanel.setLayout(new BoxLayout(goalsPanel,1));
		JLabel goalsTitleLabel = new JLabel("Daily Goals");
		goalsTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		goalsTitleLabel.setForeground(new Color(255,255,255));
		goalsPanel.add(goalsTitleLabel);
		goalsListLabel= new JLabel("<html>Blah<br/>Blah<br/>Blah<br/>Blah<br/>Blah<br/>Blah<br/>Blah<br/></html>");
		goalsListLabel.setForeground(new Color(255,255,255));
		goalsPanel.add(goalsListLabel);

		a.add(goalsPanel);


		awardsPanel = new JPanel();
		awardsPanel.setBackground(SharedData.SMOKE);
		awardsPanel.setLayout(new BoxLayout(awardsPanel,1));
		JLabel awardsTitleLabel = new JLabel("Achievements");
		awardsTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		awardsTitleLabel.setForeground(new Color(255,255,255));
		awardsPanel.add(awardsTitleLabel);
		awardsListLabel= new JLabel("<html>Blah<br/>Blah<br/>Blah<br/>Blah<br/>Blah<br/>Blah<br/>Blah<br/></html>");
		awardsListLabel.setForeground(new Color(255,255,255));
		awardsPanel.add(awardsListLabel);

		a.add(awardsPanel);


	}


	private void populateWestPanel(JPanel a){
		a.setOpaque(false);
		a.setLayout(new GridLayout(7,2));

		JPanel navBtn = createNavButton(IDs.CALORIES, "C");
		navBtn.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				addRemoveWidget(IDs.CALORIES);
				repaint();

			}
		});
		a.add(navBtn);

		navBtn = new ImagePanel("graph.png");
		navBtn.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){

				addRemoveGraph(IDs.CALORIES);
				repaint();
			}
		});
		a.add(navBtn);

		navBtn = createNavButton(IDs.DISTANCE, "D");
		navBtn.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				addRemoveWidget(IDs.DISTANCE);
				repaint();

			}
		});
		a.add(navBtn);

		navBtn = new ImagePanel("graph.png");
		navBtn.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				Component source = (Component)e.getSource();
				source.getParent().dispatchEvent(e);
				addRemoveGraph(IDs.DISTANCE);
				repaint();

			}
		});
		a.add(navBtn);

		navBtn = createNavButton(IDs.CLIMB, "V");
		navBtn.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				addRemoveWidget(IDs.CLIMB);
				repaint();

			}
		});
		a.add(navBtn);

		a.add(Box.createHorizontalBox());

		navBtn = createNavButton(IDs.STEPS, "S");
		navBtn.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				addRemoveWidget(IDs.STEPS);
				repaint();

			}
		});
		a.add(navBtn);

		navBtn = new ImagePanel("graph.png");
		navBtn.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){

				addRemoveGraph(IDs.STEPS);
				repaint();

			}
		});
		a.add(navBtn);

		navBtn = createNavButton(IDs.ACTIVE, "A");
		navBtn.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				addRemoveWidget(IDs.ACTIVE);
				repaint();

			}
		});
		a.add(navBtn);

		navBtn = new ImagePanel("graph.png");
		navBtn.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){

				addRemoveGraph(IDs.ACTIVE);
				repaint();
			}
		});
		a.add(navBtn);

		navBtn = createNavButton(IDs.SEDENTARY, "Sed");
		navBtn.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				addRemoveWidget(IDs.SEDENTARY);
				repaint();

			}
		});
		a.add(navBtn);

		a.add(Box.createHorizontalBox());

		navBtn = createNavButton(IDs.HEART_RATE, "HR");
		navBtn.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				addRemoveWidget(IDs.HEART_RATE);
				repaint();

			}
		});
		a.add(navBtn);

		navBtn = new ImagePanel("graph.png");
		navBtn.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){

				addRemoveGraph(IDs.HEART_RATE);
				repaint();
			}
		});
		a.add(navBtn);
	}

	private void populateCenterPanel(JPanel a){
		a.setLayout(new BoxLayout(a, 1));
		a.setOpaque(false);

		widgetPanel = new JPanel();
		widgetPanel.setBackground(SharedData.SMOKE);
		a.add(widgetPanel);

		graphPanel = new JPanel();
		graphPanel.setBackground(SharedData.SMOKE);
		a.add(graphPanel);
	}

	/**
	 * Creating buttons to display different components onto the dash such as calories or distance travelled
	 * @param type -- Label of the component (Calorie, distance...)
	 * @param tag -- Holds value of current component
	 * @return
	 */
	private JPanel createNavButton(IDs type, String tag) {
		JPanel a = new JPanel();
		a.setBackground(SharedData.COLOR_SET[type.ordinal()]);
		a.setPreferredSize(new Dimension(32, 32));
		a.setBorder(BorderFactory.createLineBorder(SharedData.COLOR_SET[type.ordinal()].darker()));
		a.add(new JLabel(tag));
		return a;
	}

}
