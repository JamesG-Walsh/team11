package ca.uwo.csd.cs2212.team11; 

import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JFrame;


public class Demo extends JFrame {

	private Widgets[] contents_array = new Widgets[4];
	
	public Demo(){
		super("Team 11 Widgets demo");
		Container pane = getContentPane();
		BoxLayout l = new BoxLayout(pane,1);
		pane.setLayout(l);
		this.setSize(800, 400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		for (int i = 0; i < this.contents_array.length; i++){
			this.contents_array[i] = new Widgets(i);
			pane.add(this.contents_array[i]);
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
			
		Demo GUI = new Demo();
		GUI.setVisible(true);
	
	}

}
