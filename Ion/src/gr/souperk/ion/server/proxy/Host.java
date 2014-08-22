package gr.souperk.ion.server.proxy;

import gr.souperk.ion.server.http.HttpHeaderBean;
import gr.souperk.ion.server.http.HttpHeaderBeanList;
import gr.souperk.ion.server.http.HttpRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a http server. Contains its port and address. 
 * Also decides whether to proxy a request to the server.
 * 
 * 
 * @since 1.2
 * @author Kostas "souperk" Alexopoulos
 *
 */
public class Host 
{
	private String address;
	private int port;
	
	private List<Rule> rules;
	                                
	public Host(String address, int port) 
	{
		this.address = address;
		this.port = port;

		this.rules = new ArrayList<Rule>();
	}

	public void addRule(Rule r)
	{
		rules.add(r);
	}
	
	//TODO write the code and javadoc.
	public boolean isValid(HttpRequest request)
	{
		url = url.trim();
		
		if(url.contains(":"))
			url = url.substring(0, url.indexOf(":"));
		
		for(Rule r : rules)
		{
			if(r.meetsRule(url))
				return true;
		}
		
		return false;
	}
	
	/**
	 * 
	 * @return the addres to connect to Host.
	 */
	public String getAddress()
	{
		return address;
	}
	
	/**
	 * 
	 * @return the port to connect to Host.
	 */
	public int getPort()
	{
		return port;
	}
	
	
}
