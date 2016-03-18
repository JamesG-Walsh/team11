package ca.uwo.csd.cs2212.team11;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.ImageIcon;


/**
 * Accolades used for accolades
 * @author Dara
 *
 */
public class Accolades {

	public static final String MILLY_IMG = "./src/main/resources/acc-Large/1million.jpg" ;
	

	public Accolades() {


	}

	public void recievedAccoladePopup(JFrame j, String img){


	  JLabel lbl = new JLabel(new ImageIcon(img));
	  JOptionPane.showMessageDialog(j, lbl, "thank you for using java", JOptionPane.PLAIN_MESSAGE, null);

	}

}
