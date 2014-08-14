package gr.souperk.ion.conf;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProxyConfiguration 
{
	
	/** Logger ProxyConfiguration*/
	private static Logger log = LogManager.getLogger(ProxyConfiguration.class);
	
	private static ProxyConfiguration instance;
	
	private XMLConfiguration conf;
	
	private ProxyConfiguration() 
	{
		try {
			conf = new XMLConfiguration("conf/proxy.xml");
		} catch (ConfigurationException e) 
		{
			log.error("Unable to open proxy configuration file (conf/proxy.xml).");
		}
	}
	
	public static ProxyConfiguration getInstance()
	{
		if(instance == null)
			instance = new ProxyConfiguration();
		return instance;
	}
	
	public 
}
