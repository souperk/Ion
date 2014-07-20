package gr.souperk.ion.properties;

/**
 * {@code ServerProperties} holds the main properties of the server
 * like the port it runs on. 
 * <br></br>
 * It is designed with the singleton pattern which enables it to create only one
 * instance avoiding unnecessary reading.
 * 
 * @author kostas
 *
 */
//TODO Finish the javadoc
//TODO Provide with some sort of default properties
public class ServerProperties 
{
	private static ServerProperties instace;
	
	private ServerProperties()
	{
		
	}	
	
	/**
	 * 
	 * @return the instace of {@code ServerProperties} if it exists else it creates a new one.
	 */
	public static ServerProperties getInstance()
	{
		if(instace == null)
			instace = new ServerProperties();
		return instace;
	}
	
	public String getProperty(String key)
	{
		return null;
	}
	
	public int getInt(String key)
	{
		return Integer.parseInt(getProperty(key));
	}
}
