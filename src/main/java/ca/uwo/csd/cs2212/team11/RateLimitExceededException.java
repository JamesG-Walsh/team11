/**
 * Class that represents the exception that occurs when the fitbit server denies a request because too many requests have been made within an hour.
 */
package ca.uwo.csd.cs2212.team11;

/**
 * @author James Walsh
 *
 */
public class RateLimitExceededException extends RuntimeException 
{
	/**
	 * 
	 */
	public RateLimitExceededException() 
	{
		super ("You are doing that too much.  Try again at the top of the hour.");//TODO edit message?
	}

}