package ca.uwo.csd.cs2212.team11;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ca.uwo.csd.cs2212.team11.SharedData.IDs;

public class CGraph extends JPanel{
	
	private IDs type;
	private int twentyFive, fifty, seventyFive;
	private int[] hourlyData = new int[24];
	private int[] qtrHourlyData = new int[96];
	private int legend = 575;
	private int endOfGraph = 534;
	private Graphics q;
	private boolean hourView = true;

	public CGraph(IDs type){
		this.type = type;
		switch(type){
		case CALORIES:

			prepData(getCaloriesData());
			break;
		case STEPS:
			prepData(getStepsData());
			break;
		case DISTANCE:
			prepData(getDistanceData());
			break;
		default:
			System.err.println("Error in graph creation " + type.name() + " is not recognized");
		}
		hourlyData = normalizeData(hourlyData);
		qtrHourlyData = normalizeData(qtrHourlyData);
	}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		this.q = g;
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(SharedData.GRAPH_WIDTH, SharedData.GRAPH_HEIGHT));
		this.setMinimumSize(new Dimension(SharedData.GRAPH_WIDTH, SharedData.GRAPH_HEIGHT));
		this.setMaximumSize(new Dimension(SharedData.GRAPH_WIDTH, SharedData.GRAPH_HEIGHT));
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		paintCGraph();
		
		JPanel mouseListenerPanel = new JPanel();
		mouseListenerPanel.setSize(SharedData.GRAPH_WIDTH, SharedData.GRAPH_HEIGHT);
		mouseListenerPanel.setOpaque(false);
		mouseListenerPanel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(e.getButton() == MouseEvent.BUTTON1){
					hourView = true;
				}
				if(e.getButton() == MouseEvent.BUTTON3){	
					hourView = false;
				}
			}
		});
		this.add(mouseListenerPanel);

	}
	
	
	
	public static void main (String[] args){
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CGraph g = new CGraph(IDs.CALORIES);
		frame.add(g);
		frame.setSize(SharedData.GRAPH_WIDTH, SharedData.GRAPH_HEIGHT);
		frame.setVisible(true);

	}
	
	private void paintCGraph(){
		if (hourView == true){ paintHourGraph();	}
		else {	paintQtrHourGraph();	}
	}
	
	private void paintHourGraph(){
		paintVerticleScale();
		q.setColor(Color.RED);
		for (int i = 0; i < 24; i++){
			if (i !=0 && i%12 == 0){
				q.setColor(Color.BLACK);
				q.drawLine(i*20 + 10, 0, i*20 + 10, SharedData.GRAPH_HEIGHT);
				q.setColor(Color.RED);
			}
			q.fillRect(i * 20 + 3, 200 - hourlyData[i], 15, hourlyData[i]);
		}
		repaint();
	}
	
	private void paintQtrHourGraph(){
		invalidate();
		paintVerticleScale();
		q.setColor(Color.RED);
		for (int i = 0; i < 96; i++){
			q.fillRect(i*5, 200 - qtrHourlyData[i], 3, qtrHourlyData[i]);
		}
		repaint();
	}
	
	private void paintVerticleScale(){
		q.drawString(type.name(), 10, 20);
		q.drawString("Noon", 235, 50);
        
		Graphics2D g2d = (Graphics2D) q.create();

        Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        
        //draw scale lines at 25, 50 and 75%
        g2d.setColor(Color.CYAN);
        g2d.setStroke(dashed);
        g2d.drawLine(0, (int)(SharedData.GRAPH_HEIGHT * .75), legend, (int)(SharedData.GRAPH_HEIGHT * .75));
        g2d.setColor(Color.GREEN);
        g2d.drawLine(0, (int)(SharedData.GRAPH_HEIGHT * .5), legend, (int)(SharedData.GRAPH_HEIGHT * .5));
        g2d.setColor(Color.BLUE);
        g2d.drawLine(0, (int)(SharedData.GRAPH_HEIGHT * .25), legend, (int)(SharedData.GRAPH_HEIGHT * .25));
        g2d.dispose();
        
        // label scale lines
        q.drawString(twentyFive + "", legend + 5, (int)(SharedData.GRAPH_HEIGHT * 0.75) + 5);
        q.drawString(fifty + "", legend + 5, (int)(SharedData.GRAPH_HEIGHT * 0.5) + 5);
        q.drawString(seventyFive + "", legend + 5, (int)(SharedData.GRAPH_HEIGHT * 0.25) + 5);
		q.setColor(Color.BLACK);
		q.drawLine(endOfGraph, 0, endOfGraph, SharedData.GRAPH_HEIGHT);
	}
	
	private void prepData(double[] array)
	{
		double hourAccumulator=0, qtrAccumulator=0;
		for (int i = 0; i < array.length; i++){
			hourAccumulator += array[i];
			qtrAccumulator += array[i];
			if(i%15 == 0){
				if (i/15 == 0){		qtrHourlyData[(i/15)] = (int)(qtrAccumulator);	}
				else { 	qtrHourlyData[(i/15)] = qtrHourlyData[(i/15)-1] + (int)(qtrAccumulator);	}
				qtrAccumulator -= (int)qtrAccumulator;
			}
			if(i%60 == 0){
				if (i/60 == 0) {	hourlyData[(i/60)] = (int)(hourAccumulator);	}
				else { hourlyData[(i/60)] = hourlyData[(i/60)-1] + (int)(hourAccumulator);	}
				hourAccumulator -= (int)hourAccumulator;
			}
		}
	}

	
	private int[] normalizeData(int[] array){
		double maxVal = (double)array[array.length-1];
		maxVal = maxVal * 1.1;
		this.twentyFive = (int) Math.round(maxVal * 0.25);
		this.fifty = (int) Math.round(maxVal * 0.5);
		this.seventyFive = (int) Math.round(maxVal * 0.75);
		for (int i = 0; i < array.length; i++){
			array[i] = (int)Math.round(array[i] / maxVal * 200);
		}
		return array;
	}
	
	private double[] getCaloriesData(){
		return SharedData.newBigD;
	}

	private double[] getStepsData(){
		return SharedData.newBigD;
	}
	
	private double[] getDistanceData(){
		return SharedData.newBigD;
	}
	
}
