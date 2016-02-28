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

