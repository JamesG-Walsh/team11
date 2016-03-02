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
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import java.awt.Dimension;

import ca.uwo.csd.cs2212.team11.SharedData.*;


public class DeskTop extends JFrame{
	
	private Widget[] all_widgets = new Widget[7];	
	private boolean[] widgetVisible = {false, false, false, false, false, false, false};
	private JPanel goalsPanel, widgetPanel, datePanel, northPanel, westPanel, awardsPanel, eastPanel, southPanel;
	private JLabel goalsListLabel, dateLabel, awardsListLabel;
	
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

		
		ImagePanel backPanel = new ImagePanel(SharedData.PATH_TO_RESOURCES + "imgs/jogger.jpg"); // replace with no copyright
		JLayeredPane main = new JLayeredPane();
		this.setSize(backPanel.getWidth(), backPanel.getHeight());

		
		JPanel mainDisplay = new JPanel();
		mainDisplay.setLayout(new BorderLayout(1,1));
		mainDisplay.setPreferredSize(new Dimension(1000, 600));
		mainDisplay.setOpaque(false);
		mainDisplay.setVisible(true);
		

		northPanel = new JPanel();
		northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.LINE_AXIS));
		northPanel.setOpaque(false);

			ImagePanel fitBitPic = new ImagePanel("Fitbit.png");
			fitBitPic.setSize(fitBitPic.getWidth(), fitBitPic.getHeight());
			fitBitPic.setAlignmentX(Component.CENTER_ALIGNMENT);
			northPanel.add(fitBitPic);
			northPanel.add(Box.createHorizontalGlue());
		
			JPanel hello = new JPanel();
			hello.setBackground(SharedData.SMOKE);
			JLabel helloLabel = new JLabel("Welcome, USER!");
			helloLabel.setFont(new Font("T ahoma", Font.PLAIN, 36));
			helloLabel.setForeground(new Color(255,255,255));
			hello.add(helloLabel);
			northPanel.add(hello);
			northPanel.add(Box.createHorizontalGlue());
		
			ImagePanel settingsButton = new ImagePanel("Settings.png");
			settingsButton.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e){
					openSettingsPanel();
				}
			});
			northPanel.add(settingsButton);
	
			ImagePanel exitButton = new ImagePanel("Exit_Button.png");
			exitButton.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e){
					System.exit(0);
				}
			});
			northPanel.add(exitButton);
		
		mainDisplay.add(northPanel, BorderLayout.NORTH);
		
			
		westPanel = new JPanel();
		westPanel.setOpaque(false);
		westPanel.setLayout(new GridLayout(7,2));

			JPanel a = createNavButton(IDs.CALORIES, "C");
			a.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e){
					addRemoveWidget(IDs.CALORIES);
				}
			});
			westPanel.add(a);

			a = new ImagePanel("graph.png");
			a.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e){
					addRemoveGraph(IDs.CALORIES);
				}
			});
			westPanel.add(a);
			
			a = createNavButton(IDs.DISTANCE, "D");
			a.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e){
					addRemoveWidget(IDs.DISTANCE);
				}
			});
			westPanel.add(a);
			
			a = new ImagePanel("graph.png");
			a.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e){
					addRemoveGraph(IDs.DISTANCE);
				}
			});
			westPanel.add(a);
			
			a = createNavButton(IDs.CLIMB, "V");
			a.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e){
					addRemoveWidget(IDs.CLIMB);
				}
			});
			westPanel.add(a);
			
			westPanel.add(Box.createHorizontalBox());
			
			a = createNavButton(IDs.STEPS, "S");
			a.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e){
					addRemoveWidget(IDs.STEPS);
				}
			});
			westPanel.add(a);

			a = new ImagePanel("graph.png");
			a.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e){
					addRemoveGraph(IDs.STEPS);
				}
			});
			westPanel.add(a);
			
			a = createNavButton(IDs.ACTIVE, "A");
			a.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e){
					addRemoveWidget(IDs.ACTIVE);
				}
			});
			westPanel.add(a);

			a = new ImagePanel("graph.png");
			a.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e){
					addRemoveGraph(IDs.ACTIVE);
				}
			});
			westPanel.add(a);
			
			a = createNavButton(IDs.SEDENTARY, "Sed");
			a.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e){
					addRemoveWidget(IDs.SEDENTARY);
				}
			});
			westPanel.add(a);

			westPanel.add(Box.createHorizontalBox());
			
			a = createNavButton(IDs.HEART_RATE, "HR");
			a.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e){
					addRemoveWidget(IDs.HEART_RATE);
				}
			});
			westPanel.add(a);

			a = new ImagePanel("graph.png");
			a.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e){
					addRemoveGraph(IDs.HEART_RATE);
				}
			});
			westPanel.add(a);

		mainDisplay.add(westPanel, BorderLayout.WEST);
		
		eastPanel = new JPanel();
		eastPanel.setOpaque(false);
		eastPanel.setLayout(new BoxLayout(eastPanel,1));
		
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
		
		eastPanel.add(goalsPanel);
		

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
		
		eastPanel.add(awardsPanel);
		
		mainDisplay.add(eastPanel, BorderLayout.EAST);

		
		
		widgetPanel = new JPanel();
		widgetPanel.setBackground(SharedData.SMOKE);
		mainDisplay.add(widgetPanel, BorderLayout.CENTER);
		
		
		southPanel = new JPanel();
		southPanel.setOpaque(false);
		southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.LINE_AXIS));
		southPanel.add(Box.createHorizontalBox());
		
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
			datePanel.add(refreshButton); // refreshButton starts disappearing after adding addWidgetButton 
			
			
		southPanel.add(datePanel);
		southPanel.add(Box.createHorizontalGlue());
	
			ImagePanel addWidgetButton = new ImagePanel("Add_Widgit.png");
			addWidgetButton.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e){
					addWidgetPanel();
				}
			});
			southPanel.add(addWidgetButton);
		
		mainDisplay.add(southPanel, BorderLayout.SOUTH);
		mainDisplay.setSize(800, 600);
		backPanel.add(mainDisplay);
		this.add(backPanel);
	}
	
	protected void addRemoveGraph(IDs steps) {
		System.err.println("DeskTop.refreshData() called");
		System.err.println("\t***Does nothing yet");
	}

	private JPanel createNavButton(IDs type, String tag) {
		JPanel a = new JPanel();
		a.setBackground(SharedData.COLOR_SET[type.ordinal()]);
		a.setSize(32, 32);
		a.setBorder(BorderFactory.createLineBorder(SharedData.COLOR_SET[type.ordinal()].darker()));
		a.add(new JLabel(tag));
		return a;
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
	
	public Date getDateOfLastRefresh(){
		return new Date();
	}
	
	private void openSettingsPanel(){
		System.err.println("DeskTop.openSettingsPanel() called");
		System.err.println("\t***Does nothing yet");
	}

}
