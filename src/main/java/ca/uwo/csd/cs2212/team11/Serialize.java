package ca.uwo.csd.cs2212.team11; 

import java.io.Serializable;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;


public class Serialize implements Serializable{
	
	public Serialize(){


	}
	
	/**
	 * serializes the data to file
	 * @pram obj an object of the clas whose data is to be serialized
	 * @pram fileName the file in which the data is to be saved
	 */
	public void writeObject(Object obj, String fileName){

		ObjectOutputStream out = null;

		try {
            
			XMLEncoder x = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(fileName)));
            x.writeObject(obj);
            x.close();
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
	
  }
	
	/**
	 * Decodes the binary file generated from the writeObject method above to human readable file
	 * @pram fileName the file that the method decodes
	 * 
	 */
  public XMLDecoder readObject(String fileName){

    XMLDecoder x;
        try {
            
            x = new XMLDecoder(new BufferedInputStream(new FileInputStream(fileName)));

            
        } catch (FileNotFoundException ex) {
            x = null;
            ex.printStackTrace();
        }

        return x;

  }


}

