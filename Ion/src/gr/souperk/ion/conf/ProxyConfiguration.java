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
	private ProxyConfiguration() 
	{
		hosts = new ArrayList<Host>();

		try {
			
			XMLConfiguration conf = new XMLConfiguration("conf/proxy.xml");

			//TODO decide if i should move following code to another method.
			//<--
			List<HierarchicalConfiguration> hosts = conf.configurationsAt("host");
			
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
			//-->
			
		} catch (ConfigurationException e) 
		{
			log.error("Unable to open proxy configuration file (conf/proxy.xml).");
		}
	}
	
	/**
	 * @return an active instance of ProxyConfiguration.
	 */
	public static ProxyConfiguration getInstance()
	{
		if(instance == null)
			instance = new ProxyConfiguration();
		
		return instance;
	}

	/**
	 * @param request an http request.
	 * @return Checks if a host exist that would accept the request and returns the host otherwise it null.
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
