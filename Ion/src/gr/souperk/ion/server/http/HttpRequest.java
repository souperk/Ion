package gr.souperk.ion.server.http;

import java.util.ArrayList;

/**
 * 
 * @author Kostas "souperk" Alexopoulos
 *
 */
//TODO write javadoc
public class HttpRequest 
	extends HttpHeaderBeanList
{
	//TODO Decide whether to make getHeaders
	private String command;
	
	public HttpRequest() 
	{
		heads = new ArrayList<HttpHeaderBean>();
	}
	
	public String getCommand()
	{
		return this.command;
	}
	
	//TODO decide whether it will parse many lines or only one lines,
	public void add(String line)
	{
		line = line.trim();
		
		if(line.isEmpty())
			return; //TODO decide whether to throw an exception or not. (Probably not)
		
		if(line.indexOf(":") == -1 && command == null)
		{
			command = line;
		}else if (line.indexOf(":") == -1 )
		{
			//TODO throw exception or something.
		}
		
		String name = line.substring(0, line.indexOf(":"));
		String value = line.substring(line.indexOf(":")+1); 
			
		add(name, value);
		
	}
	
	//TODO decide whether this will be public or private.
	public void add(String name, String value)
	{
		for(HttpHeaderBean bean : heads)
		{
			if(bean.getName().equals(name))
			{
				bean.setValue(value);
				return;
			}
		}
		
		heads.add(new HttpHeaderBean(name, value));
	}
	
	@Override
	public String toString() 
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append(this.command + "\n");
		
		for(HttpHeaderBean bean : heads)
			sb.append(bean.toString() + "\n");
		
		return sb.toString();
	}
	
	/**
	 * <ul>
	 * 		<li>Checks whether commands is null or empty. If so returns false.</li>
	 * </ul>
	 * 
	 * @return whether HttpRequest is empty.
	 */
	public boolean isEmpty()
	{
		if(command == null || command.isEmpty())
			return true;
		
		return false;
	}
}
