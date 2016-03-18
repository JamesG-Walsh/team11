package ca.uwo.csd.cs2212.team11; 

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ca.uwo.csd.cs2212.team11.SharedData.IDs;



/**
 * Class to initialize a graph object
 * @author Andrew Hall, Dara Amin
 *
 */
public class Graph extends javax.swing.JPanel implements Serializable
{
	private int[] data;
	private IDs type;
	private int zoom = 1;
	private int offset = 0;
	private int spread = 4;
	private int twentyFive, fifty, seventyFive;
	private int legend = 400;
	private JPanel paintPanel;

	/**
	 * Attach all methods in JPanel to our object
	 */
	public Graph(IDs type){
		this.type = type;
		switch(type){
			case HEART_RATE:
				data = SharedData.time_series;
				break;
			case CALORIES:
				data = normalizeData(SharedData.dummyCalories);
				break;
			case STEPS:
				data = normalizeData(SharedData.dummySteps);	
				break;
			case DISTANCE:
				data = normalizeData(SharedData.dummyDistance);
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
		this.setPreferredSize(new Dimension(450, 200));
		this.setMinimumSize(new Dimension(450, 200));
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		
		if (type == IDs.HEART_RATE)	{	paintHRGraph(g);	}
		else { paintGraph(this.type, g);	}
//		this.repaint();
		JPanel mouseListenerPanel = new JPanel();
		mouseListenerPanel.setSize(450, 200);
		mouseListenerPanel.setOpaque(false);
		mouseListenerPanel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(e.getButton() == MouseEvent.BUTTON1){
					if (zoom < 16){
						updateOffset(e.getX(), zoom * 2);
						zoom = zoom * 2;
					}
				}
				if(e.getButton() == MouseEvent.BUTTON3){
					if (zoom > 1){
						updateOffset(e.getX(), zoom / 2);
						zoom = zoom / 2;
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
		Graph g = new Graph(IDs.HEART_RATE);
		frame.add(g);
		frame.setSize(500,200);
		frame.setVisible(true);

	}
	
	private void paintHRGraph(Graphics g){
		paintHRVerticleScale(g);
		g.setColor(Color.RED);
		for(int i = 0; i < data.length -1; i++){
			if ((i * spread * zoom) + offset < 0) {	continue;	}
			if (((i+1) * spread * zoom) + offset > 384) {	break;	}
			
			g.drawLine((i * spread * zoom) + offset, data[i], ((i+1) * spread * zoom) + offset, data[i+1]);
			if ((i % 12) == 0 || ((zoom >= 8) && (i%4 == 0))){
				g.setColor(Color.GRAY);
				g.drawLine((i * spread * zoom) + offset, 200, (i * spread * zoom) + offset, 150);
				String time = String.format("%02d:00", i/4);
				g.drawString(time, (i*zoom*spread)+offset - 15, 195);
				if ((i % 48) == 0){
					g.setColor(Color.BLACK);
					g.drawLine((i * spread * zoom) + offset, 200, (i * spread * zoom) + offset, 0);
				}
				g.setColor(Color.RED);
			}
			g.drawLine((i * spread * zoom) + offset, data[i], ((i+1) * spread * zoom) + offset, data[i+1]);
		}
		g.setColor(Color.BLACK);
		g.drawLine(384, 200, 384, 0);

	}
	
	private void paintHRVerticleScale(Graphics g){
		int maxHR = getUsersMaxHR();
        
		Graphics2D g2d = (Graphics2D) g.create();

        Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        
        //draw 68 bpm (base healthy resting HR)
        g2d.setColor(Color.CYAN);
        g2d.setStroke(dashed);
        g2d.drawLine(0, 200-68, 450, 200-68);
        g.drawString("68 bpm", legend, 200-70);

        //draw maximum HR line
        g2d.setColor(Color.BLUE.darker());
        g2d.setStroke(dashed);
        g2d.drawLine(0, 200-maxHR, 450, 200-maxHR);
        g.drawString("Max HR", legend, 200-maxHR);
        g.drawString(maxHR + " bpm", legend, 200-(maxHR-10));

        //draw fat burn line
        g2d.setColor(Color.GREEN);
        g2d.setStroke(dashed);
        g2d.drawLine(0, 200-((int)(maxHR*.65)), 450, 200-((int)(maxHR*.65)));
        g.drawString("FatBurn", legend, 200-((int)(maxHR*.65)));
        g.drawString(((int)(maxHR*.65)) + " bpm", legend, 200-(((int)(maxHR*.65))-10));

        //draw cardio line
        g2d.setColor(Color.BLUE);
        g2d.setStroke(dashed);
        g2d.drawLine(0, 200-((int)(maxHR*.8)), 450, 200-((int)(maxHR*.8)));
        g.drawString("Cardio", legend, 200-((int)(maxHR*.8)));
        g.drawString((int)(maxHR*.8) + " bpm", legend, 200-((int)(maxHR*.8)-10));

        
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
        g2d.drawLine(0, 150, 450, 150);
        g2d.setColor(Color.GREEN);
        g2d.drawLine(0, 100, 450, 100);
        g2d.setColor(Color.BLUE);
        g2d.drawLine(0, 50, 450, 50);
        g2d.dispose();
        
        // label scale lines
        g.drawString(twentyFive + "", legend, 150);
        g.drawString(fifty + "", legend, 100);
        g.drawString(seventyFive + "", legend, 50);
        
		g.setColor(Color.RED);
		for(int i = 0; i < data.length -1; i++){
			if ((i * spread * zoom) + offset < 0) {	continue;	}
			if (((i+1) * spread * zoom) + offset > 384) {	break;	}
			if ((i % 12) == 0 || ((zoom >= 8) && (i%4 == 0))){
				g.setColor(Color.GRAY);
				g.drawLine((i * spread * zoom) + offset, 200, (i * spread * zoom) + offset, 150);
				String time = String.format("%02d:00", i/4);
				g.drawString(time, (i*zoom*spread)+offset - 15, 195);
				if ((i % 48) == 0){
					g.setColor(Color.BLACK);
					g.drawLine((i * spread * zoom) + offset, 200, (i * spread * zoom) + offset, 0);
				}
				g.setColor(Color.RED);
			}
			g.drawLine((i * spread * zoom) + offset, data[i], ((i+1) * spread * zoom) + offset, data[i+1]);
		}
		g.setColor(Color.BLACK);
		g.drawLine(384, 200, 384, 0);
	}
	
	private int[] normalizeData(int[] array){
		double maxVal = 0;
		for (int i = 0; i < array.length; i++){
			if(array[i] > maxVal) {	maxVal = array[i];	}
		}
		this.twentyFive = (int) Math.round(maxVal * 0.25);
		this.fifty = (int) Math.round(maxVal * 0.5);
		this.seventyFive = (int) Math.round(maxVal * 0.75);
		maxVal = maxVal * 1.1;
		for (int i = 0; i < array.length; i++){
			array[i] = (int)Math.round(array[i] / maxVal * 200);
		}
		return array;
	}
	
	private void updateOffset(int x, int newZoom){
		// find which data point is at center of zoom rounds down
		int element = (int) ((x - this.offset) / (this.spread * this.zoom));
		this.offset = 192 - (element * this.spread * newZoom);
		
		//keep offset to either start or end at an edge
		if (this.offset > 0) { this.offset = 0;	}
		if ((this.offset + (data.length * this.spread * newZoom)) < 384){
			this.offset = 384 - (data.length * this.spread * newZoom);
		}
	}
	
	private int getUsersMaxHR(){
		return 180;
	}
	/**
	 * Converts per-minute fitness data to total-so-far fitness data for graphical display.
	 */ 
	private void dataToCumulative() {
		int numPoints = data.length;
		if ( numPoints > 0 ) {
			int[] dataCum = new int[numPoints]; /* cumulative values of data */
			dataCum[0] = data[0];
			for ( int i = 1; i < numPoints; i++) {
				dataCum[i] = dataCum[i-1] + data[i];
			}
			data = dataCum; 
		}
	}
}
