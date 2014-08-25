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
 * @since 1.3
 * @author Kostas "souperk" Alexopoulos
 *
 */

//TODO write javadoc
public class ProxyConfiguration 
{
	
	/** Logger ProxyConfiguration*/
	private static Logger log = LogManager.getLogger(ProxyConfiguration.class);
	
	private static ProxyConfiguration instance;
	
	private XMLConfiguration conf;
	private List<Host> hosts;
	
	private ProxyConfiguration() 
	{
		hosts = new ArrayList<Host>();

		try {
			conf = new XMLConfiguration("conf/proxy.xml");

			init();
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
	
	private void init()
	{
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
	}
	
	public boolean exists(HttpRequest request)
	{
		for(Host host : hosts)
		{
			if(host.isValid(request))
				return true;
			log.info(host.toString() + " is invalid to " + request.get("Host"));
		}
		
		return false;
	}
	
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
