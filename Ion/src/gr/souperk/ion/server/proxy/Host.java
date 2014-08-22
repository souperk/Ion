package gr.souperk.ion.server.proxy;

import gr.souperk.ion.server.http.HttpRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Host 
{
	private String address;
	private int port;
	
	private List<Rule> rules;
	private Map<String, String> heads;
	                                
	public Host(String address, int port) 
	{
		this.address = address;
		this.port = port;
		this.rules = new ArrayList<Rule>();
		this.heads = new HashMap<String, String>();
	}
	
	public String alertHeader(String header)
	{
		
		return null;
	}
	
	public void addRule(Rule r)
	{
		rules.add(r);
	}
	
	public void addHeader(String key, String value)
	{
		heads.put(key, value);
	}
	
	public void changeRequest(HttpRequest request)
	{
		Iterator<Entry<String, String>> i = heads.entrySet().iterator();
		
		while(i.hasNext())
		{
			Entry<String, String> e = i.next();
			request.addHeader(e.getKey(), e.getValue());
			i.remove();
		}
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
