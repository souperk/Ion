package gr.souperk.ion.server.http;

import gr.souperk.ion.SouperkUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Kostas "souperk" Alexopoulos
 *
 */
//TODO write javadoc
public class HttpRequest 
{
	private String command;
	private List<HttpHeader> heads;

	
	public HttpRequest(String command) 
	{
		this.command = command;
		heads = new ArrayList<HttpHeader>();
	}
	
	//TODO note adding an already existing header it overrides the previous one
	//should probably throw some exception
	public void addHeader(String line)
	{
		
		if(!line.trim().isEmpty())
		{
			String header = line.substring(0,line.indexOf(':'));
			String value = line.substring(line.indexOf(':')+1);
				
			addHeader(header, value);
		}
	}
	
	public void addHeader(String header, String value)
	{
		heads.put(header, value);
	}
	
	public String getHeader(String header)
	{
		return heads.get(header);
	}
	
	//TODO probably will need to change this
	public void setHeader(String key, String value)
	{
		heads.put(key, value);
	}
	
	public Map<String, String> getHeaders()
	{
		return heads;
	}
	
	public String getCommand()
	{
		return command;
	}
	
	public int headsCount()
	{
		return heads.size();
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append(command + SouperkUtils.NEW_LINE);
		
		Iterator<Map.Entry<String, String>> it = heads.entrySet().iterator();
		
		while(it.hasNext())
		{
			Map.Entry<String, String> e = it.next();
			
			sb.append(e.getKey() + ":" + e.getValue() + SouperkUtils.NEW_LINE);

			it.remove();
		}
		
		return sb.toString();
	}
}
