package ca.uwo.csd.cs2212.team11;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import ca.uwo.csd.cs2212.team11.SharedData.*;

/**
 * Class that will display components on a JFRAME dashboard 
 * @author Andrew Hall
 *
 */
public class DeskTop extends JFrame implements Serializable{

	private Widget[] all_widgets = new Widget[7];	
	private boolean[] widgetVisible = {false, false, false, false, false, false, false};
	private JPanel goalsPanel, widgetPanel, datePanel, northPanel, westPanel, awardsPanel, eastPanel, southPanel;
	private JLabel goalsListLabel, dateLabel, awardsListLabel;
	private PieChart activeChart;
	private boolean activeChartVisible = false;
	private Graph[] allGraphs = new Graph[7];
	private boolean[] graphVisible = {false, false, false, false, false, false, false};
	
	/**
	 * Constructor to create Desktop with all widgets hidden (for now)
	 */
	public DeskTop()
	{
	
		super("Team 11 FitBit Viewer - Click on left Panel Colors to add Components");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		all_widgets[IDs.CALORIES.ordinal()] = new Widget(IDs.CALORIES);
		all_widgets[IDs.DISTANCE.ordinal()] = new Widget(IDs.DISTANCE);
		all_widgets[IDs.CLIMB.ordinal()] = new Widget(IDs.CLIMB);
		all_widgets[IDs.STEPS.ordinal()] = new Widget(IDs.STEPS);
		all_widgets[IDs.ACTIVE.ordinal()] = new Widget(IDs.ACTIVE);
		all_widgets[IDs.SEDENTARY.ordinal()] = new Widget(IDs.SEDENTARY);
		all_widgets[IDs.HEART_RATE.ordinal()] = new Widget(IDs.HEART_RATE);
		activeChart = new PieChart();
		allGraphs[IDs.CALORIES.ordinal()] = new Graph(IDs.CALORIES);
		allGraphs[IDs.DISTANCE.ordinal()] = new Graph(IDs.DISTANCE);
		allGraphs[IDs.STEPS.ordinal()] = new Graph(IDs.STEPS);
		allGraphs[IDs.HEART_RATE.ordinal()] = new Graph(IDs.HEART_RATE);
        
		

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
		
		mainDisplay.add(northPanel, BorderLayout.NORTH);
		mainDisplay.add(eastPanel, BorderLayout.EAST);
		mainDisplay.add(southPanel, BorderLayout.SOUTH);
		mainDisplay.add(westPanel, BorderLayout.WEST);
		mainDisplay.add(widgetPanel, BorderLayout.CENTER);
		
		backPanel.add(mainDisplay);
		this.add(backPanel);
	}
	
	/**
	 * @return the all_widgets
	 */
	public Widget[] getAll_widgets() {
		return all_widgets;
	}

	/**
	 * @param all_widgets the all_widgets to set
	 */
	public void setAll_widgets(Widget[] all_widgets) {
		this.all_widgets = all_widgets;
	}

	/**
	 * @return the widgetVisible
	 */
	public boolean[] getWidgetVisible() {
		return widgetVisible;
	}

	/**
	 * @param widgetVisible the widgetVisible to set
	 */
	public void setWidgetVisible(boolean[] widgetVisible) {
		this.widgetVisible = widgetVisible;
	}

	/**
	 * @return the goalsPanel
	 */
	public JPanel getGoalsPanel() {
		return goalsPanel;
	}

	/**
	 * @param goalsPanel the goalsPanel to set
	 */
	public void setGoalsPanel(JPanel goalsPanel) {
		this.goalsPanel = goalsPanel;
	}

	/**
	 * @return the widgetPanel
	 */
	public JPanel getWidgetPanel() {
		return widgetPanel;
	}

	/**
	 * @param widgetPanel the widgetPanel to set
	 */
	public void setWidgetPanel(JPanel widgetPanel) {
		this.widgetPanel = widgetPanel;
	}

	/**
	 * @return the datePanel
	 */
	public JPanel getDatePanel() {
		return datePanel;
	}

	/**
	 * @param datePanel the datePanel to set
	 */
	public void setDatePanel(JPanel datePanel) {
		this.datePanel = datePanel;
	}

	/**
	 * @return the northPanel
	 */
	public JPanel getNorthPanel() {
		return northPanel;
	}

	/**
	 * @param northPanel the northPanel to set
	 */
	public void setNorthPanel(JPanel northPanel) {
		this.northPanel = northPanel;
	}

	/**
	 * @return the westPanel
	 */
	public JPanel getWestPanel() {
		return westPanel;
	}

	/**
	 * @param westPanel the westPanel to set
	 */
	public void setWestPanel(JPanel westPanel) {
		this.westPanel = westPanel;
	}

	/**
	 * @return the awardsPanel
	 */
	public JPanel getAwardsPanel() {
		return awardsPanel;
	}

	/**
	 * @param awardsPanel the awardsPanel to set
	 */
	public void setAwardsPanel(JPanel awardsPanel) {
		this.awardsPanel = awardsPanel;
	}

	/**
	 * @return the eastPanel
	 */
	public JPanel getEastPanel() {
		return eastPanel;
	}

	/**
	 * @param eastPanel the eastPanel to set
	 */
	public void setEastPanel(JPanel eastPanel) {
		this.eastPanel = eastPanel;
	}

	/**
	 * @return the southPanel
	 */
	public JPanel getSouthPanel() {
		return southPanel;
	}

	/**
	 * @param southPanel the southPanel to set
	 */
	public void setSouthPanel(JPanel southPanel) {
		this.southPanel = southPanel;
	}

	/**
	 * @return the goalsListLabel
	 */
	public JLabel getGoalsListLabel() {
		return goalsListLabel;
	}

	/**
	 * @param goalsListLabel the goalsListLabel to set
	 */
	public void setGoalsListLabel(JLabel goalsListLabel) {
		this.goalsListLabel = goalsListLabel;
	}

	/**
	 * @return the dateLabel
	 */
	public JLabel getDateLabel() {
		return dateLabel;
	}

	/**
	 * @param dateLabel the dateLabel to set
	 */
	public void setDateLabel(JLabel dateLabel) {
		this.dateLabel = dateLabel;
	}

	/**
	 * @return the awardsListLabel
	 */
	public JLabel getAwardsListLabel() {
		return awardsListLabel;
	}

	/**
	 * @param awardsListLabel the awardsListLabel to set
	 */
	public void setAwardsListLabel(JLabel awardsListLabel) {
		this.awardsListLabel = awardsListLabel;
	}

	/**
	 * @return the activeChart
	 */
	public PieChart getActiveChart() {
		return activeChart;
	}

	/**
	 * @param activeChart the activeChart to set
	 */
	public void setActiveChart(PieChart activeChart) {
		this.activeChart = activeChart;
	}

	/**
	 * @return the activeChartVisible
	 */
	public boolean isActiveChartVisible() {
		return activeChartVisible;
	}

	/**
	 * @param activeChartVisible the activeChartVisible to set
	 */
	public void setActiveChartVisible(boolean activeChartVisible) {
		this.activeChartVisible = activeChartVisible;
	}

	/**
	 * @return the allGraphs
	 */
	public Graph[] getAllGraphs() {
		return allGraphs;
	}

	/**
	 * @param allGraphs the allGraphs to set
	 */
	public void setAllGraphs(Graph[] allGraphs) {
		this.allGraphs = allGraphs;
	}

	/**
	 * @return the graphVisible
	 */
	public boolean[] getGraphVisible() {
		return graphVisible;
	}

	/**
	 * @param graphVisible the graphVisible to set
	 */
	public void setGraphVisible(boolean[] graphVisible) {
		this.graphVisible = graphVisible;
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
				widgetPanel.remove(activeChart);
			}else{
				activeChartVisible = true;
				widgetPanel.add(activeChart);
			}
		}else{
			if(graphVisible[type.ordinal()] == true){
				graphVisible[type.ordinal()] = false;
				widgetPanel.remove(allGraphs[type.ordinal()]);
			}else{
				graphVisible[type.ordinal()] = true;
				widgetPanel.add(allGraphs[type.ordinal()]);
			}
		}
		widgetPanel.revalidate();
		repaint();
	}
	
	/**
	 * Button that will refresh the data -- Make request to api and store new values in all containers
	 */
	private void refreshData(){
		System.err.println("DeskTop.refreshData() called");
		System.err.println("\t***Does nothing yet");
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
	 * This will set our last refresh label and will be called whenever the refresh button is hit
	 * @return
	 */
	public Date getDateOfLastRefresh(){
		return new Date();
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
			String dateString = new SimpleDateFormat("yyyy-MM-dd").format(getDateOfLastRefresh());
			dateLabel = new JLabel(dateString);
			dateLabel.setFont(new Font("Tahoma", Font.PLAIN, 36));
			dateLabel.setForeground(new Color(255,255,255));
			datePanel.add(dateLabel);
			
			ImagePanel refreshButton = new ImagePanel("Refresh.png");
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
					
					ObjectOutputStream out;
					try 
					{
						out = new ObjectOutputStream(new FileOutputStream("./src/main/resources/Desktop.config"));
						out.writeObject(Team11_FitBitViewer.GUI);
						out.close();
					} catch (FileNotFoundException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
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
