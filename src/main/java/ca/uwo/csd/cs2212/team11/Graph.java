package ca.uwo.csd.cs2212.team11; 

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;



/**
 * Class to initialize a graph object
 * @author Andrew Hall, Dara Amin
 *
 */
public class Graph extends javax.swing.JPanel {
	private int[] data = Widgets.time_series;
	/**
	 * Attach all methods in JPanel to our object
	 */
	public Graph(){
		super();
	}
	/**
	 * Paint the line graph with canned data
	 * @param g 
	 */
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.GREEN);
		g.fillRect(0, (200-114) + 1, 500, (200-114));
		g.setColor(Color.CYAN);
		g.fillRect(0, (200-127) + 1, 500, (127-114));
		g.setColor(Color.BLUE);
		g.fillRect(0, (200-140) + 1, 500, (140-127));
		g.setColor(Color.ORANGE);
		g.fillRect(0, 0, 500, (200-140) + 1);
		g.setColor(Color.WHITE);
		for (int i = 0; i < data.length - 1; i++){
			g.drawLine(i * 10, data[i], (i+1)*10, data[i+1]);
		}
		this.repaint();
	}
	
	/**
	 * For testing purposes..
	 * @param args 
	 */
	public static void main (String[] args){
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Graph g = new Graph();
		frame.add(g);
		frame.setSize(500,200);
		frame.setVisible(true);
		frame.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(e.getButton() == MouseEvent.BUTTON1){
					System.out.println("Left Click at " + e.getX());
				}
				if(e.getButton() == MouseEvent.BUTTON3){
					System.out.println("Right Click at " + e.getX());
				}
			}
		});

	}
}
