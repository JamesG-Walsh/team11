package ca.uwo.csd.cs2212.team11;

/**
 * This class contains this user's login ID and password.
 * 
 * @author Team 11
 */
public class UserLoginInfo {

private String userID;
private String userPassWord;	
	
/**
 *  Class constructor.
 */
public UserLoginInfo() {
}	

/**
 * Sets this user's ID and password.
 * 
 * @param userID 
 * @param userPassword
 */

public void setUserLoginInfo( String userID, String userPassWord ) {
	this.userID = userID;
	this.userPassWord = userPassWord;	
}

/**
 * Gets this user's ID and password.
 * 
 * @return     userID and userPassWord, separated by a space 
 */

public String getUserLoginInfo() {
	String s = userID;
	s = s.concat(" ");
	s = s.concat(userPassWord);
	return s;
}



	
	
	
	
	
	
}
