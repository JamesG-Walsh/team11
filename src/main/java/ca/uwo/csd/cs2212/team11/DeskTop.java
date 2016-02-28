package ca.uwo.csd.cs2212.team11;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import ca.uwo.csd.cs2212.team11.SharedData.*;


public class DeskTop extends JFrame{
	
	private Widget[] all_widgets = new Widget[7];	
	private boolean[] widgetVisible = {false, false, false, false, false, false, false};
	private JPanel[] primaryDisplayAreas = new JPanel[4];
	private JPanel overallPanel, goalsPanel, widgetPanel, datePanel;
	private JLabel[] overallLabels = new JLabel[28];
	private JLabel goalsListLabel, dateLabel;
	
	public DeskTop(){
		super("Team 11 FitBit Viewer");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		all_widgets[IDs.CALORIES.ordinal()] = new Widget(IDs.CALORIES);
		all_widgets[IDs.DISTANCE.ordinal()] = new Widget(IDs.DISTANCE);
		all_widgets[IDs.CLIMB.ordinal()] = new Widget(IDs.CLIMB);
		all_widgets[IDs.STEPS.ordinal()] = new Widget(IDs.STEPS);
		all_widgets[IDs.ACTIVE.ordinal()] = new Widget(IDs.ACTIVE);
		all_widgets[IDs.SEDENTARY.ordinal()] = new Widget(IDs.SEDENTARY);
		all_widgets[IDs.HEART_RATE.ordinal()] = new Widget(IDs.HEART_RATE);
		
		ImagePanel backPanel = new ImagePanel(SharedData.PATH_TO_RESOURCES + "../src/main/resources/imgs/jogger.jpg"); // replace with no copyright
		JLayeredPane main = new JLayeredPane();
		this.setSize(backPanel.getWidth(), backPanel.getHeight());
		main.setSize(backPanel.getWidth(), backPanel.getHeight());
		main.add(backPanel, -3000);

		
		JPanel mainDisplay = (JPanel) this.getGlassPane();
		this.setGlassPane(mainDisplay);
		mainDisplay.setLayout(new BoxLayout(mainDisplay, 1));
		mainDisplay.setOpaque(false);
		mainDisplay.setVisible(true);
		mainDisplay.setSize(main.getSize());
		this.add(mainDisplay, 0);
		
		primaryDisplayAreas[0] = new JPanel();
		primaryDisplayAreas[0].setLayout(new BoxLayout(primaryDisplayAreas[0], 1));
		JPanel welcomePanel = new JPanel();
		welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.LINE_AXIS));
		String temp = SharedData.PATH_TO_RESOURCES + "imgs/Fitbit.png";
		ImagePanel fitBitPic = new ImagePanel(temp);
		temp = null;
		fitBitPic.setSize(fitBitPic.getWidth(), fitBitPic.getHeight());
		fitBitPic.setAlignmentX(Component.CENTER_ALIGNMENT);
		welcomePanel.add(fitBitPic);
		welcomePanel.add(Box.createHorizontalGlue());
		
		JPanel hello = new JPanel();
		hello.setBackground(SharedData.SMOKE);
		JLabel helloLabel = new JLabel("Welcome, USER!");
		helloLabel.setFont(new Font("Tahoma", Font.PLAIN, 36));
		hello.add(helloLabel);
		welcomePanel.add(hello);
		welcomePanel.add(Box.createHorizontalGlue());
		
		temp = SharedData.PATH_TO_RESOURCES + "/imgs/Settings.png";
		ImagePanel settingsButton = new ImagePanel(temp);
		temp = SharedData.PATH_TO_RESOURCES + "/imgs/Exit_Button.png";
		ImagePanel exitButton = new ImagePanel(temp);
		temp = null;
		settingsButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				openSettingsPanel();
			}
		});
		exitButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				System.exit(0);
			}
		});
		welcomePanel.add(settingsButton);
		welcomePanel.add(exitButton);
		
		primaryDisplayAreas[0].add(welcomePanel);
		
		JPanel buttonArray = new JPanel();
		JPanel caloriesButton = new JPanel();
		caloriesButton.setBackground(SharedData.COLOR_SET[IDs.CALORIES.ordinal()]);
		caloriesButton.add(new JLabel("C"));
		caloriesButton.setSize(25,25);
		caloriesButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				addRemoveWidget(IDs.CALORIES);
			}
		});
		buttonArray.add(caloriesButton);
		
		JPanel distanceButton = new JPanel();
		distanceButton.setBackground(SharedData.COLOR_SET[IDs.DISTANCE.ordinal()]);
		distanceButton.add(new JLabel("D"));
		distanceButton.setSize(25,25);
		distanceButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				addRemoveWidget(IDs.DISTANCE);
			}
		});
		buttonArray.add(distanceButton);
		
		JPanel climbButton = new JPanel();
		climbButton.setBackground(SharedData.COLOR_SET[IDs.CLIMB.ordinal()]);
		climbButton.add(new JLabel("V"));
		climbButton.setSize(25,25);
		climbButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				addRemoveWidget(IDs.CLIMB);
			}
		});
		buttonArray.add(climbButton);
		
		JPanel stepsButton = new JPanel();
		stepsButton.setBackground(SharedData.COLOR_SET[IDs.STEPS.ordinal()]);
		stepsButton.add(new JLabel("S"));
		stepsButton.setSize(25,25);
		stepsButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				addRemoveWidget(IDs.STEPS);
			}
		});
		buttonArray.add(stepsButton);
		
		JPanel activeButton = new JPanel();
		activeButton.setBackground(SharedData.COLOR_SET[IDs.ACTIVE.ordinal()]);
		activeButton.add(new JLabel("A"));
		activeButton.setSize(25,25);
		activeButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				addRemoveWidget(IDs.ACTIVE);
			}
		});
		buttonArray.add(activeButton);
		
		JPanel sedentaryButton = new JPanel();
		sedentaryButton.setBackground(SharedData.COLOR_SET[IDs.SEDENTARY.ordinal()]);
		sedentaryButton.add(new JLabel("R"));
		sedentaryButton.setSize(25,25);
		sedentaryButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				addRemoveWidget(IDs.SEDENTARY);
			}
		});
		buttonArray.add(sedentaryButton);
		
		JPanel hRateButton = new JPanel();
		hRateButton.setBackground(SharedData.COLOR_SET[IDs.HEART_RATE.ordinal()]);
		hRateButton.add(new JLabel("HR"));
		hRateButton.setSize(25,25);
		hRateButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				addRemoveWidget(IDs.HEART_RATE);
			}
		});
		buttonArray.add(hRateButton);
		primaryDisplayAreas[0].add(buttonArray);
		
		JPanel decoration = new JPanel();
		decoration.setSize((int) (mainDisplay.getWidth() * 0.75), 10); // why doesn't width work?
		decoration.setBackground(SharedData.SMOKE);
		primaryDisplayAreas[0].add(decoration);
		
		mainDisplay.add(primaryDisplayAreas[0]);
		
		primaryDisplayAreas[1] = new JPanel();
		overallPanel = new JPanel();
		overallPanel.setBackground(SharedData.SMOKE);
//		SpringLayout oap_layout = new SpringLayout();
//		overallPanel.setLayout(oap_layout);
		overallPanel.setLayout(new GridLayout(4, 8));
		for(int i = 0; i < overallLabels.length; i++){
			overallLabels[i] = new JLabel("Blah");
			overallPanel.add(overallLabels[i]);
		}
		overallLabels[0].setText("");
		overallLabels[1].setText("Calories");
		overallLabels[2].setText("Steps");
		overallLabels[3].setText("Distance");
		overallLabels[4].setText("Floors");
		overallLabels[5].setText("Active");
		overallLabels[6].setText("Sedentary");
		overallLabels[7].setText("Today:");
		overallLabels[7].setFont(new Font("Tahoma", Font.PLAIN, 18));
		overallLabels[14].setText("Best:");
		overallLabels[14].setFont(new Font("Tahoma", Font.PLAIN, 18));
		overallLabels[21].setText("Lifetime:");
		overallLabels[21].setFont(new Font("Tahoma", Font.PLAIN, 18));
//		SpringUtilities.makeGrid(overallPanel, 4, 8, 5, 5, 5, 5);
		primaryDisplayAreas[1].add(overallPanel);
		
		goalsPanel = new JPanel();
		goalsPanel.setBackground(SharedData.SMOKE);
		goalsPanel.setLayout(new BoxLayout(goalsPanel,1));
		JLabel goalsTitleLabel = new JLabel("Daily Goals");
		goalsTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		goalsPanel.add(goalsTitleLabel);
		goalsListLabel= new JLabel("<html>Blah<br/>Blah<br/>Blah<br/>Blah<br/>Blah<br/>Blah<br/>Blah<br/></html>");
		goalsPanel.add(goalsListLabel);
		primaryDisplayAreas[1].add(goalsPanel);
		mainDisplay.add(primaryDisplayAreas[1]);
		
		primaryDisplayAreas[2] = new JPanel();
		widgetPanel = new JPanel();
		widgetPanel.setBackground(SharedData.SMOKE);
		primaryDisplayAreas[2].add(widgetPanel);
		mainDisplay.add(primaryDisplayAreas[2]);
		
		
		primaryDisplayAreas[3] = new JPanel();
		datePanel = new JPanel();
//		datePanel.setOpaque(false);
//		datePanel.setLayout(new BoxLayout(datePanel, BoxLayout.LINE_AXIS));
		FlowLayout dp_layout = new FlowLayout(FlowLayout.LEFT);
//		dp_layout.putConstraints(5); for SpringLayout only
		datePanel.setLayout(dp_layout);
		datePanel.setBackground(SharedData.SMOKE);
		dateLabel = new JLabel(new Date().toString());
		dateLabel.setFont(new Font("Tahoma", Font.PLAIN, 36));
		datePanel.add(dateLabel); /// *** should change format
		temp = SharedData.PATH_TO_RESOURCES + "imgs/Refresh.png";
		ImagePanel refreshButton = new ImagePanel(temp);
		refreshButton.setAlignmentX(CENTER_ALIGNMENT); // doesn't seem to be working
		refreshButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				refreshData();
			}
		});
		datePanel.add(refreshButton); // refreshButton starts disappearing after adding addWidgetButton 
		primaryDisplayAreas[3].add(datePanel);
		primaryDisplayAreas[3].add(Box.createHorizontalGlue());
		
		temp = SharedData.PATH_TO_RESOURCES + "imgs/Add_Widgit.png";
		ImagePanel addWidgetButton = new ImagePanel(temp);
		addWidgetButton.setAlignmentX(CENTER_ALIGNMENT); // doesn't seem to be working
		addWidgetButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				addWidgetPanel();
			}
		});
		primaryDisplayAreas[3].add(addWidgetButton);
		
		mainDisplay.add(primaryDisplayAreas[3]);
	}
	
	private void refreshData(){
		System.err.println("DeskTop.refreshData() called");
		System.err.println("\t***Does nothing yet");
	}
	
	private void addWidgetPanel(){
		System.err.println("DeskTop.addWidgetsPanel() called");
		System.err.println("\t***Does nothing yet");
	}
	
	private void addRemoveWidget(IDs type){

		if(widgetVisible[type.ordinal()] == true){
			widgetVisible[type.ordinal()] = false;
			widgetPanel.remove(all_widgets[type.ordinal()]);
			widgetPanel.revalidate();
			repaint();
		}else{
			widgetVisible[type.ordinal()] = true;
			all_widgets[type.ordinal()].setVisible(true);
			widgetPanel.add(all_widgets[type.ordinal()]);
			widgetPanel.revalidate();
			repaint();
		}
	}
	
	
	private void openSettingsPanel(){
		System.err.println("DeskTop.openSettingsPanel() called");
		System.err.println("\t***Does nothing yet");
	}

}
