package gr.souperk.ion.properties;

import java.util.Map;

/**
 * {@code ServerProperties} holds the main properties of the server
 * like the port it runs on. 
 *
 * <br></br>
 * It is designed with the singleton pattern which enables it to create only one
 * instance avoiding any unnecessary reading.
 * 
 * <br></br>
 * 
 * @author Kostas "souperk" Alexopoulos (kostas@alcinia.net)
 *
 */
//TODO Finish the javadoc
//TODO Change it to apache configuration
public class ServerProperties 
{
	private static ServerProperties instace;
	
	private Map<String, String> props;
	
	private ServerProperties()
	{
		props = PropertiesTool.defaults();
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
		return props.get(key);
	}
	
	public int getInt(String key)
	{
		return Integer.parseInt(getProperty(key));
	}
}