/**
 * 
 */
package ca.uwo.csd.cs2212.team11;

import org.json.JSONObject;
import org.json.JSONArray;

import com.github.scribejava.core.*;
import com.github.scribejava.core.model.Response;

//import org.apache.http.util.EntityUtils;

/**
 * @author James Walsh
 *
 */
public class ResponseParser {
	
	public JSONObject parseResponse(Response rsp)
	{
		// JSONArray ja = new JSONArray(rsp.getBody());
		JSONObject jo = new JSONObject(rsp.getBody());
		return jo;
	}//end of method

} //end of class
