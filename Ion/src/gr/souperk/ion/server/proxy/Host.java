package gr.souperk.ion.server.proxy;

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
	/** The addres of the host.*/
	private String address;
	
	/** The port of the host.*/
	private int port;
	
	/** Rules for the server to respond to a HttpRequest.*/
	private List<Rule> rules;
	              
	/**
	 * 
	 * @param address address of the host.
	 * @param port port of the host.
	 */
	public Host(String address, int port) 
	{
		this.address = address;
		this.port = port;

		this.rules = new ArrayList<Rule>();
	}

	/**
	 * Creates a rule and adds it to the rule list.
	 * 
	 * @param header the name of the header.
	 * @param expectedValue the expected value of the rule.
	 */
	public void addRule(String header, String expectedValue)
	{
		Rule r = new Rule(header, expectedValue);
		
		addRule(r);
	}
	
	/**
	 * Stores the rule to the rule list. If a rule for the same HttpHeader it exists without doing anything.
	 * 
	 * @param r rule to be stored.
	 */
	public void addRule(Rule rule)
	{
		for(Rule r : rules)
		{
			if(r.getHeader().equals(rule.getHeader()))
			{
				return;
				//TODO decide whether to throw exception or not.
			}
		}
		
		rules.add(rule);
	}

	/**
	 * In current approach the Host will held only one rule per HttpHeader.
	 * 
	 * @param request
	 * @return whether the Host will respond to HttpRequest.
	 */
	public boolean isValid(HttpRequest request)
	{
		for(Rule r : rules)
		{
			if(!r.isValid(request))
			{
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * 
	 * @return the address to connect to Host.
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
	
	@Override
	public String toString() {
		return "[" + address + ":" + port + "]";
	}
	
	
}
