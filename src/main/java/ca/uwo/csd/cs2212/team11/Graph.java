package ca.uwo.csd.cs2212.team11; 

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ca.uwo.csd.cs2212.team11.SharedData.IDs;



/**
 * Class to initialize a graph object
 * @author Andrew Hall, Dara Amin
 *
 */
public class Graph extends javax.swing.JPanel {
	private int [] data;
	private IDs type;
	private int zoom = 1;
	private int offset = 0;
	private int spread = 6;
	private int step = 16;
	private int twentyFive, fifty, seventyFive;
	private int legend = 575;
	private static int graphWidth = 650;
	private static int graphHeight = 200;
	private int endOfGraph = 534;

	/**
	 * Attach all methods in JPanel to our object
	 */
	public Graph(IDs type){
		this.type = type;
		switch(type){
			case HEART_RATE:
				data = plot(getHRData());
				break;
			case CALORIES:
				data = plot(normalizeData(getCaloriesData()));
				break;
			case STEPS:
				data = plot(normalizeData(getStepsData()));
				break;
			case DISTANCE:
				data = plot(normalizeData(getDistanceData()));
				break;
			default:
				System.err.println("Error in graph creation " + type.name() + " is not recognized");
		}
	}
	/**
	 * Paint the line graph with canned data
	 * @param g 
	 */
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		this.setBackground(Color.WHITE);
		this.setSize(graphWidth, graphHeight);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		
		if (type == IDs.HEART_RATE)	{	paintHRGraph(g);	}
		else { paintGraph(this.type, g);	}
//		this.repaint();
		JPanel mouseListenerPanel = new JPanel();
		mouseListenerPanel.setSize(graphWidth, graphHeight);
		mouseListenerPanel.setOpaque(false);
		mouseListenerPanel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(e.getButton() == MouseEvent.BUTTON1){
					if (zoom < 16){
						updateOffset(e.getX(), zoom * 2);
						zoom = zoom * 2;
						step = 16 / zoom;
						//System.err.println("zoom is now " + zoom + "\tstep is now " + step);
					}
				}
				if(e.getButton() == MouseEvent.BUTTON3){
					if (zoom > 1){
						updateOffset(e.getX(), zoom / 2);
						zoom = zoom / 2;
						step = 16 / zoom;
						//System.err.println("zoom is now " + zoom + "\tstep is now " + step);
					}
				}
			}
		});
		this.add(mouseListenerPanel);
	}
	
	/**
	 * For testing purposes..
	 * @param args 
	 */
	public static void main (String[] args){
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Graph g = new Graph(IDs.CALORIES);
		frame.add(g);
		frame.setSize(graphWidth, graphHeight);
		frame.setVisible(true);

	}
	
	private void paintHRGraph(Graphics g){
		paintHRVerticleScale(g);
		
		int plotPoint = this.offset;
		int i = 0;
		while(i + step < data.length){
			if ( plotPoint >= 0 && plotPoint + spread <= endOfGraph){
				if ((i/60) != (i+step)/60){ // falls on hour check for scale markers
					if (zoom >= 8 || (i/60+1) %3 == 0){
						int fix = ((i+step) - ((i+step)/60) *60) * spread / step;
						g.setColor(Color.GRAY);
						g.drawLine(plotPoint + fix, graphHeight, plotPoint + fix, graphHeight - 50);
						String time = String.format("%02d:00", i/60 + 1);
						g.drawString(time, plotPoint + fix - 15, graphHeight - 5);
						if ((i/60+1) %12 == 0){
							g.setColor(Color.BLACK);
							g.drawLine(plotPoint + fix, 0, plotPoint + fix, graphHeight);
						}
					}
				}
				g.setColor(Color.RED);
				g.drawLine(plotPoint, graphHeight - data[i], plotPoint + spread, graphHeight- data[i + step]);
			}
			plotPoint += spread;
			i += step;
		}
		
		g.setColor(Color.BLACK);
		g.drawLine(endOfGraph, 0, endOfGraph, graphHeight);

	}
	
	private void paintHRVerticleScale(Graphics g){
        
		Graphics2D g2d = (Graphics2D) g.create();

        Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        
        //draw 68 bpm (base healthy resting HR)
        g2d.setColor(Color.CYAN);
        g2d.setStroke(dashed);
        g2d.drawLine(0, graphHeight-68, legend, graphHeight-68);
        g.drawString("68 bpm", legend, graphHeight-63);

        //draw maximum HR line
        g2d.setColor(Color.BLUE.darker());
        g2d.setStroke(dashed);
        g2d.drawLine(0, graphHeight-getMaxCardioHR(), legend, graphHeight-getMaxCardioHR());
        g.drawString(getMaxCardioHR() + " bpm", legend, graphHeight-(getMaxCardioHR()-5));

        //draw fat burn line
        g2d.setColor(Color.GREEN);
        g2d.setStroke(dashed);
        g2d.drawLine(0, graphHeight-getMaxRestingHR(), legend, graphHeight-getMaxRestingHR());
        g.drawString(getMaxRestingHR() + " bpm", legend, graphHeight-(getMaxRestingHR()-5));

        //draw cardio line
        g2d.setColor(Color.BLUE);
        g2d.setStroke(dashed);
        g2d.drawLine(0, graphHeight-getMaxFatBurnHR(), legend, graphHeight-getMaxFatBurnHR());
        g.drawString(getMaxFatBurnHR() + " bpm", legend, graphHeight-(getMaxFatBurnHR()-5));

        g.drawString("Peak", endOfGraph + 1, (graphHeight - getMaxCardioHR())/2 + 5);
        g.drawString("FatBurn", endOfGraph + 1, ((graphHeight-getMaxFatBurnHR()) + (graphHeight - getMaxRestingHR())) /2 + 5);
        g.drawString("Cardio", endOfGraph + 1, ((graphHeight-getMaxFatBurnHR()) + (graphHeight - getMaxCardioHR())) /2 + 5);
        g.drawString("Resting", endOfGraph + 1, graphHeight - (getMaxRestingHR() /2));
        //gets rid of the copy
        g2d.dispose();
	}
	
	private void paintGraph(IDs type, Graphics g){
		g.drawString(type.name(), 10, 20);
        
		Graphics2D g2d = (Graphics2D) g.create();

        Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        
        //draw scale lines at 25, 50 and 75%
        g2d.setColor(Color.CYAN);
        g2d.setStroke(dashed);
        g2d.drawLine(0, (int)(graphHeight * .75), graphWidth, (int)(graphHeight * .75));
        g2d.setColor(Color.GREEN);
        g2d.drawLine(0, (int)(graphHeight * .5), graphWidth, (int)(graphHeight * .5));
        g2d.setColor(Color.BLUE);
        g2d.drawLine(0, (int)(graphHeight * .25), graphWidth, (int)(graphHeight * .25));
        g2d.dispose();
        
        // label scale lines
        g.drawString(twentyFive + "", legend, (int)(graphHeight*.75));
        g.drawString(fifty + "", legend, (int)(graphHeight*.5));
        g.drawString(seventyFive + "", legend, (int)(graphHeight*.25));
        
		int plotPoint = this.offset;
		int i = 0;
		while(i + step < data.length){
			if ( plotPoint >= 0 && plotPoint + spread <= endOfGraph){
				if ((i/60) != (i+step)/60){ // falls on hour check for scale markers
					if (zoom >= 8 || (i/60+1) %3 == 0){
						int fix = ((i+step) - ((i+step)/60) *60) * spread / step;
						g.setColor(Color.GRAY);
						g.drawLine(plotPoint + fix, graphHeight, plotPoint + fix, graphHeight - 50);
						String time = String.format("%02d:00", i/60 + 1);
						g.drawString(time, plotPoint + fix - 15, graphHeight - 5);
						if ((i/60+1) %12 == 0){
							g.setColor(Color.BLACK);
							g.drawLine(plotPoint + fix, 0, plotPoint + fix, graphHeight);
						}
					}
				}
				g.setColor(Color.RED);
				g.drawLine(plotPoint, graphHeight - data[i], plotPoint + spread, graphHeight- data[i + step]);
			}
			plotPoint += spread;
			i += step;
		}
		
		g.setColor(Color.BLACK);
		g.drawLine(endOfGraph, 0, endOfGraph, graphHeight);
	}
	
	private double[] normalizeData(double[] array){
		double maxVal = 0;
		for (int i = 0; i < array.length; i++){
			if(array[i] > maxVal) {	maxVal = array[i];	}
		}
		maxVal = maxVal * 1.1;
		this.twentyFive = (int) Math.round(maxVal * 0.25);
		this.fifty = (int) Math.round(maxVal * 0.5);
		this.seventyFive = (int) Math.round(maxVal * 0.75);
		for (int i = 0; i < array.length; i++){
			array[i] = Math.round(array[i] / maxVal * 200);
		}
		return array;
	}
	
	private int[] plot(double[] array){
		int [] newArray = new int[array.length];
		for (int i = 0; i < array.length; i++){
			newArray[i] = (int)Math.round(array[i]);
		}
		return newArray;
	}
	
	
	private void updateOffset(int x, int newZoom){
		if (x > endOfGraph) {	x = endOfGraph;	}
		if (newZoom > this.zoom){		this.offset = this.offset - (x * newZoom/2); 	}
		if (newZoom < this.zoom){		this.offset = this.offset + (x * newZoom);	}
		if (newZoom == 1) {	this.offset = 0;	}
		//System.err.println("x is " + x +"\toffset resolves to " + offset);
	}
	
	
	private int getMaxRestingHR(){
		return 114;
	}
	
	private int getMaxFatBurnHR(){
		return 144;
	}
	
	private int getMaxCardioHR(){
		return 160;
	}
	
	private double[] getHRData(){
		return SharedData.newBigD;
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
