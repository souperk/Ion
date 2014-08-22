package gr.souperk.ion.conf;

import gr.souperk.ion.server.proxy.Host;
import gr.souperk.ion.server.proxy.Rule;

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
			
			for(Object url : host.getList("urls.url"))
			{
				h.addRule(new Rule((String) url));
			}
			
			List<HierarchicalConfiguration> heads = host.configurationsAt("headers.header");
			
			for(HierarchicalConfiguration header : heads)
			{
				h.addHeader(header.getString("[@id]"), header.getString(""));
			}
			
			this.hosts.add(h);
		}
	}
	
	public boolean exists(String url)
	{
		if(url == null)
			return false;
		
		url = url.trim();
		
		for(Host host : hosts)
		{
			if(host.isValid(url))
				return true;
		}
		
		return false;
	}
	
	public Host getHost(String url)
	{
		for(Host host : hosts)
		{
			if(host.isValid(url))
				return host;
		}
		
		return null;
	}
	
}
