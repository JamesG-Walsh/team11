package ca.uwo.csd.cs2212.team11; 

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 * Widgets will be a container that holds all widgets objects
 * @author Andrew Hall
 *
 */
public class Widgets extends JPanel {

	private static final double METERS_PER_YARD = 0.9144;
	private static final double YARDS_PER_MILE = 1760.0;
	private static final Color[] color_set = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE}; 
	private static final String[] views = {"<html>D<br/>a<br/>i<br/>l<br/>y</html>",
											"<html>R<br/>e<br/>c<br/>o<br/>r<br/>d</html>", 
											"<html>L<br/>i<br/>f<br/>e<br/>t<br/>i<br/>m<br/>e</html>"};
	private int goals = 0;
	private int currentView = 0;
	private int[] data;
	private JLabel[] label = new JLabel[10];
	private JTextField[] box = new JTextField[10];
	private JButton[] button = new JButton[5];
	private JPanel panel = new JPanel();
	
	// create dummy values
	private static int[] base_array = {1492, 55555, 987654321};
	static int[] time_series = {126, 83, 118, 53, 80, 51, 83, 144, 98, 115, 134, 77, 129, 92, 55, 141, 149, 121, 60, 107, 109, 50, 131, 130, 122, 124, 117, 97, 61, 115, 102, 125, 92, 146, 99, 65, 140, 84, 107, 120, 69, 112, 89, 109, 83, 64, 106, 135, 104, 148} ;
	
	
	/**
	 * Class constructor -- Set size of container
	 * @param type	the type of the widget
	 */
	public Widgets(int type) {
			super();
//			System.err.println("Widget constructor entered");
//			this.setSize(500, 150);
			this.setVisible(true);
			Border b = BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder());
			this.setBorder(b);
			if(type == 0) 	{
				this.data = get_data(1);
				panel = makePrimative();
				this.add(panel);	
			}
			if(type == 1) 	{
				this.data = get_data(1);
				panel = makeCarousel();
				this.add(panel);	
			}
			if(type == 2) 	{
				this.data = get_data(1);
				panel = makeTabbed();
				this.add(panel);	
			}
			if(type == 3) 	{
				this.data = get_data(2);
				panel = makeGraph();
				this.add(panel);	
			}
	}
	
	/**
	 * Make an initial panel
	 * @return
	 */
	private JPanel makePrimative(){
		JPanel a = new JPanel();
		a.setBackground(Color.WHITE);
		
		BorderLayout layout = new BorderLayout(2,2);
		a.setLayout(layout);
		
		label[0] = new JLabel("Basic SwingX -- Single View");
		a.add(label[0], BorderLayout.NORTH);
		
		JPanel middle = new JPanel();
		BoxLayout l = new BoxLayout(middle, 1);
		middle.setLayout(l);
		middle.setOpaque(false);
		
		JPanel daily = new JPanel();
		daily.setOpaque(false);
		label[1] = new JLabel("Daily: ");
		box[1] = new JTextField(20);
		box[1].setText(showValue(0, "yards", false));
		box[1].setEditable(false);
		box[1].setToolTipText(showValue(0, "", true));
		daily.add(label[1]);
		daily.add(box[1]);
		middle.add(daily);
		
		JPanel record = new JPanel();
		record.setOpaque(false);
		label[2] = new JLabel("Record: ");
		box[2] = new JTextField(20);
		box[2].setText(showValue(1, "yards", false));
		box[2].setEditable(false);
		box[2].setToolTipText(showValue(1, "", true));
		record.add(label[2]);
		record.add(box[2]);
		middle.add(record);
		
		JPanel life = new JPanel();
		life.setOpaque(false);
		label[3] = new JLabel ("Lifetime: ");
		box[3] = new JTextField(20);
		box[3].setText(showValue(2, "yards", false));
		box[3].setEditable(false);
		box[3].setToolTipText(showValue(2, "", true));
		life.add(label[3]);
		life.add(box[3]);
		middle.add(life);
		
		a.add(middle, BorderLayout.CENTER);
		
		JPanel goalPanel = new JPanel();
		BoxLayout lo = new BoxLayout(goalPanel, 1);
		goalPanel.setLayout(lo);
		goalPanel.setOpaque(false);
		
		label[4] = new JLabel("Goals");
		goalPanel.add(label[4]);
		box[4] = new JTextField(10);
		box[4].setText(showGoals());
		box[4].setEditable(false);
		goalPanel.add(box[4]);
		button[4] = new JButton("Set Goal");
		button[4].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String s = JOptionPane.showInputDialog("Enter a new goal");
				setGoals(Integer.parseInt(s));
				box[4].setText(showGoals());
			}
		});
		goalPanel.add(button[4]);
		
		a.add(goalPanel, BorderLayout.EAST);
		return a;
	}

	//must be fixed to keep constant size
	/**
	 * Used to create carousel for different criteria in components (Daily, Record, Lifetime)
	 * @return
	 */
	private JPanel makeCarousel(){
		JPanel a = new JPanel();
		BorderLayout layout = new BorderLayout(0,0);
		a.setLayout(layout);

		label[0] = new JLabel("Carousel View");
		a.add(label[0], BorderLayout.NORTH);

		label[9] = new JLabel("Click to change View");
		a.add(label[9], BorderLayout.SOUTH);
		
		label[8] = new JLabel("n");
		a.add(label[8], BorderLayout.WEST);
		
		JPanel dataPanel = new JPanel();
		BoxLayout l = new BoxLayout(dataPanel, 1);
		dataPanel.setLayout(l);
		dataPanel.setOpaque(false);
		dataPanel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				Component source = (Component)e.getSource();
				source.getParent().dispatchEvent(e);
			}
		});
		
		box[1] = new JTextField(15);
		box[1].setEditable(false);
		box[1].addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				Component source = (Component)e.getSource();
				source.getParent().getParent().dispatchEvent(e);
			}
		});
		dataPanel.add(box[1]);
		
		box[2] = new JTextField(15);
		box[2].setEditable(false);
		box[2].addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				Component source = (Component)e.getSource();
				source.getParent().getParent().dispatchEvent(e);
			}
		});
		dataPanel.add(box[2]);
		
		box[3] = new JTextField(15);
		box[3].setEditable(false);
		box[3].addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				Component source = (Component)e.getSource();
				source.getParent().getParent().dispatchEvent(e);
			}
		});
		dataPanel.add(box[3]);
		changeView(0);
		
		a.add(dataPanel, BorderLayout.EAST);
		
		a.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				currentView = (currentView + 1) % 3;
				changeView(currentView);
			}
		});
		
		return a;
	}
	
	/**
	 * Make the labels for the components
	 * @return
	 */
	private JPanel makeTabbed(){
		JPanel a = new JPanel();
		JTabbedPane t = new JTabbedPane();
		Icon icon = new ImageIcon("arrrow.png", "Down right pointing arrow");
		JPanel dailyPanel = new JPanel();
		JPanel recordPanel = new JPanel();
		JPanel lifePanel = new JPanel();
		JPanel goalsPanel = new JPanel();
		
		label[1] = new JLabel("Daily");
		dailyPanel.add(label[1]);
		label[2] = new JLabel("Record");
		recordPanel.add(label[2]);
		label[3] = new JLabel("Lifetime");
		lifePanel.add(label[3]);
		label[4] = new JLabel("Goals");
		goalsPanel.add(label[4]);
		
		box[1] = new JTextField(10);
		box[1].setText(data[0] + " yards");
		box[1].setEditable(false);
		dailyPanel.add(box[1]);

		box[2] = new JTextField(10);
		box[2].setText(((int)(data[0] / YARDS_PER_MILE * 100) / 100.0) + " Miles");
		box[2].setEditable(false);
		dailyPanel.add(box[2]);

		box[3] = new JTextField(10);
		box[3].setText(((int)(data[0] / METERS_PER_YARD * 100) / 100.0) + " Meters");
		box[3].setEditable(false);
		dailyPanel.add(box[3]);

		box[4] = new JTextField(10);
		box[4].setText(data[1] + " yards");
		box[4].setEditable(false);
		recordPanel.add(box[4]);

		box[5] = new JTextField(10);
		box[5].setText(((int)(data[1] / YARDS_PER_MILE * 100) / 100.0) + " Miles");
		box[5].setEditable(false);
		recordPanel.add(box[5]);

		box[6] = new JTextField(10);
		box[6].setText(((int)(data[1] / METERS_PER_YARD * 100) / 100.0) + " Meters");
		box[6].setEditable(false);
		recordPanel.add(box[6]);

		box[7] = new JTextField(10);
		box[7].setText(data[2] + " yards");
		box[7].setEditable(false);
		lifePanel.add(box[7]);

		box[8] = new JTextField(10);
		box[8].setText(((int)(data[2] / YARDS_PER_MILE * 100) / 100.0) + " Miles");
		box[8].setEditable(false);
		lifePanel.add(box[8]);

		box[9] = new JTextField(10);
		box[9].setText(((long)(data[2] / (METERS_PER_YARD * 100)) / 100.0) + " Meters");// overflow error
		box[9].setEditable(false);
		lifePanel.add(box[9]);
		
		JLabel nothin = new JLabel("Nothing special for goals");
		goalsPanel.add(nothin);
		
		t.addTab("Daily", icon, dailyPanel);
		t.addTab("Record", icon, recordPanel);
		t.addTab("Lifetime", icon, lifePanel);
		t.addTab("Goals", icon, goalsPanel);
		a.add(t);
		return a;
	}	
      /**
       * makes a graph for time series data
       */
	private JPanel makeGraph(){
		this.setSize(500, 200);
		JPanel a = new JPanel();
		a.setSize(500, 200);
		a.setVisible(true);
		Graph g = new Graph();
		g.setSize(500,200);
		g.setVisible(true);
		a.add(g);
		
		
		return a;
	}
	
	/**
	 * Change view will set the containers data fields for display
	 * @param v The type of data
	 */
	private void changeView(int v) {
		panel.setBackground(color_set[v*2]); // glitch in startup
		label[8].setText(views[v]);
		box[1].setText(data[v] + " yards");
		box[2].setText(((int)(data[currentView] / YARDS_PER_MILE * 100)) / 100.0 + " Miles");
		box[3].setText(((int)(data[currentView] / METERS_PER_YARD * 100)) / 100.0 + " Meters");
	}
	
	  /**
	   * @return the user's goals
	   */
	private String showGoals() {
		String s = "";
		if (this.goals < 1){	s = "No current goals";	}
		else {	s = "" + this.goals;	}
		return s;
	}

	/**
	 * 
	 * @param type
	 * @param unit
	 * @param metric
	 * @return
	 */
	private String showValue(int type, String unit, boolean metric) {
		String s = "";
		if (metric == false){
			s += data[type] + " " + unit;
			s += ", or " + ((int)(data[type] / YARDS_PER_MILE * 100)) / 100.0 + " Miles";
		}else{
			s += ((int)(data[type] / METERS_PER_YARD * 100)) / 100.0;
			s += " Meters";
		}
		return s;
	}
    
	/**
	 * sets the user's goals
	 * @param x the value the goal
	 */
	private void setGoals(int x){
		this.goals = x;
	}
	
	/**
	 * 
	 * @param set
	 * @return
	 */
	private int[] get_data(int set){
		if (set == 1) {	return Widgets.base_array;	}
		if (set == 2) {	return Widgets.time_series;	}
		return null;
	}
}
