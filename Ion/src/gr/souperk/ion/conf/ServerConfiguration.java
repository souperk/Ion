package gr.souperk.ion.conf;

import java.io.File;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * {@code ServerConfiguration} is the container of the general server configurations
 * like in which port it should run.
 *
 * <br></br>
 * {@code ServerConfiguration} is designed with the singleton pattern enabling the server to open the configuration 
 * only once in a runtime saving a lot of time. With the first call of {@code ServerConfiguration.getInstance()} 
 * it creates a static instance of the class that isn't destroyed until System exit.
 * 
 * <br></br>
 * {@code ServerConfiguration} inherits the {@code CompositeConfiguration} of apache commons 
 * configuration enabling it to use all of its cool methods.
 * 
 * <br></br>
 * Since 1.4 {@code ServerConfiguration} can be loaded from SouperkUtils.getBean(Class<T> bean) more resistant to 
 * future changes in turns of backwards compatibility.
 * 
 * @since 1.0 as {@code ServerProperties} in 1.4 renamed to {@code ServerConfiguration}
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
		
	/**
	 * Constructor of {@code ServerConfiguration}. Creates a {@code CompositeConfiguration} 
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
	 * @return An active instance of {@code ServerProperties}. If one does not exist it creates an new one first.
	 */
	public static ServerConfiguration getInstance()
	{
		if(instace == null)
			instace = new ServerConfiguration();
		return instace;
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

}