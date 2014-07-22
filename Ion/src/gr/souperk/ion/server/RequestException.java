package gr.souperk.ion.server;

/**
 * Exception for managing exceptions for the HTTP request.
 * 
 * @author Kostas "souperk" Alexopoulos (kostas@alcinia.net)
 *
 */
public class RequestException 
	extends Exception
{
	/** Random generated serialUID*/
	private static final long serialVersionUID = -3404097321986300199L;

	private int code;
	
	/**
	 * 
	 * @param code The HTTP exit code.
	 */
	public RequestException(int code) 
	{
		super();
		
		this.code = code;
	}
	
	/**
	 * 
	 * @return The code the exception was thrown for.
	 */
	public int getCode()
	{
		return code;
	}
}
