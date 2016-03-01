package ca.uwo.csd.cs2212.team11;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class PieChart extends javax.swing.JPanel {
	private int[] data = Widgets.time_series;
	private int sum;
	private int[] arc = new int[4];
	
	public PieChart(){
		sum = data[0]+data[1]+data[2]+data[3];
		arc[0] = 0;
		arc[1] = 360*data[1]/sum;
		arc[2] = 360*data[2]/sum;
		arc[3] = 360*data[3]/sum;
	}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		this.setBackground(Color.GRAY);
		this.setSize(350, 200);
		g.setColor(Color.WHITE);
		g.fillOval(1, 1, 199, 199);
		g.setColor(Color.CYAN);
		g.fillArc(0, 0, 200, 200, arc[0], arc[1]);
		g.setColor(Color.BLUE);
		g.fillArc(0, 0, 200, 200, arc[1], arc[2]);
		g.setColor(Color.BLUE.darker());
		g.fillArc(0, 0, 200, 200, arc[1] + arc[2], arc[3]);
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
		g.drawString("Light Activity", 250, 135);

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
		frame.setSize(250, 200);
		frame.setVisible(true);

	}

}
