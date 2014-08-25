package gr.souperk.ion.server.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 
 * @author Kostas "souperk" Alexopoulos
 *
 */
//TODO write javadoc
public class HttpRequest 
{
	//TODO Decide whether to make getHeaders
	private String command;
	private Map<String, String> heads;
	
	public HttpRequest(BufferedReader in) 
			throws IOException
	{
		heads = new HashMap<String, String>();
		
		String line;
		
		while((line = in.readLine()) != null && !line.isEmpty())
		{
			add(line);
		}
	}
	
	public String getCommand()
	{
		return this.command;
	}
	
	public String get(String key)
	{
		return heads.get(key);
	}
	
	public void add(String line)
	{
		
		if(line.trim().isEmpty())
			return; //TODO decide whether to throw an exception or not. (Probably not)
		
		if(line.indexOf(":") == -1 && command == null)
		{
			command = line;
			return;
		}

		line = line.trim();
		
		String name = line.substring(0, line.indexOf(":"));
		String value = line.substring(line.indexOf(":")+1); 
			
		add(name, value);
		
	}
	
	public void add(String name, String value)
	{
		heads.put(name, value);
	}
	
	@Override
	public String toString() 
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append(this.command + "\n");
		
		Iterator<String> it = heads.keySet().iterator();
		while(it.hasNext())
		{
			String key = it.next();
			
			sb.append(key + ":" + heads.get(key) + "\n");
			
			it.remove();
		}
		
		return sb.toString();
	}
	
	/**
	 * <ul>
	 * 		<li>Checks whether commands is null or empty. If so returns false.</li>
	 * 		<li>Checks whether header map is null or empty. If so returns false.</li>
	 * </ul>
	 * 
	 * @return whether HttpRequest is empty.
	 */
	//TODO finish code and javadoc
	public boolean isEmpty()
	{
		if(command == null || command.isEmpty())
			return true;
		
		if(heads == null || heads.isEmpty())
			return true;
		
		return false;
	}
}
