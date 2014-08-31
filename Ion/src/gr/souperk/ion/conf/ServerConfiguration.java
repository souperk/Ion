package gr.souperk.ion.conf;

import java.io.File;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * {@code ServerProperties} holds the main properties of the server
 * like the port it runs on. 
 *
 * <br></br>
 * It is designed with the singleton pattern enabling to load the properties
 * only once in a runtime. With the first call of {@code ServerProperties.getInstance()} 
 * it creates a static instance of class that is never closed till System exit.
 * 
 * <br></br>
 * Use {@code ServerProperties.getInstance().getProperty(String)} to get the property for the key.
 * 
 * @author Kostas "souperk" Alexopoulos
 *
 */
public class ServerConfiguration 
	extends CompositeConfiguration
{	
	
	/** Logger ServerProperties*/
	private static Logger log = LogManager.getLogger(ServerConfiguration.class);
	
	/** The active instance of {@code ServerProperties}*/
	private static ServerConfiguration instace;
	
//	/** Holds all loaded configuration.*/
//	private CompositeConfiguration conf;
	
	/**
	 * Constructor of {@code ServerProperties}. Creates a {@code CompositeConfiguration} 
	 * and then adds default and file properties
	 * <br></br>
	 * Note : {@code CompositeConfiguration.getProperty(String ,boolean)} returns
	 * the first matched property in the added {@code Configuration}s while the matching order 
	 * is the same of addition order. So add Configurations with priority order.
	 * 
	 */
	private ServerConfiguration()
	{
		super(PropertiesTool.defaults());

		try 
		{
			addConfiguration(loadProperties());
		} catch (ConfigurationException e) 
		{
			//TODO Fix this.
			log.warn("Unable to open configuration file (resources/ion.conf)");
		}
	}	
	
	/**
	 * @return A {@code PropertiesConfiguration} loaded from {@code PropertiesTool.DEFAULT_CONF_FILE}.
	 * @throws ConfigurationException If error while loading properties file.
	 */
	private PropertiesConfiguration loadProperties() 
			throws ConfigurationException
	{
		return new PropertiesConfiguration(new File(PropertiesTool.DEFAUL_CONF_FILE));
	}
	
	/**
	 * @return An active instance of {@code ServerProperties}. If one does not exist it creates an new one first.
	 */
	public static ServerConfiguration getInstance()
	{
		if(instace == null)
			instace = new ServerConfiguration();
		return instace;
	}
	

}