package gr.souperk.ion.server.proxy;

import java.util.ArrayList;
import java.util.List;

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
	
	public boolean isValid(String url)
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
	
	public String getAddress()
	{
		return address;
	}
	
	public int getPort()
	{
		return port;
	}
	
	
}
