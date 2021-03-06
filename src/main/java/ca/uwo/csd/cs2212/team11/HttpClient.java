package ca.uwo.csd.cs2212.team11;
import java.io.BufferedReader;
import ca.uwo.csd.cs2212.team11.RateLimitExceededException;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import com.github.scribejava.apis.FitbitApi20;
import com.github.scribejava.core.builder.*;
import com.github.scribejava.core.oauth.OAuthService;
import com.github.scribejava.core.model.*; //Request Verb
import com.github.scribejava.core.oauth.OAuth20Service;
import com.github.scribejava.apis.service.FitbitOAuth20ServiceImpl;

import java.awt.Desktop;
import java.net.URI;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;


/**
 * @author Beth Locke, James Walsh
 * Class that contains methods for making requests to the fitbit API and returning the resultant JSONObjects
 * getSpecificData() is mostly copied from the AppRefreshTokens.java file that Beth Locke provided on confluence
 */
public class HttpClient
{
	private static String CALL_BACK_URI="http://localhost:8080";
	private static int CALL_BACK_PORT=8080;

	//public String URL;

	/**
	 * 
	 * @param urlSuffix describes the data to be requested as specified by the fitbit api
	 * @return
	 * @throws RateLimitExceededException
	 */
	public static JSONObject getSpecificData(String urlSuffix) throws RateLimitExceededException
	{
		//read credentials from a file
		BufferedReader bufferedReader=null;
		// This will reference one line at a time
		String line = null;

		//Need to save service credentials for Fitbit
		String apiKey = null;      
		String apiSecret = null;
		String clientID = null;

		//holder for all the elements we will need to make an access token ( information about an authenticated session )
		String accessTokenItself =  null;
		String tokenType = null;
		String refreshToken = null;
		Long expiresIn = null;
		String rawResponse = null;

		//This is the only scope you have access to currently
		String scope = "activity%20heartrate";
		try {
			// File with service credentials.
			FileReader fileReader =
					new FileReader("Team11Credentials.txt");       
			bufferedReader = new BufferedReader(fileReader);
			clientID= bufferedReader.readLine();
			apiKey= bufferedReader.readLine();
			apiSecret = bufferedReader.readLine();
			bufferedReader.close();

			String workingDir = System.getProperty("user.dir");
			//System.out.println("Current working directory : " + workingDir);
			fileReader = new FileReader("./src/main/resources/Team11Tokens.txt");

			bufferedReader = new BufferedReader(fileReader);

			accessTokenItself = bufferedReader.readLine();
			tokenType = bufferedReader.readLine();
			refreshToken = bufferedReader.readLine();
			expiresIn = Long.parseLong(bufferedReader.readLine());
			rawResponse = bufferedReader.readLine();

		}
		catch(FileNotFoundException ex) {		
			System.exit(1);
		}
		catch(IOException ex) {
			
			//System.out.println("Unable to open file\n"+ex.getMessage());
			System.exit(1);
		}
		finally{
			try{
				if (bufferedReader!=null)
					// Always close files.
					bufferedReader.close(); 
			}
			catch(Exception e){
				//System.out.println("Error closing file\n"+e.getMessage()); 
			}
		}
		//  Create the Fitbit service - you will ask this to ask for access/refresh pairs
		//     and to add authorization information to the requests to the API
		FitbitOAuth20ServiceImpl service = (FitbitOAuth20ServiceImpl) new ServiceBuilder()
		.apiKey(clientID)       //fitbit uses the clientID here
		.apiSecret(apiSecret)
		.callback("http://localhost:8080")
		.scope(scope)
		.grantType("authorization_code")
		.build(FitbitApi20.instance());

		//  The access token contains everything you will need to authenticate your requests
		//  It can expire - at which point you will use the refresh token to refresh it
		//  See: https://dev.fitbit.com/docs/oauth2/#refreshing-tokens
		//    I have authenticated and given you the contents of the response to use
		OAuth2AccessToken accessToken = new OAuth2AccessToken(
				accessTokenItself,
				tokenType,
				refreshToken,
				expiresIn,
				rawResponse);
		// Now let's go and ask for a protected resource!
		//System.out.println("Now we're going to access a protected resource...");
		//System.out.println();
		//Example request:
		//    This is always the prefix (for my account)
		
		//String requestUrlPrefix = "https://api.fitbit.com/1/user/-/";
		String requestUrlPrefix = "https://api.fitbit.com/1/user/3WGW2P/";
		
		String requestUrl;
		//    The URL from this point is how you ask for different information
		requestUrl = requestUrlPrefix + urlSuffix;
		// This actually generates an HTTP request from the URL
		//    -it has a header, body etc.
		OAuthRequest request = new OAuthRequest(Verb.GET, requestUrl, service);

		// This adds the information required by Fitbit to add the authorization information to the HTTP request
		// You must do this before the request will work
		// See: https://dev.fitbit.com/docs/oauth2/#making-requests
		service.signRequest(accessToken, request);
		//  If you are curious
		//System.out.println(request.toString());
		//System.out.println(request.getHeaders());
		//System.out.println(request.getBodyContents());


		//  This actually sends the request:
		Response response = request.send();

		//  The HTTP response from fitbit will be in HTTP format, meaning that it has a numeric code indicating
		//     whether is was successful (200) or not (400's or 500's), each code has a different meaning
		//System.out.println();
		//System.out.println("HTTP response code: "+response.getCode());
		int statusCode = response.getCode();

		JSONObject jo = null;
		
		//System.out.println("-----");
		switch(statusCode)
		{
		case 200:
			//System.out.println("Success!");
			//System.out.println("HTTP response body:\n"+response.getBody());

			try 
			{
				jo = new JSONObject(response.getBody());
			}
			catch (JSONException e1)
			{

				//System.out.println("JSON Exception when generating JSONObject from successful request response body.  Null JSONObject returned.");
				//e1.printStackTrace();
			}

			break;
		case 400:
			//System.out.println("Bad Request - may have to talk to Beth (or bad URL in request)");
			//System.out.println("HTTP response body:\n"+response.getBody());
			break;
		case 401:
			//System.out.println("Likely Expired Token");
			//System.out.println("HTTP response body:\n"+response.getBody()); 
			//System.out.println("Try to refresh");

			// This uses the refresh token to get a completely new accessToken object
			//   See:  https://dev.fitbit.com/docs/oauth2/#refreshing-tokens           
			// This accessToken is now the current one, and the old ones will not work
			//   again.  You should save the contents of accessToken.
			accessToken = service.refreshOAuth2AccessToken(accessToken);

			// Now we can try to access the service again
			// Make sure you create a new OAuthRequest object each time!
			request = new OAuthRequest(Verb.GET, requestUrl, service);
			service.signRequest(accessToken, request);
			response = request.send();
			
			try 
			{
				jo = new JSONObject(response.getBody());
			}
			catch (JSONException e1) 
			{
				//System.out.println("JSON Exception when generating JSONObject from response body.  Null JSONObject to be returned;");
				//e1.printStackTrace();
			}

			// Hopefully got a response this time:
			//System.out.println("HTTP response code: "+response.getCode());
			//System.out.println("HTTP response body:\n"+response.getBody());
			break;
		case 429:
			//System.out.println("Rate limit exceeded");
			//System.out.println("HTTP response body:\n"+response.getBody());
			throw new RateLimitExceededException();
		default:
			//System.out.println("HTTP response code: "+response.getCode());
			//System.out.println("HTTP response body:\n"+response.getBody());
		}

		BufferedWriter bufferedWriter=null;
		//  Save the current accessToken information for next time

		// IF YOU DO NOT SAVE THE CURRENTLY ACTIVE TOKEN INFO YOU WILL NOT BE ABLE TO REFRESH
		//   - contact Beth if this happens and she can reissue you a fresh set

		try 
		{
			FileWriter fileWriter; 
			fileWriter =
					new FileWriter("./src/main/resources/Team11Tokens.txt");
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(accessToken.getToken());
			bufferedWriter.newLine();
			bufferedWriter.write(accessToken.getTokenType());
			bufferedWriter.newLine();
			bufferedWriter.write(accessToken.getRefreshToken());
			bufferedWriter.newLine();
			bufferedWriter.write(accessToken.getExpiresIn().toString() );
			bufferedWriter.newLine();
			bufferedWriter.write(accessToken.getRawResponse());
			bufferedWriter.newLine();
			bufferedWriter.close();
		}
		catch(FileNotFoundException ex) {

			//System.out.println("Unable to open file\n"+ex.getMessage());               
		}
		catch(IOException ex) {
			//System.out.println("Error reading/write file\n"+ex.getMessage());                 
		}
		finally
		{
			try
			{
				if (bufferedWriter!=null)
					bufferedWriter.close(); 
			}
			catch(Exception e)
			{

			}
		}//end try
		return jo;
	}//end of method

	/**
	 * makes request for a specified daily total (calories, steps etc.) as specified by fitbit api
	 * @param activity
	 * @param date
	 * @return
	 * @throws RateLimitExceededException
	 */
	public static JSONObject getSpecificDataDailyTotal(String activity, String date) throws RateLimitExceededException
	{
		return HttpClient.getSpecificData("activities/tracker/" + activity + "/date/" + date +"/1d.json");
	}

	/**
	 * makes server request for by the minute data as specified by fitbit api
	 * @param activity
	 * @param date
	 * @param interval
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws RateLimitExceededException
	 */
	public static JSONObject getSpecificDataByTheMin(String activity, String date, String interval, String startTime, String endTime) throws RateLimitExceededException
	{
		return HttpClient.getSpecificData("activities/" + activity + "/date/" + date +"/1d/"+ interval +"/time/" + startTime + "/" +endTime + ".json");
	}
	
	/**
	 * makes server request for lifetime totals and best days as specified by fitbit api
	 * @return
	 * @throws RateLimitExceededException
	 */
	public static JSONObject getLifetimeAndBestDays() throws RateLimitExceededException
	{
		return HttpClient.getSpecificData("activities.json");
	}
	
	/**
	 * makes server request for heart rate data as specified by fitbit api
	 * @param date
	 * @return
	 * @throws RateLimitExceededException
	 */
	public static JSONObject getHeartRateZones(String date) throws RateLimitExceededException
	{
		return HttpClient.getSpecificData("activities/heart/date/" + date +"/1d/1min.json");
	}

	/**
	 * makes server request for heart rate data as specified by fitbit api
	 * @return
	 * @throws RateLimitExceededException
	 *//*
	public static JSONObject getTodaysHeartRateZones() throws RateLimitExceededException
	{
		
		return HttpClient.getSpecificData("activities/heart/date/today/1d/1min.json");
	}*/
	
	
	
	
/*
	*//**
	 * public method that takes Strings to build a URL for making a request to activities/tracker for a daily total and returns the resultant JSONObject
	 * @param activity 	the activity field for the URL for the request (eg. "calories", "minutesSedentary" etc.)
	 * @param date		the date part of the URL for the request ( "yyyy-mm-dd" eg. "2016-03-25")
	 * @return jo		the JSONObject that is returned by the request
	 * @throws JSONException
	 *//*
	public static JSONObject getSpecificDataDailyTotalOrig(String activity, String date) throws JSONException
	{
		//read credentials from a file
		BufferedReader bufferedReader=null;
		// This will reference one line at a time
		String line = null;

		//Need to save service credentials for Fitbit
		String apiKey = null;      
		String apiSecret = null;
		String clientID = null;

		//holder for all the elements we will need to make an access token ( information about an authenticated session )
		String accessTokenItself =  null;
		String tokenType = null;
		String refreshToken = null;
		Long expiresIn = null;
		String rawResponse = null;

		//This is the only scope you have access to currently
		String scope = "activity%20heartrate";
		try {
			// File with service credentials.
			FileReader fileReader =
					new FileReader("Team11Credentials.txt");       
			bufferedReader = new BufferedReader(fileReader);
			clientID= bufferedReader.readLine();
			apiKey= bufferedReader.readLine();
			apiSecret = bufferedReader.readLine();
			bufferedReader.close();

			String workingDir = System.getProperty("user.dir");
			System.out.println("Current working directory : " + workingDir);
			fileReader = new FileReader("./src/main/resources/Team11Tokens.txt");

			bufferedReader = new BufferedReader(fileReader);

			accessTokenItself = bufferedReader.readLine();
			tokenType = bufferedReader.readLine();
			refreshToken = bufferedReader.readLine();
			expiresIn = Long.parseLong(bufferedReader.readLine());
			rawResponse = bufferedReader.readLine();

		}
		catch(FileNotFoundException ex) {
			System.out.println(
					"Unable to open file\n"+ex.getMessage());
			System.exit(1);
		}
		catch(IOException ex) {
			System.out.println(
					"Error reading/write file\n"+ex.getMessage());  
			System.exit(1);
		}
		finally{
			try{
				if (bufferedReader!=null)
					// Always close files.
					bufferedReader.close(); 
			}
			catch(Exception e){
				System.out.println(
						"Error closing file\n"+e.getMessage()); 
			}
		}
		//  Create the Fitbit service - you will ask this to ask for access/refresh pairs
		//     and to add authorization information to the requests to the API
		FitbitOAuth20ServiceImpl service = (FitbitOAuth20ServiceImpl) new ServiceBuilder()
		.apiKey(clientID)       //fitbit uses the clientID here
		.apiSecret(apiSecret)
		.callback("http://localhost:8080")
		.scope(scope)
		.grantType("authorization_code")
		.build(FitbitApi20.instance());

		//  The access token contains everything you will need to authenticate your requests
		//  It can expire - at which point you will use the refresh token to refresh it
		//  See: https://dev.fitbit.com/docs/oauth2/#refreshing-tokens
		//    I have authenticated and given you the contents of the response to use
		OAuth2AccessToken accessToken = new OAuth2AccessToken(
				accessTokenItself,
				tokenType,
				refreshToken,
				expiresIn,
				rawResponse);
		// Now let's go and ask for a protected resource!
		System.out.println("Now we're going to access a protected resource...");
		System.out.println();
		//Example request:
		//    This is always the prefix (for my account)
		String requestUrlPrefix = "https://api.fitbit.com/1/user/3WGW2P/";
		String requestUrl;
		//    The URL from this point is how you ask for different information
		requestUrl = requestUrlPrefix + "activities/tracker/" + activity + "/date/" + date +"/1d.json";
		// This actually generates an HTTP request from the URL
		//    -it has a header, body etc.
		OAuthRequest request = new OAuthRequest(Verb.GET, requestUrl, service);

		// This adds the information required by Fitbit to add the authorization information to the HTTP request
		// You must do this before the request will work
		// See: https://dev.fitbit.com/docs/oauth2/#making-requests
		service.signRequest(accessToken, request);
		//  If you are curious
		System.out.println(request.toString());
		System.out.println(request.getHeaders());
		System.out.println(request.getBodyContents());


		//  This actually sends the request:
		Response response = request.send();

		//  The HTTP response from fitbit will be in HTTP format, meaning that it has a numeric code indicating
		//     whether is was successful (200) or not (400's or 500's), each code has a different meaning
		System.out.println();
		System.out.println("HTTP response code: "+response.getCode());
		int statusCode = response.getCode();

		JSONObject jo = null;
		switch(statusCode){
		case 200:
			System.out.println("Success!");
			System.out.println("HTTP response body:\n"+response.getBody());

			jo = new JSONObject(response.getBody());

			break;
		case 400:
			System.out.println("Bad Request - may have to talk to Beth");
			System.out.println("HTTP response body:\n"+response.getBody());
			break;
		case 401:
			System.out.println("Likely Expired Token");
			System.out.println("HTTP response body:\n"+response.getBody()); 
			System.out.println("Try to refresh");

			// This uses the refresh token to get a completely new accessToken object
			//   See:  https://dev.fitbit.com/docs/oauth2/#refreshing-tokens           
			// This accessToken is now the current one, and the old ones will not work
			//   again.  You should save the contents of accessToken.
			accessToken = service.refreshOAuth2AccessToken(accessToken);

			// Now we can try to access the service again
			// Make sure you create a new OAuthRequest object each time!
			request = new OAuthRequest(Verb.GET, requestUrl, service);
			service.signRequest(accessToken, request);
			response = request.send();

			// Hopefully got a response this time:
			System.out.println("HTTP response code: "+response.getCode());
			System.out.println("HTTP response body:\n"+response.getBody());
			break;
		case 429:
			System.out.println("Rate limit exceedgited");
			System.out.println("HTTP response body:\n"+response.getBody());
			break;
		default:
			System.out.println("HTTP response code: "+response.getCode());
			System.out.println("HTTP response body:\n"+response.getBody());
		}

		BufferedWriter bufferedWriter=null;
		//  Save the current accessToken information for next time

		// IF YOU DO NOT SAVE THE CURRENTLY ACTIVE TOKEN INFO YOU WILL NOT BE ABLE TO REFRESH
		//   - contact Beth if this happens and she can reissue you a fresh set

		try {
			FileWriter fileWriter; 
			fileWriter =
					new FileWriter("./src/main/resources/Team11Tokens.txt");
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(accessToken.getToken());
			bufferedWriter.newLine();
			bufferedWriter.write(accessToken.getTokenType());
			bufferedWriter.newLine();
			bufferedWriter.write(accessToken.getRefreshToken());
			bufferedWriter.newLine();
			bufferedWriter.write(accessToken.getExpiresIn().toString() );
			bufferedWriter.newLine();
			bufferedWriter.write(accessToken.getRawResponse());
			bufferedWriter.newLine();
			bufferedWriter.close();
		}
		catch(FileNotFoundException ex) {
			System.out.println(
					"Unable to open file\n"+ex.getMessage());               
		}
		catch(IOException ex) {
			System.out.println(
					"Error reading/write file\n"+ex.getMessage());                 
		}
		finally{
			try{
				if (bufferedWriter!=null)
					bufferedWriter.close(); 
			}
			catch(Exception e){
				System.out.println(
						"Error closing file\n"+e.getMessage()); 
			}
		}//end try
		return jo;
	}//end of method
 
	
	*//**
	 * public method that takes Strings to build a URL for making a by the minute request to activities/ and returns the resultant JSONObject
	 * @param activity		the activity field for the URL for the request (eg. "calories", "minutesSedentary" etc.)
	 * @param date			the date part of the URL for the request ("yyyy-mm-dd" eg. "2016-03-25")
	 * @param interval		the resolution of the data (eg "1d", "15min", "1min")
	 * @param startTime		the start of the time interval(inclusive)
	 * @param endTime		the end of the time interval (inclusive)
	 * @return				the JSONObject that is returned by the request
	 * @throws JSONException
	 *//*
	public static JSONObject getSpecificDataByTheMinOrig(String activity, String date, String interval, String startTime, String endTime) throws JSONException
	{
		//read credentials from a file
		BufferedReader bufferedReader=null;
		// This will reference one line at a time
		String line = null;

		//Need to save service credentials for Fitbit
		String apiKey = null;      
		String apiSecret = null;
		String clientID = null;

		//holder for all the elements we will need to make an access token ( information about an authenticated session )
		String accessTokenItself =  null;
		String tokenType = null;
		String refreshToken = null;
		Long expiresIn = null;
		String rawResponse = null;

		//This is the only scope you have access to currently
		String scope = "activity%20heartrate";
		try {
			// File with service credentials.
			FileReader fileReader =
					new FileReader("Team11Credentials.txt");       
			bufferedReader = new BufferedReader(fileReader);
			clientID= bufferedReader.readLine();
			apiKey= bufferedReader.readLine();
			apiSecret = bufferedReader.readLine();
			bufferedReader.close();

			String workingDir = System.getProperty("user.dir");
			System.out.println("Current working directory : " + workingDir);
			fileReader = new FileReader("./src/main/resources/Team11Tokens.txt");

			bufferedReader = new BufferedReader(fileReader);

			accessTokenItself = bufferedReader.readLine();
			tokenType = bufferedReader.readLine();
			refreshToken = bufferedReader.readLine();
			expiresIn = Long.parseLong(bufferedReader.readLine());
			rawResponse = bufferedReader.readLine();

		}
		catch(FileNotFoundException ex) {
			System.out.println(
					"Unable to open file\n"+ex.getMessage());
			System.exit(1);
		}
		catch(IOException ex) {
			System.out.println(
					"Error reading/write file\n"+ex.getMessage());  
			System.exit(1);
		}
		finally{
			try{
				if (bufferedReader!=null)
					// Always close files.
					bufferedReader.close(); 
			}
			catch(Exception e){
				System.out.println(
						"Error closing file\n"+e.getMessage()); 
			}
		}
		//  Create the Fitbit service - you will ask this to ask for access/refresh pairs
		//     and to add authorization information to the requests to the API
		FitbitOAuth20ServiceImpl service = (FitbitOAuth20ServiceImpl) new ServiceBuilder()
		.apiKey(clientID)       //fitbit uses the clientID here
		.apiSecret(apiSecret)
		.callback("http://localhost:8080")
		.scope(scope)
		.grantType("authorization_code")
		.build(FitbitApi20.instance());

		//  The access token contains everything you will need to authenticate your requests
		//  It can expire - at which point you will use the refresh token to refresh it
		//  See: https://dev.fitbit.com/docs/oauth2/#refreshing-tokens
		//    I have authenticated and given you the contents of the response to use
		OAuth2AccessToken accessToken = new OAuth2AccessToken(
				accessTokenItself,
				tokenType,
				refreshToken,
				expiresIn,
				rawResponse);
		// Now let's go and ask for a protected resource!
		System.out.println("Now we're going to access a protected resource...");
		System.out.println();
		//Example request:
		//    This is always the prefix (for my account)
		String requestUrlPrefix = "https://api.fitbit.com/1/user/3WGW2P/";
		String requestUrl;
		//    The URL from this point is how you ask for different information
		requestUrl = requestUrlPrefix + "activities/" + activity + "/date/" + date +"/1d/"+ interval +"/time/" + startTime + "/" +endTime + ".json";
		// This actually generates an HTTP request from the URL
		//    -it has a header, body ect.
		OAuthRequest request = new OAuthRequest(Verb.GET, requestUrl, service);

		// This adds the information required by Fitbit to add the authorization information to the HTTP request
		// You must do this before the request will work
		// See: https://dev.fitbit.com/docs/oauth2/#making-requests
		service.signRequest(accessToken, request);
		//  If you are curious
		System.out.println(request.toString());
		System.out.println(request.getHeaders());
		System.out.println(request.getBodyContents());


		//  This actually sends the request:
		Response response = request.send();

		//  The HTTP response from fitbit will be in HTTP format, meaning that it has a numeric code indicating
		//     whether is was successful (200) or not (400's or 500's), each code has a different meaning
		System.out.println();
		System.out.println("HTTP response code: "+response.getCode());
		int statusCode = response.getCode();

		JSONObject jo = null;
		switch(statusCode){
		case 200:
			System.out.println("Success!");
			System.out.println("HTTP response body:\n"+response.getBody());

			jo = new JSONObject(response.getBody());

			break;
		case 400:
			System.out.println("Bad Request - may have to talk to Beth");
			System.out.println("HTTP response body:\n"+response.getBody());
			break;
		case 401:
			System.out.println("Likely Expired Token");
			System.out.println("HTTP response body:\n"+response.getBody()); 
			System.out.println("Try to refresh");

			// This uses the refresh token to get a completely new accessToken object
			//   See:  https://dev.fitbit.com/docs/oauth2/#refreshing-tokens           
			// This accessToken is now the current one, and the old ones will not work
			//   again.  You should save the contents of accessToken.
			accessToken = service.refreshOAuth2AccessToken(accessToken);

			// Now we can try to access the service again
			// Make sure you create a new OAuthRequest object each time!
			request = new OAuthRequest(Verb.GET, requestUrl, service);
			service.signRequest(accessToken, request);
			response = request.send();

			// Hopefully got a response this time:
			System.out.println("HTTP response code: "+response.getCode());
			System.out.println("HTTP response body:\n"+response.getBody());
			break;
		case 429:
			System.out.println("Rate limit exceedgited");
			System.out.println("HTTP response body:\n"+response.getBody());
			break;
		default:
			System.out.println("HTTP response code: "+response.getCode());
			System.out.println("HTTP response body:\n"+response.getBody());
		}

		BufferedWriter bufferedWriter=null;
		//  Save the current accessToken information for next time

		// IF YOU DO NOT SAVE THE CURRENTLY ACTIVE TOKEN INFO YOU WILL NOT BE ABLE TO REFRESH
		//   - contact Beth if this happens and she can reissue you a fresh set

		try {
			FileWriter fileWriter; 
			fileWriter =
					new FileWriter("./src/main/resources/Team11Tokens.txt");
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(accessToken.getToken());
			bufferedWriter.newLine();
			bufferedWriter.write(accessToken.getTokenType());
			bufferedWriter.newLine();
			bufferedWriter.write(accessToken.getRefreshToken());
			bufferedWriter.newLine();
			bufferedWriter.write(accessToken.getExpiresIn().toString() );
			bufferedWriter.newLine();
			bufferedWriter.write(accessToken.getRawResponse());
			bufferedWriter.newLine();
			bufferedWriter.close();
		}
		catch(FileNotFoundException ex) {
			System.out.println(
					"Unable to open file\n"+ex.getMessage());               
		}
		catch(IOException ex) {
			System.out.println(
					"Error reading/write file\n"+ex.getMessage());                 
		}
		finally{
			try{
				if (bufferedWriter!=null)
					bufferedWriter.close(); 
			}
			catch(Exception e){
				System.out.println(
						"Error closing file\n"+e.getMessage()); 
			}
		}//end try
		return jo;
	}//end getSpecificData()

	
	*//**
	 * 
	 * @param date		String formatted as yyyy-mm-dd
	 * @return
	 * @throws JSONException 
	 *//*
	public static JSONObject getHeartRateZonesOrig(String date) throws JSONException
	{
		//read credentials from a file
		BufferedReader bufferedReader=null;
		// This will reference one line at a time
		String line = null;

		//Need to save service credentials for Fitbit
		String apiKey = null;      
		String apiSecret = null;
		String clientID = null;

		//holder for all the elements we will need to make an access token ( information about an authenticated session )
		String accessTokenItself =  null;
		String tokenType = null;
		String refreshToken = null;
		Long expiresIn = null;
		String rawResponse = null;

		//This is the only scope you have access to currently
		String scope = "activity%20heartrate";
		try {
			// File with service credentials.
			FileReader fileReader = new FileReader("Team11Credentials.txt");       
			bufferedReader = new BufferedReader(fileReader);
			clientID= bufferedReader.readLine();
			apiKey= bufferedReader.readLine();
			apiSecret = bufferedReader.readLine();
			bufferedReader.close();

			String workingDir = System.getProperty("user.dir");
			System.out.println("Current working directory : " + workingDir);
			fileReader = new FileReader("./src/main/resources/Team11Tokens.txt");

			bufferedReader = new BufferedReader(fileReader);

			accessTokenItself = bufferedReader.readLine();
			tokenType = bufferedReader.readLine();
			refreshToken = bufferedReader.readLine();
			expiresIn = Long.parseLong(bufferedReader.readLine());
			rawResponse = bufferedReader.readLine();

		}
		catch(FileNotFoundException ex) {
			System.out.println(
					"Unable to open file\n"+ex.getMessage());
			System.exit(1);
		}
		catch(IOException ex) {
			System.out.println(
					"Error reading/write file\n"+ex.getMessage());  
			System.exit(1);
		}
		finally{
			try{
				if (bufferedReader!=null)
					// Always close files.
					bufferedReader.close(); 
			}
			catch(Exception e){
				System.out.println(
						"Error closing file\n"+e.getMessage()); 
			}
		}
		//  Create the Fitbit service - you will ask this to ask for access/refresh pairs
		//     and to add authorization information to the requests to the API
		FitbitOAuth20ServiceImpl service = (FitbitOAuth20ServiceImpl) new ServiceBuilder()
		.apiKey(clientID)       //fitbit uses the clientID here
		.apiSecret(apiSecret)
		.callback("http://localhost:8080")
		.scope(scope)
		.grantType("authorization_code")
		.build(FitbitApi20.instance());

		//  The access token contains everything you will need to authenticate your requests
		//  It can expire - at which point you will use the refresh token to refresh it
		//  See: https://dev.fitbit.com/docs/oauth2/#refreshing-tokens
		//    I have authenticated and given you the contents of the response to use
		OAuth2AccessToken accessToken = new OAuth2AccessToken(
				accessTokenItself,
				tokenType,
				refreshToken,
				expiresIn,
				rawResponse);
		// Now let's go and ask for a protected resource!
		System.out.println("Now we're going to access a protected resource...");
		System.out.println();
		//Example request:
		//    This is always the prefix (for my account)
		String requestUrlPrefix = "https://api.fitbit.com/1/user/3WGW2P/";
		String requestUrl;
		//    The URL from this point is how you ask for different information
		requestUrl = requestUrlPrefix + "activities/heart/date/" + date +"/1d/1min.json";
		// This actually generates an HTTP request from the URL
		//    -it has a header, body etc.
		OAuthRequest request = new OAuthRequest(Verb.GET, requestUrl, service);

		// This adds the information required by Fitbit to add the authorization information to the HTTP request
		// You must do this before the request will work
		// See: https://dev.fitbit.com/docs/oauth2/#making-requests
		service.signRequest(accessToken, request);
		//  If you are curious
		System.out.println(request.toString());
		System.out.println(request.getHeaders());
		System.out.println(request.getBodyContents());


		//  This actually sends the request:
		Response response = request.send();

		//  The HTTP response from fitbit will be in HTTP format, meaning that it has a numeric code indicating
		//     whether is was successful (200) or not (400's or 500's), each code has a different meaning
		System.out.println();
		System.out.println("HTTP response code: "+response.getCode());
		int statusCode = response.getCode();

		JSONObject jo = null;
		switch(statusCode){
		case 200:
			System.out.println("Success!");
			System.out.println("HTTP response body:\n"+response.getBody());

			jo = new JSONObject(response.getBody());

			break;
		case 400:
			System.out.println("Bad Request - may have to talk to Beth");
			System.out.println("HTTP response body:\n"+response.getBody());
			break;
		case 401:
			System.out.println("Likely Expired Token");
			System.out.println("HTTP response body:\n"+response.getBody()); 
			System.out.println("Try to refresh");

			// This uses the refresh token to get a completely new accessToken object
			//   See:  https://dev.fitbit.com/docs/oauth2/#refreshing-tokens           
			// This accessToken is now the current one, and the old ones will not work
			//   again.  You should save the contents of accessToken.
			accessToken = service.refreshOAuth2AccessToken(accessToken);

			// Now we can try to access the service again
			// Make sure you create a new OAuthRequest object each time!
			request = new OAuthRequest(Verb.GET, requestUrl, service);
			service.signRequest(accessToken, request);
			response = request.send();

			// Hopefully got a response this time:
			System.out.println("HTTP response code: "+response.getCode());
			System.out.println("HTTP response body:\n"+response.getBody());
			break;
		case 429:
			System.out.println("Rate limit exceedgited");
			System.out.println("HTTP response body:\n"+response.getBody());
			break;
		default:
			System.out.println("HTTP response code: "+response.getCode());
			System.out.println("HTTP response body:\n"+response.getBody());
		}

		BufferedWriter bufferedWriter=null;
		//  Save the current accessToken information for next time

		// IF YOU DO NOT SAVE THE CURRENTLY ACTIVE TOKEN INFO YOU WILL NOT BE ABLE TO REFRESH
		//   - contact Beth if this happens and she can reissue you a fresh set

		try {
			FileWriter fileWriter; 
			fileWriter =
					new FileWriter("./src/main/resources/Team11Tokens.txt");
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(accessToken.getToken());
			bufferedWriter.newLine();
			bufferedWriter.write(accessToken.getTokenType());
			bufferedWriter.newLine();
			bufferedWriter.write(accessToken.getRefreshToken());
			bufferedWriter.newLine();
			bufferedWriter.write(accessToken.getExpiresIn().toString() );
			bufferedWriter.newLine();
			bufferedWriter.write(accessToken.getRawResponse());
			bufferedWriter.newLine();
			bufferedWriter.close();
		}
		catch(FileNotFoundException ex) {
			System.out.println(
					"Unable to open file\n"+ex.getMessage());               
		}
		catch(IOException ex) {
			System.out.println(
					"Error reading/write file\n"+ex.getMessage());                 
		}
		finally{
			try{
				if (bufferedWriter!=null)
					bufferedWriter.close(); 
			}
			catch(Exception e){
				System.out.println(
						"Error closing file\n"+e.getMessage()); 
			}
		}//end try
		return jo;
	}

	public static JSONObject getLifetimeAndBestDaysOrig() throws JSONException
	{
		//read credentials from a file
		BufferedReader bufferedReader=null;
		// This will reference one line at a time
		String line = null;

		//Need to save service credentials for Fitbit
		String apiKey = null;      
		String apiSecret = null;
		String clientID = null;

		//holder for all the elements we will need to make an access token ( information about an authenticated session )
		String accessTokenItself =  null;
		String tokenType = null;
		String refreshToken = null;
		Long expiresIn = null;
		String rawResponse = null;

		//This is the only scope you have access to currently
		String scope = "activity%20heartrate";
		try {
			// File with service credentials.
			FileReader fileReader =
					new FileReader("Team11Credentials.txt");       
			bufferedReader = new BufferedReader(fileReader);
			clientID= bufferedReader.readLine();
			apiKey= bufferedReader.readLine();
			apiSecret = bufferedReader.readLine();
			bufferedReader.close();

			String workingDir = System.getProperty("user.dir");
			System.out.println("Current working directory : " + workingDir);
			fileReader = new FileReader("./src/main/resources/Team11Tokens.txt");

			bufferedReader = new BufferedReader(fileReader);

			accessTokenItself = bufferedReader.readLine();
			tokenType = bufferedReader.readLine();
			refreshToken = bufferedReader.readLine();
			expiresIn = Long.parseLong(bufferedReader.readLine());
			rawResponse = bufferedReader.readLine();

		}
		catch(FileNotFoundException ex) {
			System.out.println(
					"Unable to open file\n"+ex.getMessage());
			System.exit(1);
		}
		catch(IOException ex) {
			System.out.println(
					"Error reading/write file\n"+ex.getMessage());  
			System.exit(1);
		}
		finally{
			try{
				if (bufferedReader!=null)
					// Always close files.
					bufferedReader.close(); 
			}
			catch(Exception e){
				System.out.println(
						"Error closing file\n"+e.getMessage()); 
			}
		}
		//  Create the Fitbit service - you will ask this to ask for access/refresh pairs
		//     and to add authorization information to the requests to the API
		FitbitOAuth20ServiceImpl service = (FitbitOAuth20ServiceImpl) new ServiceBuilder()
		.apiKey(clientID)       //fitbit uses the clientID here
		.apiSecret(apiSecret)
		.callback("http://localhost:8080")
		.scope(scope)
		.grantType("authorization_code")
		.build(FitbitApi20.instance());

		//  The access token contains everything you will need to authenticate your requests
		//  It can expire - at which point you will use the refresh token to refresh it
		//  See: https://dev.fitbit.com/docs/oauth2/#refreshing-tokens
		//    I have authenticated and given you the contents of the response to use
		OAuth2AccessToken accessToken = new OAuth2AccessToken(
				accessTokenItself,
				tokenType,
				refreshToken,
				expiresIn,
				rawResponse);
		// Now let's go and ask for a protected resource!
		System.out.println("Now we're going to access a protected resource...");
		System.out.println();
		//Example request:
		//    This is always the prefix (for my account)
		String requestUrlPrefix = "https://api.fitbit.com/1/user/3WGW2P/";
		String requestUrl;
		//    The URL from this point is how you ask for different information
		requestUrl = requestUrlPrefix + "activities.json";
		// This actually generates an HTTP request from the URL
		//    -it has a header, body ect.
		OAuthRequest request = new OAuthRequest(Verb.GET, requestUrl, service);

		// This adds the information required by Fitbit to add the authorization information to the HTTP request
		// You must do this before the request will work
		// See: https://dev.fitbit.com/docs/oauth2/#making-requests
		service.signRequest(accessToken, request);
		//  If you are curious
		System.out.println(request.toString());
		System.out.println(request.getHeaders());
		System.out.println(request.getBodyContents());


		//  This actually sends the request:
		Response response = request.send();

		//  The HTTP response from fitbit will be in HTTP format, meaning that it has a numeric code indicating
		//     whether is was successful (200) or not (400's or 500's), each code has a different meaning
		System.out.println();
		System.out.println("HTTP response code: "+response.getCode());
		int statusCode = response.getCode();

		JSONObject jo = null;
		switch(statusCode){
		case 200:
			System.out.println("Success!");
			System.out.println("HTTP response body:\n"+response.getBody());

			jo = new JSONObject(response.getBody());

			break;
		case 400:
			System.out.println("Bad Request - may have to talk to Beth");
			System.out.println("HTTP response body:\n"+response.getBody());
			break;
		case 401:
			System.out.println("Likely Expired Token");
			System.out.println("HTTP response body:\n"+response.getBody()); 
			System.out.println("Try to refresh");

			// This uses the refresh token to get a completely new accessToken object
			//   See:  https://dev.fitbit.com/docs/oauth2/#refreshing-tokens           
			// This accessToken is now the current one, and the old ones will not work
			//   again.  You should save the contents of accessToken.
			accessToken = service.refreshOAuth2AccessToken(accessToken);

			// Now we can try to access the service again
			// Make sure you create a new OAuthRequest object each time!
			request = new OAuthRequest(Verb.GET, requestUrl, service);
			service.signRequest(accessToken, request);
			response = request.send();

			// Hopefully got a response this time:
			System.out.println("HTTP response code: "+response.getCode());
			System.out.println("HTTP response body:\n"+response.getBody());
			break;
		case 429:
			System.out.println("Rate limit exceedgited");
			System.out.println("HTTP response body:\n"+response.getBody());
			break;
		default:
			System.out.println("HTTP response code: "+response.getCode());
			System.out.println("HTTP response body:\n"+response.getBody());
		}

		BufferedWriter bufferedWriter=null;
		//  Save the current accessToken information for next time

		// IF YOU DO NOT SAVE THE CURRENTLY ACTIVE TOKEN INFO YOU WILL NOT BE ABLE TO REFRESH
		//   - contact Beth if this happens and she can reissue you a fresh set

		try {
			FileWriter fileWriter; 
			fileWriter =
					new FileWriter("./src/main/resources/Team11Tokens.txt");
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(accessToken.getToken());
			bufferedWriter.newLine();
			bufferedWriter.write(accessToken.getTokenType());
			bufferedWriter.newLine();
			bufferedWriter.write(accessToken.getRefreshToken());
			bufferedWriter.newLine();
			bufferedWriter.write(accessToken.getExpiresIn().toString() );
			bufferedWriter.newLine();
			bufferedWriter.write(accessToken.getRawResponse());
			bufferedWriter.newLine();
			bufferedWriter.close();
		}
		catch(FileNotFoundException ex) {
			System.out.println(
					"Unable to open file\n"+ex.getMessage());               
		}
		catch(IOException ex) {
			System.out.println(
					"Error reading/write file\n"+ex.getMessage());                 
		}
		finally{
			try{
				if (bufferedWriter!=null)
					bufferedWriter.close(); 
			}
			catch(Exception e){
				System.out.println(
						"Error closing file\n"+e.getMessage()); 
			}
		}//end try
		return jo;
	}//end of method
*/
}//end class