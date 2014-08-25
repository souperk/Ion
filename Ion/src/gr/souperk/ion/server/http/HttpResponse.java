package gr.souperk.ion.server.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HttpResponse 
{
	
	private Map<String, String> heads;
	private String message;
	
	public HttpResponse(BufferedReader in)
			throws IOException 
	{
		heads = new HashMap<String, String>();
		
		String line;
		
		while((line = in.readLine()) != null && !line.isEmpty())
		{
			add(line);
		}
	}
	
	public String getMessage()
	{
		return message;
	}
	
	public String get(String name)
	{
		return heads.get(name);
	}
	
	public void add(String line)
	{
		if(line.trim().isEmpty())
			return; //TODO decide whether to throw an exception or not. (Probably not)
		
		if(line.indexOf(":") == -1)
		{
			
			message += line + "\n";
			return;
		}

		line = line.trim();
		
		String name = line.substring(0, line.indexOf(":"));
		String value = line.substring(line.indexOf(":")+1); 
			
		add(name, value);
		
	}
	
	public void add(String name,String value)
	{
		heads.put(name, value);
	}
	
	@Override
	public String toString() 
	{
		StringBuilder sb = new StringBuilder();
		Iterator<String> it = heads.keySet().iterator();
		
		while(it.hasNext())
		{
			String key = it.next();
			
			sb.append(key + ":" + heads.get(key) + "\n");
			
			it.remove();
		}
		
		sb.append(message);
		
		return sb.toString();
	}
}
