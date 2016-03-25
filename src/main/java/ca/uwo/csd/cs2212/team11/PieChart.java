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
	private int legend = 400;
	private int circleX = SharedData.GRAPH_WIDTH / 2 - 175;
	private int legendText = 475;
	private int legendBox = 25;
	private int legendLineOne = 25;
	private int legendLineTwo = 65;
	private int legendLineTre = 110;
	private int legendLineFor = 150;

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
		g.fillOval(circleX+1, 26, 149, 149);
		g.setColor(Color.CYAN);
		g.fillArc(circleX, 25, 150, 150, arc[0], arc[1]);
		g.setColor(Color.BLUE);
		g.fillArc(circleX, 25, 150, 150, arc[1], arc[2]);
		g.setColor(Color.BLUE.darker());
		g.fillArc(circleX, 25, 150, 150, arc[1] + arc[2], arc[3]);
		
		g.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		g.setColor(Color.WHITE);
		g.fillRect(legend, legendLineOne, legendBox * 2, legendBox);
		g.setColor(Color.BLACK);
		g.drawRect(legend, legendLineOne, legendBox * 2, legendBox);
		g.drawString("Inactive", legendText, legendLineOne + 20);
		
		g.setColor(Color.CYAN);
		g.fillRect(legend, legendLineTwo, legendBox * 2, legendBox);
		g.setColor(Color.BLACK);
		g.drawRect(legend, legendLineTwo, legendBox * 2, legendBox);
		g.drawString("Light Activity", legendText, legendLineTwo + 20);

		g.setColor(Color.BLUE);
		g.fillRect(legend, legendLineTre, legendBox *2, legendBox);
		g.setColor(Color.BLACK);
		g.drawRect(legend, legendLineTre, legendBox *2, legendBox);
		g.drawString("Med Activity", legendText, legendLineTre + 20);

		g.setColor(Color.BLUE.darker());
		g.fillRect(legend, legendLineFor, legendBox * 2, legendBox);
		g.setColor(Color.BLACK);
		g.drawRect(legend, legendLineFor, legendBox * 2, legendBox);
		g.drawString("Heavy Activity", legendText, legendLineFor + 20);

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

	/**
	*@return sedentary minutes 
	*/

	private int getSedentaryMinutes(){
		return 237;
	}

	/**
	*@return light active minutes
	*/
	
	private int getLightActivityMinutes(){
		return 321;
	}

	/**
	*@return medium active minutes
	*/
	
	private int getMediumActivityMinutes(){
		return 98;
	}

	/**
	*@return heavy active minutes
	*/
	
	private int getHeavyActivityMinutes(){
		return 12;
	}
}
