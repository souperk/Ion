package gr.souperk.ion.server.http;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @since 1.4
 * @author Kostas "souperk" Alexopoulos
 */
public class HttpResponse 
{
	
	private String code;
	private Map<String, String> heads;
	private String message;
	
	public HttpResponse()
	{
		heads = new HashMap<String, String>();		
	}
	
	public void add(String line)
	{
		if(code == null || code.isEmpty())
			code = line;
		else if(message == null || !message.isEmpty())
			message = message + line + "\n";
		else if (line == "\n" || line.indexOf(":") < 0 )
			message = line;
		else 
		{
			String name = line.substring(0, line.indexOf(":")-1);
			String value = line.substring(line.indexOf(":"));
			
			setHeader(name, value);
		}
	}
	
	public String getCode()
	{
		return message;
	}
	
	public void setCode(String code)
	{
		this.code = code;
	}

	public String getMessage()
	{
		return message;
	}
	
	public void setMessage(String message)
	{
		this.message = message;
	}
	
	public String getHeader(String name)
	{
		return heads.get(name);
	}
	
	
	public void setHeader(String name,String value)
	{
		heads.put(name, value);
	}
	
	@Override
	public String toString() 
	{
		StringBuilder sb = new StringBuilder();
		Iterator<String> it = heads.keySet().iterator();

		sb.append(code + "\n");
		
		while(it.hasNext())
		{
			String key = it.next();
			
			sb.append(key + ":" + heads.get(key) + "\n");
			
			it.remove();
		}
		
		sb.append("\n");
		sb.append(message);
		
		return sb.toString();
	}
}
