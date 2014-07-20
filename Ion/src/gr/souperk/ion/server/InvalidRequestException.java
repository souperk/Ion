package gr.souperk.ion.server;

public class InvalidRequestException 
	extends Exception
{
	private static final long serialVersionUID = -3404097321986300199L;

	private int code;
	
	public InvalidRequestException(int code) 
	{
		super();
		
		this.code = code;
	}
	
	public int getCode()
	{
		return code;
	}
}
