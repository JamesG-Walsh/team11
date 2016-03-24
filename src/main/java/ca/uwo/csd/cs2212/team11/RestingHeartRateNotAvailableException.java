/**
 * 
 */
package ca.uwo.csd.cs2212.team11;

/**
 * @author James Walsh
 *
 */
public class RestingHeartRateNotAvailableException extends RuntimeException 
{
	
	public RestingHeartRateNotAvailableException()
	{
		super ("No resting heart rate data available");//TODO edit msg?
	}
	
}