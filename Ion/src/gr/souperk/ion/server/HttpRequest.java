package gr.souperk.ion.server;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Kostas "souperk" Alexopoulos
 *
 */
public class HttpRequest 
{
	private String command;
	private Map<String ,String> heads;

	
	public HttpRequest(String command) 
	{
		this.command = command;
		heads = new HashMap<String, String>();
	}
	
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
}
