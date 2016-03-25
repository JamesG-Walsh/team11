package ca.uwo.csd.cs2212.team11;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * Class is used to paint an image on top of Swing components
 * @author James
 *
 */
public class ImagePanel extends JPanel {

	private BufferedImage image;
	private int height, width;
	
	
	/**
	 * class constructor
	 * @param source the path to the image file that will serve as the background
	 */
	public ImagePanel(String source){
		File f = new File(SharedData.PATH_TO_IMAGES + source);
		if (!f.exists()){
			f = new File(SharedData.ALT_PATH_TO_IMAGES + source);
		}
	       try {                
	           image = ImageIO.read(f);
		        this.height = image.getHeight();
		        this.width = image.getWidth();
	        } catch (IOException ex) {
	        }
	        this.setOpaque(false);
	}
	
	/**
	 * Draws the image on top of swing component
	 * @param g	
	 */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);       
    }

	/**
	 * Getter for height of the background picture
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Getter for width of the picture
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}
	/**
	 * For testing purposes...
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame f = new JFrame();
		ImagePanel i = new ImagePanel("./src/main/resources/imgs/Fitbit.png");
		i.setSize(200, 50);
		i.setVisible(true);
		f.setSize(300,100);
		f.setVisible(true);
		f.add(i);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
