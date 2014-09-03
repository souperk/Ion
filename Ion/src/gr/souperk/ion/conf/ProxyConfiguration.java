package gr.souperk.ion.conf;

import gr.souperk.ion.server.http.HttpRequest;
import gr.souperk.ion.server.proxy.Host;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * 
 * @since 1.3
 * @author Kostas "souperk" Alexopoulos
 *
 */
//TODO write javadoc
public class ProxyConfiguration 
{
	
	/** Logger ProxyConfiguration*/
	private static Logger log = LogManager.getLogger(ProxyConfiguration.class);
	
	/** An instance of ProxyConfiguration passed to other classes by getInstace().*/
	private static ProxyConfiguration instance;
	
	/** A list o http proxies.*/
	private List<Host> hosts;
	
	/**
	 * Constructor of ProxyConfiguration.
	 */
	private ProxyConfiguration(ServerConfiguration conf) 
	{
		hosts = new ArrayList<Host>();

		load(conf.getString(PropertiesTool.PROXY_CONF_FILE));
	}
	
	/**
	 * @return an active instance of {@code ProxyConfiguration}.
	 */
	public static ProxyConfiguration getInstance(ServerConfiguration conf)
	{
		if(instance == null)
			instance = new ProxyConfiguration(conf);
		
		return instance;
	}
	
	/**
	 * Loads {@code Host}s from a specified file.
	 * @param filename proxy configuration file.
	 */
	private void load(String filename) 
	{
		XMLConfiguration xmlConf;
		
		try {
			xmlConf = new XMLConfiguration(filename);
		} catch (ConfigurationException e) 
		{
			log.error("Unable to open proxy configuration file (" + filename + ").");
			return ;
		}

		List<HierarchicalConfiguration> hosts = xmlConf.configurationsAt("host");
		
		for(HierarchicalConfiguration host : hosts)
		{
			Host h = new Host(host.getString("target"), host.getInt("target[@port]"));

			List<HierarchicalConfiguration> heads = host.configurationsAt("headers.header");
			
			for(HierarchicalConfiguration header : heads)
			{
				h.addRule(header.getString("[@name]"), header.getString(""));
			}
			
			this.hosts.add(h);
		}

	}

	/**
	 * @param request an http request.
	 * @return Checks if a {@code Host} exists that would accept the request and returns that {@code Host} otherwise null.
	 */
	public Host getHost(HttpRequest request)
	{
		for(Host host : hosts)
		{
			if(host.isValid(request))
				return host;
		}
		
		return null;
	}
	
}
