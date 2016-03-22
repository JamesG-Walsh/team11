package ca.uwo.csd.cs2212.team11;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;

/**
 * PieChart is used to create a pie chart object and paint it ontop of JPanel
 * @author James
 *
 */
public class PieChart extends javax.swing.JPanel {
	private int[] data;
	private int sum;
	private int[] arc = new int[4];
	private int legend = 575;

	/**
	 * Construct with canned Data
	 */
	public PieChart(){
		this.setBackground(Color.GRAY);
		this.setPreferredSize(new Dimension (SharedData.GRAPH_WIDTH, SharedData.GRAPH_HEIGHT));
		data = getTimeData();
		sum = data[0]+data[1]+data[2]+data[3];
		arc[0] = 0;
		arc[1] = 360*data[1]/sum;
		arc[2] = 360*data[2]/sum;
		arc[3] = 360*data[3]/sum;
	}
	/**
	 * Set different portions of pie to different colors
	 */
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		this.setBackground(Color.GRAY);
		this.setSize(SharedData.GRAPH_WIDTH, SharedData.GRAPH_HEIGHT);
		g.setColor(Color.WHITE);
		g.fillOval(1, 1, 199, 199);
		g.setColor(Color.CYAN);
		g.fillArc(0, 0, 200, 200, arc[0], arc[1]);
		g.setColor(Color.BLUE);
		g.fillArc(0, 0, 200, 200, arc[1], arc[2]);
		g.setColor(Color.BLUE.darker());
		g.fillArc(0, 0, 200, 200, arc[1] + arc[2], arc[3]);
		
		g.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		g.setColor(Color.WHITE);
		g.fillRect(225, 50, 20, 20);
		g.setColor(Color.BLACK);
		g.drawRect(225, 50, 20, 20);
		g.drawString("Inactive", 250, 60);
		
		g.setColor(Color.CYAN);
		g.fillRect(225, 75, 20, 20);
		g.setColor(Color.BLACK);
		g.drawRect(225, 75, 20, 20);
		g.drawString("Light Activity", 250, 85);

		g.setColor(Color.BLUE);
		g.fillRect(225, 100, 20, 20);
		g.setColor(Color.BLACK);
		g.drawRect(225, 100, 20, 20);
		g.drawString("Med Activity", 250, 110);

		g.setColor(Color.BLUE.darker());
		g.fillRect(225, 125, 20, 20);
		g.setColor(Color.BLACK);
		g.drawRect(225, 125, 20, 20);
		g.drawString("Heavy Activity", 250, 135);

		this.repaint();
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PieChart p = new PieChart();
		frame.add(p);
		frame.setSize(SharedData.GRAPH_WIDTH, SharedData.GRAPH_HEIGHT);
		frame.setVisible(true);

	}
	
	private int[] getTimeData(){
		int [] times = new int[4];
		times[0] = getSedentaryMinutes();
		times[1] = getLightActivityMinutes();
		times[2] = getMediumActivityMinutes();
		times[3] = getHeavyActivityMinutes();
		return times;
	}

	private int getSedentaryMinutes(){
		return 237;
	}
	
	private int getLightActivityMinutes(){
		return 321;
	}
	
	private int getMediumActivityMinutes(){
		return 98;
	}
	
	private int getHeavyActivityMinutes(){
		return 12;
	}
}
